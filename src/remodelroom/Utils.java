
package remodelroom;


public class Utils {
    
    private final int pixelsPerMeter;
    private final double width;
    private final double height;
    public Utils (double width,double height,int limitX,int limitY){
        double nuevaX= limitX/width;
        double nuevaY= limitY/height;
        this.width=width;
        this.height=height;
        pixelsPerMeter=(int)Math.min(nuevaY, nuevaX);
    }
    
    public int calculateInPixels(double meters){
        return  (int) (meters * this.pixelsPerMeter);
    }

    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    
    
}
