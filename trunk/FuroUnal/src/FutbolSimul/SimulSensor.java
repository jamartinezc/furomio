package FutbolSimul;

import java.util.Vector;

import Unalcolteam.Ball;
import Unalcolteam.Vector3D;

import unalcol.agents.Sensor;

public class SimulSensor implements Sensor{
	private SimulEnviroment env;
	
	public SimulEnviroment getEnv() {
		return env;
	}

	public void setEnv(SimulEnviroment env) {
		this.env = env;
	}
	
	public SimulSensor(SimulEnviroment env) {
		this.env = env;
	}

	public Object getSensation(String s)
	{
		if(s.equals("id"))
		{
			return 0;
		//codigo retornar id	
		}
		if(s.equals("ball"))
		{
			Ball bola = new Ball(new Vector3D(env.bola.devolverX(),180-env.bola.devolverY()));
			//System.out.print("percibe bola "+ env.bola.devolverX());
			return bola;
			//codigo para retornar una bola
		}
		if(s.equals("home"))
		{
			return env.posicioneshome();
			//codigo para retornar equipo local
		}
		if(s.equals("opponent"))
		{
			return env.posicionesop();
			//codigo para retornar los oponentes
		}
		if(s.equals("field"))
		{
			return null;
			//codigo para retornar el campo
		}
		if(s.equals("goal"))
		{
			return null;
			//codigo para retornar la porteria
		}
		return null;
	}

	public Vector<String> canget() 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
