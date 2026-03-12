package practica_final_programacion_ii;

import java.awt.*;

public class Chocolate {
    //Atributos de la clase chocolate como los tipos que hay (diferentes figuras)
    public static Image decoracion = Toolkit.getDefaultToolkit().getImage("fondo.jpg");
    public static Image imagen = Toolkit.getDefaultToolkit().getImage("CHOCOLATE.jpg");
    public static Image ocupado = Toolkit.getDefaultToolkit().getImage("OCUPADA.jpg");
    public static Image libre = Toolkit.getDefaultToolkit().getImage("LIBRE_V.jpg");
    int x;
    int y;
    boolean ocupada;
    boolean pintar_ocupada = false;
    boolean pintar_libre = false;
    //Constructor de las casillas chocolate con su coordenada y estado
    public Chocolate(int x, int y){
        this.x = x;
        this.y = y;
        ocupada = false;
    }
    //metodo que cambia la aparencia según un entero introducido por parámetro
    public static void setImagen(int i){
        switch(i){
            case 1:imagen=Toolkit.getDefaultToolkit().getImage("CHOCOLATE.jpg");
            break;
            case 2:imagen=Toolkit.getDefaultToolkit().getImage("LADRILLO.jpg");
            break;
            case 3:imagen=Toolkit.getDefaultToolkit().getImage("MICKEY_MOUSE.jpg");
            break;
            case 4:imagen=Toolkit.getDefaultToolkit().getImage("AZULEJO1.jpg");
            break;
            case 5:imagen=Toolkit.getDefaultToolkit().getImage("AZULEJO2.jpg");
            break;
            default:
                imagen=Toolkit.getDefaultToolkit().getImage("CHOCOLATE.jpg");
                break;
        }
        
    }
    //metodos setters y getters
    public void ocupar(){
        ocupada = true;
    }
    
    public void desocupar(){
        ocupada = false;
    }
    
    public int get_x(){
        return x;
    }
    
    public int get_y(){
        return y;
    }
    
    public void set_x(int x){
        this.x = x;
    }
    
    public void set_y(int y){
        this.y = y;
    }
    
    public boolean get_ocupada(){
        return ocupada;
    }
    
    public boolean get_es_libre(){
        return pintar_libre;
    }
    
    public boolean get_es_ocupada(){
        return pintar_ocupada;
    }
    
    public void set_pintar_libre(boolean pintar_libre){
        this.pintar_libre = pintar_libre;
    }
    
    public void set_pintar_ocupado(boolean pintar_ocupada){
        this.pintar_ocupada = pintar_ocupada;
    }
}
