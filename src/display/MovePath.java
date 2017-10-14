/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author angle
 */
public class MovePath implements Drawable {
    
    public Point startPoint;
    public final ArrayList<Pointer> pointers;
    public ArrayList<DisplayLine> lines;
    
    public MovePath(Point startPoint) {
        this.startPoint = startPoint;
        pointers = new ArrayList<>();
        lines = new ArrayList<>();
    }
    
    public void addPoint(Pointer pointer) {
        Point prevpoint;
        if (pointers.size() > 0)
            prevpoint = pointers.get(pointers.size() - 1).point;
        else prevpoint = startPoint;
        
        lines.add(new DisplayLine(prevpoint, pointer.point));
        pointers.add(pointer);
    }
    
    public void removePoint(Point point) {
        if (pointers.size() > 0) {
            
            Pointer pointer = null;
            
            double distance = 75; // If we can't find a point within this  
                                  // distance, we don't remove anything.
            int k = -1;
            for (int i = 0; i < pointers.size(); i++) {
                Pointer curPointer = pointers.get(i);
                if (curPointer.point.distance(point) < distance) {
                    pointer = curPointer;
                    k = i;
                    distance = curPointer.point.distance(point);
                }
            }
            if (k >= 0) {
                if (k == pointers.size() - 1) {
                    pointers.remove(k);
                    lines.remove(k);
                    return;
                }
                
                DisplayLine newLine;
                
                if (k == 0)
                    newLine = new DisplayLine(startPoint, pointers.get(1).point);
                else newLine = new DisplayLine(pointers.get(k - 1).point, pointers.get(k + 1).point);
                
                lines.set(k, newLine);
                lines.remove(k + 1);
                pointers.remove(pointer);
            }
        }
    }
    
    public Point getEnd() {
        if (pointers.size() > 0)
            return pointers.get(pointers.size() - 1).point;
        else return startPoint;
    }
    
    @Override
    public void draw(Graphics2D g) {
        for (DisplayLine line: lines)
            line.draw(g);
        for (Pointer pointer : pointers)
            pointer.draw(g);
    }
    
}
