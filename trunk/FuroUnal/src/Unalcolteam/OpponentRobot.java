package Unalcolteam;

public class OpponentRobot 
{
private Vector3D pos;
private double rotation;
public OpponentRobot(Vector3D pos, double rotation) 
{
	this.pos = pos;
	this.rotation = rotation;
}
public Vector3D getPos() {
	return pos;
}
public void setPos(Vector3D pos) {
	this.pos = pos;
}
public double getRotation() {
	return rotation;
}
public void setRotation(double rotation) {
	this.rotation = rotation;
}



}
