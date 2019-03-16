package com.donghyun.controller;

import com.donghyun.domain.ToDoList;
import com.donghyun.repository.ToDoListRepository;
import com.donghyun.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
