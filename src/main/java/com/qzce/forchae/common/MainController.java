package com.qzce.forchae.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(Principal principal, Model model) {

        String mname = "";

        if(principal != null) {
            mname = principal.getName();
        }

        model.addAttribute("mname", mname);

        return "index";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signUp";
    }

    @GetMapping("/signUpMessage")
    public String signUpMessage(Model model) {

        model.addAttribute("message", "회원가입 성공");
        model.addAttribute("searchUrl", "/login");

        return "message";
    }

    @GetMapping("/logoutMessage")
    public String logoutMessage(Model model) {

        model.addAttribute("message", "로그아웃");
        model.addAttribute("searchUrl", "/");

        return "message";
    }

}
