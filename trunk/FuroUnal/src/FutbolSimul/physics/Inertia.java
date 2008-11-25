/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FutbolSimul.physics;

/**
 *
 * @author Jorge
 */
public class Inertia {
    /**
     * calculates the moment of inertia of a cube with the axis trought the center
     * @return
     */
    public static double cubeInertia(double side, double mass){
        return (1.0/12.0)*mass*(2.0*Math.pow(side, 2));
    }

    public static double sphereInertia(double radio, double mass){
        return (2.0/5.0)*mass*Math.pow(radio, 2);
    }
}
