package org.donglight.campus.guide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 *
 * @author ANS
 */
@SpringBootApplication
@EnableNeo4jRepositories(basePackages = "org.donglight.campus.guide.repository")
// 激活SDN隐式事务
@EnableTransactionManagement
@EntityScan(basePackages = "org.donglight.campus.guide.entity")
public class CampusGuideApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusGuideApplication.class, args);
    }

}
