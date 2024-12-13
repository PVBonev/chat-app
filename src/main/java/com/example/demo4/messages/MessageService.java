package com.example.demo4.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private MessageRepository messageRepository;

    public List<MessageEntity> getAllMessages() {
        logger.info("Getting all messages");
        return messageRepository.findAll();
    }

    public void saveMessage(MessageEntity message) {
        long startTime = System.nanoTime();

        messageRepository.addMessage(message.getUsername(), message.getContent(), message.getTimestamp());

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        logger.info("Singlethreaded save took {} ms", duration);
    }
}