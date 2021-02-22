package com.demo.service.impl;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import com.demo.model.UserDomain;
import com.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceBoltImpl {

    @SofaReference(interfaceType = UserService.class,binding = @SofaReferenceBinding( bindingType = "bolt",directUrl = "127.0.0.1:12221"))
    private UserService userService;

    public String addUser(UserDomain user){
          System.out.println("is user");
        return  userService.addUser(user);


    }


    public String updateMobile(String oldMobile, String newMobile) {
        return null;
    }


    public boolean deleteUser(String mobile) {
        return false;
    }


    public String updatePasswd(String mobile, String newPasswd) {
        return null;
    }


    public PageInfo<UserDomain> findAllUser(int pageNum, int pageSize) {
        return null;
    }


    public List<UserDomain> selectUsersByname(String name) {
        return null;
    }


    public String queryUsersByMobile(String mobile) {
        return null;
    }


    public int queryUserById(int userId) {
        return 0;
    }
}
