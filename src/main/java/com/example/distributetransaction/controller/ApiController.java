package com.example.distributetransaction.controller;

import com.example.distributetransaction.dao1.MasterDao;
import com.example.distributetransaction.dao2.ClusterDao;
import com.example.distributetransaction.entity.StudentEntity;
import com.example.distributetransaction.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@Slf4j
public class ApiController {

    @Autowired
    private MasterDao masterDao;
    @Autowired
    private ClusterDao clusterDao;


    @RequestMapping(value = "/api/createUser", method = RequestMethod.POST)
    public String createUser(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {

        log.info("请求参数为:{}",user);
        masterDao.createUser(user);
        return "";
    }
    @RequestMapping(value = "/api/createStudent", method = RequestMethod.POST)
    public String createStudent(@RequestBody StudentEntity student, HttpServletRequest request, HttpServletResponse response) {

        log.info("请求参数为:{}",student);
        clusterDao.createStudent(student);
        return "";
    }
    @RequestMapping(value = "/api/getUser", method = RequestMethod.GET)
    public String getUser(String name, HttpServletRequest request, HttpServletResponse response) {

        log.info("请求参数为:{}",name);
        UserEntity user = masterDao.getUser(name);
        log.info("响应报文为:{}",user);
        return user.toString();
    }

    @RequestMapping(value = "/api/getStudent", method = RequestMethod.GET)
    public String getStudent(String name, HttpServletRequest request, HttpServletResponse response) {

        log.info("请求参数为:{}",name);
        StudentEntity student = clusterDao.getStudent(name);
        log.info("响应报文为:{}",student);
        return student.toString();
    }

    @Transactional(transactionManager = "xatx")
    @RequestMapping(value = "/api/testTransaction", method = RequestMethod.POST)
    public String testTransaction(@RequestBody StudentEntity student, HttpServletRequest request, HttpServletResponse response) {
        log.info("请求参数为:{}",student);
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(student.getAge());
        userEntity.setName(student.getName()+"");//这个字段插入时会超出长度限制报错
        clusterDao.createStudent(student);
        int i = 1/0;
        masterDao.createUser(userEntity);
        return "";
    }

    @Transactional(transactionManager = "xatx")
    @RequestMapping(value = "/api/testTransaction2", method = RequestMethod.POST)
    public String testTransaction2(@RequestBody Map<String,String> data, HttpServletRequest request, HttpServletResponse response) {
        log.info("请求参数为:{}",data);
        String param = data.get("param");
        masterDao.addLdcom(param);
        int i = 1/0;
        clusterDao.addBaseCode(param);
        return "";
    }
}
