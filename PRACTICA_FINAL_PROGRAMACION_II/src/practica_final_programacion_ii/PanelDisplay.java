package practica_final_programacion_ii;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;

public class PanelDisplay extends JPanel {
    public String nombreTexto;
    public JTextArea textoDisplay = null;
    public Partida partida;
    public JPanel panelInformacion, panelPartidas, panelUIB;
    int tiempo = 30;

    public PanelDisplay() {
        super();
        this.setLayout(new CardLayout());

        // Panel que contiene la partida actual
        partida = new Partida(this);
        this.add(partida, "PARTIDA");

        // Panel de la información del programa
        panelInformacion = new JPanel();
        textoDisplay = new JTextArea();
        textoDisplay.setEditable(false);
        textoDisplay.setOpaque(false);
        textoDisplay.setFont(new Font("arial", Font.BOLD, 25));
        panelInformacion.add(textoDisplay);
        panelInformacion.setBackground(Color.GRAY);
        textoDisplay.setBackground(Color.GRAY);
        textoDisplay.setForeground(Color.WHITE);
        textoDisplay.setText("ESTA APLICACIÓN HA SIDO REALIZADA EN EL CONTEXTO DE LA ASIGNATURA DE PROGRAMACIÓN II \n" +
                "DEL PRIMER CURSO DE LOS ESTUDIOS DE INGENIERIA INFORMÁTICA DE LA UNIVERSITAT DE LES ILLES\n" +
                "BALEARS PARA EL CURSO ACADÉMICO 2023-2024.\n" +
                "PRESENTAN LA PRÁCTICA QUE HAN DE REALIZAR LOS ESTUDIANTES DE LA ASIGNATURA MENCIONADA.\n" +
                "LOS OBJETIVOS DE ESTA PRÁCTICA PASAN POR TRABAJAR CON UN ENTORNO GRÁFICO E INTERACTIVO \n" +
                "UTILIZANDO LAS PRESTACIONES QUE LES OFRECEN LAS LIBRERIAS GRÁFICAS DE JAVA Y LA APLICACIÓN \n" +
                "DE LOS CONCEPTOS DE OBJETOS Y TIPOS DE DATOS ABSTRACTOS CORRESPONDIENTES A LA PROGRAMACIÓN\n" +
                "ORIENTADA A OBJETOS.");
        panelInformacion.setLayout(new BorderLayout());
        panelInformacion.add(textoDisplay, BorderLayout.CENTER);
        this.add(panelInformacion, "INFORMACION");

        // Panel del registro de partidas
        panelPartidas = new JPanel();
        panelPartidas.setBackground(Color.GRAY);
        this.add(panelPartidas, "PARTIDAS");

        // Panel con la imagen por defecto del juego
        JLabel imagenUIB = new JLabel(new ImageIcon("TETRIS_UIB.jpg"));
        panelUIB = new JPanel();
        panelUIB.setLayout(new BorderLayout());
        panelUIB.add(imagenUIB, BorderLayout.CENTER);
        this.add(panelUIB, "FONDO");
    }
    //Metodo que se asegura que si no hay partida iniciada se inicia una mostrandola en el panel central
    public void iniciarPartida() {
        if (partida.partidaEnCurso()) {
            mostrarMensajePartidaEnCurso();
            return;
        }
        //Pide el nombre del jugador antes de iniciarla
        String nombre_jugador = JOptionPane.showInputDialog(this, "Nombre jugador: ");
        if(nombre_jugador!=null && !nombre_jugador.equals("")){
            partida.IniciarPartida(tiempo,nombre_jugador);
            CardLayout local = (CardLayout)(this.getLayout());
            local.show(this, "PARTIDA");
        }else {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un nombre de jugador válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
       
    }
    //Metodo que muestra el fondo de pantalla si no hay partida en curso y es llamado
    public void mostrarFondo() {
        if (partida.partidaEnCurso()) {
            mostrarMensajePartidaEnCurso();
            return;
        }
        CardLayout local = (CardLayout)(this.getLayout());
        local.show(this, "FONDO");
    }
    //Metodo que muestra en pantalla el contenido del fichero de partidas 
    public void mostrarPartidas() {
        if (partida.partidaEnCurso()) {
            mostrarMensajePartidaEnCurso();
            return;
        }

        panelPartidas.removeAll(); // Limpiar el panel antes de añadir el nuevo contenido

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

            panelPartidas.setLayout(new BorderLayout());
            panelPartidas.add(new JLabel("HISTORIAL PARTIDAS TETRIS UIB", SwingConstants.CENTER), BorderLayout.NORTH);
            panelPartidas.add(scrollPane, BorderLayout.CENTER);

        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.toString());
        }

