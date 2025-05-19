package com.zele.crspringboot.repositories;

import com.zele.crspringboot.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
