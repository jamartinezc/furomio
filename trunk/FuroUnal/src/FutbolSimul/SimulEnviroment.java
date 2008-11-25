package FutbolSimul;
import FutbolSimul.entities.SimulAgent;
import FutbolSimul.entities.SimulBall;
import FutbolSimul.entities.SimulWall;
import FutbolSimul.physics.Collision;
import FutbolSimul.physics.PhysicsObject;
import FutbolSimul.physics.Vector2D;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;
import javax.swing.JFrame;

import Unalcolteam.*;
import java.util.ArrayList;
import java.util.Collection;
/**
 * 
 * @author Peter
 *
 */
public class SimulEnviroment{
		
	//objetos
	public int bandera;
	SimulAgent Jugador[];
	SimulBall bola;
	SimulDrawer drawer;
        
	//datos cancha
	double alto;
	double ancho;
	int relacion;
	int extraCuadroAncho;
	int extraCuadroAlto;
	int zonaAAlto;
	int zonaAAncho;
	int zonaPenaltyAlto;
	int zonaPenaltyAncho;
	int diametroCentro;
	double velocidadBolaX=10, velocidadBolaY=10, velocidadBola=10;

    private double dtime=16;//delta de t, tiempo que se mueve el simulador en cada paso
    private double tol;//Tolerance: intervalo de tiempo minimo para buscar un choque.
	/**
	 *devuelve los objetos del equipo de la izquierda	 
	 */	
	public Vector <Robot> posicioneshome(){
		Vector <Robot> home=new Vector<Robot>();
		for(int i=0;i<=4;i++){			
			home.add(new Robot(new Vector3D(Jugador[i].devolverX(),180-Jugador[i].devolverY()),Jugador[i].devolverDireccion()-(Math.PI/2)));
		}
		return home;
	}
	/**
	 *devuelve los objetos del equipo de la derecha
	 */
	public Vector <Robot> posicionesop(){
		Vector <Robot> op=new Vector<Robot>() ;
		for(int i=5;i<=9;i++){
			op.add(new Robot(new Vector3D(Jugador[i].devolverX(),180-Jugador[i].devolverY()),Jugador[i].devolverDireccion()-(Math.PI/2)));
		}
		return op;
	}
	
	/**
	 * recibe el alto y ancho del canvas que se quiere dibujar
	 */
	public SimulEnviroment(SimulDrawer drawer) {
		
		this.drawer= drawer;
		//datos cancha grande
		/*alto=280;
		ancho=400;		
		relacion = 2;
		extraCuadroAncho=20;
		extraCuadroAlto=60;
		zonaAAlto=80;
		zonaAAncho=24;
		zonaPenaltyAlto=120;
		zonaPenaltyAncho=60;
		diametroCentro=75;*/
		
		//datos cancha mediana
		alto=180;
		ancho=220;
		relacion = 2;
		extraCuadroAncho=15;
		extraCuadroAlto=40;
		zonaAAlto=50;
		zonaAAncho=15;
		zonaPenaltyAlto=80;
		zonaPenaltyAncho=35;
		diametroCentro=50;
		
		//datos objetos
		int l=15;
		int radioRuedas=6;
		double radioBola=2.135;
		
		//ubica y crea los robots en unas posiciones medio servibles 
		Jugador=new SimulAgent[11];
		//equipo izq
		Jugador[0] = new SimulAgent(extraCuadroAncho+ ancho/12,alto/2,l,Math.PI/2,radioRuedas);
		Jugador[1] = new SimulAgent(extraCuadroAncho+ancho*3/12,alto/4,l,0,radioRuedas);
		Jugador[2] = new SimulAgent(extraCuadroAncho+ancho*3/12,3*alto/4,l,Math.PI/4,radioRuedas);
		Jugador[3] = new SimulAgent(extraCuadroAncho+ancho*5/12,alto/4,l,Math.PI/2,radioRuedas);
		Jugador[4] = new SimulAgent(extraCuadroAncho+ancho*5/12,3*alto/4,l,3*Math.PI/4,radioRuedas);
		//equipo der
		Jugador[5] = new SimulAgent(extraCuadroAncho+ancho*7/12,alto/4,l,-Math.PI/2,radioRuedas);
		Jugador[6] = new SimulAgent(extraCuadroAncho+ancho*7/12,3*alto/4,l,-Math.PI/2,radioRuedas);
		Jugador[7] = new SimulAgent(extraCuadroAncho+ancho*9/12,alto/4,l,-Math.PI/2,radioRuedas);
		Jugador[8] = new SimulAgent(extraCuadroAncho+ancho*9/12,3*alto/4,l,-Math.PI/2,radioRuedas);
		Jugador[9] = new SimulAgent(extraCuadroAncho+ancho*11/12,alto/2,l,-Math.PI/2,radioRuedas);		
		
		for(int i=0;i<10;i++){
			Jugador[i].calcularVertices();
		}
		
		//ubica la pelota en el centro y le asigna un angulo de movimiento
		bola = new SimulBall(ancho/2+extraCuadroAncho,alto/2,Math.random()*2*Math.PI,radioBola);
        this.drawer.setBola(bola);
        this.drawer.setJugador(Jugador);
		//bola = new Pelota(0,0,0,1);
		//reshape(0, 0, 1000, 1000);
	}
	
