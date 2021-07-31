
package remodelroom;

import Frames.Vent_Principal;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Control {

    private final Vent_Principal vent;
    private MiCanvas canvas;
    private MiMouseMotionL miMM;
    private ArrayList <Objeto> objetos;
    public Control(Vent_Principal vent) {
        this.vent=vent;
        init();
    }
    
    public ArrayList<Objeto> getObjetos (){
        return this.objetos;
    }
    
    private void init (){
        miMM=new MiMouseMotionL(this);
        this.objetos=new ArrayList <> ();
        this.canvas=new MiCanvas(this);
        this.canvas.setSize(vent.jPanel2.getSize());
        this.canvas.setFocusable(true);
        this.canvas.addMouseListener(miMM);
        this.canvas.addMouseMotionListener(miMM);
        this.vent.jPanel2.add(canvas);
    }

    public synchronized Objeto obtObjetoSeleccionado (int x,int y){
        for (int i=objetos.size()-1;i>=0;i--){
            if (objetos.get(i).isClick(x, y)){
                return objetos.get(i);
            }
        }
        return null;
    }
    
    public synchronized void agregarObjeto (double width,double height,Color color){
        Objeto nuevo=new Objeto((int)vent.util.calculateInPixels(width), (int)vent.util.calculateInPixels(height), color);
        objetos.add(nuevo);
    }
    public synchronized void agregarObjeto (Objeto obj){
        objetos.add(obj);
    }
    
    public synchronized void eliminarObjeto (){
        Objeto eliminar= miMM.getSelect();
        if (eliminar!=null){
            objetos.remove(eliminar);
        }
    }
    public synchronized void voltearObjeto (){
        Objeto voltear= miMM.getSelect();
        if (voltear!=null){
            voltear.voltear();
        }
    }
    
    public synchronized void subirBajarObjeto (boolean arriba){
        Objeto mover=miMM.getSelect();
        Objeto aux;
        if (mover==null) return;
        
        int pos=objetos.indexOf(mover);
        if (arriba){
            if (pos<objetos.size()-1) {
                aux=objetos.get(pos+1);
                objetos.set(pos+1, mover);
                objetos.set(pos, aux);
            }
        } else {
            if (pos>0) {
                aux=objetos.get(pos-1);
                objetos.set(pos-1, mover);
                objetos.set(pos, aux);
            }
        }
    }
    
    public void render (){
        canvas.repaint();
    }
    
    public void renderFromCanvas (Graphics g){
        this.renderCuarto(g);
        this.renderLines(g);
        this.renderObjetos(g);
    }
    
    private void renderLines (Graphics g){
        int pixelsPerM=vent.util.calculateInPixels(1);
        g.setColor(Color.CYAN);
        for (int i=0;i<=canvas.getWidth();i+=pixelsPerM){
            g.drawLine(i, 0, i, canvas.getHeight());
        }
        for (int i=0;i<=canvas.getHeight();i+=pixelsPerM){
            g.drawLine(0, i, canvas.getWidth(), i);
        }
    }
    private void renderObjetos (Graphics g){
        for (int i=0;i<objetos.size();i++){
            objetos.get(i).render(g);
        }
    }
    private void renderCuarto (Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(0, 0,vent.util.calculateInPixels(vent.util.getWidth()),vent.util.calculateInPixels(vent.util.getHeight()));
    }
}
