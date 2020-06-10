package cuner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * OAuth2.0 查询用户信息
 */
@Component(value = "customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 去除角色ROLE_前缀
     *//*
    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 这里应该是从数据库或者其他地方根据用户名将加密后的密码及所属角色查出来的
        List<String> users = Arrays.asList("admin", "user", "guest");
        List<String> roles = Arrays.asList("ADMIN", "USER");
        String pwd = "123456";
        int index = users.indexOf(username);

        if (!users.contains(username)) {
            throw new UsernameNotFoundException("用户不存在");
        } else {
            String password = passwordEncoder.encode(pwd);
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            if (index<roles.size()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_"+roles.get(index)));
            }
            return new User(username, password, authorities);
        }
    }
}
