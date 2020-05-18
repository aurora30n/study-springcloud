package cuner.nacos;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "cuner-nacos", groupId = "cuner", type = ConfigType.YAML, autoRefreshed = true)
public class CunerNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(CunerNacosApplication.class, args);
    }

    // 监控配置变化
    @NacosConfigListener(dataId = "cuner-nacos", groupId = "cuner", type=ConfigType.YAML)
    public void listening(String config) {
        System.out.println("listen: " + config);
    }
}
