/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import display.CombatScreen;
import display.MovePath;
import display.Pointer;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author angle
 */
public class MoveAction implements BodyAction {
    
    private MovePath movePath;
    
    public MoveAction(MovePath movePath) {
        this.movePath = movePath;
    }

    @Override
    public void perform(Body body) {
        if (movePath.pointers.size() > 0) {
            Point point = movePath.pointers.get(0).point;
            body.setPosition(point);
            movePath.startPoint = point;
            movePath.pointers.remove(0);
            movePath.lines.remove(0);
        }
    }

    @Override
    public boolean isValid() {
        
        return true;
    }

    @Override
    public boolean isDone() {
        return (movePath.pointers.isEmpty());
    }

    @Override
    public void onEnd(CombatScreen combatScreen) {
        combatScreen.localAreaScreen.drawables.remove(movePath);
    }
    
}
