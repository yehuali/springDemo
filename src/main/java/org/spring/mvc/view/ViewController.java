package org.spring.mvc.view;

import org.spring.mvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
@RequestMapping(path = "/view")
public class ViewController {
    @RequestMapping(path = "/showUserList")
    public String showUserList(ModelMap mm){
        Calendar calendar = new GregorianCalendar();
        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆");
        calendar.set(1980,1,1);
        user1.setBirthday(calendar.getTime());

        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());

        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList",userList);
        return "user/userList";
    }

    @RequestMapping(path = "/showUserListInJson")
    public String showUserListInJson(ModelMap mm){
        Calendar calendar = new GregorianCalendar();
        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆");
        calendar.set(1980,1,1);
        user1.setBirthday(calendar.getTime());

        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());

        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList",userList);
        return "user/userListJson";
    }

}
