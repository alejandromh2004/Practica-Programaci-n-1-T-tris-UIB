/*
CLASE QUE EXTIENDE A LA CLASE JFRAME CON LA CUAL CREAREMOS LA PANTALLA DE INICIO DEL VIDEOJUEGO EN LA CUAL PODREMOS 
INTERACTUAR CON EL MENÚ, LOS AJUSTES DEL JUEGO, LAS PARTIDAS ANTERIORES, INCIAR JUEGO, SALIR Y OBTENER INFORMACIÓN
 */
package practica_final_programacion_ii;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MENU extends JFrame{
    PanelDisplay panelCentral=new PanelDisplay();
    public MENU(){
        //Creación de la ventana principal, su panel de contenidos y asignación de 
        super("VENTANA PRINCIPAL");
        Container panelContenidos=this.getContentPane();
        panelContenidos.setLayout(new BorderLayout());
        
        //Añadimos el panel central donde estará toda la información y juego de la clase PanelDisplay
        panelCentral.mostrarFondo();
        panelContenidos.add(panelCentral, BorderLayout.CENTER);
        
        //Creación botones columna de la izquierda
        JPanel columna_botones=new JPanel();
        columna_botones.setLayout(new GridLayout(5,1,2,2));
        String [] etiquetas_botones= {"NUEVA PARTIDA","CONFIGURACIÓN","HISTORIAL","INFORMACIÓN","SALIR"};
        for (int i=0;i!=5;i++){
            JButton boton_i=new JButton(etiquetas_botones[i]);
            boton_i.setBackground(Color.BLACK);
            boton_i.setForeground(Color.WHITE);
            boton_i.addActionListener(new gestorEventosMenu());
            columna_botones.add(boton_i);
            
        }
        panelContenidos.add(columna_botones, BorderLayout.WEST);
        
        //Creación barra de menú superior
        JMenuBar barraMenu= new JMenuBar();
        barraMenu.setLayout(new BorderLayout());
        barraMenu.setBackground(Color.BLACK);
        //Creacion panel de iconos con los botones con iconos
        JPanel barra_iconos=new JPanel();
        barra_iconos.setBackground(Color.BLACK);
        String [] nombre_iconos={"iconoNuevaPartida.jpg","iconoConfiguracion.jpg","iconoHistorial.jpg","iconoInformacion.jpg","iconoSalir.jpg"};
        for(int i=0;i!=5;i++){
            ImageIcon icono=new ImageIcon(nombre_iconos[i]);
            Dimension dimensionIcono=new Dimension(icono.getIconWidth(),icono.getIconHeight());
            JButton boton_l=new JButton();
            boton_l.setBackground(Color.BLACK);
            boton_l.setIcon(icono);
            boton_l.setPreferredSize(dimensionIcono);
            boton_l.setActionCommand(nombre_iconos[i]);
            boton_l.addActionListener(new gestorEventosMenu());
            barra_iconos.add(boton_l);
        }
        barraMenu.add(barra_iconos, BorderLayout.WEST);
        panelContenidos.add(barraMenu,BorderLayout.NORTH);

        //Ajustes dimensiones y características de la ventana principal
        this.setSize(1470,925);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    //Gestor de eventos de los botones e iconos del menú
    //con el cual se cambia el contenido del panel display
    //central de la ventana
    public class gestorEventosMenu implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            switch(e.getActionCommand()){
                case "NUEVA PARTIDA": panelCentral.iniciarPartida();
                break;
                case "CONFIGURACIÓN":panelCentral.mostrarConfiguracion();
                break;
                case "SALIR":System.exit(0);
                break;
                case "iconoSalir.jpg":System.exit(0);
                break;
                case "INFORMACIÓN": panelCentral.mostrarInformacion();
                break;
                case "iconoInformacion.jpg": panelCentral.mostrarInformacion();
                break;
                case "HISTORIAL": panelCentral.mostrarPartidas();
                break;
                case"iconoHistorial.jpg": panelCentral.mostrarPartidas();
                break;
                case"iconoConfiguracion.jpg": panelCentral.mostrarConfiguracion();
                    break;
                case"iconoNuevaPartida.jpg": panelCentral.iniciarPartida();
                    break;
            }

        }

    }

    
}
