package com.upnet.iot.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目的启动类
 * @author 芋道源码
 */
@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${upnet.info.base-package}
@SpringBootApplication(scanBasePackages = {"${upnet.info.base-package}.server", "${upnet.info.base-package}.module"})
public class UpnetServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpnetServerApplication.class, args);
    }

}
