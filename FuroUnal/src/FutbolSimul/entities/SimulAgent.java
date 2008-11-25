package FutbolSimul.entities;

import FutbolSimul.physics.PhysicsObject;
import FutbolSimul.physics.Point;
import FutbolSimul.physics.Vector2D;

public class SimulAgent extends PhysicsObject{
	private double l;
	private double radio;
	private double vertices[];
    private double RWvel;
    private double LWvel;
    //caracteristicas de las llantas
    private double pow=0.5;//potencia del motor de las llantas del carro
    private double wRad=0.01;//metros

	public SimulAgent(double x,double y,double l,double direccion, double radio){
		this.x = x;
		this.y = y;
		this.l = l;
		this.radio = radio;
		this.angle = direccion;
		vertices = new double[8];		
	}
	/*
	//coordenadas puntos rueda
	public int devolverArribaIzqRuedaX(){
		return (int)(x+l*Math.cos(direccion)/2+radio*Math.sin(direccion));
		
	}
	public int devolverAbajoIzqRuedaX(){
		
		return (int)(x+l*Math.cos(direccion)/2-radio*Math.sin(direccion));
	}
	public int devolverAbajoDerRuedaX(){
		return (int)(x-l*Math.cos(direccion)/2-radio*Math.sin(direccion));
	}
	public int devolverArribaDerRuedaX(){
		return (int)(x-l*Math.cos(direccion)/2+radio*Math.sin(direccion));
	}
	
	public int devolverAbajoIzqRuedaY(){
		
		return (int)(y-l*Math.sin(direccion)/2-radio*Math.cos(direccion));
	}
	public int devolverArribaIzqRuedaY(){
		return (int)(y-l*Math.sin(direccion)/2+radio*Math.cos(direccion));
	}
	public int devolverAbajoDerRuedaY(){
		return (int)(y+l*Math.sin(direccion)/2-radio*Math.cos(direccion));
	}	
	public int devolverArribaDerRuedaY(){
		return (int)(y+l*Math.sin(direccion)/2+radio*Math.cos(direccion));
	}*/
	//coordenadas bordes
	public int devolverArribaIzqX(){
		return (int)getVertices()[0];
		//return (int)(x+l*Math.cos(direccion)/2+l/2*Math.sin(direccion));
	}
	public int devolverAbajoIzqX(){
		return (int)getVertices()[1];
		//return (int)(x+l*Math.cos(direccion)/2-l/2*Math.sin(direccion));
	}
	public int devolverAbajoDerX(){
		return (int)getVertices()[2];
		//return (int)(x-l*Math.cos(direccion)/2-l/2*Math.sin(direccion));
	}
	public int devolverArribaDerX(){
		return (int)getVertices()[3];
		//return (int)(x-l*Math.cos(direccion)/2+l/2*Math.sin(direccion));
	}
	
	
	public int devolverArribaIzqY(){
		return (int)getVertices()[4];
		//return (int)(y-l*Math.sin(direccion)/2+l/2*Math.cos(direccion));
	}	
	public int devolverAbajoIzqY(){		
		return (int)getVertices()[5];
		//return (int)(y-l*Math.sin(direccion)/2-l/2*Math.cos(direccion));
	}
	public int devolverAbajoDerY(){
		return (int)getVertices()[6];
		//return (int)(y+l*Math.sin(direccion)/2-l/2*Math.cos(direccion));
	}	
	public int devolverArribaDerY(){
		return (int)getVertices()[7];
		//return (int)(y+l*Math.sin(direccion)/2+l/2*Math.cos(direccion));
	}
        
    public void cambiarX(double velX){
		setX(getX() + velX);
	}
	public void cambiarY(double velY){
		setY(getY() + velY);
	}
	
