package com.campusCloudStorage.web;

import com.campusCloudStorage.entity.User;
import com.campusCloudStorage.enums.LoginStateEnum;
import com.campusCloudStorage.enums.RegisterStateEnum;
import com.campusCloudStorage.service.UserFriendService;
import com.campusCloudStorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private UserFriendService userFriendService;


    @RequestMapping(value="/{uId}",method= RequestMethod.POST)
    public String list(@PathVariable("uId") int uId, HttpServletRequest request, RedirectAttributes attributes, RedirectAttributesModelMap modelMap) {




        return ;
    }


}
