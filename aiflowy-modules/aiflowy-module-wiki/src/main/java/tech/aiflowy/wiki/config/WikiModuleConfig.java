package tech.aiflowy.wiki.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("tech.aiflowy.wiki.mapper")
@AutoConfiguration
@EnableScheduling
public class WikiModuleConfig {

    public WikiModuleConfig() {
        System.out.println("启用模块 >>>>>>>>>> module-wiki");
    }

}
