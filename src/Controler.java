/*
 * Created by TonyVazgar on 5/5/18.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controler implements ActionListener {

    Interfaz view;
    ProjectDeductiveDatabase model;

    public Controler(ProjectDeductiveDatabase model, Interfaz view){
        this.model = model;
        this.view = view;
    }

    public void actionPerformed(ActionEvent event){
        Button botonAccionado = (Button) event.getSource();
        if (botonAccionado == view.verificarButton) {
            view.consola.setText(ProjectDeductiveDatabase.preguntados());
        }
    }

    /*private String obtieneDatoDelView() {
        String aBuscar;
        aBuscar = view.buscarPorPalabraField.getText();
        return aBuscar;
    }*/
}
