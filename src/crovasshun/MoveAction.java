/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import display.CombatScreen;
import display.MovePath;
import java.awt.Point;

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
    public void perform(Body body, long dt) {
        if (movePath.pointers.size() > 0) {
            Point target = movePath.pointers.get(0).point;
            Point start = body.getCenterPoint();
            
            int x = target.x - start.x;
            int y = target.y - start.y;
            
            double normal = Math.sqrt(x*x+y*y);
            
            x *= 2*dt;
            x/= normal;
            
            y *= 2*dt;
            y /= normal;
            
            Point destination = new Point(start.x + x, start.y + y);
            
            if(x > 0 && destination.x > target.x) {
                destination.x = target.x;
            }
            
            if(x < 0 && destination.x < target.x) {
                destination.x = target.x;
            }
            
            if(y > 0 && destination.y > target.y) {
                destination.y = target.y;
            }
            
            if(y < 0 && destination.y < target.y) {
                destination.y = target.y;
            }
            
            body.setPosition(destination);
            movePath.setStart(destination);
            if(destination.x == target.x && destination.y == target.y) {
                movePath.pointers.remove(0);
                movePath.lines.remove(0);
            }
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
