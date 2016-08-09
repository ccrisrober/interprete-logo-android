package Interface;

/**
 * Write a description of class Tortuga here.
 *
 * @author Francisco Dominguez
 * @date 14/03/2013
 * @version 20130314
 */
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Turtle {

    private Point pos;
    private double angle;
    private boolean penDown;
    private Paint p;
    private Canvas canvas;
    
   // private Graphics g;

    /**
     * Constructor for objects of class Tortuga
     */
    public Turtle() {
        this.p = new Paint();
        pos = new Point(0, 0);
        angle = 0;
        penDown = true;
    }
    
    public Turtle(Paint pp) {
        this.p = pp;
        pos = new Point(0, 0);
        angle = 0;
        penDown = true;
    }
    
    public Turtle(Paint pp, Canvas canvas, int x, int y) {
    	this.canvas = canvas;
        this.p = pp;
        pos = new Point(x, y);
        angle = 0;
        penDown = true;
    }

    private double a2r(double a) {
        return Math.PI * a / 180;
    }

    public void home() {
        pos = new Point(0, 0);
    }

    public void pen(boolean b) {
        penDown = b;
    }

    public void penDown() {
        pen(true);
    }

    public void penUp() {
        pen(false);
    }

    public void move(double dist) {
        int despX = (int) (pos.x + Math.cos(a2r(angle)) * dist);
        int despY = (int) (pos.y + Math.sin(a2r(angle)) * dist);
        Point newPos = new Point(despX, despY);
        if (penDown) {
        	canvas.drawLine(pos.x, pos.y, newPos.x, newPos.y, p); 
        }
        pos = newPos;
    }

    public void forward(double dist) {
        move(dist);
    }

    public void backward(double dist) {
        move(-dist);
    }

    public void turn(double a) {
        angle += a;
    }

    public void turnRight(double a) {
        turn(a);
    }

    public void turnLeft(double a) {
        turn(-a);
    }

    public void setColor(int c) {
    	p.setColor(c);
    }
}
