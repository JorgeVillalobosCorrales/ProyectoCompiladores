/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfor;
import java.io.File;
/**
 *
 * @author Jorge
 */
public class EjecutableCMD {
    public void ejecutarCmd(String nombreArchivo){
        if(!Errores.hayErrores){//Si no hay errores, se ejecuta lo siguiente
            try {
                File archivo = new File("");//Permite del getAbsolutePatch
                nombreArchivo = nombreArchivo.replace(".transfor", ".f90");//Cambiar el nombre de la extensión del archivoa .90 
                String exe = nombreArchivo.replace(".f90", ".exe");//Cambiar el .f90 a .exe

                //La siguiente línea se ejecuta automática, permite leer todo con TRANSFOR, luego getabsolutePath es para la ruta e independencia.
                //Para que se ejecute de manera automática.
                String FORTRAN = "cmd /c gfortran " + nombreArchivo + " -o " + exe + 
                    " && cmd /c \"start cmd /k " + exe + " && cd " + archivo.getAbsolutePath() + "\\ && " + exe + " \"";
                Runtime.getRuntime().exec(FORTRAN); 
            } catch (Exception e) {
            }
        } else{
                System.out.println("El archivo analizado tiene errores");    
               }
            
    }     
}
        