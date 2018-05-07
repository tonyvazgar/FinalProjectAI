/*
 * Created by TonyVazgar on 5/5/18.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controler implements ActionListener {

    Interfaz view;
    ProjectDeductiveDatabase model;

    public Controler(ProjectDeductiveDatabase model, Interfaz view){
        this.model = model;
        this.view = view;
        view.consola.setText(ProjectDeductiveDatabase.preguntadosPreguntas());
        for(int i = 0; i < ProjectDeductiveDatabase.respuestas.size(); i++){
            view.respuestas.addItem(ProjectDeductiveDatabase.respuestas.get(i));
        }
    }

    public void actionPerformed(ActionEvent event){
        Button botonAccionado = (Button) event.getSource();
        if(botonAccionado == view.siguiente){
            actualizarMarcadores();
            ProjectDeductiveDatabase.checarRespuesta(view.respuestas.getItemAt(view.respuestas.getSelectedIndex()).toString());
            view.respuestas.removeAllItems();
            view.consola.setText(ProjectDeductiveDatabase.preguntadosPreguntas());
            for(int i = 0; i < ProjectDeductiveDatabase.respuestas.size(); i++){
                view.respuestas.addItem(ProjectDeductiveDatabase.respuestas.get(i));
            }
            if(ProjectDeductiveDatabase.puntaje > 8){
                view.setVisible(false);
                JOptionPane.showMessageDialog(view, "Haz ganado la ronda!!", "AVISO", JOptionPane.PLAIN_MESSAGE);
            }
            if(ProjectDeductiveDatabase.errores > 4){
                view.setVisible(false);
                JOptionPane.showMessageDialog(view, "Haz perdido el juego!", "AVISO", JOptionPane.PLAIN_MESSAGE);
            }
            actualizarMarcadores();
        }
    }

    private void actualizarMarcadores(){
        view.puntos.setText(Integer.toString(ProjectDeductiveDatabase.puntaje));
        view.errores.setText(Integer.toString(ProjectDeductiveDatabase.errores));
    }
}
