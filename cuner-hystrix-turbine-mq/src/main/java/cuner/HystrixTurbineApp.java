package cuner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbineStream
public class HystrixTurbineApp {
    public static void main(String[] args) {
        SpringApplication.run(HystrixTurbineApp.class, args);
    }
}