        CardLayout local = (CardLayout)(this.getLayout());
        local.show(this, "PARTIDAS");
    }
    //metodo que muestra la información del juego mientras no haya partida en curso y se haya llamado
    public void mostrarInformacion() {
        if (partida.partidaEnCurso()) {
            mostrarMensajePartidaEnCurso();
            return;
        }
        CardLayout local = (CardLayout)(this.getLayout());
        local.show(this, "INFORMACION");
    }
    //metodo que abre ventanas emergentes con las cuales se puede modificar las características del juego
    public void mostrarConfiguracion() {
        if (partida.partidaEnCurso()) {
            mostrarMensajePartidaEnCurso();
            return;
        }
        //Edición de la ventana de ajustes abierta
        Object[] botones = new Object[]{"CONFIG JUEGO", "CAMBIAR TIEMPO", "NADA"};
        int opcionSeleccionada = JOptionPane.showOptionDialog(this, "DESCRIPCION", "CONFIGURACIÓN", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
        //Si se ha seleccionado la opción CONFIG JUEGO
        if (opcionSeleccionada == 0) {
            //Apertura de una nueva ventana
            JTextField config1 = new JTextField();
            JTextField config2 = new JTextField();
            JTextField config3 = new JTextField();
            JTextField config4 = new JTextField();
            Object[] message = {
                    "PUNTUACIÓN CASILLAS FORMAS ELIMINADAS DEL TABLERO: 1 punto:", config1,
                    "PUNTUACIÓN ROTAR FORMA: -20 puntos:", config2,
                    "PUNTUACIÓN NUEVA FORMA: -20 puntos:", config3,
                    "IMAGEN CASILLAS FORMAS: (1)chocolate (2)ladrillo (3)Mickey (4)Azulejo1 (5)Azulejo2: ", config4
            };
            //Mientras que el dato introducido no sea en blanco se hacen sets de los valores
            //introducidos por el usuario
            int option = JOptionPane.showConfirmDialog(null, message, "Config", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
            try{
                if (!config1.getText().isBlank()) {
                    partida.setIncremento(Integer.parseInt(config1.getText()));
                }
                if (!config2.getText().isBlank()) {
                    partida.setRotarFiguraDecremento(Integer.parseInt(config2.getText()));
                }
                if (!config3.getText().isBlank()) {
                    partida.setNuevaFiguraDecremento(Integer.parseInt(config3.getText()));
                }
                if (!config4.getText().isBlank()) {
                    Chocolate.setImagen(Integer.parseInt(config4.getText()));
                } 
                //Control de que lo introducido sea un número
            }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Por favor, introduce un número entero válido", "Error", JOptionPane.ERROR_MESSAGE);                    
            }
                
            }
        } else if (opcionSeleccionada == 1) {
            //Cambio del tiempo
            String t = JOptionPane.showInputDialog(this, "Cambiar tiempo por defecto (30 secs): ");
            try{
               if(t!=null){
                tiempo = Integer.parseInt(t);
            } 
            }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Por favor, introduce un número entero válido", "Error", JOptionPane.ERROR_MESSAGE);                                    
            }
            
            
        }
    }

    public void mostrarMensajePartidaEnCurso() {
        JOptionPane.showMessageDialog(this, "ANTES DEBES TERMINAR LA PARTIDA EN CURSO", "Partida en Curso", JOptionPane.WARNING_MESSAGE);
    }
}
