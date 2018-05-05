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

    TextArea consola;
    TextField verificarTF;
    Label verificarLabel;

    /* ---------------------------------------------- *
     *            Constructor de la clase             *
     * ---------------------------------------------- */
    public Interfaz() {
        setTitle("*** PREGUNTADOS ***");
        setBounds(100, 100, 600, 500);
        setLocationRelativeTo(null);
        setBackground(new Color(255,255,255));
        setLayout(null);
        setResizable(false);
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
        verificarButton = new Button("Verificar Respuesta");
        verificarTF = new TextField();
        verificarLabel = new Label("Ingresa una respuesta de arriba: ");

        verificarTF.setBounds(200, 340, 210, 20);
        verificarTF.setFont(tipografia);
        add(verificarTF);
        verificarButton.setBounds(225, 390, 150, 20);
        verificarButton.setFont(tipografia);
        add(verificarButton);
        verificarLabel.setBounds(200, 290, 300,20);
        verificarLabel.setFont(tipografia);
        add(verificarLabel);
    }

    private void ponerConsola(){
        consola = new TextArea();
        consola.setBounds(50, 50, 500, 220);
        consola.setEditable(false);
        add(consola);
    }

    private void endProgram() {
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
    }

    public static void main(String[] Args){
        ProjectDeductiveDatabase model;
        Interfaz view;
        Controler controler;
        //Menu menu;

        model = new ProjectDeductiveDatabase();
        view = new Interfaz();
        controler = new Controler(model, view);
        //menu = new Menu(model);

        view.inicia(controler);
        //menu.iniciar();
    }
}
