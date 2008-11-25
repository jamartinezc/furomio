package Estrategias;

import java.util.Vector;

import Unalcolteam.Ball;
import Unalcolteam.Bounds;
import Unalcolteam.RoboAction;
import Unalcolteam.Robot;
import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;

public class AgentProgramOpponentArq implements AgentProgram
{

	public final static double PI=3.1415923;
	public final static double ox=215;//solo funciona para equipo a la derecha
	public final static double oy=90;//solo funciona para equipo a la derecha
	public final static double KVp=5;
	public final static double KVd=4;
	public final static double Kp=14;
	public final static double Kd=13;
	public final static double v=100;
	public final static double L=7.5;//pulgadas = 7.5 cm 2.94 pulg
	public final static double minerrorangle=0.01745;
	private Bounds AreaAccion;
	private double eant;
	private double Yant;
	private double Xant;
	private double bx;
	private double by;
	private double vleft;
	private double vright;
	private double Y;
	private double X;
	private double a;
	private double aux;
	private int id;
	
	private int j;
	
	public AgentProgramOpponentArq(int _id)
	{
		init();
		id=_id;
		j=0;
	}
	
	
	public Action compute(Percept p) 
	{
		 vleft=0;
		 vright=0;
		//--------------------------------------
		// aqui va la estrategia que al final debe asignar valor a vleft y vright
		Vector<Robot> home=(Vector<Robot>)p.getAttribute("opponent");
		Ball ball=(Ball)p.getAttribute("ball");
		
		//System.out.print("s");
		//System.out.print("vel:" + vleft + " "+ vright+"\n");
		bx=ball.getPos().getX();
		by=ball.getPos().getY();
		//System.out.print("Yball:" + by + " YRobot "+ home.elementAt(0).getPos().getY()+"\n");
		Y=by-home.elementAt(id-5).getPos().getY();
		X=bx-home.elementAt(id-5).getPos().getX();
		a=home.elementAt(id-5).getRotation();
		
	
		if(Math.abs(home.elementAt(id-5).getPos().getX())>=215)//esta cerca a su arco
			BloqueaArquero(home);
		else
			Regresa(home,ox,oy,ox,oy-10);
		
		
		
		//vleft=3.1;
		//vright=3;
		Yant=by;
		//----------------------------------------fin de la estrategia
		//System.out.println("VELLEFT="+vleft+" VRIGHT="+vright);
		RoboAction act=new RoboAction(id,vleft,vright);
		return act;
	}

	public void init() 
	{
		 AreaAccion=new Bounds(170,215,115,65);		 
		 eant=0;
		 Yant=0;
		 vleft=0;
		 vright=0;
		
	}
	/**
	 * La función realiza un movimiento vertical en el agente para bloquear el paso de la bola
	 * @param _home Vector que contiene la informacion de los robots.
	 */
	private void BloqueaArquero(Vector<Robot> _home)//movimiento vertical
	{
		j=0;
		double DelY=(by-Yant);
		double DelX=(bx-Xant);
		double d=Math.sqrt(Math.pow(DelY,2)+Math.pow(DelX,2))/10;
		//--Lineas para que el arquero no salga de su area
		if(by<AreaAccion.getBottom())
		{
			Y=AreaAccion.getBottom()-_home.elementAt(id-5).getPos().getY();
		}
		if(by>AreaAccion.getTop())
			Y=AreaAccion.getTop()-_home.elementAt(id-5).getPos().getY();
		
		double e=-PI/2-a;
		double de=e-eant;
		eant=e;
		//--
		if(Math.abs(e)>minerrorangle)//si el agente esta desviado corrije posicion
		{
			double w=50*e+50*de;
			vleft=-w*L/2;
			vright=w*L/2;
		}
		else//si esta bien dirigido va a bloquear
		{
			double DY=Y-Yant;
			Yant=Y;
			double w=(KVp*Y*+KVd*DY)/70;
			vleft=w;
			vright=w;
		}
	}
	/**
	 * La función lleva al agente hacia un punto determinado trazando una trayectoria
	 * dependiente de el punto hacia el cual quiere que mire. 
	 * 
	 * @param _home Vector que contiene la informacion de los robots
	 * @param x Coordenada X del punto al cual se dirije
	 * @param y Coordenada Y del punto al cual se dirije
	 * @param fx Coordenada en X del punto hacia el cual se quiere que el agente quede mirando
	 * @param fy Coordenada en Y del punto hacia el cual se quiere que el agente quede mirando
	 */
	private void Regresa(Vector<Robot> _home,double x, double y,double fx,double fy)
	{
		X=x-_home.elementAt(id-5).getPos().getX();
		Y=y-_home.elementAt(id-5).getPos().getY();
		double phi=Math.atan2(Y,X);
		X=fx-_home.elementAt(id-5).getPos().getX();
		Y=fy-_home.elementAt(id-5).getPos().getY();
		double alpha=Math.atan2(Y,X)-phi;
		phi=phi-alpha;
		double e;
		if(Math.abs(phi)<=PI/2)
		   e=phi-a;
		else
		{
			if(phi<0)
				phi=2*PI+phi;
			if(a<0)
				a=2*PI+a;
			e=phi-a;
		}
		double de=e-eant;
		eant=e;
		double w=Kp*e+Kd*de;
		vleft=v-w*L/2;
		vright=v+w*L/2;
	}
	
	private void Atacabola(Vector<Robot> _home, double _x, double _y,Percept p)
	{
		X=bx-_home.elementAt(id-5).getPos().getX();
		Y=by-_home.elementAt(id-5).getPos().getY();
		double phi=Math.atan2(Y,X);
		X=_x-_home.elementAt(id-5).getPos().getX();
		Y=_y-_home.elementAt(id-5).getPos().getY();
		double alpha=Math.atan2(Y,X)-phi;
		phi=phi-alpha;
		double e=0;
		if( Math.abs(phi) <= PI/2)
		{
			e = phi - a;
		}
		else
		{
			if(phi < 0)
				phi = 2*PI + phi;
			if(a < 0)
				a = 2*PI + a;
			e = phi - a;
		}
		//Fin Prueba
		double de = e - eant;
		eant=e;
		double w = Kp*e + Kd*de;
		vleft = v - w*(L/2);
		vright = v + w*(L/2); 
	}

}
