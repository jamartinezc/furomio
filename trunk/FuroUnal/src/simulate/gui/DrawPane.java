package simulate.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;



/**
 * Panel to draw a fuzzy space, and highlight a specific set and value
 */
public class DrawPane extends JPanel{

  public Drawer drawer = null;
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JTextPane logPane = new JTextPane();
  BorderLayout borderLayout2 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();

  /**
   * Default constructor
   */
  public DrawPane() {
      this( null );
  }

  /**
   * Default constructor
   */
  public DrawPane( Drawer _drawer ) {
    drawer = _drawer;
   setBackground(new Color(255,255,255));
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void setDrawer( Drawer _drawer ) {
    drawer = _drawer;
    setBackground(new Color(255,255,255));
  }


  public void update(){
//    System.out.println("trying" );
    repaint();
  }

  public void setText( String text ){
    if( text != null && text != "")  logPane.setText( logPane.getText() + text + "\n" );
  }

  /**
   * Paints the graphic component
   * @param g Graphic component
   */
  protected void paintComponent(Graphics g){
    super.paintComponent(g);
    if( drawer != null )  drawer.paint(g);
  }

  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    jPanel1.setLayout(borderLayout2);
    logPane.setToolTipText("");
    logPane.setEditable(false);
    logPane.setText("");
    jPanel1.setBorder(BorderFactory.createLoweredBevelBorder());
    this.add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(jScrollPane1, BorderLayout.NORTH);
    jScrollPane1.getViewport().add(logPane, null);
    jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
  }
}
