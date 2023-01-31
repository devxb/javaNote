package org.example.controller;

import org.example.message.GreetingMessage;
import org.example.message.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController{

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public GreetingMessage greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000);
        return new GreetingMessage("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}
