package sophia.service;

import sophia.model.Message;
import sophia.model.Task;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TaskService {


    private static String taskPath = "/home/asuna/IdeaProjects/Sophia-Gemini/resource/task-discrible/task_list.xml";

    private static List<Task> defaultTask;

    static  {
        SAXReader reader = new SAXReader();
        defaultTask = new ArrayList<>();
        Document document = null;
        try {
            document = reader.read(new File(taskPath));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        for (Iterator iterator = root.elementIterator();iterator.hasNext();) {
            Element element = (Element) iterator.next();
            Task task = new Task();
            task.setTaskUID(element.element("taskUID").getText());
            task.setTaskTpye(element.element("taskTpye").getText());
            task.setTaskDiscrible(element.elementText("taskDiscrible"));
            task.setTaskDeadline(element.elementText("taskDeadline"));
            task.setTaskBase(Double.parseDouble(element.elementText("taskBase")));
            task.setRemain(Integer.parseInt(element.elementText("taskRemain")));
            defaultTask.add(task);
        }
    }

    public static List<Task> getAllTask() throws DocumentException {
        return defaultTask;
    }

    /**
     * 选择任务
     * @param taskUID
     */
    public static Message chooseTask(String taskUID) throws ParseException {
        Task task = null;
        Message message = new Message(0, "执行成功", "task service", "chooseTask");
        for (int i = 0; i < defaultTask.size(); i++) {
            if (defaultTask.get(i).getTaskUID().equals(taskUID)){
                task = defaultTask.get(i);
            }
        }
        if (task == null){
            message.setCode(-1);
            message.setMsg("所选任务不存在");
        }else {
            if (!checkDeadline(task)){
                message.setCode(-2);
                message.setMsg("任务已经过期");
            }else if (!checkRemain(task)){
                message.setCode(-3);
                message.setMsg("任务数量不足");
            }else {
                task.decrease();
            }
        }
        return message;
    }

    /**
     * 判断任务时间和当前时间，如果任务时间在当前时间之前，任务失败
     * @param task
     * @return true：可以执行任务；false：任务失败
     * @throws ParseException
     */
    public static boolean checkDeadline(Task task) throws ParseException {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date taskDeadline = format.parse(task.getTaskDeadline());
        if (now.after(taskDeadline)){
            return false;
        }else {
            return true;
        }
    }

    public static boolean checkRemain(Task task){
        if (task.getRemain() <= 0){
            return false;
        }
        return true;
    }

    public static double getBouns(Task task){
        return  0.0;
    }
}
