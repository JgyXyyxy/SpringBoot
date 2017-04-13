package com.sdu.controller;

import com.sdu.bean.ManagerUser;
import com.sdu.service.ManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by J on 2017/3/3.
 */
@Controller
public class LoginController {
    @Autowired
    ManagerUserService managerUserService;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute("manageruser",new ManagerUser());
        return "login";
    }

    @RequestMapping(value="/login" ,method = RequestMethod.POST)
    public String loginAccess(@Valid ManagerUser manageruser, HttpSession session, BindingResult result) throws Exception{

        if (result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            for(ObjectError error:list){
                System.out.println(error.getCode()+"----"+error.getArguments()+"----"+error.getDefaultMessage());
            }
            return "redirect:/login";
        }
        String username = manageruser.getUsername();
        String password = manageruser.getPassword();
        ManagerUser user = managerUserService.findByUsername(username);
        if(user.getPassword().equals(password)) {
            session.setAttribute("username",username);
            return "redirect:/";
        }

        return "redirect:/login";
    }


    @RequestMapping(value="/logout")
    public String logout(HttpSession session) throws Exception{
        //清除Session
        session.invalidate();

        return "redirect:/";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }


}
