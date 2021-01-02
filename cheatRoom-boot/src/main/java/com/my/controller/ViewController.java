package com.my.controller;


        import com.my.dao.KeyMapper;
        import com.my.pojo.base.User;
        import com.my.service.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;

        import javax.servlet.http.HttpServletRequest;
        import java.security.Principal;

/**
 * @author w
 */
@CrossOrigin
@Controller
public class ViewController {
    @Autowired
    UserService userService;

    @GetMapping("/user/nasus")
    public String getView(Principal principal, HttpServletRequest request){
        String name = principal.getName();
        User userByName = userService.getUserByName(name);
        request.setAttribute("myInfo",userByName);
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

    @RequestMapping("/toTest")
    public String toTest(){
        return "records";
    }

    @RequestMapping("/user/information")
    public String information(){
        return "information";
    }

    @RequestMapping("/user/toNasus")
    public String nasus(Principal principal, HttpServletRequest request){
        String name = principal.getName();
        User userByName = userService.getUserByName(name);
        request.setAttribute("myInfo",userByName);
        return "nasus";
    }

    @RequestMapping("/toResearch")
    public String toResearch(){
        return "research";
    }

    @RequestMapping("/user/toUpKey")
    public String toUpKey(){
        return "upVip";
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

