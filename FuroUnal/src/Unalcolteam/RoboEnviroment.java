package Unalcolteam;

import java.util.StringTokenizer;
import java.util.Vector;

import unalcol.agents.Action;
import unalcol.agents.Agent;
import unalcol.agents.Environment;
import unalcol.agents.Percept;
import unalcol.agents.Sensor;
import FutbolSimul.Ejecutable;

public class RoboEnviroment extends Environment
{
	private Sensor sense;
    private int roboready;
    private String message;
    
    private static final int PLAYERS_PER_SIDE=5;
    private static final int FREE_BALL=1;
    private static final int PLACE_KICK=2;
    private static final int FREE_KICK=3;
    private static final int GOAL_KICK=4;
    
    private static final int ANYONES_BALL=0;
    private static final int BLUE_BALL=1;//pensar en cambiar por home
    private static final int YELLOW_BALL=2;//pensar en cambiar por away
    
    private static final double FTOP = 77.2392;
    private static final double FBOT = 6.3730;
    private static final double GTOPY = 49.6801;
    private static final double GBOTY = 33.9320;
    private static final double GRIGHT = 97.3632;
    private static final double GLEFT = 2.8748;
    private static final double FRIGHTX = 93.4259;
    private static final double FLEFTX = 6.8118;
    private int[] sincronizador;
    private double[] mensaje;
    
    
	public RoboEnviroment(Vector<Agent> _agents,Sensor s) 
	{
		super(_agents);
		sense=s;
		sincronizador=new int[10];
		mensaje=new double[(PLAYERS_PER_SIDE*3)*2];
	}
	
	public void update()
	{
		//codigo de actualizacion del ambiente
		super.run();
		super.stop();
	}

	public synchronized boolean act(Agent agent, Action action) 
	{
		RoboAction aux2=(RoboAction)action;
		if(sincronizador[aux2.id]==0)
		{
		mensaje[aux2.id*3]=aux2.id;
		mensaje[(aux2.id*3)+1]=aux2.velocityleft;
		mensaje[(aux2.id*3)+2]=aux2.velocityright;
		roboready++;
		sincronizador[aux2.id]=1;
		//System.out.print(roboready+"\n");
		if(roboready==RoboEnviroment.PLAYERS_PER_SIDE*2)
			
		{
			for(int i=0;i<mensaje.length;i++)
				message=message+" "+mensaje[i];
			//System.out.print(message+"\n");
			for(int i=0;i<sincronizador.length;i++)
				sincronizador[i]=0;
			transmit(message);
			notify();
			
		}
		return false;
		}
		else
			return false;
		
	}

	private void transmit(String message2) 
	{
		//System.out.println(message2);//para probar la cadena
		Ejecutable.thread.setVel(Ejecutable.armarvector(message2));
		//StringTokenizer mesg =new StringTokenizer(message2);
		
		//Codigo para la transmision de la informacion
		//----
		//
		//
		roboready=0;
		message="";
	}

	public Vector<Action> actions() {
		return null;
	}

	public void init(Agent agent) {
		roboready=0;
		message="";
	}

	public Percept sense(Agent agent) 
	{
		Percept p = new Percept();
		p.setAttribute("id", sense.getSensation("id"));	
		p.setAttribute("ball", sense.getSensation("ball"));
		p.setAttribute("home", sense.getSensation("home"));
		p.setAttribute("opponent",sense.getSensation("opponent"));
	//	p.setAttribute("field",sense.getSensation("field"));
		//p.setAttribute("goal", sense.getSensation("goal"));
		return p;
	}

}
