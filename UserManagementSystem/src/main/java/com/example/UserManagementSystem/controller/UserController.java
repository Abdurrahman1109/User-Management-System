package com.example.UserManagementSystem.controller;

import com.example.UserManagementSystem.entity.User;
import com.example.UserManagementSystem.repository.UserRepository;
import com.example.UserManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/allUsers")
    public String user_list(Model model){
        List<User> users=userService.allUsers();
        model.addAttribute("users",users);
        return "users_list";
    }

    @GetMapping("/signup")
    public String registration(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "sign_up";
    }

    @GetMapping("/showUpdateForm")
    public String updateUser(@RequestParam("userId") int id,Model model){
        Optional<User> dUser=userRepository.findById(id);
        userService.addUser(dUser.orElse(null));
        model.addAttribute("user",dUser);
        return "sign_up";
    }

    @PostMapping("/save")
    public String addUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes,Model model){
        userService.addUser(user);
        model.addAttribute("user",user);
        redirectAttributes.addFlashAttribute("success","User Added Successfully");
        return "redirect:/welcome";
    }

    @GetMapping("/deleteUserById")
    public String deleteUser(@RequestParam("userId")int id,RedirectAttributes redirectAttributes){
        userService.deleteUserById(id);
        redirectAttributes.addFlashAttribute("error","User Deleted Successfully");
        return "redirect:/allUsers";

    }

    @GetMapping("/welcome")
    public String greeting(){
        return "greeting";
    }
}
