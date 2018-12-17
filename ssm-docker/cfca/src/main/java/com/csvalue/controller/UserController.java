package com.csvalue.controller;

import com.csvalue.model.User;
import com.csvalue.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @ResponseBody
    @RequestMapping(value = "/query",method = {RequestMethod.GET,RequestMethod.POST})
    public String queryUserList(HttpServletRequest request){
        List<User> userList=iUserService.queryUserList();
        for(User user:userList){
            System.out.println("out=>"+user.toString());
        }
        return "hello Springmvc";
    }
}
