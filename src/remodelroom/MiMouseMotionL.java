
package remodelroom;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



public class MiMouseMotionL implements MouseListener,MouseMotionListener{

    private int aX,aY;
    private final Control cl;
    private Objeto select;
    private boolean dragged;
    public MiMouseMotionL(Control cl) {
        this.cl=cl;
        this.aX=0;
        this.aY=0;
        this.select=null;
        this.dragged=false;
    }
    
    public Objeto getSelect (){
        return this.select;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        this.select=cl.obtObjetoSeleccionado(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int moverX;
        int moverY;
        if (this.dragged) {
            if (select != null) {
                moverX = e.getX() - aX;
                moverY = e.getY() - aY;
                select.mover(moverX, moverY);
                this.cl.render();
            } else {
                this.dragged = false;
            }
        } 
        aX=e.getX();
        aY=e.getY();
    }


    @Override
    public void mousePressed(MouseEvent e) {
        this.select = cl.obtObjetoSeleccionado(e.getX(), e.getY());
        this.aX=e.getX();
        this.aY=e.getY();
        
        if (this.select!= null){
            this.dragged=true;
        }
        else {
            this.dragged=false;
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
    
}