	public double moversimu(double vel[]){

        double step = dtime;
        double advancedTime = 0;

        //asignar las nuevas velocidades de las llantas
        for(int i = 0; i < vel.length; i=i+3){
            Jugador[i].setLWvel(vel[i+1]);//   /100?
            Jugador[i].setRWvel(vel[i+2]);//   /100?
        }

        SimulAgent backupJugador[] = new SimulAgent[Jugador.length];
        SimulBall backupBola;


        while(advancedTime < dtime){
            //guardar el estado actual de los cuerpos
            for(int i = 0; i < Jugador.length; i++){ 
                backupJugador[i] = Jugador[i].clone();
            }
            backupBola = bola.clone();
            //fin de backup de cuerpos

            moveObjects(Jugador, bola, step);
            Collision[] collisions = findCollisions(Jugador, bola);
            if( collisions.length > 0 ){//si hay colisiones
                //Revertir cuerpos a su estado original
                for(int i = 0; i < Jugador.length; i++){
                    Jugador[i] = backupJugador[i].clone();
                }
                bola = backupBola.clone();
                //fin revertir cuerpos

                if(step <= tol){//si es verdadero, se a encontrado el instante de la colision
                    handleCollisions(collisions);
                    step = dtime - advancedTime;
                }
            }else{//no hay colisiones
                advancedTime += step;
            }
            if(step >= tol){
                step = step / 2;
            }
        }
        return advancedTime;
	}

    private double getElasticity(PhysicsObject bodyA, PhysicsObject bodyB) {
        if(bodyA.getClass() == SimulAgent.class){
            if(bodyB.getClass() == SimulAgent.class){
                return 0.8;
            }else if(bodyB.getClass() == SimulBall.class){
                return 0.8;
            }else if(bodyB.getClass() == SimulWall.class){
                return 0.8;
            }else{
                throw new IllegalArgumentException("elasticity not found");
            }
        }else if(bodyA.getClass() == SimulBall.class){
            if(bodyB.getClass() == SimulAgent.class){
                return 0.8;
            }else if(bodyB.getClass() == SimulWall.class){
                return 0.8;
            }else{
                throw new IllegalArgumentException("elasticity not found");
            }
        }else if(bodyA.getClass() == SimulWall.class){
            if(bodyB.getClass() == SimulAgent.class){
                return 0.8;
            }else if(bodyB.getClass() == SimulBall.class){
                return 0.8;
            }else{
                throw new IllegalArgumentException("elasticity not found");
            }
        }else{
            throw new IllegalArgumentException("elasticity not found");
        }
    }

