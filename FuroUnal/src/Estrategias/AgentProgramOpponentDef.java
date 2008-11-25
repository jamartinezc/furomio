package Estrategias;

import java.util.Vector;

import Unalcolteam.Ball;
import Unalcolteam.Bounds;
import Unalcolteam.RoboAction;
import Unalcolteam.Robot;
import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;

/**
 * 
 * @author Personal
 *El agente siempre atacara la bola hacia el arco contrario
 */
public class AgentProgramOpponentDef implements AgentProgram
{

	public final static double PI=3.1415923;
	public final static double ox=165;//solo funciona para equipo a la Izquierda
	public final static double oy=90;//solo funciona para equipo a la Izquierda
	public final static double KVp=2;
	public final static double KVd=2;
	public final static double Kp=15;
	public final static double Kd=15;
	public final static double v=200;
	public final static double L=7.5;//pulgadas = 7.5 cm 2.94 pulg
	public final static double minerrorangle=0.01745;
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
	private int id;
	private Bounds AreaAccion;
	
	public Action compute(Percept p) 
	{
		//--------------------------------------
		// aqui va la estrategia que al final debe asignar valor a vleft y vright
		capturadatos(p);
		
		if(by<AreaAccion.getTop()&&by>AreaAccion.getBottom()&&bx<AreaAccion.getRight()&&bx>AreaAccion.getLeft())
		{	
			Atacabola((Vector<Robot>) p.getAttribute("opponent"),bx-10,by,p);	
		}
		else
		{
			if(Math.abs(((Vector<Robot>) p.getAttribute("opponent")).elementAt(id-5).getPos().getX()-ox)<5&&Math.abs(((Vector<Robot>) p.getAttribute("opponent")).elementAt(id-5).getPos().getY()-oy)<5)
			{
				vleft=0;
				vright=0;
			}
			else
			Regresa((Vector<Robot>)p.getAttribute("opponent"),ox,oy,ox-5,oy);
		}
		Yant=by;
		Xant=bx;
		//----------------------------------------fin de la estrategia
		RoboAction act=new RoboAction(id,vleft,vright);
		return act;
	}

	public AgentProgramOpponentDef(int _id)
	{
		init();
		id=_id;
	}
	public void init() 
	{
		AreaAccion=new Bounds(130,220,180,0);
		 eant=0;
		 Yant=0;
		 vleft=0;
		 vright=0;
		
	}
	
	private void capturadatos(Percept p)
	{
		Ball ball=(Ball)p.getAttribute("ball");
		bx=ball.getPos().getX();
		by=ball.getPos().getY();
		Vector<Robot> home=(Vector<Robot>)p.getAttribute("opponent");
		a=home.elementAt(id-5).getRotation();
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
	
	/**
	 * La función realiza un movimiento vertical en el agente para bloquear el paso de la bola
	 * @param _home Vector que contiene la informacion de los robots.
	 */
	private void Bloquea(Robot []_home)//movimiento vertical
	{
		double DelY=(by-Yant);
		double DelX=(bx-Xant);
		double d=Math.sqrt(Math.pow(DelY,2)+Math.pow(DelX,2));
		
		double e=-PI/2-a;
		double de=e-eant;
		eant=e;
		//--
		if(Math.abs(e)>minerrorangle)//si el agente esta desviado corrije posicion
		{
			double w=20*e+20*de;
			vleft=-w*L/2;
			vright=w*L/2;
		}
		else//si esta bien dirigido va a bloquear
		{
			if( d < 0.5 )
				d = 1;
			else
				d = d * 2;//aumenta la velocidad si la bola va muy rapido
			double DY=Y-Yant;
			Yant=Y;
			double w=KVp*Y*d+KVd*DY*d;
			vleft=-w;
			vright=-w;
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
	
	private void RaquetDerecha()
	{
		vleft=v;
		vright=-v;
	}
	
	private void RaquetIzquierda()
	{
		vleft=-v;
		vright=v;
	}
	
	
	private boolean comprobarlimites(Bounds limites,Robot robot)
	{
		if(robot.getPos().getX()>limites.getRight())
			return false;
		if(robot.getPos().getX()<limites.getLeft())
			return false;
		if(robot.getPos().getY()>limites.getTop())
			return false;
		if(robot.getPos().getY()<limites.getBottom())
			return false;
		return true;
	}
	
	private Vector<Integer> oponentesEnArea(Bounds limites,Robot[] _opponents)
	{
		Vector<Integer> info = new Vector<Integer>();
		for(int i=0;i<_opponents.length;i++)
		{
			if(comprobarlimites(limites,_opponents[i]))
			{
				info.add(i);
			}
		}
		return info;
	}
	
	private Vector<Integer> oponentesEnTrayectoria()
	{
		Vector<Integer> info = new Vector<Integer>();
		
		
		
		return info;
	}

}

