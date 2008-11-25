package Unalcolteam;

import unalcol.agents.Action;

public class RoboAction extends Action
{
	public int id;
	public double velocityleft;
	public double velocityright;
	
	private static final String  CODE="Changespeed";
	
	public RoboAction(int _id,double vleft,double vright)
	{
		super(CODE);
		id=_id;
		velocityleft=vleft;
		velocityright=vright;
	}

}
