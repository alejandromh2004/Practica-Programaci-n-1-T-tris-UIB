/*
    CLASE FICHEROPARTIDAS con la cual se pueden escribir en un fichero de datos las partidas jugadas (con nombre de jugador, fecha, puntuación,
    y tiempo)
*/
package practica_final_programacion_ii;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FicheroPartidas {
    private RandomAccessFile fichero;
    //Constructor que abre el fichero en modo escritura/lectura
    public FicheroPartidas(String nombreFichero){
        try{
           fichero = new RandomAccessFile(nombreFichero, "rw"); 
        }catch(IOException e){
            System.err.println(e.toString());
        }
        
    }
    //metodo que escribe componenete por componenete los datos en el fichero
    public void escribirPartida(UsuarioPartida usuario, int duracion){
        try{
            //Posicionamiento en el final del fichero
            fichero.seek(fichero.length());
            //Escritura del nombre
            fichero.writeUTF(usuario.nombre);
            fichero.writeUTF(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yy:MM:dd HH:mm")));
            //Escritura de la duración y puntuación
            fichero.writeInt(duracion);
            fichero.writeInt(usuario.getPuntuacion());
        }catch(IOException e){
            System.err.println(e.toString());
        }
       
    }
    //Cierre del fichero
    public void cerrar(){
        try{
           if (fichero != null) {
            fichero.close();
            } 
        }catch(IOException e){
            System.err.println(e.toString());
        }
       
    }
    //Lectura de todas las partidas para ser mostradas en pantalla
    public String leerHistorial() throws IOException {
        StringBuilder historial = new StringBuilder();
        fichero.seek(0);
        while (fichero.getFilePointer() < fichero.length()) {
            String nombre = fichero.readUTF();
            String fechaHora = fichero.readUTF();
            int duracion = fichero.readInt();
            int puntuacion = fichero.readInt();
            historial.append(String.format("- %s > partida jugada con un tiempo de %d segundos por %s obteniendo una puntuación de %d puntos.\n", fechaHora, duracion, nombre, puntuacion));
        }
        return historial.toString();
    }
}
