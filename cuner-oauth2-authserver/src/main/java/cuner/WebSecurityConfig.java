package cuner;

import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * spring security 基础配置
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 允许匿名访问饥/oauth/下的接口
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/oauth/**", "/actuator/**") // 放行options方法
                .permitAll()
                .and().httpBasic() // Basic提交，否则/oauth/authorize报403错误
                .and().csrf().disable(); // 关闭跨域保护*/

        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll() // 放行options方法
                .antMatchers("/oauth/**", "/actuator/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().httpBasic() // Basic提交，否则/oauth/authorize报403错误
                .and().csrf().disable(); // 关闭跨域保护
    }
}
