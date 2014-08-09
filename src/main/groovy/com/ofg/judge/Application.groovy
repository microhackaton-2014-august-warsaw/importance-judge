package com.ofg.judge

import com.ofg.infrastructure.environment.EnvironmentSetupVerifier
import com.ofg.microservice.Profiles
import groovy.transform.TypeChecked
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

@TypeChecked
@Configuration
@EnableAutoConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = ["com.ofg.judge", "com.ofg.twitter" ,"com.ofg.twitter.controller.relations.extractor","com.mangofactory.swagger"])
@EnableCaching
class Application {

    static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application)
        application.addListeners(new EnvironmentSetupVerifier(Profiles.all()))
        application.run(args)
    }
}
