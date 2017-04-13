package com.sdu.controller;

import com.sdu.bean.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by J on 2017/2/20.
 */

@Controller
public class AdviceController {

    @RequestMapping(value = "/advice")
    public String getSomething(//Model model,
                               @ModelAttribute("msg") String msg, DemoObj obj){

//        model.addAttribute("obj",obj);
        throw new IllegalArgumentException("Sorry,error"+msg);

    }
}
