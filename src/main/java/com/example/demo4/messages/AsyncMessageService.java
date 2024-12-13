package com.example.demo4.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncMessageService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncMessageService.class);

    @Autowired
    private MessageRepository messageRepository;

    @Async
    public void saveMessageAsync(MessageEntity message) {
        long startTime = System.nanoTime();

        messageRepository.addMessage(message.getUsername(), message.getContent(), message.getTimestamp());

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        logger.info("Multithreaded save took {} ms", duration);
    }
}