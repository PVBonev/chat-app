package com.example.demo4.messages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    @Query("SELECT m FROM MessageEntity m")
    List<MessageEntity> findAll();

    @Transactional
    @Modifying
    @Query("INSERT INTO MessageEntity (username, content, timestamp) VALUES (?1, ?2, ?3)")
    int addMessage(String username, String content, String timestamp);
}