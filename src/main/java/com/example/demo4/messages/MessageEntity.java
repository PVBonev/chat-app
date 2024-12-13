// MessageEntity.java
package com.example.demo4.messages;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class MessageEntity {
    @Id
    @SequenceGenerator(
            name = "messages_sequence",
            sequenceName = "messages_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "messages_sequence"
    )
    private Long id;
    private String username;
    private String content;
    private String timestamp;

}