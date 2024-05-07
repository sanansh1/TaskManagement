package com.api.mvctaskmgmt.controller;

import com.api.mvctaskmgmt.model.Task;
import com.api.mvctaskmgmt.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Controller for handling CRUD operations of task
 */
@Controller
public class TaskController {
    @Autowired
    private TaskService service;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView welcome(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("welcome");
        model.addAttribute("tasks", service.findAll());
        return modelAndView;
    }


    @RequestMapping(value = "/add-task", method = RequestMethod.GET)
    public String openTaskPage(Task task) {
        return "addtask";
    }

    @RequestMapping(value = "/add-task", method = RequestMethod.POST)
    public String addTask(ModelMap model, @Valid Task task, BindingResult result) {
        if (result.hasErrors())
            return "addtask";
        Task item = new Task(task.getId(),getLoggedInUserName(model), task.getDesc(), new Date(), false);
        service.save(item);
        model.clear();// to prevent request parameter "name" to be passed
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Task todoItem = service
                .findById(id);
        model.addAttribute("task", todoItem);
        return "edittask";
    }

    @PutMapping("/task/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid Task todoItem, BindingResult result, Model model) {
        Task item = service
                .findById(id);
        item.setDone(todoItem.isDone());
        item.setDesc(todoItem.getDesc());
        service.save(item);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
        service.deleteById(id);
        return "redirect:/";
    }

    private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();
        return principal.toString();
    }



}
