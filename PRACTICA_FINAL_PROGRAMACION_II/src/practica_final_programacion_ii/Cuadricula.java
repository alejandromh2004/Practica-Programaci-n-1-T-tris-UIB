/*
    CLASE CUADRICULA la cual crea el tablero de casillas chocolate, cada una de estas cuenta con
    los atributos vistos en la clase chocolate (como su imagen, si está ocupadas,etc)
*/
package practica_final_programacion_ii;

public class Cuadricula {

    Chocolate[][] cuadricula;
    public static int[] coordenadas_init = {21,21};
    //Constructor del array bidimensional de chocolates con su coordenada respectiva
    public Cuadricula() {
        cuadricula = new Chocolate[20][20];
        for (int h = 0; h < cuadricula.length; h++) {
            for (int l = 0; l < cuadricula.length; l++) {
                cuadricula[h][l] = new Chocolate(coordenadas_init[0] + 30 * h, coordenadas_init[1] + 30 * l);
            }
        }
    }
    //metodo que comprueba si está llena una fila devolviendo su booleano correspondiente
    public boolean vaciar_fila(int f){
        for(int i = 0;i<20;i++){
            if(!cuadricula[i][f].get_ocupada()){
                return false;
            }
        }
        //Vacía la fila
        for(int i = 0;i<20;i++){
            cuadricula[i][f].desocupar();
        }
        return true;
    }
    //metodo que comprueba si está llena una columna devolviendo su booleano correspondiente
    public boolean vaciar_columna(int f){
        for(int i = 0;i<20;i++){
            if(!cuadricula[f][i].get_ocupada()){
                return false;
            }
        }
        //Vacía la columna
        for(int i = 0;i<20;i++){
            cuadricula[f][i].desocupar();
        }
        return true;
    }
    //Comprueba si la figura soltada está dentro de una zona posible de poner
    public boolean comprobar_posicion(FiguraConjunto fig, int x, int y) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    if (fig.get_Chocolate(i, j).get_ocupada() && cuadricula[x + i][y + j].get_ocupada()) {
                        return false;
                    }
                } catch (Exception e) {
                    if (fig.get_Chocolate(i, j).get_ocupada()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    //Hace un set de las casillas de la figura soltada si se ha podido poner
    public void afegir_figura(FiguraConjunto fig, int x, int y) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    if (fig.get_Chocolate(i, j).get_ocupada()) {
                        cuadricula[x + i][y + j].ocupar();
                    }
                } catch (Exception e) {

                }
            }
        }
    }

    public void afegir_figura_en_movimiento(FiguraConjunto fig, int x, int y, int color) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (fig.get_Chocolate(i, j).get_ocupada()) {
                    try {
                        if (color == 2) {
                            cuadricula[x + i][y + j].set_pintar_ocupado(true);
                        } else {
                            cuadricula[x + i][y + j].set_pintar_libre(true);
                        }
                    } catch (Exception e) {

                    }

                }
            }

        }

    }
    public void reiniciar_colores() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {

                cuadricula[i][j].set_pintar_ocupado(false);
                cuadricula[i][j].set_pintar_libre(false);

            }
        }
    }

    public Chocolate get_Chocolate(int x, int y) {
        return cuadricula[x][y];
    }

}
