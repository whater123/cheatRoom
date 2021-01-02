package com.my.controller;


import com.my.pojo.view.Chat;
import com.my.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/guest")
public class GuestController{
    @Autowired
    RecordService recordService;

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        System.out.println(recordService.getRecords());
        return "records";
    }

    @RequestMapping(value = "/testFor", method = RequestMethod.GET)
    public String test1(HttpServletRequest request) {
        List<Chat> list = new ArrayList<>();
        list.add(new Chat("1","11"));
        list.add(new Chat("1","22"));
        list.add(new Chat("1","33"));
        list.add(new Chat("1","33"));
        list.add(new Chat("1","33"));
        list.add(new Chat("1","33"));
        list.add(new Chat("1","33"));
        list.add(new Chat("1","33"));
        list.add(new Chat("1","33"));
        list.add(new Chat("1","33"));
        list.add(new Chat("1","33"));
        list.add(new Chat("1","33"));
        list.add(new Chat("1","33"));
        list.add(new Chat("1","33"));
        list.add(new Chat("1","33"));
        list.add(new Chat("1","33"));
        list.add(new Chat("1","33"));
        request.setAttribute("test",list);
        return "records";
    }
}