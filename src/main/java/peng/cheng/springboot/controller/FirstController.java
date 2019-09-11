package peng.cheng.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by chengpeng
 * @description 控制器
 * @time 2019/9/11 18:44
 */
@RestController
public class FirstController {

    @GetMapping("echo")
    public String echo(){
        return "Hello Spring Boot";
    }
}
