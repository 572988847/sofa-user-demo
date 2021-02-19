package com.winterchen.controller;


import com.winterchen.model.UserDomain;
import com.winterchen.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public String addUser(UserDomain user){

          return  userService.addUser(user);


    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        return userService.findAllUser(pageNum,pageSize);
    }
    @ResponseBody
    @GetMapping("/delete")
    public String deleteUser(@RequestParam(name = "mobile", required = true)String mobile)
    {
        if(userService.deleteUser(mobile))
        {
            return "删除成功";

        }else {
            return "删除失败" ;
        }

    }
    @ResponseBody
    @GetMapping("/updateMobile")
    public String updateMobile(@RequestParam(name = "oldMobile", required = true)
    String oldMobile,@RequestParam(name = "newMobile", required = true)
            String newMobile
    )
    {
        return  userService.updateMobile(oldMobile,newMobile);
    }

    @ResponseBody
    @GetMapping("/updatePasswd")
    public String updatePasswd(@RequestParam(name = "mobile", required = true)
                                       String mobile,@RequestParam(name = "newPasswd", required = true)
                                       String newPasswd
    )
    {
        return  userService.updatePasswd(mobile,newPasswd);
    }
    @ResponseBody
    @PostMapping("/selectUsersByname")
    public String selectUsersByname(String name){

        return  userService.selectUsersByname(name).toString();


    }
    @ResponseBody
    @PostMapping("/queryUsersByMobile")
    public String queryUsersByMobile(String mobile){

        return  userService.queryUsersByMobile(mobile);


    }

}
