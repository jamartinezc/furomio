package Unalcolteam;

public class Bounds 
{
private long left;
private long right;
private long top;
private long bottom;


public Bounds(long left, long right, long top, long bottom) 
{
	this.left = left;
	this.right = right;
	this.top = top;
	this.bottom = bottom;
}

public long getLeft() {
	return left;
}
public void setLeft(long left) {
	this.left = left;
}
public long getRight() {
	return right;
}
public void setRight(long right) {
	this.right = right;
}
public long getTop() {
	return top;
}
public void setTop(long top) {
	this.top = top;
}
public long getBottom() {
	return bottom;
}
public void setBottom(long bottom) {
	this.bottom = bottom;
}

}
