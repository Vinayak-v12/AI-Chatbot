package com.chatapp.Ai.chatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.chatapp.Ai.chatbot.service.ChatBotService;


@Controller
public class chatBotController {
	@Autowired
	private ChatBotService service;

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @PostMapping("/fromuser")
    public String getResonseFromAi(@RequestParam("user_msg") String user_msg,Model model ) {
    	String message=service.getResponseFromAi(user_msg);
    	model.addAttribute("reply", message);
    	return "index";
    	
    }
	
}
