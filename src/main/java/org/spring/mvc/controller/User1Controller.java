package org.spring.mvc.controller;

import org.spring.mvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

/**
 * @SessionAttributes的作用是将处理方法对应的模型属性透明低保存到HttpSession中
 * @ModelAttribute及@SessionAttributes遵循特定流程：
 *   1.SpringMVC在调用处理方法前，在请求线程中自动创建一个隐含的模型对象
 *   2.调用所有标注了@ModelAttribute的方法，并将方法返回值添加到隐含模型中
 *   3.查看Session中是否存在@SessionAttributes("xxx")所指定的xxx属性，如果有，则将其添加到隐含模型中。
 *        --->如果隐含模型中已经有xxx属性，则该步操作会覆盖模型中已有的属性值
 *   4.对标注了@ModelAttribute（"xxx"）处理方法的入参流程、
 *     4.1  如果隐含模型拥有名为xxx的属性，则将其赋给该入参，再用请求消息填充该入参对象直接返回，否则转到4.2
 *     4.2 如果xxx是会话属性（在处理类定义处标注@SessionAttributes("xxx")），查不到---> HttpSessionRequiredException
 */
@Controller
@RequestMapping(path = "/user1")
@SessionAttributes("user")
public class User1Controller {

    @ModelAttribute("user")
    public User getUser(){
        User user = new User();
        user.setUserId("1001");
        return user;
    }

    @RequestMapping(path = "/handle71")
    public String handle71(@ModelAttribute("user") User user){
       user.setUserName("zhangjie");
       return "redirect:/user1/handle72.html";
    }

    @RequestMapping(path = "/handle72")
    public String handle72(ModelMap modelMap, SessionStatus sessionStatus){
        User user = (User)modelMap.get("user");
        if(user != null){
            user.setUserName("Jetty");
            sessionStatus.setComplete();
        }
        return "/user/showUser";
    }

    @RequestMapping(path = "/handle81")
    public String handle81(@RequestParam("user") User user,ModelMap modelMap){
        modelMap.put("user",user);
        return "/user/showUser";
    }

    @RequestMapping(value="/{pagename}")
    public String index(@PathVariable String pagename){
        return "/user/"+pagename;
    }

    @RequestMapping(value="/test",method=RequestMethod.POST)
    public String test(@ModelAttribute User user,Model model){
        model.addAttribute("user",user);
        return "user/success";
    }



}