    private void handleCollisions(Collision[] collision) {
        for(int i = 0; i < collision.length; i++){
            //inicializar variables
            PhysicsObject bodyA = collision[i].getBody1();
            PhysicsObject bodyB = collision[i].getBody2();
            Vector2D rap = new Vector2D(collision[i].getColPoint().getX() - bodyA.getX(), collision[i].getColPoint().getY() - bodyA.getY());
            Vector2D rbp = new Vector2D(collision[i].getColPoint().getX() - bodyB.getX(), collision[i].getColPoint().getY() - bodyB.getY());
            double Ia = bodyA.getInertia();
            double Ib = bodyB.getInertia();
            double e = getElasticity(bodyA, bodyB);
            Vector2D n = collision[i].getN();//vector normal al choque
            Vector2D vap1 = bodyA.getVel().add( rap.scalarMultiply( bodyA.getAngularVel() ) );

            //hacer la operación: j = -(1 + e)*vap1*n/( 1/bodyA.getMass() + ((rap*n)^2)/Ia)
            double rapXn = rap.crossProduct(n);
            double j = -(vap1.scalarMultiply( 1+e ).dotProduct( n ))/( 1/bodyA.getMass() + Math.pow(rapXn,2)/Ia );
            //FIN de inicializar variables

            //Calcular nuevas velocidades (lineales y angulares) para bodyA y bodyB
            double Wa2 = bodyA.getAngularVel() + ( rap.crossProduct( n.scalarMultiply(j) ) )/Ia;
            double Wb2 = bodyB.getAngularVel() + ( rbp.crossProduct( n.scalarMultiply(j) ) )/Ib;

            Vector2D va2 = bodyA.getVel().add( n.scalarMultiply( j/bodyA.getMass() ) );
            Vector2D vb2 = bodyB.getVel().add( n.scalarMultiply( j/bodyB.getMass() ) );

            bodyA.setVel(va2);
            bodyB.setVel(vb2);

            bodyA.setAngularVel(Wa2);
            bodyB.setAngularVel(Wb2);
        }

    }

    private void moveObjects(SimulAgent[] players, SimulBall ball, double timeStep){

        for(int i = 0; i < players.length; i++){
            double x = players[i].getX();
            double y = players[i].getY();

            //Calcular Aceleracion
            Vector2D vro = new Vector2D( players[i].getAngularVel() * players[i].getRadio() , players[i].getDireccion());//velocidad en la direccion de las llantas, del punto con la llanta derecha del agente
            Vector2D vlo = new Vector2D(-vro.getModule(), players[i].getDireccion());//velocidad en la direccion de las llantas, del punto con la llanta izquierda del agente
            Vector2D vr = players[i].getVel().add(vro);//velocidad total = (velocidad provocada por el giro) + (velocidad lineal del agente)
            Vector2D vl = players[i].getVel().add(vlo);
            //con la direccion de la velocidad real de cada punto, podemos saber la direccion de la fuerza de friccion
            Vector2D ffr = new Vector2D( -players[i].getMass()/2, vr.getAngle() );
            Vector2D ffl = new Vector2D( -players[i].getMass()/2, vl.getAngle() );

            //Fuerza generada por las llantas
            Vector2D frw = new Vector2D( players[i].getPow()/players[i].getRWvel()/players[i].getWRad(),  players[i].getDireccion() );
            Vector2D flw = new Vector2D( players[i].getPow()/players[i].getLWvel()/players[i].getWRad(),  players[i].getDireccion() );

            //sumar fuerzas
            double r = players[i].getRadio();
            Vector2D totalForce = ffr.scalarMultiply( r );
            totalForce = totalForce.add( ffl.scalarMultiply( r ) );
            totalForce = totalForce.add( frw.scalarMultiply( r ) );
            totalForce = totalForce.add( flw.scalarMultiply( r ) );

            players[i].setAcel( totalForce.scalarMultiply( 1/players[i].getMass() ) );//a = Ftot/m

            //calcular torque
            double Tr = frw.scalarMultiply( r ).add( ffr.component( players[i].getDireccion() ).scalarMultiply( r ) ).getModule();
            double Tl = flw.scalarMultiply( r ).add( ffl.component( players[i].getDireccion() ).scalarMultiply( r ) ).getModule();
            double T = Tr + Tl;

            players[i].setAngularAcel( T/players[i].getInertia() );//alpha = T / Inertia

            //FIN calcular Aceleración


            //Calcular posición
            x += players[i].getVel().getX() + 0.5*( players[i].getAcel().getX() * Math.pow(timeStep, 2) );
            y += players[i].getVel().getY() + 0.5*( players[i].getAcel().getY() * Math.pow(timeStep, 2) );
            players[i].setX(x);
            players[i].setY(y);

            //calcular rumbo (direccion del agente)
            double theta = players[i].getDireccion();
            theta += players[i].getAngularVel() + 0.5*( players[i].getAngularAcel() * Math.pow(timeStep, 2) );
            players[i].setDireccion(theta);

            //Calcular Velocidad lineal
            players[i].setVel( players[i].getVel().add( players[i].getAcel().scalarMultiply( timeStep ) ) );//V = Vo + a*t
            //Calcular Velocidad angular
            players[i].setAngularVel( players[i].getAngularVel() + players[i].getAngularAcel() * timeStep );
        }

        // TODO calcular el nuevo estado de la bola
        double x = ball.getX();
        double y = ball.getY();

        //Calcular Velocidad
        //Calcular Aceleracion

        x += ball.getVel().getX() + 0.5*( ball.getAcel().getX() * Math.pow(timeStep, 2) );
        y += ball.getVel().getY() + 0.5*( ball.getAcel().getY() * Math.pow(timeStep, 2) );
    }

