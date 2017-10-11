/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author angle
 */
public class MoveAction implements BodyAction {
    
    public ArrayList<Point> points;
    
    public MoveAction() {
        points = new ArrayList<>();
    }
    
    public void addPoint(Point point) {
        points.add(point);
    }
    
    public void removePoint() {
        if (points.size() > 0)
            points.remove(points.size() - 1);
    }

    @Override
    public void perform(Body body) {
        body.setPosition(points.get(points.size() - 1));
    }

    @Override
    public boolean isValid() {
        
        return true;
    }
    
}
