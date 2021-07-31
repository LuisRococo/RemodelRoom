
package remodelroom;

import java.awt.Color;
import java.awt.Graphics;


public class Objeto {

    private int x, y;
    private int width;
    private int height;
    private final Color color;
    public Objeto (int width,int height,Color color) {
        x=0;
        y=0;
        this.color=color;
        this.width= width;
        this.height=height;
    }
    public Objeto (int width,int height,int posX,int posY,Color color) {
        x=posX;
        y=posY;
        this.color=color;
        this.width= width;
        this.height=height;
    }
    
    public boolean isClick (int xM,int yM){
        if (xM>=x && xM<= (x+width)){
            if (yM>=y && yM<= (y+height)){
                return true;
            }
        }
        return false;
    }
    
    public void render (Graphics g){
        g.setColor(this.color);
        g.fillRect(x, y, width, height);
    }
    
    public void mover (int nX,int nY){
        this.x+=nX;
        this.y+=nY;
    }
    public void voltear (){
        int aux=this.width;
        this.width=this.height;
        this.height=aux;
    }
    
    //GUARDADO
    public String infoParaGuardar (){
        String terminado;
        String colorString;
        String info;
        
        info=String.valueOf(this.width)+" "+String.valueOf(this.height)+" "+String.valueOf(x)+" "+String.valueOf(y);
        colorString=String.valueOf(String.valueOf(this.color.getRGB()));
        terminado=info+" "+colorString;
        return terminado;
    }
}
