package cn.limitless.ruijitakeout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class RuijiTakeOutApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuijiTakeOutApplication.class, args);
    }

}
