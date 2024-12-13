package com.example.demo4.controller;

import com.example.demo4.appuser.AppUserService;
import com.example.demo4.messages.AsyncMessageService;
import com.example.demo4.messages.MessageEntity;
import com.example.demo4.messages.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private MessageService messageService;//used in the non-async version
    @Autowired
    private AsyncMessageService asyncMessageService;//used in the async version

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/")
    public String index(Model model) {
        logger.info("Accessing index page and loading messages");
        model.addAttribute("messages", messageService.getAllMessages());
        model.addAttribute("message", new MessageEntity());
        return "index";
    }

    @PostMapping("/send")
    public String sendMessage(@ModelAttribute MessageEntity message, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();//thats the email
        String username = appUserService.getUsernameByEmail(email);
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));

        message.setUsername(username);
        message.setTimestamp(currentTime);

        asyncMessageService.saveMessageAsync(message);//used in async version
        messageService.saveMessage(message);//used in the non-async version


        logger.info("Message sent by {} at {}: {}", username, currentTime, message.getContent());

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        logger.info("Login page accessed");
        return "login";
    }
}