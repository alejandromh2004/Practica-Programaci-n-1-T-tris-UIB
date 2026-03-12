package practica_final_programacion_ii;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class Partida extends JPanel implements MouseMotionListener, MouseListener {
    // Declaraciones de variables
    boolean pulsado = false;
    Cuadricula cuadricula;
    FiguraConjunto figura;
    Dibujo dibujo;
    JButton botonRotar, botonCambiar;
    Timer cronometro;
    BarraTiempo timer;
    UsuarioPartida usuarioJugando;
    int separacion_x;
    int separacion_y;
    JPanel panelInferior;
    JTextArea puntosActuales;
    JPanel informacionUsuario;
    JTextArea nombreActual;
    private final PanelDisplay panelDisplay;
    private boolean mensaje_mostrado = false;
    int decrementoRotar=20;
    int decrementoNueva=20;
    int incremento=20;

    // Constructor
    public Partida(PanelDisplay panelDisplay) {
        super();
        this.panelDisplay = panelDisplay;
        
    }
    public void setNuevaFiguraDecremento(int decr){
        decrementoRotar=decr;
    }
    public void setRotarFiguraDecremento(int decr){
        decrementoNueva=decr;
    }
    public void setIncremento(int incr){
        incremento=incr;
    }
    public void IniciarPartida(int tiempo, String nombre) {
        // Detener el cronómetro existente si hay uno en marcha
        if (cronometro != null && cronometro.isRunning()) {
            cronometro.stop();
        }

        timer = new BarraTiempo(100);
        timer.setValorBarraTemporal(100);

        mensaje_mostrado = false;
        
        usuarioJugando = new UsuarioPartida(nombre);
        usuarioJugando.IniciarPartida();
        final Partida thisPartida = this;

        metodoPrincipal();

        ActionListener gestorEventos = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer.getValorBarraTemporal() != timer.getValorMinimo()) {
                    // Decrementar el valor de la barra temporal
                    timer.setValorBarraTemporal(timer.getValorBarraTemporal() - 1);
                }
                if (timer.getValorBarraTemporal() == 0) {
                    usuarioJugando.AcabarPartida();
                    if (!mensaje_mostrado) {
                        JOptionPane.showMessageDialog(thisPartida, "PUNTUACIÓN:" + usuarioJugando.getPuntuacion());
                        mensaje_mostrado = true;
                        panelDisplay.mostrarFondo();

                        // Guardar la partida en el fichero
                        FicheroPartidas ficheroPartidas = new FicheroPartidas("partidasTetrisUIB.dat");
                        ficheroPartidas.escribirPartida(usuarioJugando, tiempo);
                        ficheroPartidas.cerrar();
                    }
                }
                puntosActuales.setText("PUNTUACIÓN:" + usuarioJugando.getPuntuacion());
                nombreActual.setText("NOMBRE:" + nombre);
            }
        };

        cronometro = new Timer(tiempo*9, gestorEventos);

        // Asegurarse de actualizar la interfaz gráfica
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                timer.setValorBarraTemporal(100); // Reiniciar la barra al 100%
                crearPanelInferior(); // Crear panel inferior después de inicializar la barra
                revalidate(); // Validar el layout después de los cambios
                repaint(); // Repintar para reflejar los cambios
            }
        });

        cronometro.start();
    }

    public boolean partidaEnCurso() {
        return usuarioJugando != null && usuarioJugando.getHayPartida();
    }

    public void crearPanelInferior() {
        // Método que crea un panel con los datos del usuario
        // y añade la barra temporal
        informacionUsuario = new JPanel();
        informacionUsuario.setLayout(new GridLayout(1, 2));
        nombreActual = new JTextArea("NOMBRE:" );

        nombreActual.setFont(new Font("Arial", Font.PLAIN, 24)); // Ajusta la fuente y el tamaño de la fuente aquí
        nombreActual.setEditable(false);
        nombreActual.setBackground(Color.BLACK);
        nombreActual.setForeground(Color.RED);

        puntosActuales = new JTextArea("PUNTUACIÓN:0");

        puntosActuales.setFont(new Font("Arial", Font.PLAIN, 24)); // Ajusta la fuente y el tamaño de la fuente aquí
        puntosActuales.setEditable(false);
        puntosActuales.setBackground(Color.BLACK);
        puntosActuales.setForeground(Color.RED);
        informacionUsuario.add(nombreActual);
        informacionUsuario.add(puntosActuales);

        panelInferior = new JPanel();
        panelInferior.setLayout(new GridLayout(2, 1));
        panelInferior.add(informacionUsuario);
        panelInferior.add(timer);

        this.add(panelInferior, BorderLayout.SOUTH);
    }
    //metodo principal con el cual se puede jugar al juego y gestionar los movimientos del ratón
    public void metodoPrincipal() {
        //Adición del mouse listener
        try {
            addMouseListener(this);
            addMouseMotionListener(this);
        } catch (Exception e) {
            System.out.println("");
        }

        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
        JPanel botonesFigura = new JPanel();
        botonesFigura.setLayout(new GridLayout(2, 1));
        //Adición de los botones para el juego
        botonRotar = new JButton();
        ImageIcon iconoRotar = new ImageIcon("iconoBotonRotar.jpg");
        botonRotar.setIcon(iconoRotar);
        botonRotar.setBackground(Color.BLACK);
        botonRotar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Evento que permite rotar la figura perdiendo puntos 
                figura.rotar_figura();
                figura.cambiar_pos(FiguraConjunto.coordenadas_init[0], FiguraConjunto.coordenadas_init[1]);
                usuarioJugando.DecrementarPuntuacion(decrementoRotar);
                dibujo.repaint();
            }
        });
        botonCambiar = new JButton();
        botonCambiar.setIcon(new ImageIcon("iconoBotonNuevaForma.jpg"));
        botonCambiar.setBackground(Color.BLACK);
        botonCambiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Evento que permite volver a mezclar la figura perdiendo puntos
                figura.mezclar();
                usuarioJugando.DecrementarPuntuacion(decrementoNueva);
                dibujo.repaint();
            }
        });
        botonesFigura.add(botonCambiar);
        botonesFigura.add(botonRotar);
        //Creación de la figura, tablero y dibujos que hacen estas
        figura = new FiguraConjunto();
        cuadricula = new Cuadricula();
        dibujo = new Dibujo();
        this.add(dibujo, BorderLayout.CENTER);
        this.add(botonesFigura, BorderLayout.EAST);
    }

    @Override
    //Ratón arrastrando
    public void mouseDragged(MouseEvent evento) {
        int x = evento.getX();
        int y = evento.getY();
        //mientras se está arrastrando con el botón pulsado se comprueban constantemente las posiciones 
        //y se pinta de nuevo la figura
        if (pulsado) {
            figura.cambiar_pos(x + separacion_x, y + separacion_y);
            
            cuadricula.reiniciar_colores();
            if (cuadricula.comprobar_posicion(figura, (figura.getx()) / 30, (figura.gety()) / 30)) {
                cuadricula.afegir_figura_en_movimiento(figura, (figura.getx()) / 30, (figura.gety()) / 30, 1);
            } else {
                cuadricula.afegir_figura_en_movimiento(figura, (figura.getx()) / 30, (figura.gety()) / 30, 2);
            }
            dibujo.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent evento) {}

    @Override
    //Cuando se pulsa se marca como figura pulsada y se actualiza tambien las separaciones
    //de la figura
    public void mousePressed(MouseEvent evento) {
        pulsado = true;
        int x = evento.getX();
        int y = evento.getY();
        separacion_x = FiguraConjunto.coordenadas_init[0] - x;
        separacion_y = FiguraConjunto.coordenadas_init[1] - y;
    }

    @Override
    //Una vez soltado se pone como pulsado=false y se comprueba si la figura se puede poner en ese sitio y si la figura 
    //ha hechoq que se rellene alguna fila/columna
    public void mouseReleased(MouseEvent evento) {
        pulsado = false;
        if (cuadricula.comprobar_posicion(figura, (figura.getx()) / 30, (figura.gety()) / 30)) {
            cuadricula.afegir_figura(figura, (figura.getx()) / 30, (figura.gety()) / 30);
            figura.mezclar();
        }
        for (int i = 0; i < 20; i++) {
            if (cuadricula.vaciar_fila(i) == true) {
                cuadricula.vaciar_fila(i);
                usuarioJugando.IncrementarPuntuacion(incremento);
            }
            if (cuadricula.vaciar_columna(i) == true) {
                cuadricula.vaciar_columna(i);
                usuarioJugando.IncrementarPuntuacion(incremento);
            }
        }
        cuadricula.reiniciar_colores();
        figura.cambiar_pos(FiguraConjunto.coordenadas_init[0], FiguraConjunto.coordenadas_init[1]);

        dibujo.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    //clase que pinta la componente 
    private class Dibujo extends JComponent {
        @Override
        public void paintComponent(Graphics g) {
            //Dibujo del decorado alrededor de la siguiente figura
            g.drawImage(Chocolate.decoracion, FiguraConjunto.coordenadas_init[0] - 15, FiguraConjunto.coordenadas_init[1] - 280, this);
            //Dibujo de la figura una vez agarrada y soltada por el usuario
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    if (cuadricula.get_Chocolate(i, j).get_es_libre()) {
                        g.drawImage(Chocolate.libre, cuadricula.get_Chocolate(i, j).get_x(), cuadricula.get_Chocolate(i, j).get_y(), this);
                    } else if (cuadricula.get_Chocolate(i, j).get_es_ocupada()) {
                        g.drawImage(Chocolate.ocupado, cuadricula.get_Chocolate(i, j).get_x(), cuadricula.get_Chocolate(i, j).get_y(), this);
                    }
                    if (cuadricula.get_Chocolate(i, j).get_ocupada()) {
                        g.drawImage(Chocolate.imagen, Cuadricula.coordenadas_init[0] + i * 30, Cuadricula.coordenadas_init[1] + j * 30, this);
                    }
                }
            }
            //Dibujo del tablero
            for (int i = 0; i < 21; i++) {
                g.drawLine(Cuadricula.coordenadas_init[0] + i * 30, Cuadricula.coordenadas_init[1], Cuadricula.coordenadas_init[0] + i * 30, Cuadricula.coordenadas_init[1] + 20 * 30);
                g.drawLine(Cuadricula.coordenadas_init[0], Cuadricula.coordenadas_init[1] + i * 30, Cuadricula.coordenadas_init[0] + 20 * 30, Cuadricula.coordenadas_init[1] + i * 30);
            }
            //Dibujo de la figura 3x3 siguiente
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (figura.get_Chocolate(i, j).get_ocupada()) {
                        g.drawImage(Chocolate.imagen, figura.get_Chocolate(i, j).get_x(), figura.get_Chocolate(i, j).get_y(), this);
                    }
                }
            }
        }
    }
    //metodo que permite mostrar el historial de las partidas guardadas en el fichero  partidasTetrisUIB.dat
    public void mostrarHistorial() {
        try {
            FicheroPartidas ficheroPartidas = new FicheroPartidas("partidasTetrisUIB.dat");
            String historial = ficheroPartidas.leerHistorial();
            ficheroPartidas.cerrar();

            JTextArea textArea = new JTextArea(historial);
            textArea.setEditable(false);
            textArea.setBackground(Color.DARK_GRAY);
            textArea.setForeground(Color.WHITE);
            textArea.setFont(new Font("Arial", Font.PLAIN, 16));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(800, 600));

            JPanel panelHistorial = new JPanel(new BorderLayout());
            panelHistorial.add(new JLabel("HISTORIAL PARTIDAS TETRIS UIB", SwingConstants.CENTER), BorderLayout.NORTH);
            panelHistorial.add(scrollPane, BorderLayout.CENTER);

            JFrame frame = new JFrame("Historial de Partidas");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(panelHistorial);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.toString());
        }
    }
}
