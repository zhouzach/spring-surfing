package org.rabbit.wiringbeans;

import org.springframework.stereotype.Component;

@Component
public class SgtPeppers implements CompactDisc{

    private String tile = "Sgt. Pepper Lonely Hearts Club Band";
    private String artist = "The Beatles";

    public void play() {
        System.out.println("Playing ");
    }
}
