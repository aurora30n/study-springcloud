package cuner.sys.consumer.feignclient;

import cuner.common.req.ResData;
import cuner.sys.common.entity.User;
import cuner.sys.common.vo.UserVo;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallBack implements FallbackFactory<UserFeignClient> {
    private static Logger log = LoggerFactory.getLogger(UserFeignClientFallBack.class);
    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {

            @Override
            public ResData<Object> reg(UserVo userVo) {
                log.error("服务不可用：", throwable);
                return ResData.error("注册失败，服务不可用");
            }

            @Override
            public ResData<User> findByUsername(String username) {
                log.error("服务不可用：", throwable);
                return ResData.error("查询失败，服务不可用");
            }

            @Override
            public ResData page(Integer pageNo, Integer pageSize) {
                log.error("服务不可用：", throwable);
                return ResData.error("查询失败，服务不可用");
            }

            @Override
            public ResData<Object> login(User user) {
                log.error("服务不可用：", throwable);
                return ResData.error("登录失败，服务不可用");
            }
        };
    }

}
