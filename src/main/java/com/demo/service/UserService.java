package com.demo.service;

import com.github.pagehelper.PageInfo;
import com.demo.model.UserDomain;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */
public interface UserService {

    String addUser(UserDomain user);
    String updateMobile(String oldMobile,String newMobile);
    boolean deleteUser(String mobile);
    String updatePasswd(String mobile,String newPasswd);
    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);
    List<UserDomain> selectUsersByname(String name);
    String  queryUsersByMobile(String mobile);
    int queryUserById(int userId);
}
