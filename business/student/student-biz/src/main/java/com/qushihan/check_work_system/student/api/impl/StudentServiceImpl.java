package com.qushihan.check_work_system.student.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qushihan.check_work_system.clazz.api.ClazzService;
import com.qushihan.check_work_system.clazz.dto.ClazzDto;
import com.qushihan.check_work_system.student.api.StudentService;
import com.qushihan.check_work_system.student.dao.StudentDao;
import com.qushihan.check_work_system.student.dto.StudentDto;
import com.qushihan.check_work_system.student.model.auto.Student;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ClazzService clazzService;

    @Override
    public List<StudentDto> getByClazzId(Long clazzId) {
        List<Student> students = studentDao.getByClazzId(clazzId);
        List<StudentDto> studentDtos = students.stream()
                .map(student -> {
                    StudentDto studentDto = new StudentDto();
                    BeanUtils.copyProperties(student, studentDto);
                    ClazzDto clazzDto = clazzService.getByClazzId(student.getClazzId());
                    studentDto.setClazzName(clazzDto.getClazzName());
                    return studentDto;
                }).collect(Collectors.toList());
        return studentDtos;
    }

    @Override
    public void updateByClazzId(Long clazzId) {
        List<Student> students = studentDao.getByClazzId(clazzId);
        students.forEach(student -> {
            student.setClazzId(null);
            studentDao.updateById(student);
        });
    }

    @Override
    public List<StudentDto> getByStudentIdList(List<Long> studentIds) {
        List<Student> students = studentDao.getByStudentIdList(studentIds);
        return students.stream()
                .map(student ->
                     {
                         StudentDto studentDto = new StudentDto();
                         BeanUtils.copyProperties(student, studentDto);
                         return studentDto;
                     })
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getBySearchStudentName(String searchStudentName) {
        List<Student> students = studentDao.getBySearchStudentName(searchStudentName);
        return students.stream()
                .map(student -> {
                    StudentDto studentDto = new StudentDto();
                    BeanUtils.copyProperties(student, studentDto);
                    return studentDto;
                })
                .collect(Collectors.toList());
    }
}
