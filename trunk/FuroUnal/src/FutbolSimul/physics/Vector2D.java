/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FutbolSimul.physics;

/** Representa un vector en dos dimensiones
 *
 *  @author Sergio Bobillier Ceballos
 */
public class Vector2D
{
    /** Coordenada x del vector */
    private double x;
    
    /** Cordenada y del vector */
    private double y;
    
    /** Constructora sin parámetros. Crea el vector (0, 0) */
    public Vector2D()
    {
        this.x = 0;
        this.y = 0;
    }
    
    /** Crea un vector con las coordenadas especificadas
     * 
     *  @param x La coordenada x del vector
     *  @param y La coordenada y del vector
     */
    public Vector2D(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    /**
     * Constructora que crea un vector con su magnitud y angulo
     * @param module magnitud del vector a crear
     * @param angle angulo del vector a crear
     * @param flag puede tomar cualquier valor, sirve para usar esta contructora en lugar de la normal
     */
    public Vector2D(double module, double angle, int flag){
        this.x = module * Math.cos(angle);
        this.y = module * Math.sin(angle);
    }

    /** Calcula la suma vectorial entre este vector y el vector especificado.
     * 
     * @param vector El vector con el que se desea sumar.
     * @return El vector resultante.
     */
    
    public Vector2D add(Vector2D vector)
    {
        Vector2D result;
        result = new Vector2D();
        result.setX(this.x + vector.x);
        result.setY(this.y + vector.y);
        return result;
    }

    /**
     * calcula la componente de este vector en esa dirección.
     * @param direction direccion a calcularle la componente.
     * @return el vector resultante
     */
    public Vector2D component(double direction) {

        Vector2D result;
        result = new Vector2D( this.getModule() * Math.cos(direction), direction);
        return result;
    }
    
    /** Devuelve verdadero si el vector es el vector (0,0), falso en cualquier
     *  otro caso.
     * 
     *  @return Verdadero si se trata del vector (0,0) falso de lo contrario.
     */
    
    public boolean isZeroVector()
    {
        if(this.x == 0.0f && this.y == 0.0f)
            return true;
        
        return false;
    }
    
    /** Devuelve el módulo del vector
     * 
     *  @return Un valor flotante, el módulo del vector.
     */
    
    public double getModule()
    {
        double module;
        module = (double)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return module;
    }
        
    /** Devuelve la coordenada x del vector.
     * 
     *  @return La coordenada x del vector.
     */
    
    public double getX() { return x; }
    
    /** Devuelve la coordenada y del vector.
     * 
     * @return. La coordenada y del vector.
     */
    
    public double getY() { return y; }
    
    /** Devuelve una representación del vector en forma de cadena de caracteres
     * 
     *  @return La representación del vector.
     */
    
    @Override
    public String toString()
    {
        StringBuffer string;
        string = new StringBuffer();
        string.append("(");
        string.append(String.valueOf(x));
        string.append(", ");
        string.append(String.valueOf(y));
        string.append(")");
        
        return string.toString();
    }

    /** Devuelve el vector unitario que tiene la misma dirección que este vector.
     * 
     *  @return Un vector que representa el vector unitario con la misma
     *      dirección de este vector.
     */
    
    public Vector2D toUnitVector()
    {
        Vector2D unitVector;
        double module;
        
        module = this.getModule();
        
        unitVector = new Vector2D();
        unitVector.setX(this.x/module);
        unitVector.setY(this.y/module);

        return unitVector;
    }
    
    /** Devuelve el vector resultante de multiplicar este vector por un
     *  escalar.
     * 
     * @param f El escalar por el cual se quiere multiplicar el vector.
     * @return El vector resultante.
     */
    public Vector2D scalarMultiply(double f)
    {
        Vector2D result;
        result = new Vector2D();
        result.setX(this.x * f);
        result.setY(this.y * f);
        return result;
    }

    /**
     * Devuelve el escalar resultado del producto punto entre este vector y 'vector'
     * @param vector el vector con el que se quiere calcular el producto punto
     * @return el escalar resultante
     */
    public double dotProduct(Vector2D vector){
        return this.getX() * vector.getX() + this.getY() * vector.getY();
    }

    public double crossProduct(Vector2D vector){
        double theta = this.getMinAngle(vector);

        if(this.getAngle() <= vector.getAngle()){//la regla de la mano derecha da el signo
            return this.getModule() * vector.getModule() * Math.sin(theta);
        }else{
            return - this.getModule() * vector.getModule() * Math.sin(theta);
        }
    }
    
    /** Establece la coordenada x del vector.
     * 
     *  @param x La coordenada x del vector.
     */
    
    public void setX(double x) { this.x = x; }
    
    /** Establece la coordenada y del vector.
     * 
     *  @param y La coordenada y del vector.
     */
    
    public void setY(double y) { this.y = y; }

    /**
     * Devuelve el angulo del vector en [0 ; 2PI]
     * @return the vector's angle
     */
    public double getAngle(){
        double theta = Math.atan2(y, x);
        if(theta < 0 ){
            theta = 2*Math.PI + theta;
        }
        return theta;
    }

    /**
     * Devuelve el menor angulo entre este vector y 'vector'
     * @param vector vector con el que se quiere calcular el menor angulo
     * @return el angulo resultante
     */
    public double getMinAngle(Vector2D vector) {

        double highAngle , lowAngle;

        if(this.getAngle() > vector.getAngle()){
            highAngle = this.getAngle();
            lowAngle = vector.getAngle();
        }else{
            highAngle = vector.getAngle();
            lowAngle = this.getAngle();
        }
        double theta = highAngle - lowAngle;
        theta = theta % (2*Math.PI);

        if(theta < 0){
            theta = 2*Math.PI + theta;
        }

        if(theta > Math.PI){
            theta = 2*Math.PI - theta;
        }
        return theta;
    }

}
