import it.unical.mat.dlv.program.Term;
import it.unical.mat.wrapper.FactResult;

import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;


public class ProjectDeductiveDatabase {

	static DeductiveDatabase database;
	static Vector<FactResult>  answerSet;
	static Vector<Dato> datos;
	static int i;
	static Dato dato;
	static Fact fact;
	static String palabra;
	static Vector<String> historia;
	static Vector<String> general;
	static Vector<String> musica;
	static Vector<String> ciencia;
	static Vector<String> preguntados;

	public static Vector<Dato> getDatos(Vector<FactResult>  answerSet) {
		Vector<Dato> datos;
		Dato dato;
		FactResult factResult;
		int i;
		int j;
        List<Term> lista;
        String predicate;
        StringTokenizer tokenizer;
		//
		datos = new Vector<Dato>();
		i = 0;
		while(i < answerSet.size()) {
			factResult = answerSet.get(i);
			lista = factResult.attributes();
            if(lista.size() == 0)       //predicado sin atributos (como iAmHungry)
                predicate = factResult.toString();
            else {
                tokenizer = new StringTokenizer(factResult.toString(), "(");
                predicate = tokenizer.nextToken();
            }//end else
			dato = new Dato(predicate);
            if(lista.size() != 0) {
                j = 0;
                while(j < lista.size()) {
                    dato.addArgument(lista.get(j).toString());
                    j = j + 1;
                }//end while
            }//end if
            datos.add(dato);
			i = i + 1;
		}//end while

		return datos;
	}//end getDatos

	public static void cargarArchivos(){
		database = new DeductiveDatabase("./dlv.bin");
		database.load("./preguntados.txt");
		answerSet = database.getAnswerSet();
	}

	public static String cargarAnswerSet(){
		cargarArchivos();
		return database.showInConsole();
	}

	public static String cargarPreguntasMusica(){
		cargarArchivos();
		musica = new Vector<String>();
		datos = getDatos(answerSet);
		for(Dato elDato:datos){
			if(elDato.getPredicate().equals("temaMusica")){
				musica.add(elDato.getArgument(0));
			}
		}
		return musica.toString();
	}
	public static String cargarPreguntasCiencia(){
		cargarArchivos();
		ciencia = new Vector<String>();
		datos = getDatos(answerSet);
		for(Dato elDato:datos){
			if(elDato.getPredicate().equals("temaCiencia")){
				ciencia.add(elDato.getArgument(0));
			}
		}
		return ciencia.toString();
	}

	public static String cargarPreguntasGeneral(){
		cargarArchivos();
		general = new Vector<String>();
		datos = getDatos(answerSet);
		for(Dato elDato:datos){
			if(elDato.getPredicate().equals("temaGeneral")){
				general.add(elDato.getArgument(0));
			}
		}
		return general.toString();
	}

	public static String cargarPreguntasHistoria(){
		cargarArchivos();
		historia = new Vector<String>();
		datos = getDatos(answerSet);
		for(Dato elDato:datos){
			if(elDato.getPredicate().equals("temaHistoria")){
				historia.add(elDato.getArgument(0));
			}
		}
		return historia.toString();
	}


	public static String preguntados(){
		preguntados = new Vector<String>();
		cargarPreguntasHistoria();
		cargarPreguntasGeneral();
		cargarPreguntasMusica();
		cargarPreguntasCiencia();

		for(int i = 0; i < historia.size(); i++){
			preguntados.add(historia.get(i));
		}
		for(int i = 0; i < general.size(); i++){
			preguntados.add(general.get(i));
		}
		for(int i = 0; i < musica.size(); i++){
			preguntados.add(musica.get(i));
		}
		for(int i = 0; i < ciencia.size(); i++){
			preguntados.add(ciencia.get(i));
		}
		int randomQuestion = (int) (Math.random() * preguntados.size()-1) + 1;
		return "PREGUNTA: \n\n" + preguntados.get(randomQuestion);
	}

}//end ProjectDeductiveDatabase
