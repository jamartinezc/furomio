/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FutbolSimul;

import FutbolSimul.entities.SimulAgent;
import FutbolSimul.entities.SimulBall;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


/**
 *
 * @author ZTIPHEN
 */
class SimulDrawer extends Canvas  {
    
    int x1[] = new int[3];
    int y1[] = new int[3];
    int x2[] = new int[6];
    int y2[] = new int[6];
    
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
        private SimulAgent Jugador[];
        private BufferStrategy baseBuffer;
        private boolean run=false;
        private Graphics g;
        public int golesEquipo1=0, golesEquipo2=0;

    public void setJugador(SimulAgent[] Jugador) {
        this.Jugador = Jugador;
    }
    
    public void startActiveRendering(Thread thread) {
        this.createBufferStrategy(4);
	this.baseBuffer = this.getBufferStrategy();
	
        
    }
    
    public void setBola(SimulBall bola) {
        this.bola = bola;
    }
        private SimulBall bola;
    public SimulDrawer(int x, int y)
    {
        super();
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
                
                this.setPreferredSize(new Dimension(900, 700));
     
     
    }
    
   public void init() {
        this.g = baseBuffer.getDrawGraphics();
        this.run = true;
    }
    
    public void paint() {
        if (this.run) {
		dibujarCancha(g);
		dibujarJugadores(g);		
		dibujarPelota(g);
                
        }
                this.baseBuffer.show();
                
	}
	
