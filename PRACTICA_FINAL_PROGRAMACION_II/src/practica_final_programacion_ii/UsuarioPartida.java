 /*
    CLASE USUARIOPARTIDA en la cual se guardan los ajustes del usuario, nombre y si hay o no partida jugando por ese usuario
 */
package practica_final_programacion_ii;

public class UsuarioPartida {
    boolean partidaEnCurso;
    String nombre;
    int puntuacion=0;
    int decremento;
    
    public UsuarioPartida(String nombre_dado){
        partidaEnCurso=false;
        nombre=nombre_dado;
        puntuacion=0;
        
    }
    public void IniciarPartida(){
        partidaEnCurso=true;
    }
    public void IncrementarPuntuacion(int incremento){
        puntuacion=puntuacion+incremento;
    }
    public void DecrementarPuntuacion(int decremento){
        puntuacion=puntuacion-decremento;
    }
    public int getPuntuacion(){
        return puntuacion;
    }
    public void AcabarPartida(){
        partidaEnCurso=false;
    }
    public boolean getHayPartida(){
        return partidaEnCurso;
    }
    
    
            
    
}
