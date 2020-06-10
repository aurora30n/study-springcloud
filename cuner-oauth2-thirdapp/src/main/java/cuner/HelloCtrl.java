package cuner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class HelloCtrl {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @GetMapping("/thirdLogin/callback")
    public String callbak(@RequestParam("code") String code, Model model) {
        if (code != null) {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("code", code);
            map.add("client_id", "cuner-oauth2-authresource");
            map.add("client_secret", "123456");
            map.add("redirect_uri", "http://localhost:3003/thirdLogin/callback");
            map.add("grant_type", "authorization_code");
            Map<String,String> resp = restTemplate.postForObject("http://localhost:3001/oauth/token", map, Map.class);
            String accessToken = resp.get("access_token");
            System.out.println(accessToken);
            model.addAttribute("accessToken", accessToken);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);
            HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<String> entity = restTemplate.exchange("http://localhost:3002/getCurUser", HttpMethod.GET, httpEntity, String.class);
            model.addAttribute("msg", entity.getBody());
        } else {
            model.addAttribute("errorMsg", "第三方登录失败");
        }

        return "main";
    }

    @GetMapping("/thirdRes/{accessToken}/{param}")
    public String thirdRes(@PathVariable("accessToken") String accessToken, @PathVariable("param") String param, Model model) {
        if (accessToken != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);
            HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<String> entity = restTemplate.exchange("http://localhost:3002/"+param, HttpMethod.GET, httpEntity, String.class);
            model.addAttribute("msg", entity.getBody());
            model.addAttribute("accessToken", accessToken);
        } else {
            model.addAttribute("errorMsg", "accessToken不能为空");
        }

        return "main";
    }

}