	public void calcularVertices(){
		getVertices()[0] = getX()+getL()*Math.cos(getDireccion())/2+getL()/2*Math.sin(getDireccion());
		getVertices()[1] = getX()+getL()*Math.cos(getDireccion())/2-getL()/2*Math.sin(getDireccion());
		getVertices()[2] = getX()-getL()*Math.cos(getDireccion())/2-getL()/2*Math.sin(getDireccion());
		getVertices()[3] = getX()-getL()*Math.cos(getDireccion())/2+getL()/2*Math.sin(getDireccion());
		getVertices()[4] = getY()-getL()*Math.sin(getDireccion())/2+getL()/2*Math.cos(getDireccion());
		getVertices()[5] = getY()-getL()*Math.sin(getDireccion())/2-getL()/2*Math.cos(getDireccion());
		getVertices()[6] = getY()+getL()*Math.sin(getDireccion())/2-getL()/2*Math.cos(getDireccion());
		getVertices()[7] = getY()+getL()*Math.sin(getDireccion())/2+getL()/2*Math.cos(getDireccion());
		
	}
	
	
	
	public double devolverX(){
		return getX();
	}
	public double devolverY(){
		return getY();
	}
	public double devolverl(){
		return getL();
	}
	public double devolverRadio(){
		return getRadio();
	}
	public double devolverDireccion(){
		return getDireccion();
	}

    @Override
public SimulAgent clone(){
    SimulAgent clone = new SimulAgent(this.getX(), this.getY(), this.getL(), this.getDireccion(), this.getRadio());
    clone.setVertices(this.getVertices());

    return clone;
}

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the l
     */
    public double getL() {
        return l;
    }

    /**
     * @param l the l to set
     */
    public void setL(double l) {
        this.l = l;
    }

