/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FutbolSimul.physics;


/**
 * Una colision está definida para dos cuerpos, relativos a los atributos body1 y body2
 * también define el punto en el que colisionan
 * @author Jorge
 */
public class Collision {
    private PhysicsObject body1;
    private PhysicsObject body2;
    private Point colPoint;
    private Vector2D N;

    /**
     * @return the body1
     */
    public PhysicsObject getBody1() {
        return body1;
    }

    /**
     * @param body1 the body1 to set
     */
    public void setBody1(PhysicsObject body1) {
        this.body1 = body1;
    }

    /**
     * @return the body2
     */
    public PhysicsObject getBody2() {
        return body2;
    }

    /**
     * @param body2 the body2 to set
     */
    public void setBody2(PhysicsObject body2) {
        this.body2 = body2;
    }

    /**
     * @return the colPoint
     */
    public Point getColPoint() {
        return colPoint;
    }

    /**
     * @param colPoint the colPoint to set
     */
    public void setColPoint(Point colPoint) {
        this.colPoint = colPoint;
    }

    /**
     *
     * @return vector normal al punto de colision
     */
    public Vector2D getN(){
        if(this.N == null){
            //buscar a que pared del body2 está mas cerca el punto (con esa pared es la colision)
            double minDist1 = colPoint.distance( body2.getCorner(0) );
            int minPoint1Index = 0;
            double minDist2;
            int minPoint2Index;
            int n=4;// TODO cambiar n por el numero de esquinas del cuerpo body2

            //buscar las esquinas mas cercanas al punto de colision
            for(int i = 1; i<n; i++){

                double currDist = colPoint.distance( body2.getCorner(i) );
                if( currDist < minDist1 ){
                    minDist1 = currDist;
                    minPoint1Index = i;
                }
            }

            //verificar las esquinas de los lados de la encontrada
            if( colPoint.distance( body2.getCorner( minPoint1Index + 1 ) ) < colPoint.distance( body2.getCorner( minPoint1Index - 1 ) )){
                minPoint2Index = minPoint1Index + 1;
            }else{
                minPoint2Index = minPoint1Index - 1;
            }

            //TODO la pared con la que se colisionó es la recta entre minPoint1 y minPoint2

        }
        return N;
    }
    
}
