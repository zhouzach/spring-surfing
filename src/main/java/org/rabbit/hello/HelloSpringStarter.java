package org.rabbit.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringStarter {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        HelloSpring spring = (HelloSpring) context.getBean("helloSpring");
        spring.sayHello();

    }
}
