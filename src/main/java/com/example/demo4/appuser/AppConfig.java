package com.example.demo4.appuser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    CommandLineRunner commandLineRunner(AppUserRepository repository) {

        if(repository.count() != 0) {
            return args -> {};
        }
        return args -> {
            AppUserEntity adminP = new AppUserEntity("Pete", "p", "p@gmail.com", "$2a$10$kEYIErmANDhgrbUr6aGfNO3l5k5ugp.B0ARxOWBxxvplZOg6NSuYq", AppUserRole.ADMIN);//password is "p"
            adminP.setEnabled(true);
            repository.save(adminP);

            AppUserEntity adminA = new AppUserEntity("Alek", "a", "a@abv.bg", "$2a$10$kEYIErmANDhgrbUr6aGfNO3l5k5ugp.B0ARxOWBxxvplZOg6NSuYq", AppUserRole.ADMIN);//password is "p"
            adminA.setEnabled(true);
            repository.save(adminA);
        };
    }

}
