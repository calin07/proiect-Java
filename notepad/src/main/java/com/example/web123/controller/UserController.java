package com.example.web123.controller;
import com.example.web123.Service.UserService;
import com.example.web123.repository.UserRepository;
import model.CustomUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Collections;
import java.util.UUID;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;

    public UserController(UserRepository userRepository,UserService userService) {
        this.userRepository = userRepository;
        this.userService=userService;
    }

    @GetMapping("/")
    public String list(Model model,Principal principal) {
        String name=this.userRepository.findByName(getUserDetailsFromAuthentication(principal).getId());
        model.addAttribute("name",name);
        return "index";
    }

    @GetMapping("/add")
    public String from(){
        return "users/add";
    }

    @PostMapping("/add")
    public String addUsers(Model model, @RequestParam("username") String username, @RequestParam("password") String password){
        userService.saveUser(username,password);
        return "/login";
    }

    @GetMapping("/delete")
    public String deleteByName(@RequestParam String param1){
        UUID id=UUID.fromString(param1);
        System.out.println("Test empty:"+param1);
        userRepository.deleteAllById(Collections.singleton(id));
        return "redirect:/users/";
    }

    private CustomUserDetails getUserDetailsFromAuthentication(Principal principal) {
        return ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
    }
}