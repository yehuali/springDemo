package org.spring.mvc.controller;

import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.junit.Test;
import org.spring.mvc.entity.User;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

public class UserCotrollerTest {

    @Test
    public void testMethod1() throws IOException {
        try{
            RestTemplate restTemplate = buildRestTemplate();

            User user = new User();
            user.setUserName("tom");
            user.setPassword("1234");
            user.setRealName("汤姆");

            HttpHeaders entityHeaders = new HttpHeaders();
            //xml传输
//            entityHeaders.setContentType(MediaType.valueOf("application/xml;UTF-8"));
//            entityHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
            //json传输
            entityHeaders.setContentType(MediaType.valueOf("application/json;UTF-8"));
            entityHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<User> requestEntity = new HttpEntity<User>(user,entityHeaders);

            ResponseEntity<User> responseEntity = restTemplate.exchange(
                    "http://localhost:8070//user/handle51.html", HttpMethod.POST, requestEntity, User.class);


            User responseUser = responseEntity.getBody();
            System.out.println(responseUser);
//			Assert.assertNotNull(responseUser);
//			Assert.assertEquals("1000", responseUser.getUserName())
        }catch(Exception e){ e.printStackTrace();}

    }

    private RestTemplate buildRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();

        XStreamMarshaller xmlMarshaller = new XStreamMarshaller();
        xmlMarshaller.setStreamDriver(new StaxDriver());
        xmlMarshaller.setAnnotatedClasses(new Class[]{User.class});

        MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();
        xmlConverter.setMarshaller(xmlMarshaller);
        xmlConverter.setUnmarshaller(xmlMarshaller);
        restTemplate.getMessageConverters().add(xmlConverter);

        MappingJackson2HttpMessageConverter jsonConverter =
                new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(jsonConverter);
        return restTemplate;
    }
}
