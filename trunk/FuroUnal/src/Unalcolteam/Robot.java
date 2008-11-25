package Unalcolteam;

import unalcol.agents.AgentArchitecture;
import unalcol.agents.AgentProgram;

public class Robot 
{
	private Vector3D pos;
	private double rotation;
	public Robot(Vector3D pos, double rotation)
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
