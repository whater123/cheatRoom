package com.my.controller;


        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;

/**
 * @author w
 */
@CrossOrigin
@Controller
public class ViewController {

    @GetMapping("/user/nasus")
    public String getView(){
        return "nasus";
    }

    @GetMapping("/admin/nasus")
    public String getView2(){
        return "nasus2";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public String notLogin() {
        return "您尚未登陆！";
    }

    @ResponseBody
    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public String notRole() {
        return "您没有权限！";
    }
}

