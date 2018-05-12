import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Intro extends Frame implements ActionListener {

    Font tipografia = new Font("Helvetica", Font.PLAIN, 14);

    Button ok;
    Label infoNombre;
    Label info;
    Label info2;
    Label info3;
    Label patron1;
    Label patron2;
    Label patron3;

    public Intro() {
        setTitle("Intro");
        setBounds(100, 100, 400, 350);
        setLocationRelativeTo(null);
        setBackground(new Color(255,255,255));
        setLayout(null);
        setResizable(false);
        constuyeComponentes();
        setFont(tipografia);
        endProgram();
    }

    private void endProgram() {
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }
        });
    }

    private void constuyeComponentes() {
        ok = new Button("OK");
        ok.setBounds(150, 300, 80, 25);
        add(ok);
        ok.addActionListener(this::actionPerformed);

        infoNombre = new Label("Luis Antonio Vázquez García                    153675");
        infoNombre.setBounds(30, 50, 450, 20);
        add(infoNombre);

        info = new       Label("* Este proyecto usa DLV para relacionar preguntas");
        info.setBounds(30, 120, 450, 20);
        add(info);

        info2 = new       Label("  con respuestas deacuerdo a la categoria.");
        info2.setBounds(30, 140, 450, 20);
        add(info2);

        info3 = new       Label("* Las respuestas posibles son de la misma categoria");
        info3.setBounds(30, 160, 450, 20);
        add(info3);

        patron1 = new     Label("  que la pregunta a la que pertenece.");
        patron1.setBounds(30, 180, 300, 20);
        add(patron1);

        patron2 = new     Label("* Las preguntas se seleccionan aleatoriamente");
        patron2.setBounds(30, 200, 450,20);
        add(patron2);

        patron3 = new     Label("  entre todas las preguntas y puede que se repitan.");
        patron3.setBounds(30, 220, 450,20);
        add(patron3);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        Button botonAccionado = (Button) evento.getSource();
        if (botonAccionado == ok){
            ProjectDeductiveDatabase model;
            Interfaz view;
            Controler controler;
            model = new ProjectDeductiveDatabase();
            view = new Interfaz();
            controler = new Controler(model, view);
            view.inicia(controler);
            setVisible(false);
        }
    }

    public Interfaz ventanaPrincipal() {
        return new Interfaz();
    }


    public void inicia(){
        setVisible(true);
    }
}
