package com.springboot.learn.spring.data.jpa.repository;

import com.springboot.learn.spring.data.jpa.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Teacher jpa repository.
 *
 * @author Aviator
 */
public interface TeacherJpaRepository extends JpaRepository<Teacher,Long> {
    /**
     * 根据名字查询老师
     *
     * @param name 姓名
     * @return 老师列表 list
     */
    List<Teacher> findByName(String name);

    /**
     * Delete by name.
     *
     * @param name the name
     */
    void deleteByName(String name);
}
