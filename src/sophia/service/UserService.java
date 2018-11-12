package sophia.service;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import sophia.model.Message;
import sophia.model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserService {

    private static String userXMLPath = "/home/asuna/IdeaProjects/Sophia-Gemini/resource/user/user.xml";

    private static List<User> allUsers = null;

    static {
        reloadUsers();
    }

    /**
     * 重性加载整个用户列表
     */
    private static void reloadUsers() {
        if (allUsers != null && allUsers.size() > 0){
            allUsers.clear();
        }
        SAXReader reader = new SAXReader();
        allUsers = new ArrayList<>();
        Document document = null;
        try {
            document = reader.read(new File(userXMLPath));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
            Element element = (Element) iterator.next();
            User user = new User();
            user.setId(element.elementText("id"));
            user.setPasswordHash(element.elementText("password"));
            user.setAge(Integer.parseInt(element.elementText("age")));
            user.setLove(Integer.parseInt(element.elementText("love")));
            allUsers.add(user);
        }
    }

    /**
     * 获取所有用户
     * @return
     */
    public static List<User> getAllUsers(){
        return allUsers;
    }

    /**
     * 登录操作
     * @param id:用户id
     * @param passwordHash:用户密码的hash值
     * @return
     */
    public static User login(String id, String passwordHash){
        User user = null;
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getId().equals(id)){
                if (allUsers.get(i).getPasswordHash().equals(passwordHash)){
                    user = allUsers.get(i);
                    user.setCode(0);
                    user.setMsg("登录成功");
                    user.setServiceName("user service");
                    user.setServiceMethod("login");
                }else {
                    user.setCode(-2);
                    user.setMsg("password error");
                }
            }
        }
        if (user == null){
            user.setCode(-1);
            user.setMsg("user not exist");
        }
        return user;
    }

    /**
     * 用户注册
     * @param newUser
     * @return
     * @throws IOException
     */
    public static User regist(User newUser) throws IOException {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new File(userXMLPath));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        Element newElement = root.addElement("user");
        Element id = newElement.addElement("id");
        id.setText(newUser.getId());
        Element password = newElement.addElement("password");
        password.setText(newUser.getPasswordHash());
        Element age = newElement.addElement("age");
        age.setText(String.valueOf(newUser.getAge()));
        Element love = newElement.addElement("love");
        love.setText(String.valueOf(newUser.getLove()));
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter writer = new XMLWriter(new FileWriter(new File(userXMLPath)), format);
        writer.write(document);
        writer.close();
        reloadUsers();
        return newUser;
    }
}