    private Collision[] findCollisions(SimulAgent[] players, SimulBall ball){
        ArrayList collisions = new ArrayList();
        for(int i = 0; i < players.length; i++){//para cada jugador
            for(int j = 0; j < 4; j++){//para cada esquina
                for(int k = 0; k < players.length; k++){//para cada otro jugador
                    if(k != j){//garantiza que sean los otros
                        if(players[k].contains(players[i].getCorner(i))){//si hay una colision con esa esquina
                            //añadir colision
                            Collision coll = new Collision();
                            coll.setBody1(players[i]);
                            coll.setBody2(players[k]);
                            coll.setColPoint( players[i].getCorner(i) )
                            // TODO añadir mas datos de la coslision
                        }
                    }
                }
            }
        }
        // TODO añadir colisiones con las paredes
        // TODO ingresar las colisiones encontradas
        return (Collision[])(collisions.toArray());
    }
	
	public void moverjugadores(double vel[]){
		for(int j=0;j<=9;j++){
			double derecha = vel[j*3+3]/100.0;
			double izquierda = vel[j*3+2]/100.0;
			if(vel[j*3+3]>0.01){
				double aux=vel[j*3+3];
				vel[j*3+3]=vel[j*3+3]/aux;
				vel[j*3+2]=vel[j*3+2]/aux;
			}
			
			//revisa choques del agente j con la cancha
			double tiempo = 0.1;		
			double L = tiempo*izquierda*Jugador[j].devolverRadio();
			double D = tiempo*derecha*Jugador[j].devolverRadio();		
			double mod = (D+L)/2;
			double angulo = (D-L)/Jugador[j].devolverl();		
			Jugador[j].setDireccion(Jugador[j].getDireccion() + angulo);
			Jugador[j].setDireccion(Jugador[j].getDireccion() % (2 * Math.PI));
			if((0<(Jugador[j].devolverY()+mod*Math.cos(Jugador[j].getDireccion())-Jugador[j].devolverl()/2))&&(180>(Jugador[j].devolverY()+mod*Math.cos(Jugador[j].getDireccion())+Jugador[j].devolverl()/2))){
				if((20<(Jugador[j].devolverX()+mod*Math.sin(Jugador[j].getDireccion())-Jugador[j].devolverl()/2))&&(280+20>(Jugador[j].devolverX() + mod*Math.sin(Jugador[j].getDireccion())+Jugador[j].devolverl()/2))){
					moverJugador(j,mod);
				}
			}
			Jugador[j].calcularVertices();
		}
	}
	
