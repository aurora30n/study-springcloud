package cuner;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserCtrl {

    @GetMapping(value = "/getCurUser")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Principal getCurrentUser(Principal principal) {
        return principal;
    }

    @GetMapping(value = "/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public Object admin(){
        return "admin";
    }

    @GetMapping(value = "/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Object user(){
        return "user";
    }

    @GetMapping(value = "/guest")
    public Object guest(){
        return "guest";
    }

}
