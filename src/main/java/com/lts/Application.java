package com.lts;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author houxi
 * @Description
 * @Date 15:09 2019/4/9
 **/
@SpringBootApplication
@EnableEurekaServer
@EnableApolloConfig
@Controller
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }











    @ResponseBody
    @RequestMapping(value = "/test")
    public String test(@RequestParam String name, @RequestParam Date date) throws Exception {
        System.out.println(name);
        System.out.println(date);
        return name;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(String.class,
                new StringTrimmerEditor(true));

        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));

    }
//	@ModelAttribute
//	public void populateModel(@RequestParam String abc, Model model) {
//		model.addAttribute("attributeName", abc);
//	}

    @ModelAttribute
    public String populateModel2(@ModelAttribute String abc, @ModelAttribute String def) {
        return "123String";
    }

    @RequestMapping(value = "/helloWorld")
    public String helloWorld() {
        return "helloWorld";
    }
}