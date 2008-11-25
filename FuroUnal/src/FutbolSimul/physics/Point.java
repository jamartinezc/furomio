/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FutbolSimul.physics;

import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 *
 * @author Jorge
 */
public class Point extends Point2D implements Serializable{
    private double x;
    private double y;

    public Point(double x,double y){
        this.x=x;
        this.y=y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setLocation(double x, double y) {
        this.x=x;
        this.y=y;
    }

    public String toString(){
        return "("+getX()+";"+getY()+")";
    }

}
