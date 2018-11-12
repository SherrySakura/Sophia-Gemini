package sophia.controller;


import sophia.model.Message;
import sophia.model.Task;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sophia.model.User;
import sophia.service.TaskService;
import sophia.service.TomagoService;
import sophia.service.UserService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
public class CoreController {


    @RequestMapping(value = "login.do", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Object login(String id, String passwordHash){
        User user = UserService.login(id, passwordHash);
        return user;
    }

    @RequestMapping(value = "regist.do", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Object regist(String id, String passwordHash, String age){
        User newUser = new User(id, passwordHash, Integer.parseInt(age), 0);
        try {
            newUser = UserService.regist(newUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newUser;
    }

    @RequestMapping(value = "upload.do", produces = "text/html;charset=UTF-8")
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

    @RequestMapping(value = "query.do", produces = "text/html;charset=UTF-8")
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

    @RequestMapping(value = "transaction.do", produces = "text/html;charset=UTF-8")
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

    @RequestMapping(value = "tasks.do", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public List<Task> getTasks(){
        try {
            return TaskService.getAllTask();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "choose.do", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Object chooseTask(String taskUID){
        Message message = null;
        try {
            message = TaskService.chooseTask(taskUID);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return message;
    }
}
