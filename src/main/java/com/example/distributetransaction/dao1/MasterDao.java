package com.example.distributetransaction.dao1;


import com.example.distributetransaction.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

public interface MasterDao {
    void createUser(UserEntity user);
    UserEntity getUser(String name);
    void addLdcom(@Param("param")String param);
}
