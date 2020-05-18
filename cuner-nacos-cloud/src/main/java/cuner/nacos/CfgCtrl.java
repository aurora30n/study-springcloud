package cuner.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class CfgCtrl {

    @Value(value = "${db.ip:default}")
    private String dbIp = null;

    @RequestMapping(value = "/cfg/get", method = RequestMethod.POST)
    public Object get() {
        System.out.println("dbIp=" + this.dbIp);
        return dbIp;
    }

}
