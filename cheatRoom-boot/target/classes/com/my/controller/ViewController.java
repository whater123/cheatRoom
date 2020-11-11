package com.my.controller;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author w
 */
@Controller
public class ViewController {

    @GetMapping("/nasus")
    public String getView(){
        return "nasus";
    }

    @GetMapping("/nasus2")
    public String getView2(){
        return "nasus2";
    }
}

