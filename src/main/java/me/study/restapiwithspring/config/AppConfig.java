package me.study.restapiwithspring.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Bean
//    public ApplicationRunner applicationRunner() {
//        return new ApplicationRunner() {
//
//            @Autowired
//            AccountService accountService;
//
//            @Override
//            public void run(ApplicationArguments args) {
//                Account account = Account.builder()
//                        .email("mycat83@gmail.com")
//                        .password("123456")
//                        .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
//                        .build();
//                accountService.saveAccount(account);
//            }
//        };
//    }
}
