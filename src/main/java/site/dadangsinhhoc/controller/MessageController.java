package site.dadangsinhhoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.MessageModel;
import site.dadangsinhhoc.services.MessageService;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/getAllMessage")
    public ResponseObject getAllMessage() {
        return messageService.getAllMessage();
    }

    @GetMapping("/countAllMessage")
    public ResponseObject countAllMessage() {
        return messageService.countAllMessage();
    }

    @PostMapping("/addNewMessage")
    public ResponseObject addNewMessage(@RequestBody MessageModel messageModel) {
        return messageService.saveMessage(messageModel);
    }

    @PutMapping("/updateMessage/{id}")
    public ResponseObject updateMessage(@PathVariable Long id, @RequestBody MessageModel messageModel) {
        messageModel.setId(id);
        return messageService.updateMessage(id, messageModel);
    }

    @DeleteMapping("/deleteMessage/{id}")
    public ResponseObject deleteMessage(@PathVariable Long id) {
        return messageService.deleteByIdMessage(id);
    }

}
