package FutbolSimul;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Estrategias.*;
import Unalcolteam.*;
import unalcol.agents.*;


/**furo v 0.3**/

public class Ejecutable extends JFrame {
	
	static RoboEnviroment arquitectura; 
	static JTextField izquierda;
	static JTextField derecha;
	static JButton boton;
	static JPanel sur;
	static JFrame frame;
        private JPanel panel;
    private SimulDrawer amb = new SimulDrawer(1000,400); 
    private SimulEnviroment ambiente = new SimulEnviroment(amb);
	public static SimulThread thread;
	
	public Ejecutable() {
            panel = (JPanel)this.getContentPane();
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            arquitectura=new RoboEnviroment(crearagentes(),new SimulSensor(ambiente));
            thread = new SimulThread(arquitectura,ambiente, 16, amb);
            thread.cambiarVel(0,0 );
            panel.add(this.amb, BorderLayout.CENTER);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            //realizeMenu();
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.amb.startActiveRendering(thread);
            thread.start();
            
            //
            
           /*
            boton.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent e) {			
				thread.cambiarVel(Double.parseDouble(izquierda.getText()), Double.parseDouble(derecha.getText()));
			}			
        });
            
        sur = new JPanel();
        sur.setLayout(new BorderLayout(15,15));
		sur.add("West",izquierda);
		sur.add("East",derecha);        
		sur.add("South",boton);		
		frame.add( "Center",amb);	
        frame.add("South",sur);
        frame.setVisible(true);
        */
        
        
    }	
	
        
        public static void main(String[] args) {	
		
		new Ejecutable();       
        
        
	}
	
	public static double[] armarvector(String s){
		String a[]=s.split(" ");
		double b[]=new double[a.length]; 
		for(int i=1;i<a.length;i++)
			b[i]=Double.parseDouble(a[i]);
		return b;
	}
	
	
	public Percept sense(){
		Percept p=new Percept();
		return p;
	}
	
	public static Vector <Agent> crearagentes(){
		Vector <Agent> ag = new Vector <Agent>();
		ag.add(new Agent(arquitectura,new AgentProgramArquero(0)));
		ag.add(new Agent(arquitectura,new AgentProgramDefensor(1)));
		ag.add(new Agent(arquitectura,new AgentProgramNostradamus(2)));
		ag.add(new Agent(arquitectura,new AgentProgramDefensorD(3)));
		ag.add(new Agent(arquitectura,new AgentProgramDefensorI(4)));
		ag.add(new Agent(arquitectura,new AgentProgramOpponentDef(5)));
		ag.add(new Agent(arquitectura,new AgentProgramOpponentDef(6)));
		ag.add(new Agent(arquitectura,new AgentProgramOpponentAtt(7)));
		ag.add(new Agent(arquitectura,new AgentProgramOpponentAtt(8)));
		ag.add(new Agent(arquitectura,new AgentProgramOpponentArq(9)));
		return ag;
	}
		
		
		
		
		
	
	public void act(Action a){
		String v[]=a.getCode().split(" ");
		for(int i=0;i<11;i++){
			thread.cambiarVel(Double.parseDouble(v[i*3]),Double.parseDouble(v[1+i*3]), Double.parseDouble(v[2+i*3]));
		}
	}
	
	public void cambiar(String velocidades){
		String v[]=velocidades.split(" ");
		thread.cambiarVel(Double.parseDouble(v[1]), Double.parseDouble(v[2]));		
	}	
	
	
}
