package hello.hellospring.controller;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model){

        model.addAttribute("data", "hello!");
        //addAttribute(addtibuteName, attributeValue)

        return "hello";

    }

    @GetMapping("/hello-mvc")
    public String helloMvc(@RequestParam String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("/hello-string")
    @ResponseBody //http의 body에 data를 직접 넣어주겠다.
    public String helloString(@RequestParam String name){
        return "hello" + name;
    }

    @GetMapping("/hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    @Data
    static class Hello{
        private String name;

    }
}
