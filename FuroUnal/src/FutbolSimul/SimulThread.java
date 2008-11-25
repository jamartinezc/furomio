package FutbolSimul;
import java.awt.image.BufferedImage;
import java.sql.Time;
import java.util.Date;

import Unalcolteam.Ball;
import Unalcolteam.RoboEnviroment;
import unalcol.agents.Environment;
//esta clase esta siempre corriendo mientras el programa funcione

public class SimulThread extends Thread{
	public SimulEnviroment env;
    public SimulDrawer e;
	public RoboEnviroment logicenv;
	public double delay;
	public double vel[];
	public double vel1, vel2;
	public int bandera;
	//cuando se crea recibe un enviroment y un delay y los asigna como variables de la clase
	public SimulThread( RoboEnviroment _logenv ,SimulEnviroment _env, double _delay, SimulDrawer _e ){
		env = _env;
		delay = _delay;
		vel = new double[32];
		logicenv=_logenv;
                e = _e;

	}

	//esta es la funcion que se ejecuta todo el tiempo
	public void run(){
                e.init();
		//este while es para que nunca se detenga
		while(true){
			
			logicenv.update();
					
			env.moversimu(vel);	
							
			e.paint();

			//aca se deja de correr el programa durante $delay milisegundos para volver a correr desde el principio del while
			
			try{
				this.sleep((long)delay);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}  

	//este metodo permite cambiar la velocidad de las variables vel1 y vel2 de esta clase, estas dos variables deberian ser un vector que contenga id vel1 vel2 para todos los robots
	
        
	public void cambiarVel(double vel1,double vel2){
		this.vel1=vel1;
		this.vel2=vel2;
	}
	//este metodo permite cambiar la velocidad de las variables vel1 y vel2 de esta clase permitiendo seleccionar el id del robot aunque aun no lo haga, estas dos variables deberian ser un vector que contenga id vel1 vel2 para todos los robots
	public void cambiarVel(double id, double vel1,double vel2){	  
		this.vel1=vel1;
		this.vel2=vel2;
	}

	public double[] getVel() {
		return vel;
	}

	public void setVel(double[] vel) {
		this.vel = vel;
	}
}