    /**
     * @return the direccion
     */
    public double getDireccion() {
        return angle;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(double direccion) {
        this.angle = direccion;
    }

    /**
     * @return the radio
     */
    public double getRadio() {
        return radio;
    }

    /**
     * @param radio the radio to set
     */
    public void setRadio(double radio) {
        this.radio = radio;
    }

    /**
     * @return the vertices
     */
    public double[] getVertices() {
        return vertices;
    }

    /**
     * @param vertices the vertices to set
     */
    public void setVertices(double[] vertices) {
        this.vertices = vertices;
    }

    @Override
    public double getInertia() {
        return FutbolSimul.physics.Inertia.cubeInertia(l, mass);
    }

    /**
     * @return the right weel velocity
     */
    public double getRWvel() {
        return RWvel;
    }

    /**
     * @param RWvel the right weel velocity to set
     */
    public void setRWvel(double RWvel) {
        this.RWvel = RWvel;
    }

    /**
     * @return the left weel velocity
     */
    public double getLWvel() {
        return LWvel;
    }

    /**
     * @param LWvel the left weel velocity to set
     */
    public void setLWvel(double LWvel) {
        this.LWvel = LWvel;
    }

    /**
     * @return the pow
     */
    public double getPow() {
        return pow;
    }

    /**
     * @param pow the pow to set
     */
    public void setPow(double pow) {
        this.pow = pow;
    }

    /**
     * @return the wRad
     */
    public double getWRad() {
        return wRad;
    }

    /**
     * @param wRad the wRad to set
     */
    public void setWRad(double wRad) {
        this.wRad = wRad;
    }

    /**
     * Retorna la esquina indicada donde la enumeración es como sigue<br>
     * &nbsp&nbsp&nbsp  1----------0  <br>
     * &nbsp&nbsp&nbsp  |&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp|  <br>
     * &nbsp&nbsp&nbsp  |&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp|  <br>
     * &nbsp&nbsp&nbsp  |&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp|  <br>
     * &nbsp&nbsp&nbsp  2----------3
     * @param index Índice de la esquina que se quiere obtener
     * @return La esquina solicitada
     */
    public Point getCorner(int index){
        if(index >= 4){
            index = index%4;
        }else if(index < 0){
            index = 4 + index%4;
        }
        switch(index){
            case 0:{
                return new Point( x+Math.sqrt(2)*radio*Math.cos(Math.PI/4 + (getDireccion()-Math.PI/2)) , y+Math.sqrt(2)*radio*Math.sin(Math.PI/4 + (getDireccion()-Math.PI/2)) );}
            case 1:{
                return new Point( x+Math.sqrt(2)*radio*Math.cos(3*Math.PI/4 + (getDireccion()-Math.PI/2)) , y+Math.sqrt(2)*radio*Math.sin(3*Math.PI/4 + (getDireccion()-Math.PI/2)) );}
            case 2:{
                return new Point( x+Math.sqrt(2)*radio*Math.cos(5*Math.PI/4 + (getDireccion()-Math.PI/2)) , y+Math.sqrt(2)*radio*Math.sin(5*Math.PI/4 + (getDireccion()-Math.PI/2)) );}
            case 3:{
                return new Point( x+Math.sqrt(2)*radio*Math.cos(7*Math.PI/4 + (getDireccion()-Math.PI/2)) , y+Math.sqrt(2)*radio*Math.sin(7*Math.PI/4 + (getDireccion()-Math.PI/2)) );}
            default:
                throw new IllegalArgumentException("Corner " + index + "not found.");
        }
    }

    public boolean contains (Point point){
        //angulo de la recta entre la esquina 0 y el punto
        Vector2D v0p = new Vector2D( point.getY() - this.getCorner(0).getY()  ,  point.getX() - this.getCorner(0).getX() );
        
        //angulo de la recta entre la esquina 2 y el punto
        Vector2D v2p = new Vector2D(  point.getY() -this.getCorner(2).getY() , point.getX() - this.getCorner(2).getX() );
        
        //angulo de la recta entre el la esquina 0 y la esquina 1
        Vector2D v01 = new Vector2D( this.getCorner(1).getY() - this.getCorner(0).getY() , this.getCorner(1).getX() - this.getCorner(0).getX() );
        
        //angulo de la recta entre el la esquina 0 y la esquina 3
        Vector2D v03 = new Vector2D( this.getCorner(3).getY() - this.getCorner(0).getY() , this.getCorner(3).getX() - this.getCorner(0).getX() );
        
        //angulo de la recta entre el la esquina 2 y la esquina 1
        Vector2D v21 = new Vector2D( this.getCorner(1).getY() - this.getCorner(2).getY() , this.getCorner(1).getX() - this.getCorner(2).getX() );
        
        //angulo de la recta entre el la esquina 2 y la esquina 3
        Vector2D v23 = new Vector2D( this.getCorner(3).getY() - this.getCorner(2).getY() , this.getCorner(3).getX() - this.getCorner(2).getX() );


        double a01_03 = Math.PI/2;//v01.getMinAngle(v03);//menor angulo entre v01 y v03
        
        double a01_0p = v01.getMinAngle(v0p);//menor angulo entre v01 y v0p
        
        double a03_0p = v03.getMinAngle(v0p);//menor angulo entre v03 y v0p
        
        double a21_23 = Math.PI/2;//v21.getMinAngle(v23);//menor angulo entre v21 y v23
        
        double a21_2p = v21.getMinAngle(v2p);//menor angulo entre v21 y v2p
        
        double a23_2p = v23.getMinAngle(v2p);//menor angulo entre v23 y v2p
        

        if( ( ( ( a01_03 <= ( a01_0p + a03_0p + 0.0000001) ) ) && ( ( a01_03 >= ( a01_0p + a03_0p - 0.0000001) ) )) && ( ( a21_23 <= ( a21_2p + a23_2p + 0.0000001 ) ) && ( a21_23 >= ( a21_2p + a23_2p - 0.0000001 ) ) ) ){//( ( ( a01_03 == ( a01_0p + a03_0p ) ) )&& ( a21_23 == ( a21_2p + a23_2p ) ) ){
            return true;
        }else{
            return false;
        }

    }

    public static void main(String[] args) {
        for(double i = 0; i< 2 ; i = i+0.001){
            SimulAgent smith = new SimulAgent(3, 3, 1, i*Math.PI, 1);
    //        System.out.println("atan="+Math.atan2(-2, -2));
    //        System.out.println("puntos");
    //        System.out.println(smith.getCorner(0));
    //        System.out.println(smith.getCorner(1));
    //        System.out.println(smith.getCorner(2));
    //        System.out.println(smith.getCorner(3));
            Point neo = new Point(2.9, 2.9);
            System.out.println(i+"PI:  "+smith.contains(neo));
        }
    }

}