	//Detectar si habr� choque
	public boolean habrachoque(){
		/*if(revisarGolpep())
			return true;*/
		return false;
	}
		
	//revisa golpes entre el jugador(i) y todos los demas agentes (solo por los vertices del jugador(i))
	//solo funciona para agentes ubicados con angulo k*pi/2
	public boolean revisarGolpeb(int i, double derecha, double izquierda){
		int v[]= new int [100];
        //revisa choques del agente i con la cancha                
        for(int j=0;j<=0;j++){
			if(i!=j){
				v[j*4]=Math.abs(Jugador[j].devolverAbajoDerX()-Jugador[i].devolverAbajoDerX());
				v[j*4+1]=Math.abs(Jugador[j].devolverAbajoDerY()-Jugador[i].devolverAbajoDerY());
				v[j*4+2]=Math.abs(Jugador[j].devolverArribaIzqX()-Jugador[i].devolverAbajoDerX());
				v[j*4+3]=Math.abs(Jugador[j].devolverArribaIzqY()-Jugador[i].devolverAbajoDerY());
				if(v[j*4]<=Jugador[i].devolverl()&&v[j*4+1]<=Jugador[i].devolverl()&&v[j*4+2]<Jugador[i].devolverl()&&v[j*4+3]<Jugador[i].devolverl()){
					//System.out.print("true\n");
					return true;
				}

				v[j*4]=Math.abs(Jugador[j].devolverAbajoDerX()-Jugador[i].devolverAbajoIzqX());
				v[j*4+1]=Math.abs(Jugador[j].devolverAbajoDerY()-Jugador[i].devolverAbajoIzqY());
				v[j*4+2]=Math.abs(Jugador[j].devolverArribaIzqX()-Jugador[i].devolverAbajoIzqX());
				v[j*4+3]=Math.abs(Jugador[j].devolverArribaIzqY()-Jugador[i].devolverAbajoIzqY());
				if(v[j*4]<=Jugador[i].devolverl()&&v[j*4+1]<=Jugador[i].devolverl()&&v[j*4+2]<Jugador[i].devolverl()&&v[j*4+3]<Jugador[i].devolverl()){
					//System.out.print("true\n");
					return true;
				}

				v[j*4]=Math.abs(Jugador[j].devolverAbajoDerX()-Jugador[i].devolverArribaDerX());
				v[j*4+1]=Math.abs(Jugador[j].devolverAbajoDerY()-Jugador[i].devolverArribaDerY());
				v[j*4+2]=Math.abs(Jugador[j].devolverArribaIzqX()-Jugador[i].devolverArribaDerX());
				v[j*4+3]=Math.abs(Jugador[j].devolverArribaIzqY()-Jugador[i].devolverArribaDerY());
				if(v[j*4]<=Jugador[i].devolverl()&&v[j*4+1]<=Jugador[i].devolverl()&&v[j*4+2]<Jugador[i].devolverl()&&v[j*4+3]<Jugador[i].devolverl()){
					//System.out.print("true\n");
					return true;
				}

				v[j*4]=Math.abs(Jugador[j].devolverAbajoDerX()-Jugador[i].devolverArribaIzqX());
				v[j*4+1]=Math.abs(Jugador[j].devolverAbajoDerY()-Jugador[i].devolverArribaIzqY());
				v[j*4+2]=Math.abs(Jugador[j].devolverArribaIzqX()-Jugador[i].devolverArribaIzqX());
				v[j*4+3]=Math.abs(Jugador[j].devolverArribaIzqY()-Jugador[i].devolverArribaIzqY());
				if(v[j*4]<=Jugador[i].devolverl()&&v[j*4+1]<=Jugador[i].devolverl()&&v[j*4+2]<Jugador[i].devolverl()&&v[j*4+3]<Jugador[i].devolverl()){
					//System.out.print("true\n");
					return true;
				}
			}
			//System.out.print(v[j*4]+" , "+v[j*4+1]+"\n"+v[j*4+2]+" , "+v[j*4+3]+"\n");
		}
		return false;
	}
	
