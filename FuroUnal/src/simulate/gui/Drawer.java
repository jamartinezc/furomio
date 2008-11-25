package simulate.gui;
import java.awt.Graphics;

import org.omg.CORBA.Environment;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public abstract class Drawer {
    protected Environment environment=null;
    public Drawer(){}
    public Drawer( Environment _environment ){ environment = _environment; }
    public void setEnvironment( Environment _environment ){ environment = _environment; }
    public abstract void paint( Graphics g );
}
