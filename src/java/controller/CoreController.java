package controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CoreController {

    @RequestMapping("upload.do")
    @ResponseBody
    public Object upload(){
        return null;
    }
}
