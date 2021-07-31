
package remodelroom;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class MiCanvas extends JPanel{

    private final Control cl;
    public MiCanvas(Control cl) {
        this.cl=cl;
        this.setBackground(Color.GRAY);
    }
    
    
    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        cl.renderFromCanvas(g);
    }
}
