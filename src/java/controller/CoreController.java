package java.controller;


import java.model.Task;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.service.TaskService;
import java.service.TomagoService;

import java.util.List;

@Controller
public class CoreController {

    @RequestMapping("upload.do")
    @ResponseBody
    public Object upload(String number, String ori, String des){
        String response = "";
        TomagoService service = new TomagoService();
        try {
            response = service.invoke(number, ori, des);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping("query.do")
    @ResponseBody
    public Object query(String men){
        String response = "";
        TomagoService service = new TomagoService();
        try {
            response = service.query(men);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping("transaction.do")
    @ResponseBody
    public Object transaction(){
        String response = "";
        TomagoService service = new TomagoService();
        try {
            response = service.transaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping("tasks.do")
    @ResponseBody
    public List<Task> getTasks(){
        try {
            return TaskService.getAllTask();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
