package com.example.demo4.appuser;

import com.example.demo4.registration.token.ConfirmationTokenEntity;
import com.example.demo4.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)//its not called loadByEmail cos we are using the UserDetailsService interface
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUserEntity appUserEntity) {
        boolean userExists = appUserRepository
                .findByEmail(appUserEntity.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("Email already taken!");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUserEntity.getPassword());

        appUserEntity.setPassword(encodedPassword);

        appUserRepository.save(appUserEntity);

        String token = UUID.randomUUID().toString();

        ConfirmationTokenEntity confirmationTokenEntity = new ConfirmationTokenEntity(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUserEntity
        );

        confirmationTokenService.saveConfirmationToken(confirmationTokenEntity);

        return token;

    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

    public String getUsernameByEmail(String email) {
        return appUserRepository.findUsernameByEmail(email);
    }
}
