package com.appSecure.codefellowship.Controller;

import com.appSecure.codefellowship.Model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;


import com.appSecure.codefellowship.Model.User;
import com.appSecure.codefellowship.Repository.PostRepository;
import com.appSecure.codefellowship.Repository.UserinputRepository;


import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class UserApp {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserinputRepository userinputRepository;

//    @GetMapping("/home{username}")
//    public String gethomeByUsername(@PathVariable String username, Model model) {
//        User user = userinputRepository.findByUsername(username);
//        model.addAttribute("username", username);
//        model.addAttribute("userInput", user);
//
//        return "home";
//    }
//
//    @GetMapping("/")
//    public String returnHome(){
//        return "home.html";
//    }

//***********************************************************  for home


        @GetMapping("/login")
        public String getLogin(){
            return "login";
        }

        @GetMapping("/signup")
        public String getSignup(){
            return "signup";
        }

        @PostMapping("/signup")
        public String postSignup(@RequestParam String username, @RequestParam String password , @RequestParam String firstName, @RequestParam String lastName, @RequestParam String dateOfBirth, @RequestParam String bio){
            User user = new User(username,encoder.encode(password),firstName,lastName,dateOfBirth,bio);
            userinputRepository.save(user);
            return "login.html";
        }

        @GetMapping("/profile")
        public String getProfileForUser(Model model){
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("newPosts", userinputRepository.findByUsername(userDetails.getUsername()).getPosts());
            return "profile.html";
        }

        @GetMapping("/signOut")
        public RedirectView signOut()
        {
            return new RedirectView("/");
        }

        @ModelAttribute("user")
        public User getUser(){
            if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass() == User.class)
            {
                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                return user;
            }
            else
                return null;
        }

//***********************************************************  Error
    @GetMapping("/")
    public String getHome(){

                return "home.html";
}

    @GetMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("usersList", userinputRepository.findAll());
        return "list.html";
    }

    @GetMapping("/users/{id}")
    String view(@PathVariable Integer id , Model model) {
        User thisUser = userinputRepository.findById(id).orElseThrow();
        model.addAttribute("posts",thisUser.getPosts());
        model.addAttribute("thisUser",thisUser);

        return "userInfo.html";
    }


    @PostMapping("/myprofile/{id}/addpost")
    public RedirectView addPost( @RequestParam String postBody , @PathVariable Integer id , Model model){

        Post post = new Post(postBody, LocalDateTime.now().toString());
        User thisUser = userinputRepository.findById(id).orElseThrow();
        post.setAuthor(thisUser);
        List<Post> newPosts = thisUser.getPosts();
        newPosts.add(post);
        thisUser.setPosts(newPosts);
        postRepository.save(post);
        model.addAttribute("newPosts",newPosts);
        return new RedirectView ("/myprofile");
    }

    private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new Java8TimeDialect());
        engine.setTemplateResolver(templateResolver);
        return engine;
    }


//***********************************************************  Error

    @GetMapping("/error")
    public String getErrorPage(){
        return "error";
    }
}