package com.example.distributetransaction.dao2;


import com.example.distributetransaction.entity.StudentEntity;
import org.apache.ibatis.annotations.Param;

public interface ClusterDao {
    void createStudent(StudentEntity student);
    StudentEntity getStudent(String name);
    void addBaseCode(@Param("param")String param);
}
