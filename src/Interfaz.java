/*
 * Created by TonyVazgar on 5/5/18.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Interfaz extends Frame{

    /* ---------------------------------------------- *
     *           Elementos de la interfaz             *
     * ---------------------------------------------- */

    Font tipografia = new Font("Helvetica", Font.PLAIN, 14);

    Button iniciar;
    Button verificarButton;
    Button siguiente;
    public JComboBox respuestas;

    Label consola;
    Label verificarLabel;
    Label puntosLabel, puntos, erroresLabel, errores;

    /* ---------------------------------------------- *
     *            Constructor de la clase             *
     * ---------------------------------------------- */
    public Interfaz() {
        setTitle("*** PREGUNTADOS ***");
        setBounds(100, 100, 500, 300);
        setLocationRelativeTo(null);
        setBackground(new Color(255,255,255));
        setLayout(null);
        setResizable(true);
        setFont(tipografia);
        constuyeComponentes();
        endProgram();
    }

    /* ---------------------------------------------- *
     *           Metodos del la clase view            *
     * ---------------------------------------------- */

    private void constuyeComponentes() {
        ponerBotones();
        ponerConsola();
    }

    private void ponerBotones(){
        iniciar = new Button("Iniciar juego");
        verificarButton = new Button("Confirmar respuesta");
        siguiente = new Button("Otra pregunta");
        respuestas = new JComboBox();
        verificarLabel = new Label("Ingresa una respuesta de arriba: ");
        puntosLabel = new Label("Puntos: ");
        puntos = new Label("0");
        erroresLabel = new Label("Errores: ");
        errores = new Label("0");


        respuestas.setBounds(150,140,210,30);
        add(respuestas);
        siguiente.setBounds(280,190,150,20);
        add(siguiente);
        verificarButton.setBounds(100, 190, 150, 20);
        verificarButton.setFont(tipografia);
        add(verificarButton);

        puntosLabel.setBounds(30,260,60,20);
        add(puntosLabel);
        puntos.setBounds(90, 260,100,20);
        add(puntos);
        erroresLabel.setBounds(390, 260, 60, 20);
        add(erroresLabel);
        errores.setBounds(450, 260,60,20);
        add(errores);
    }

    private void ponerConsola(){
        consola = new Label();
        consola.setBounds(100, 0, 500, 220);
        add(consola);
    }

    public void endProgram() {
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }
        });
    }

    public void inicia(Controler theControler) {
        setVisible(true);
        setActionListener(theControler);
    }

    public void setActionListener(Controler theController) {
        verificarButton.addActionListener(theController);
        siguiente.addActionListener(theController);
    }

}
