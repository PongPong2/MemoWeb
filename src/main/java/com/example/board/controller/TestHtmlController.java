package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/basic")
@Controller
public class TestHtmlController {

    @GetMapping("/init")
    public void init1() {

    }
}
