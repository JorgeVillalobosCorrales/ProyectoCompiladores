package transfor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class TRANSFOR {
    public static ArrayList<FormatoErrores> lista = new ArrayList<>();//Lista para almacenar los errores
    private String archivoError = "";//Variable de escritura y lectura
    FormatoErrores error;
    
    public static void main(String[] args) {
        String nombreArchivo = "";//recibe el nombre del argumento
        TRANSFOR metodos = new TRANSFOR();
        try {
            nombreArchivo = args[0].toLowerCase().trim();//Se inicializa la variable, si se recibe en mayuscula, se pasa minusculas
            if(!Archivo.validarNombre(nombreArchivo)){//Se envía el nombre de archivo para validarlo de acuerdo a las condiciones
                System.out.println("Nombre incorrecto");
            }
            
            if(Archivo.existeArchivo(nombreArchivo)){
                /*
                * El método lecturaEscrituraArchivo, permite leer linea por linea el
                 archivo y hacer la copia, del .f90
               */
                metodos.lecturaEscrituraArchivo(nombreArchivo);
                
                /*
                * El método EscrituraErrores, permite leer linea por linea el
                 archivo y hacer la copia, del archivo de erroes.txt
               */                
                metodos.escrituraErrores(nombreArchivo);
            }
            
            EjecutableCMD cmd  = new EjecutableCMD();//Instancia de la clase
            cmd.ejecutarCmd(nombreArchivo);//Se envía la variable por parametros a otro método
        } catch (Exception e) {
        }
    }
    
    public void lecturaEscrituraArchivo(String leerEscribirArchivo) {
        String archivoFORTRAN = leerEscribirArchivo.replace(".transfor", ".f90");// Reemplaza el .transfor por el .f90
        String linea;//Variable para leer lineas del archivo
        int contador = 1;//Contando cada línea del archivo
        
        try {
            FileReader frOriginal = new FileReader(leerEscribirArchivo);//Recibe el archivo en .transfor
            BufferedReader brOriginal = new BufferedReader(frOriginal);//Permite terminar de leer por el BufferedReader
            
            FileWriter fwCopia = new FileWriter(archivoFORTRAN);//Recibe el archivo en .f90
            BufferedWriter bwCopia = new BufferedWriter(fwCopia);//Permite terminar de escribir por el BufferedWriter

            
            //Ciclo para leer línea por línea del archivo Calcular Inflación .transfor
            while ((linea = brOriginal.readLine()) != null) {
                bwCopia.write(linea);//Copia la línea en otro archivo .f90
                bwCopia.newLine();//Hace un salto de línea

                
                AnalisisLexico analizar = new AnalisisLexico();
                //Se encarga de analizar el archivo, luego con el número de línea, este se envía a los cases
                //Contador aumenta para que vaya leyendo otras líneas
                analizar.analisisLexicoLineas(linea, contador);
 
                contador++;
            }

            brOriginal.close();//Cerrar archivo original
            bwCopia.close();//Cerrar archivo copia
        } catch (Exception e) {
        }
    }
    
    /*
    Permite crear el archivo de errores en el siguiente método
    */
    
    public String escrituraErrores (String crearArchivo){
        //Se reemplaza el .transfor por -errores.txt
        String archivoErrores = crearArchivo.replace(".transfor", "-errores.txt");
        String linea;//Leer línea
        int n = 1;
        try {
            FileReader frOriginal = new FileReader(crearArchivo);//Recibe el archivo en .trfansfor
            BufferedReader brOriginal = new BufferedReader(frOriginal);//Permite terminar de leer por el BufferedReader
            
            FileWriter fwErrores = new FileWriter(archivoErrores);//Recibe el archivo en -errores.txt
            BufferedWriter bwErrores = new BufferedWriter(fwErrores);//Permite terminar de escribir por el BufferedWriter
            
            while((linea = brOriginal.readLine()) != null){
                bwErrores.write(String.format("%05d ", n++));//Se genera la numeración del archivo al inicio
                bwErrores.write(linea);//Escribe la línea en el archivo
                bwErrores.newLine();//Genera un salto de línea
                
                
                /*Método para escribir los errores*/
              
                for (FormatoErrores error : TRANSFOR.lista) {
                    if(n - 1 == error.getNumeroError()){// El n - 1  permite escribir el error debajoi de la lína con problemas
                        bwErrores.write(error.getMensajeError());//Utiliza una variable para escribirla dentro del archivo
                        bwErrores.newLine();//Se genera una nueva linea, salto de línea.
                    }
                }
                
                //Permite mostrar si esta o no un comando, en caso de no existir muestra un error
                if (!AnalisisLexico.existeProgram) {//La variable se encuentra en falso, por lo tanto aquí no existe program
                    error = new FormatoErrores(Errores.listaErrores(10, linea), n);//La variable linea es para recibir el mensaje
                    TRANSFOR.lista.add(error);//Se agregar a la lista
                }
                
                if (!AnalisisLexico.existeEndprogram) {//La variable se encuentra en falso, por lo tanto aquí no existe endprogram
                    error = new FormatoErrores(Errores.listaErrores(17, linea), n);//La variable linea es para recibir el mensaje
                    TRANSFOR.lista.add(error);//Se agregar a la lista
                }
                
                if (!AnalisisLexico.existeDo) {//La variable se encuentra en falso, por lo tanto aquí no existe do
                    error = new FormatoErrores(Errores.listaErrores(31, linea), n);//La variable linea es para recibir el mensaje
                    TRANSFOR.lista.add(error);//Se agregar a la lista
                }
                
                if (!AnalisisLexico.existeEndDo) {//La variable se encuentra en falso, por lo tanto aquí no existe EndDo
                    error = new FormatoErrores(Errores.listaErrores(32, linea), n);//La variable linea es para recibir el mensaje
                    TRANSFOR.lista.add(error);//Se agregar a la lista
                }
                
                if (!AnalisisLexico.existeIf) {//La variable se encuentra en falso, por lo tanto aquí no existe if
                    error = new FormatoErrores(Errores.listaErrores(36, linea), n);//La variable linea es para recibir el mensaje
                    TRANSFOR.lista.add(error);//Se agregar a la lista
                }
                
                if (!AnalisisLexico.existeENDIF) {//La variable se encuentra en falso, por lo tanto aquí no existe EndIf
                    error = new FormatoErrores(Errores.listaErrores(37, linea), n);//La variable linea es para recibir el mensaje
                    TRANSFOR.lista.add(error);//Se agregar a la lista
                }
            }

            brOriginal.close();//Cerramos el archivo original
            bwErrores.close();//Cerramos el archivo Errores
        } catch (Exception e) {
        }
        
        return archivoError;//Retorno el archivo de errores
    }
}