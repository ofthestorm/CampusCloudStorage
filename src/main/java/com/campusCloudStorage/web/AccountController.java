package com.campusCloudStorage.web;

import com.campusCloudStorage.entity.User;
import com.campusCloudStorage.enums.LoginStateEnum;
import com.campusCloudStorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/login",method= RequestMethod.POST)
    public String login(User user, HttpServletRequest request, RedirectAttributes attributes, RedirectAttributesModelMap modelMap) {
        LoginStateEnum loginState=userService.validate(user);
        if(loginState!=LoginStateEnum.SUCCESS){
            return "home";
        }

        int uId=user.getuId();
        User userFromDB=userService.selectByPrimaryKey(uId);
        int rootDir=userFromDB.getRootDir();

        HttpSession session=request.getSession();
        session.setAttribute("uId",uId);
        modelMap.addFlashAttribute("uId",uId);
        attributes.addFlashAttribute("uId",uId);
        return "forward:/home/"+rootDir;
    }

    @RequestMapping(value="/register",method= RequestMethod.POST)
    public String register(User user, HttpServletRequest request, RedirectAttributes attributes, RedirectAttributesModelMap modelMap) {


        int uId=user.getuId();
        
        return "forward:/home/";
    }

}
