package org.rabbit.hello;

public class HelloSpring {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.print("Hello " + name);
    }
}
