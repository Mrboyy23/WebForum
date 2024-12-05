package com.example.project.controller.user;

import com.example.project.model.DTO.MessDTO;
import com.example.project.model.entity.MessagesEntity;
import com.example.project.responsitory.MessResponsitory;
import com.example.project.service.MessService;  // Import service để lưu tin nhắn
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class MessagingController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessService messService;

    public MessagingController(SimpMessagingTemplate simpMessagingTemplate, MessService messService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messService = messService;
    }

    // Phương thức xử lý gửi tin nhắn từ client
    @MessageMapping("/sendMessage")
    public void sendMessage(MessDTO messageDTO) {
        // Lưu tin nhắn vào cơ sở dữ liệu
        MessagesEntity message = new MessagesEntity();
        message.setMessageId(messService.maxId() + 1);
        message.setSenderId(messageDTO.getSenderId());
        message.setReceiverId(messageDTO.getReceiverId());
        message.setContent(messageDTO.getContent());
        message.setCreatedAt(LocalDateTime.now());

        messService.saveMessage(message);

        // Phát tin nhắn tới client đang lắng nghe
        simpMessagingTemplate.convertAndSend("/topic/messages/" + messageDTO.getReceiverId(), messageDTO);
        simpMessagingTemplate.convertAndSend("/topic/messages/" + messageDTO.getSenderId(), messageDTO);
    }
}

