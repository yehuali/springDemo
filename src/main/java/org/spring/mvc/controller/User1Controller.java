package org.spring.mvc.controller;

import org.spring.mvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;


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

    // 数据转换
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


    /**
     *数据格式化
     */
    @RequestMapping(value="/{pagename}")
    public String index(@PathVariable String pagename){
        return "/user/"+pagename;
    }

    @RequestMapping(value="/test",method=RequestMethod.POST)
    public String test(@Valid @ModelAttribute User user,BindingResult bindingResult, Model model){
            model.addAttribute("user",user);
            return "user/success";
    }

    /**
     *  数据校验
     *  前一个表单/命令对象的校验结果保存在其后的入参中(必须为BindingResult
     *  “隐含模型”存储模型数据以及校验结果，隐含模型所有数据最终通过HttpServletRequest的属性列表暴露给Jsp视图对象
     *  数据绑定和数据校验发生错误 --->生成一个对应的FieldError对象
     *    1.FiledError对象实现了MessageSourceResolvable接口（国际化资源进行解析的对象）
     *    2.MessageSourceResolvable的3个接口方法
     *       Object[] getArguments():返回一组参数对象
     *       String[] getCodes():返回一组消息代码，每个代码对应一个资源属性
     *                           可以使用getArguments()方法返回的参数对资源属性值进行参数替换
     *       String getDefultMessage():默认的消息
     *   如果在数据类型转换或数据格式转换，或者参数不存在，或调用处理方法时发生错误  --->都会在隐含模型中创建错误信息
     *   其错误代码前缀说明
     *      1.required:必要的参数不存在
     *      2.tryMismatch:数据绑定时，发生数据类型不匹配
     *      3.methodInvocation：SpringMVC在调用处理方法时发生错误
     *
     *   错误对象的错误代码时对应国际化消息的键名 ---> 在i18n/下添加基名为messages的国际化资源
     */
    @RequestMapping(path="/handle91")
    public String handle91(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/user/createSuccess";
        }else{
            return "/user/showDetail";
        }
    }


}
