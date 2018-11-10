package service;

import model.Task;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskService {


    private static String taskPath = "resource/task.xml";

    public static List<Task> getAllTask() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(taskPath));
        Element root = document.getRootElement();
        List<Task> tasks = new ArrayList<>();
        for (Iterator iterator = root.elementIterator();iterator.hasNext();) {
            Element element = (Element) iterator.next();
            Task task = new Task();
            task.setTaskUID(element.element("taskUID").getText());
            task.setTaskTpye(element.element("taskTpye").getText());
            task.setTaskDiscrible(element.elementText("taskDiscrible"));
            task.setTaskDeadline(element.elementText("taskDeadline"));
            task.setTaskBase(Double.parseDouble(element.elementText("taskBase")));
            tasks.add(task);
        }
        return tasks;
    }
}
