package cuner.nacos;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CfgCtrl {

    @NacosValue(value = "${db.ip:default}", autoRefreshed = true)
    private String dbIp = null;

    @RequestMapping(value = "/cfg/get", method = RequestMethod.POST)
    public Object get() {
        return dbIp;
    }

}
