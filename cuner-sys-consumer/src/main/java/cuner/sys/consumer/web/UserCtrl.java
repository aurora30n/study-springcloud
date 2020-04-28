package cuner.sys.consumer.web;

import cuner.common.req.ResData;
import cuner.sys.common.entity.User;
import cuner.sys.common.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserCtrl {

    @Autowired
    private RestTemplate restTemplate;

    private String sysPridverUrl = "http://localhost:9001";

    @RequestMapping(value = "/user/reg", method = RequestMethod.POST)
    public ResData<Object> reg(@RequestBody UserVo userVo) {
        ResponseEntity<ResData> res = this.restTemplate.postForEntity(String.format("%s/user/reg", sysPridverUrl), userVo, ResData.class);
        return res.getBody();
    }

    @GetMapping("/user/get/{username}")
    public ResData<User> findByUsername(@PathVariable String username) {
        ResponseEntity<ResData> res = this.restTemplate.getForEntity(String.format("%s/user/get/%s", sysPridverUrl, username), ResData.class);
        return res.getBody();
    }

    @GetMapping("/user/page/{pageNo}/{pageSize}")
    public ResData page(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        ResponseEntity<ResData> res = this.restTemplate.getForEntity(String.format("%s/user/page/%s/%s", sysPridverUrl, pageNo, pageSize), ResData.class);
        return res.getBody();
    }

    @PostMapping("/user/login")
    public ResData<Object> login(HttpServletRequest request, @RequestBody User user) {
        ResponseEntity<ResData> res = this.restTemplate.postForEntity(String.format("%s/user/login", sysPridverUrl), user, ResData.class);
        return res.getBody();
    }

}
