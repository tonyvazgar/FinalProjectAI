import it.unical.mat.dlv.program.Term;
import it.unical.mat.wrapper.FactResult;

import java.util.*;


public class ProjectDeductiveDatabase {

	static DeductiveDatabase database;
	static Vector<FactResult>  answerSet;
	static Vector<Dato> datos;
	static Vector<String> historia;
	static Vector<String> general;
	static Vector<String> musica;
	static Vector<String> ciencia;
	static Vector<String> preguntados;
	static Vector<String> respuestas;
	static int puntaje;
	static int errores;
	static String respuestaCorrecta;

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

	public static String cargarRespuestaPregunta(String pregunta){
        cargarArchivos();
        datos = getDatos(answerSet);
        String respuestaCor = "";
        String respuesta = "";
        String tema = "";
        respuestas = new Vector<String>();
        for(Dato elDato: datos){
            if(elDato.getPredicate().equals("pregunta")) {
                if(elDato.getArgument(1).replace("\"","").equals(pregunta)) {
                    tema = elDato.getArgument(0);
                    respuestaCorrecta = elDato.getArgument(2).replace("\"","");
                }
            }
        }
        for(Dato elDato: datos){
            if(elDato.getPredicate().equals("pregunta") && elDato.getArgument(0).equals(tema)){
                respuestas.add(elDato.getArgument(2).replace("\"",""));
            }
        }
        for(int i = 0; i < respuestas.size(); i++){
            respuesta += respuestas.get(i);
        }
	    return "\nEscribe abajo una de las siguientes respuestas:\n" + respuesta;
    }

	public static String preguntadosPreguntas(){
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
		String pregunta = preguntados.get(randomQuestion).replace("\"","");

        cargarRespuestaPregunta(pregunta);

        return pregunta;
	}


    public static void checarRespuesta(String respuesta) {
	    System.out.println("CORRECTA --> " + respuestaCorrecta);
	    System.out.println(respuesta);
	    if(respuestaCorrecta.equals(respuesta)){
	        System.out.println("Correcto");
	        puntaje = puntaje + 1;
        }else{
	        System.out.println("Incorrecto");
	        errores = errores + 1;
        }
    }
}//end ProjectDeductiveDatabase
