package com.lts.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author luotianshun
 * @date 2021/1/18
 * @menu
 */
@Configuration
@ConfigurationProperties(prefix = "mapconfig")
public class MapConfig  implements CommandLineRunner {

    Map<String, String> mapconfig;
    String dd;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(dd);
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public Map<String, String> getMapconfig() {
        return mapconfig;
    }

    public void setMapconfig(Map<String, String> mapconfig) {
        this.mapconfig = mapconfig;
    }
}
