package com.example.demo4.registration.token;

import com.example.demo4.appuser.AppUserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationTokenEntity {

    //@Id
    @SequenceGenerator(
            name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence"
    )

    private long ID;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne//so that one user can have many confirmation tokens
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUserEntity appUserEntity;

    public ConfirmationTokenEntity(String token,
                                   LocalDateTime createdAt,
                                   LocalDateTime expiresAt,
                                   AppUserEntity appUserEntity) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.appUserEntity = appUserEntity;
    }
}
