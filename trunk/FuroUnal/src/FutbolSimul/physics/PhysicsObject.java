/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FutbolSimul.physics;

/**
 *
 * @author Jorge
 */
public abstract class PhysicsObject {
    protected Vector2D vel;
    protected Vector2D acel;
    protected double AngularVel;
    protected double AngularAcel;
    protected double mass;
    protected double x;
    protected double y;
    protected double angle;

    /**
     * @return the vel
     */
    public Vector2D getVel() {
        return vel;
    }

    /**
     * @param vel the vel to set
     */
    public void setVel(Vector2D vel) {
        this.vel = vel;
    }

    /**
     * @return the acel
     */
    public Vector2D getAcel() {
        return acel;
    }

    /**
     * @param acel the acel to set
     */
    public void setAcel(Vector2D acel) {
        this.acel = acel;
    }

    /**
     * @return the AngularVel
     */
    public double getAngularVel() {
        return AngularVel;
    }

    /**
     * @param AngularVel the AngularVel to set
     */
    public void setAngularVel(double AngularVel) {
        this.AngularVel = AngularVel;
    }

    /**
     * @return the AngularAcel
     */
    public double getAngularAcel() {
        return AngularAcel;
    }

    /**
     * @param AngularAcel the AngularAcel to set
     */
    public void setAngularAcel(double AngularAcel) {
        this.AngularAcel = AngularAcel;
    }

    /**
     * @return the mass
     */
    public double getMass() {
        return mass;
    }

    /**
     * @param mass the mass to set
     */
    public void setMass(double mass) {
        this.mass = mass;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * @param angle the angle to set
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    public abstract double getInertia();

    public abstract Point getCorner(int index);
}
