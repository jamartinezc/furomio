package FutbolSimul.entities;

import FutbolSimul.physics.PhysicsObject;
import FutbolSimul.physics.Point;

public class SimulBall extends PhysicsObject{
	private double inix,iniy;
	private double radio;
    private double movimiento=0.0;
	public SimulBall(double _x,double _y,double _direccion, double _radio){
		x = _x;
		y = _y;	
		inix = _x;
		iniy = _y;
		radio = _radio;
		angle = _direccion;
	}

	public void reset() {
		setX(getInix());
		setY(getIniy());
	}

	public double devolverX(){
		return getX();
	}
	public double devolverY(){
		return getY();
	}	
    public void cambiarX(double velX){
		setX(getX() + velX);
	}
	public void cambiarY(double velY){
		setY(getY() + velY);
	}
	public double devolverRadio(){
		return getRadio();
	}
	public double devolverDireccion(){
		return getDireccion();
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
     * @return the inix
     */
    public double getInix() {
        return inix;
    }

    /**
     * @param inix the inix to set
     */
    public void setInix(double inix) {
        this.inix = inix;
    }

    /**
     * @return the iniy
     */
    public double getIniy() {
        return iniy;
    }

    /**
     * @param iniy the iniy to set
     */
    public void setIniy(double iniy) {
        this.iniy = iniy;
    }

    /**
     * @return the direccion
     */
    public double getDireccion() {
        return angle;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(double direccion) {
        this.angle = direccion;
    }

    /**
     * @return the radio
     */
    public double getRadio() {
        return radio;
    }

    /**
     * @param radio the radio to set
     */
    public void setRadio(double radio) {
        this.radio = radio;
    }

    /**
     * @return the movimiento
     */
    public double getMovimiento() {
        return movimiento;
    }

    /**
     * @param movimiento the movimiento to set
     */
    public void setMovimiento(double movimiento) {
        this.movimiento = movimiento;
    }

    public SimulBall clone(){
        SimulBall clone = new SimulBall(x, y, angle, radio);
        clone.setInix(inix);
        clone.setIniy(iniy);
        clone.setMovimiento(movimiento);

        return clone;
    }

    @Override
    public double getInertia() {
        return FutbolSimul.physics.Inertia.sphereInertia(radio, mass);
    }

    @Override
    public Point getCorner(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
	
}
