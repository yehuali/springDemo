package org.spring.mvc.controller;



import org.spring.mvc.entity.User;
import org.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;


    /**
     * @param user  SpringMVC自动将表单中的数据按参数名和User属性名匹配的方式进行绑定
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView createUser( @ModelAttribute("user") User user){
        userService.createUser(user);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/createSuccess");
        //user作为模型数据暴露给视图对象
        mav.addObject("user",user);
        return mav;
    }

    @RequestMapping("/register")
    public String register(HttpServletResponse response){
        response.addCookie(new Cookie("userNamer","1213215"));
        return "user/register";
    }

    @RequestMapping(value = "/{userId}/detail",params = "userName")
    public ModelAndView showDetail(@PathVariable("userId") String userId,@RequestParam("userName") String userName,@CookieValue(value="JESSOINID",required = false) String sessionId){
        System.out.println("userName:"+userName+",sessionId:"+sessionId);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/showDetail");
        mav.addObject("user",userService.getUserById(userId));
        return  mav;
    }

//    @RequestMapping(path = "/handler1")
//    public String handler1(@RequestBody String requestBody){
//        System.out.println(requestBody);
//        return "successs";
//    }
//
//    @ResponseBody
//    @RequestMapping(path = "/handler2/image")
//    public byte[] handler2() throws IOException {
//        Resource res = new ClassPathResource("org/spring/mvc/controller/image.jpg");
//        byte[] fileData = FileCopyUtils.copyToByteArray(res.getInputStream());
//        return fileData;
//    }

    @RequestMapping(path = "/handler1")
    public String handler1(HttpEntity<String> httpEntity){
        System.out.println(httpEntity.getBody());
        return "successs";
    }

    @RequestMapping(path = "/handler2/image")
    public ResponseEntity<byte[]> handler2() throws IOException {
        Resource res = new ClassPathResource("org/spring/mvc/controller/image.jpg");
        byte[] fileData = FileCopyUtils.copyToByteArray(res.getInputStream());
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(fileData,HttpStatus.OK);
        return responseEntity;
    }

    /**
     *处理XML和JSON
     * 处理XML和JSON格式的请求/响应消息的HttpMessageConverter
     *  1.MarshallingHttpMessageConverter:处理XML格式的请求和响应消息
     *  2.Jaxb2RootElementHttpMessageConverter:同上，底层使用JAXB
     *  3.MappingJackon2HttpMessageConverter:处理JSON格式的请求或响应消息
     * --->只要Spring Web容器中为RequestMappingHandlerAdapter装配好相应的XML和JSON格式的请求/响应消息的HttpMessageConverter
     *     在交互中通过请求的Acceot指定MIME类型
     */
    @RequestMapping(path = "/handle51")
    public ResponseEntity<User> handle51(HttpEntity<User> requestEntity){
        User user = requestEntity.getBody();
        user.setUserId("1000");
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }



    public static void main(String[] args) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        byte[] response = restTemplate.postForObject("http://localhost:8080/user/handler2/image.html",null,byte[].class);
        Resource outFile = new FileSystemResource("D:/kobe.jpg");
        FileCopyUtils.copy(response,outFile.getFile());
    }
}
