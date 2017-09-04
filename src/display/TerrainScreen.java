/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import crovasshun.Body;
import crovasshun.LocalArea;
import crovasshun.LocalMapGenerator;
import crovasshun.Terrain;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Random;

/**
 *
 * @author angle
 */
public class TerrainScreen extends Screen {
    private int pixelsPerBorder;
    private int sideLength;	// length of one side
    private int triangleLength;	// short side of 30o triangle outside of each hex
    private int hexRadius;	// radius of inscribed circle (centre to middle of each side). r= h/2
    private int hexHeight;	// height. Distance between centres of two adjacent hexes. Distance between two opposite sides in a hex.
    
    private LocalArea area;

    public TerrainScreen() {
        this(15);
    }
    
    public TerrainScreen(int borderSize) {
        super();
        setFont(new Font("Monospaced", Font.PLAIN, 12));
        pixelsPerBorder = borderSize;
        
        area = LocalMapGenerator.getObelisk(20, 8);
        
        area.bodies.add(new Body(new ASCIISprite(new Color(255, 182, 193),  "' .\n" +
                                                                            "-0-"), new Point(3, 3)));
    }
    
    public void setSide(int side) {
        sideLength = side;
        triangleLength =  (int) (sideLength / 2);	   //t = s sin(30) = (int) CalculateH(s);
        hexRadius =  (int) (sideLength * 0.8660254037844); //r = s cos(30) = (int) CalculateR(s); 
        hexHeight=2*hexRadius;
    }
    
    public void setHeight(int height) {
        hexHeight = height;                             // h = basic dimension: height (distance between two adj centresr aka size)
        hexRadius = hexHeight/2;			// r = radius of inscribed circle
        sideLength = (int) (hexHeight / 1.73205);	// s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
        triangleLength = (int) (hexRadius / 1.73205);	// t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)
    }
    
    private Polygon hex (int x, int y) {
        if (sideLength == 0  || hexHeight == 0) {
            System.out.println("ERROR: size of hex has not been set");
            return new Polygon();
        }

        int[] cx, cy;

        cx = new int[] {x+triangleLength,x+sideLength+triangleLength,x+sideLength+(2*triangleLength),x+sideLength+triangleLength,x+triangleLength,x};	//this is for the whole hexagon to be below and to the right of this point

        cy = new int[] {y,y,y+hexRadius,y+(2*hexRadius),y+(2*hexRadius),y+hexRadius};
        return new Polygon(cx,cy,6);
    }
    
    private Polygon hex(Point point) {
        return hex(point.x, point.y);
    }
    
    public Point getHexPosition(int i, int j) {
        return new Point(i * (sideLength+triangleLength) + pixelsPerBorder, j * hexHeight + (i%2) * hexHeight/2 + pixelsPerBorder);
    }
    
    public Point getHexCenterPosition(int i, int j) {
        Point ret = getHexPosition(i, j);
        
        ret.x += triangleLength + sideLength/2;
        ret.y += hexRadius;
        
        return ret;
    }
    
    public Polygon getHex(int i, int j) {
        Point pos = getHexPosition(i, j);
        return hex(pos);
    }
    
    
    public void drawHex(int i, int j, Graphics2D g) {
        Polygon hex = getHex(i, j);
        g.setColor(Color.BLACK);
        g.fillPolygon(hex);
        g.setColor(Color.WHITE);
        g.drawPolygon(hex);
    }
    
    public void fillHex(int i, int j, Graphics2D g, ASCIITexture texture) {
        Point pos = getHexPosition(i, j);
        Polygon hex = hex(pos);
        texture.fillPolygon(hex, g);
    }
    
    public Point pxtoHex(int mx, int my) {
        Point p = new Point(-1,-1);

        //correction for BORDERS and XYVertex
        mx -= pixelsPerBorder;
        my -= pixelsPerBorder;

        int x = (int) (mx / (sideLength+triangleLength)); //this gives a quick value for x. It works only on odd cols and doesn't handle the triangle sections. It assumes that the hexagon is a rectangle with width s+t (=1.5*s).
        int y = (int) ((my - (x%2)*hexRadius)/hexHeight); //this gives the row easily. It needs to be offset by h/2 (=r)if it is in an even column

        /******FIX for clicking in the triangle spaces (on the left side only)*******/
        //dx,dy are the number of pixels from the hex boundary. (ie. relative to the hex clicked in)
        int dx = mx - x*(sideLength+triangleLength);
        int dy = my - y*hexHeight;

        if (my - (x%2)*hexRadius < 0) return p; // prevent clicking in the open halfhexes at the top of the screen

        //System.out.println("dx=" + dx + " dy=" + dy + "  > " + dx*r/t + " <");

        //even columns
        if (x%2==0) {
            if (dy > hexRadius) {	//bottom half of hexes
                if (dx * hexRadius /triangleLength < dy - hexRadius) {
                        x--;
                }
            }
            if (dy < hexRadius) {	//top half of hexes
                if ((triangleLength - dx)*hexRadius/triangleLength > dy ) {
                        x--;
                        y--;
                }
            }
        } else {  // odd columns
            if (dy > hexHeight) {	//bottom half of hexes
                if (dx * hexRadius/triangleLength < dy - hexHeight) {
                        x--;
                        y++;
                }
            }
            if (dy < hexHeight) {	//top half of hexes
                if ((triangleLength - dx)*hexRadius/triangleLength > dy - hexRadius) {
                        x--;
                }
            }
        }
        p.x=x;
        p.y=y;
        return p;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);
        //draw grid
        for (int i=0;i<area.getWidth();i++) {
            for (int j=0;j<area.getHeight();j++) {
                //drawHex(i,j,g2);
                fillHex(i, j, g2, area.getTerrain(i, j).appearance);
            }
        }
        
        for (Body b : area.bodies) {
            b.sprite.draw(getHexCenterPosition(b.position.x, b.position.y), g2);
        }
    }
    
}
