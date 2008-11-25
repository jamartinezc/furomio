package Unalcolteam;

public class Vector3D 
{
private double x;
private double y;
private double z;
public Vector3D()
{
	x=0;
	y=0;
	z=0;
}
public Vector3D(double cx, double cy, double cz)
{
	x=cx;
	y=cy;
	z=cz;
}
public Vector3D(double cx, double cy)
{
	x=cx;
	y=cy;
}
public double getX() {
	return x;
}
public void setX(double x) {
	this.x = x;
}
public double getY() {
	return y;
}
public void setY(double y) {
	this.y = y;
}
public double getZ() {
	return z;
}
public void setZ(double z) {
	this.z = z;
}


}
