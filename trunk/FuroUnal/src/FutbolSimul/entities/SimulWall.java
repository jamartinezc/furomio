/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FutbolSimul.entities;

import FutbolSimul.physics.PhysicsObject;
import FutbolSimul.physics.Point;

/**
 *
 * @author Jorge
 */
public class SimulWall extends PhysicsObject{

    @Override
    public double getInertia() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Point getCorner(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    // TODO implementar la clase que representara una pared en caso de choque
}