	//revisa choques de la pelota con los robots
	///solo funciona para agentes ubicados con angulo k*pi/2
	public boolean revisarGolpep(){		
		//revisa los choques de la pelota con los muros
		int limiteSuperiorGol = (int)((drawer.alto/2-drawer.extraCuadroAlto/2));
		int limiteInferiorGol = limiteSuperiorGol + drawer.extraCuadroAlto;
		// System.out.println("sup:"+limiteSuperiorGol+" inf:"+limiteInferiorGol);

		double tiempo = 0.01;	
		double movimiento = tiempo*velocidadBola;
		//deteccion de choques con la cancha 
		if((0<(bola.devolverY()+movimiento*Math.cos(bola.getDireccion())-bola.devolverRadio()))&&(alto>(bola.devolverY()+movimiento*Math.cos(bola.getDireccion())+bola.devolverRadio()))){
			if(!(((extraCuadroAncho<(bola.devolverX()+movimiento*Math.sin(bola.getDireccion())-bola.devolverRadio()))&&(ancho+extraCuadroAncho>(bola.devolverX() + movimiento*Math.sin(bola.getDireccion())+bola.devolverRadio()))))){
				//si cuando golpeo en los muros lateales está en el area de gol, es gol
				if((limiteSuperiorGol<=(bola.devolverY()+movimiento*Math.cos(bola.getDireccion())-bola.devolverRadio()))&&(limiteInferiorGol>=(bola.devolverY()+movimiento*Math.cos(bola.getDireccion())+bola.devolverRadio()))){
					//comprobamos de que lado esta la bola para sumarle al equipo respectivo
					if (bola.devolverX()>(drawer.ancho/2)*drawer.relacion) {
						drawer.golesEquipo1++;
					} else {
						drawer.golesEquipo2++;
					}
					bola.reset();
				}				
				return true;
			}			
		}
		else{			
			return true;                        
		}                

		for(int j=0;j<=9;j++){
			double dist1,dist2;
			dist1 = Math.sqrt(Math.pow((Jugador[j].devolverAbajoDerX()-bola.devolverX()),2)+Math.pow((Jugador[j].devolverAbajoDerY()-bola.devolverY()),2));
			dist2 = Math.sqrt(Math.pow((Jugador[j].devolverArribaIzqX()-bola.devolverX()),2)+Math.pow((Jugador[j].devolverArribaIzqY()-bola.devolverY()),2));
			if(dist1<Jugador[j].devolverl()&&dist2<Jugador[j].devolverl()){
				return true;
			}
			dist1 = Math.sqrt(Math.pow((Jugador[j].devolverAbajoIzqX()-bola.devolverX()),2)+Math.pow((Jugador[j].devolverAbajoIzqY()-bola.devolverY()),2));
			dist2 = Math.sqrt(Math.pow((Jugador[j].devolverArribaDerX()-bola.devolverX()),2)+Math.pow((Jugador[j].devolverArribaDerY()-bola.devolverY()),2));
			if(dist1<Jugador[j].devolverl()&&dist2<Jugador[j].devolverl()){
				return true;
			}

			dist1 = Math.sqrt(Math.pow((Jugador[j].devolverX()-bola.devolverX()),2)+Math.pow((Jugador[j].devolverY()-bola.devolverY()),2));
			if(dist1<Jugador[j].devolverl()/2){
				return true;
			}
		}
		return false;
	}
	public void moverBola(double velocidad) {
		double tiempo=0.05;
		bola.cambiarX(tiempo*velocidad*Math.sin(bola.getDireccion()));
		bola.cambiarY(tiempo*velocidad*Math.cos(bola.getDireccion()));
	}
	public void moverJugador(int id, double mod) {
		Jugador[id].cambiarX(mod*Math.sin(Jugador[id].getDireccion()));
		Jugador[id].cambiarY(mod*Math.cos( Jugador[id].getDireccion()));
	}
}
