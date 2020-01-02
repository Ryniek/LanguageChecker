package pl.chomiczek.projekt_kosowski_rynski.controller;

import com.detectlanguage.errors.APIError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.chomiczek.projekt_kosowski_rynski.model.Message;
import pl.chomiczek.projekt_kosowski_rynski.model.MessageDto;
import pl.chomiczek.projekt_kosowski_rynski.service.MessageService;

import java.util.List;

@Controller
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String getHome(Model model) {
        List<Message> messages = messageService.getMessages();
        model.addAttribute("messages", messages);
        model.addAttribute("messageDto", new MessageDto());
        return "index";
    }

    @PostMapping("/addMessage")
    public String addMessage(@ModelAttribute MessageDto messageDto) throws APIError {
        messageService.addMessage(messageDto);
        return "redirect:/";
    }

}
