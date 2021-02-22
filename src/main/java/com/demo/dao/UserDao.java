package com.demo.dao;


import com.demo.model.UserDomain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao {


    void insert(UserDomain record);
    int queryMobile(String mobile);
    int queryUserById(int userId);
    boolean deleteUser(String mobile);
    boolean updateMobile(String oldMobile,String newMobile);
    boolean updatePasswd(String mobile,String newPasswd);
    List<UserDomain> selectUsersByname(String name);
    UserDomain queryUsersByMobile(String mobile);
    List<UserDomain> selectUsers();


}