    public void dibujarCancha(Graphics g){
		
        //parte externa
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, 900, 700);
                g.setColor(Color.BLACK);
		g.drawRect((extraCuadroAncho)*relacion, 0, (int)((ancho)*relacion), (int)alto*relacion);
		//entrada de gol
		g.drawRect(0, (int)((alto/2-extraCuadroAlto/2)*relacion), (int)extraCuadroAncho*relacion, (int)extraCuadroAlto*relacion);
		g.drawRect((int)((ancho+extraCuadroAncho)*relacion), (int)((alto/2-extraCuadroAlto/2)*relacion), extraCuadroAncho*relacion, extraCuadroAlto*relacion);
		//zona A
		g.drawRect((int)(extraCuadroAncho*relacion), (int)((alto/2-zonaAAlto/2)*relacion), zonaAAncho*relacion, zonaAAlto*relacion);
		g.drawRect((int)((ancho-zonaAAncho+extraCuadroAncho)*relacion), (int)((alto/2-zonaAAlto/2)*relacion) , (zonaAAncho*relacion), (int)(zonaAAlto*relacion));
		//penalty area
		g.drawRect((int)(extraCuadroAncho*relacion), (int)((alto/2-zonaPenaltyAlto/2)*relacion), zonaPenaltyAncho*relacion, zonaPenaltyAlto*relacion);
		g.drawRect((int)((ancho-zonaPenaltyAncho+extraCuadroAncho)*relacion), (int)((alto/2-zonaPenaltyAlto/2)*relacion) , (zonaPenaltyAncho*relacion), (int)(zonaPenaltyAlto*relacion));
		//linea central
		g.drawLine((int)(relacion*(ancho/2+extraCuadroAncho)), 0, (int)(relacion*(ancho/2+extraCuadroAncho)), (int)(relacion*alto));
		//circulo central
		g.drawOval((int)(relacion*(ancho/2+extraCuadroAncho-diametroCentro/2)), (int)(relacion*(alto/2-diametroCentro/2)), (int)(relacion*diametroCentro), (int)(relacion*diametroCentro));
                g.drawString("Goles:", 30, (int)(relacion*alto+20));
                g.drawString(new Integer(golesEquipo1).toString(), 65, (int)(relacion*alto+20));
                g.drawString("Goles:", (int)(relacion*ancho-15), (int)(relacion*alto+20));
                g.drawString(new Integer(golesEquipo2).toString(), (int)(relacion*ancho+20), (int)(relacion*alto+20));
	}
	
    public void dibujarJugadores(Graphics g){		
		for(int i=0;i<=9;i++){
			//g.setColor(Color.BLACK);
                    //eje
			/*g.drawLine((int)(relacion*(Jugador[i].devolverX()-Jugador[i].devolverl()*Math.cos(Jugador[i].devolverDireccion())/2)),(int)(relacion*(Jugador[i].devolverY()+Jugador[i].devolverl()*Math.sin(Jugador[i].devolverDireccion())/2)),(int)(relacion*(Jugador[i].devolverX()+Jugador[i].devolverl()*Math.cos(Jugador[i].devolverDireccion())/2)),(int)(relacion*(Jugador[i].devolverY()-Jugador[i].devolverl()*Math.sin(Jugador[i].devolverDireccion())/2)));
			
                        //ruedas		
			g.drawLine((int)(relacion*Jugador[i].devolverArribaDerRuedaX()),(int)(relacion*Jugador[i].devolverArribaDerRuedaY()),(int)(relacion*Jugador[i].devolverAbajoDerRuedaX()),(int)(relacion*Jugador[i].devolverAbajoDerRuedaY()));
			g.drawLine((int)(relacion*Jugador[i].devolverArribaIzqRuedaX()),(int)(relacion*Jugador[i].devolverArribaIzqRuedaY()),(int)(relacion*Jugador[i].devolverAbajoIzqRuedaX()),(int)(relacion*Jugador[i].devolverAbajoIzqRuedaY()));
			//cuerpo
			g.drawLine((int)(relacion*Jugador[i].devolverArribaDerX()),(int)(relacion*Jugador[i].devolverArribaDerY()),(int)(relacion*Jugador[i].devolverAbajoDerX()),(int)(relacion*Jugador[i].devolverAbajoDerY()));
                        g.drawLine((int)(relacion*Jugador[i].devolverArribaIzqX()),(int)(relacion*Jugador[i].devolverArribaIzqY()),(int)(relacion*Jugador[i].devolverAbajoIzqX()),(int)(relacion*Jugador[i].devolverAbajoIzqY()));
			g.drawLine((int)(relacion*Jugador[i].devolverArribaDerX()),(int)(relacion*Jugador[i].devolverArribaDerY()),(int)(relacion*Jugador[i].devolverArribaIzqX()),(int)(relacion*Jugador[i].devolverArribaIzqY()));
			g.drawLine((int)(relacion*Jugador[i].devolverAbajoIzqX()),(int)(relacion*Jugador[i].devolverAbajoIzqY()),(int)(relacion*Jugador[i].devolverAbajoDerX()),(int)(relacion*Jugador[i].devolverAbajoDerY()));
			//cubiertas
			//g.drawLine((int)(relacion*(Jugador[i].devolverArribaDerX()-Jugador[i].devolverl()/3)),(int)(relacion*(Jugador[i].devolverArribaDerY()-Jugador[i].devolverl()/3)),(int)(relacion*(Jugador[i].devolverAbajoDerX()+Jugador[i].devolverl()/3)),(int)(relacion*(Jugador[i].devolverAbajoDerY()+Jugador[i].devolverl()/3)));
			//g.drawLine(0,0,(int)(relacion*Jugador[i].devolverAbajoIzqX()),(int)(relacion*Jugador[i].devolverAbajoIzqY()));
            
			//Diagonal
			*/
			x2[0] = (int)(relacion*(Jugador[i].devolverArribaDerX()));
			y2[0] =(int)(relacion*(Jugador[i].devolverArribaDerY()));
			x2[1] = (int)(relacion*(Jugador[i].devolverAbajoDerX()+Jugador[i].devolverArribaDerX())/2);
			y2[1] = (int)(relacion*(Jugador[i].devolverAbajoDerY()+Jugador[i].devolverArribaDerY())/2);
			x2[2] = (int)(relacion*(Jugador[i].devolverAbajoIzqX()+Jugador[i].devolverAbajoDerX())/2);
			y2[2] = (int)(relacion*(Jugador[i].devolverAbajoIzqY()+Jugador[i].devolverAbajoDerY())/2);
			x2[3] =(int)(relacion*Jugador[i].devolverAbajoIzqX());
			y2[3] = (int)(relacion*Jugador[i].devolverAbajoIzqY());
			x2[4] = (int)(relacion*(Jugador[i].devolverAbajoIzqX()+Jugador[i].devolverArribaIzqX())/2);
			y2[4] = (int)(relacion*(Jugador[i].devolverAbajoIzqY()+Jugador[i].devolverArribaIzqY())/2);
			x2[5] = (int)(relacion*(Jugador[i].devolverArribaIzqX()+Jugador[i].devolverArribaDerX())/2);
			y2[5] = (int)(relacion*(Jugador[i].devolverArribaIzqY()+Jugador[i].devolverArribaDerY())/2);            
            
			if(i<=4)                     
            	g.setColor(Color.BLUE);
            else
            	g.setColor(Color.YELLOW);
            g.fillPolygon(x2,y2,6);

            g.setColor(Color.BLACK);
            // g.drawLine((int)(relacion*(Jugador[i].devolverX()-Jugador[i].devolverl()*Math.sin(Jugador[i].devolverDireccion())/2)),(int)(relacion*(Jugador[i].devolverY()+Jugador[i].devolverl()*Math.cos(Jugador[i].devolverDireccion())/2)),(int)(relacion*(Jugador[i].devolverX()+Jugador[i].devolverl()*Math.sin(Jugador[i].devolverDireccion())/2)),(int)(relacion*(Jugador[i].devolverY()-Jugador[i].devolverl()*Math.cos(Jugador[i].devolverDireccion())/2)));
            if(i==0 || i==9)
            	g.setColor(Color.WHITE);
            else if(i==1 || i==8)
            	g.setColor(Color.GREEN);
            else if(i==2 || i==7)
            	g.setColor(Color.ORANGE);
            else if(i==3 || i==6)
            	g.setColor(Color.GRAY);
            else if(i==4 || i==5)
            	g.setColor(Color.PINK);
            
            x1[0] = (int)(relacion*(Jugador[i].devolverAbajoDerX()+Jugador[i].devolverArribaDerX())/2);
			y1[0] = (int)(relacion*(Jugador[i].devolverAbajoDerY()+Jugador[i].devolverArribaDerY())/2);
			x1[1] = (int)(relacion*Jugador[i].devolverAbajoDerX());
            y1[1] = (int)(relacion*Jugador[i].devolverAbajoDerY());
            x1[2] = (int)(relacion*(Jugador[i].devolverAbajoIzqX()+Jugador[i].devolverAbajoDerX())/2);
			y1[2] = (int)(relacion*(Jugador[i].devolverAbajoIzqY()+Jugador[i].devolverAbajoDerY())/2);;
			            
            
            g.fillPolygon(x1,y1,3);

            g.setColor(Color.RED);

            x1[0] =(int)(relacion*Jugador[i].devolverArribaIzqX());
            y1[0] = (int)(relacion*Jugador[i].devolverArribaIzqY());
            x1[1] = (int)(relacion*(Jugador[i].devolverAbajoIzqX()+Jugador[i].devolverArribaIzqX())/2);
            y1[1] = (int)(relacion*(Jugador[i].devolverAbajoIzqY()+Jugador[i].devolverArribaIzqY())/2);
            x1[2] = (int)(relacion*(Jugador[i].devolverArribaIzqX()+Jugador[i].devolverArribaDerX())/2);
            y1[2] = (int)(relacion*(Jugador[i].devolverArribaIzqY()+Jugador[i].devolverArribaDerY())/2);            

            g.fillPolygon(x1,y1,3);


		}
    }
	
    public void dibujarPelota(Graphics g){
		//pone que la pinte color naranja
		g.setColor(Color.ORANGE);
		//pinta un circulo relleno con las posiciones determinadas y el radio dado

		g.fillOval((int)(relacion*(bola.devolverX()-bola.devolverRadio())), (int)(relacion*(bola.devolverY()-bola.devolverRadio())), (int)(relacion*(bola.devolverRadio()*2)), (int)(relacion*(bola.devolverRadio()*2)));
		//vuelve a poner el color por defecto en negro		
		g.setColor(Color.BLACK);
	}
	
	
}
