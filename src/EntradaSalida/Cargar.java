
package EntradaSalida;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import remodelroom.DimensionMia;
import remodelroom.Objeto;

public class Cargar {

    private final File fl;
    private final boolean valido;
    
    private DimensionMia dim;
    private ArrayList <Objeto> objetos;
    
    public Cargar(File fl) {
        if (fl.isFile()) valido=true;
        else
            valido = false;
        this.fl=fl;
    }

    public boolean cargar() {
        boolean exito=true;
        BufferedReader lect=null;
        if (valido) {
            try {
                FileReader reader = new FileReader(fl);
                lect = new BufferedReader(reader);
                
                if (!cargarTodosDatos(lect)){
                    exito=false;
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, e);
                exito=false;
            } finally {
                try{
                    if (lect!=null){
                        lect.close();
                    }
                } catch (IOException e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        } else {
            exito=false;
        }
        return exito;
    }
    
    private boolean cargarTodosDatos (BufferedReader lect){
        try{
            String dimString=lect.readLine();
            String dimDiv[];
            if (dimString==null) return false;
            else {
                dimDiv=dimString.split("\\s+");
                this.dim=new DimensionMia(Double.valueOf(dimDiv[0]), Double.valueOf(dimDiv[1]));
                if (cargarObjetos(lect)){
                    return true;
                } else return false;
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    private boolean cargarObjetos(BufferedReader lect) {
        try {
            String srt;
            String srtObj[];
            
            this.objetos=new ArrayList<>();
            srt=lect.readLine();
            while (srt != null) {
                srtObj = srt.split("\\s+");
                if (srtObj.length != 5) {
                    return false;
                }
                getObjetos().add(new Objeto(Integer.valueOf(srtObj[0]), Integer.valueOf(srtObj[1]), Integer.valueOf(srtObj[2]), Integer.valueOf(srtObj[3]), new Color(Integer.valueOf(srtObj[4]))));
                srt = lect.readLine();
            }
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }


    public DimensionMia getDim() {
        return dim;
    }
    public ArrayList <Objeto> getObjetos() {
        return objetos;
    }
}
