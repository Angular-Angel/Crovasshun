/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import java.util.ArrayList;

/**
 *
 * @author angle
 */
public class CombatLoop {
    
    private ArrayList<Actor> actors;
    int currentActor;
    
    public CombatLoop() {
        actors = new ArrayList<>();
        currentActor = 0;
    }
    
    public void addActor(Actor actor) {
        actors.add(actor);
    }
    
    public void loop() {
        while(true) {
            if (actors.get(currentActor).ready()) {
                actors.get(currentActor).act();
                currentActor++;
                if (currentActor >= actors.size()) currentActor = 0;
            } else {
                try {
                    wait(10);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
