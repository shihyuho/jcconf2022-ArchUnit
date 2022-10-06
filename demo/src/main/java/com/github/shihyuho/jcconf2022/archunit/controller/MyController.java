package com.github.shihyuho.jcconf2022.archunit.controller;

import com.github.shihyuho.jcconf2022.archunit.service.MyService;
import org.springframework.stereotype.Controller;


@Controller
public record MyController(MyService service) {

}
