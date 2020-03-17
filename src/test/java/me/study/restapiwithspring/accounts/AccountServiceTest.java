package me.study.restapiwithspring.accounts;

import me.study.restapiwithspring.common.AppProperties;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.Matchers.containsString;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AccountServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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
//        // Given
//        String username = "mycat83@gmail.com";
//        String password = "123456";
//        Account account = Account.builder()
//                .email(username)
//                .password(password)
//                .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
//                .build();
//        this.accountService.saveAccount(account);

        // When
        UserDetailsService userDetailsService = accountService;
        UserDetails userDetails = userDetailsService.loadUserByUsername(appProperties.getUserUsername());

        // Then userDetails.getPassword()
        assertThat(this.passwordEncoder.matches(appProperties.getUserPassword(), userDetails.getPassword())).isTrue();
    }

    @Test(expected = UsernameNotFoundException.class)
    public void findByUsernameFail() {
        String username = "random@gmail.com";
        accountService.loadUserByUsername(username);
    }

    @Test
    public void findByUsernameFail2() {
        String username = "random@gmail.com";

        try {
            accountService.loadUserByUsername(username);
            fail("Supposed to be failed");
        } catch(UsernameNotFoundException e) {
            assertThat(e.getMessage()).contains(username);
        }
    }

    @Test
    public void findByUsernameFail3() {
        // Expected
        String username = "random@email.com";
        expectedException.expect(UsernameNotFoundException.class);
        expectedException.expectMessage(containsString(username));

        // When
        accountService.loadUserByUsername(username);
    }
}
