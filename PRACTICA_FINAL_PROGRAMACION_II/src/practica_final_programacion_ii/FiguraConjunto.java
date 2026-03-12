/*
    CLASE FIGURACONJUNTO con la cual se generan y gestionan las figuras y formas que van a ser colocadas
    en el tablero
*/
package practica_final_programacion_ii;

import java.util.Random;

public class FiguraConjunto {
    //Atributos de la clase
    Chocolate [] [] figura;
    public static int [] coordenadas_init = {950,300};
    int x;
    int y;
    //Un objeto figura conjunto es una matriz 3x3 con forma aleatoria
    public FiguraConjunto(){
        figura = new Chocolate[3][3];
        for(int h = 0;h<figura.length;h++){
            for(int l = 0;l<figura.length;l++){
                figura[h][l] = new Chocolate(coordenadas_init[0]+30*h,coordenadas_init[1]+30*l);
            }
        }
        mezclar();
        
        
    }
    //Metodo que hace que la matriz 3x3 tenga forma aleatoria (ocupando y 
    //desocupando casillas aleatorias)
    public void mezclar(){
        Random random = new Random();
        for(int h = 0;h<figura.length;h++){
            for(int l = 0;l<figura.length;l++){
                if(random.nextBoolean()){
                    figura[h][l].ocupar();
                }
                else{
                    figura[h][l].desocupar();
                }
                
            }
        }
    }
    //Cambio de coordenadas del conjunto de la figura
    public void cambiar_pos(int x, int y){
        this.x = x;
        this.y = y;
        for(int h = 0;h<figura.length;h++){
            for(int l = 0;l<figura.length;l++){
                figura[h][l].set_x(x+30*h); 
                figura[h][l].set_y(y+30*l);
            }
        }
    }
    //metodos getters
    public int getx(){
        return x;
    }
    
    public int gety(){
        return y;
    }
    
    public Chocolate get_Chocolate(int x, int y){
        return figura[x][y];
    }
    //metodo que rota la misma figura casilla por casilla
    public void rotar_figura(){
        //Esquinas
        Chocolate figura_auxiliar = figura[0][2];
        figura[0][2] = figura[0][0];
        figura[0][0] = figura[2][0];
        figura[2][0] = figura[2][2];
        figura[2][2] = figura_auxiliar;
        
        
        //Lados
        figura_auxiliar = figura[1][2];
        figura[1][2]=figura[0][1];
        figura[0][1]=figura[1][0];
        figura[1][0]=figura[2][1];
        figura[2][1]=figura_auxiliar;
        
        //Modificar la posición
        cambiar_pos(x,y);
        
    }
}
