
package EntradaSalida;

import java.io.File;
import java.util.ArrayList;
import remodelroom.Objeto;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import remodelroom.DimensionMia;


public class Guardar {
    
    public Guardar (){}
    
    public boolean guardar(ArrayList<Objeto> objetos,DimensionMia d ,String carpeta, String nombre) {
        boolean exito=true;
        BufferedWriter esc=null;
        File fl = new File(carpeta, nombre);
        if (fl.exists()) {
            JOptionPane.showMessageDialog(null, "ERROR: YA EXISTE UN ARCHIVO LLAMADO ASI EN LA CARPETA");
            return false;
        }
        
        try {
            FileWriter writer=new FileWriter(fl,true);
            esc=new BufferedWriter(writer);
            esc.append(String.valueOf(d.getWidth())+" "+String.valueOf(d.getHeight()));
            esc.newLine();
            
            for (int i=0;i<objetos.size();i++){
                if (!escribirObjeto(objetos.get(i), esc)){
                    exito=false;
                    break;
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
            exito=false;
        }finally {
            try{
                if (esc!=null){
                    esc.close();
                }
            } catch (IOException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return exito;
    }
    
    private boolean escribirObjeto (Objeto obj, BufferedWriter esc){
        try{
            esc.append(obj.infoParaGuardar());
            esc.newLine();
        } catch (IOException e){
            return false;
        } 
        return true;
    }
}
