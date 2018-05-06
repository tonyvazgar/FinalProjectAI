public class Main {
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
