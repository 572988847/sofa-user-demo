package com.demo.service.impl;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.demo.dao.UserDao;
import com.demo.model.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
@SofaService(interfaceType = UserService.class,bindings = {@SofaServiceBinding (bindingType = "bolt")})
@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public Sm3Utils sm3 = new Sm3Utils() ;

    public String verifyMobile(String mobile)
    {
        if(mobile.length()!=11)
        {
            return "手机号长度不合法";
        }
        else if(mobile.charAt(1)=='6')
        {
            return "虚拟手机号不可注册";
        }
        else if (mobile.charAt(0)!='1')
        {
            return "手机号格式不合法";
        }
        return "true";

    }

    public String verifyPassword(String passwd)
    {
        if(passwd.length()<8)
        {
            return "密码长度不足";
        }
        else if(!passwd.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$"))
        {
            return "8-16位，至少1个大写字母，1个小写字母和1个数字";
        }
        else
        {
            return "true";
        }
    }
    public String EncryptionPasswd(String passwd)
    {

       return sm3.encrypt(passwd);
    }
    @Override
    public String addUser(UserDomain user) {
       String mobile =  user.getPhone().trim();
       String passwd = user.getPassword();
       String result ="true";
       result = verifyMobile(mobile);
       if(result.equals("true"))
       {
           result = verifyPassword(passwd);
           if(result.equals("true"))
           {
               if (userDao.queryMobile(mobile)>=1) {
                   return  "手机号已注册";
               } else {
                   passwd = EncryptionPasswd(passwd);
                   user.setPassword(passwd);
                   userDao.insert(user);
               }
               result= "注册成功";
           }
           else
           {
               return result;
           }

       }
       else
       {
           return result;
       }

       return result;
    }

    @Override
    public String updateMobile(String oldMobile, String newMobile) {
        if(userDao.queryMobile(oldMobile)<1)
        {
            return "原手机号用户不存在";

        }
        else if(userDao.queryMobile(newMobile)>=1)
        {
            return "新手机号已注册";
        }
        else {
           if(userDao.updateMobile(oldMobile,newMobile))
             return "手机号修改成功";
           else {
               return "手机号修改失败";
           }
        }

    }

    @Override
    public boolean deleteUser(String mobile) {

        if(userDao.queryMobile(mobile)<1)
        {
            return  false;

        }
        else {

               userDao.deleteUser(mobile);

        }
        return true;
    }

    @Override
    public String updatePasswd(String mobile, String newPasswd) {
        if(userDao.queryMobile(mobile)<1)
        {
            return "用户不存在";
        }
        else {
            String passwd = sm3.encrypt(newPasswd);

           if( userDao.updatePasswd(mobile,passwd))
           {
               return "密码修改成功";
           }
           else
           {
               return "密码修改失败";
           }

        }
    }

    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    @Override
    public PageInfo<UserDomain> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<UserDomain> userDomains = userDao.selectUsers();
        PageInfo result = new PageInfo(userDomains);
        return result;
    }

    @Override
    public List<UserDomain> selectUsersByname(String name) {
        return userDao.selectUsersByname(name);
    }

    @Override
    public String  queryUsersByMobile(String mobile) {
        if(userDao.queryMobile(mobile)>=1)
         return userDao.queryUsersByMobile(mobile).toString();
        else
        {
            return "用户不存在";
        }
    }

    @Override
    public int queryUserById(int userId) {
        return userDao.queryUserById(userId);
    }
}
