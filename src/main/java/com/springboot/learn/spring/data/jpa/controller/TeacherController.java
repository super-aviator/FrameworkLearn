package com.springboot.learn.spring.data.jpa.controller;

import com.springboot.learn.spring.data.jpa.entity.Teacher;
import com.springboot.learn.spring.data.jpa.repository.TeacherJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Teacher controller.
 *
 * @author Aviator
 */
@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {
    /**
     * The Teacher repository.
     */
    @Autowired
    TeacherJpaRepository teacherRepository;

    /**
     * Gets teacher.
     *
     * @param teacher the teacher
     * @return the teacher
     */
    @GetMapping(value = "/get")
    public List<Teacher> getTeacher(@RequestParam("name") String teacher) {
        return teacherRepository.findByName(teacher);
    }

    /**
     * Delete teacher.
     *
     * @param teacher the teacher
     */
    @Transactional(rollbackFor = Exception.class)
    @GetMapping(value = "/delete")
    public void deleteTeacher(@RequestParam("name") String teacher) {
        teacherRepository.deleteByName(teacher);
    }
}
