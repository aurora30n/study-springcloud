package cuner.sys.consumer.feignclient;

import cuner.common.req.ResData;
import cuner.sys.common.entity.User;
import cuner.sys.common.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "cuner-sys-provider", fallbackFactory=UserFeignClientFallBack.class)
public interface UserFeignClient {

    @RequestMapping(value = "/user/reg", method = RequestMethod.POST)
    public ResData<Object> reg(@RequestBody UserVo userVo);

    @GetMapping("/user/get/{username}")
    public ResData<User> findByUsername(@PathVariable("username") String username);

    @GetMapping("/user/page/{pageNo}/{pageSize}")
    public ResData page(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize);

    @PostMapping("/user/login")
    public ResData<Object> login(@RequestBody User user);

}
