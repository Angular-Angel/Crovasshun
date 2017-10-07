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
public class CombatLoop implements Runnable {
    
    private ArrayList<Actor> actors;
    int currentActor;
    public Player player;
    
    public CombatLoop() {
        actors = new ArrayList<>();
        currentActor = 0;
    }
    
    public void addActor(Actor actor) {
        actors.add(actor);
    }
    
    public void run() {
        while(true) {
            actors.get(currentActor).act(this);
            currentActor++;
            if (currentActor >= actors.size()) currentActor = 0;
        }
    }
}
