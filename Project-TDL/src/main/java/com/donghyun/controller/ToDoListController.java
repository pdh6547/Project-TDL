package com.donghyun.controller;

import com.donghyun.service.ToDoListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tdl")
public class ToDoListController {

    private final ToDoListService toDoListService;

    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

//    @GetMapping({"", "/"})
//    public String tdl(@RequestParam(value = "idx", defaultValue = "0")Long idx, Model model) {
//        model.addAttribute("tdl", toDoListService.findByIdx(idx));
//        return "/tdl/form";
//    }


    @GetMapping({"/list"})
    public String list(Model model) {
        model.addAttribute("list", toDoListService.findList());
        return "/tdl/list";
    }

}
