package me.study.restapiwithspring.accounts;

import me.study.restapiwithspring.common.AppProperties;
import me.study.restapiwithspring.common.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountServiceTest extends BaseTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AppProperties appProperties;

    @Test
    public void findByUsername() {
        // When
        UserDetailsService userDetailsService = accountService;
        UserDetails userDetails = userDetailsService.loadUserByUsername(appProperties.getUserUsername());

        // Then
        assertThat(this.passwordEncoder.matches(appProperties.getUserPassword(), userDetails.getPassword())).isTrue();
    }

    @Test
    public void findByUsernameFail() {
        assertThrows(UsernameNotFoundException.class, () -> accountService.loadUserByUsername("random@gmail.com"));
    }
}
