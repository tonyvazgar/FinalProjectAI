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

	public static String cargarPersonas(){
		String personas = "***************************** Personas que existen *************************************\n\n";
		cargarArchivos();
		datos = getDatos(answerSet);
		for(Dato elDato:datos){
			if(elDato.getPredicate().equals("persona")){
				personas = personas + elDato.getArgument(0).toUpperCase() + "\n";
			}
		}
		return personas;
	}

	public static String cargarSintomasDePersonas() {
		cargarArchivos();
		String sintomas = "******************** Estas personas tienen estos sintomas *************************\n\n";
		datos = getDatos(answerSet);
		for(Dato elDato:datos){
			if(elDato.getPredicate().equals("tieneSintoma")){
				sintomas = sintomas + elDato.getArgument(0).toUpperCase() + "  tiene  " + elDato.getArgument(1) + "\n";
			}
		}
		return sintomas;
	}

	public static String cargarMedicinaEnfermedad() {
		cargarArchivos();
		String medicinas = "**********************MEDICAMENTOS PARA SINTOMAS***************************\n\n";
		datos = getDatos(answerSet);
		for(Dato elDato:datos){
			if(elDato.getPredicate().equals("enfermedad")){
				medicinas = medicinas + "Para " + elDato.getArgument(0).toUpperCase() + " tomar " + elDato.getArgument(1) + "\n";
			}
		}
		return medicinas;
	}

	public static String cargarPuedeEstarEnfermo() {
		cargarArchivos();
		String posibleEnfermedad = "***********ENFERMEDADES QUE PUEDE TENER UNA PERSONA***********\n\n";
		datos = getDatos(answerSet);
		for(Dato elDato:datos){
			if(elDato.getPredicate().equals("puedeEstarEnfermoDe")){
				posibleEnfermedad = posibleEnfermedad + elDato.getArgument(0).toUpperCase() + " puede tener  ->  " + elDato.getArgument(1).toUpperCase() + "\n";
			}
		}
		return posibleEnfermedad;
	}

	public static String cargarPosiblesMedicamentos() {
		cargarArchivos();
		String medicamentos = "***********MEDICAMENTOS PARA CURAR ENFERMEDADES***************************\n\n";
		datos = getDatos(answerSet);
		for(Dato elDato:datos){
			if(elDato.getPredicate().equals("puedeTomar")){
				medicamentos = medicamentos + elDato.getArgument(0).toUpperCase() + " puede tomar  ->  " + elDato.getArgument(1).toUpperCase() + "\n";
			}
		}
		return medicamentos;
	}

	public static String cargarEnfermedadesConCura() {
		cargarArchivos();
		String cura = "***********ESTAS ENFERMEDADES TIENEN CURA***********\n\n";
		datos = getDatos(answerSet);
		for(Dato elDato:datos){
			if(elDato.getPredicate().equals("enfermedadQueTieneCura")){
				cura = cura + elDato.getArgument(0).toUpperCase() + "\n";
			}
		}
		return  cura;
	}

	public static String cargarDesahuciado() {
		cargarArchivos();
		String muertos = "**********ESTAS PERSONAS YA NO TIENEN CURA**********\n\n";
		for(Dato elDato:datos){
			if(elDato.getPredicate().equals("noTieneCura")){
				muertos = muertos + "El paciente " + elDato.getArgument(0).toUpperCase() + " esta desahuciado." + "\n";
			}
		}
		return muertos;
	}

	public static String cargarAmigos() {
		cargarArchivos();
		String amigos = "***********PERSONAS QUE TIENEN LA MISMA ENFERMEDAD***************************\n\nDecimos que dos personas son compañeros de sintomas\n" +
				"si comparten los mismos sintomas y tienen la misma enfermedad\n\n";
		datos = getDatos(answerSet);
		for(Dato elDato:datos){
			if(elDato.getPredicate().equals("puedenSerAmigosEnfermos")){
				amigos = amigos + elDato.getArgument(0).toUpperCase() + " y " + elDato.getArgument(1).toUpperCase() + " pueden ser amigos enfermos." + "\n";
			}
		}
		return amigos;
	}

	public static String cargarAnswerSet(){
		cargarArchivos();
		return database.showInConsole();
	}

	public String resumen() {
		String resumen = "";
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingresa el nombre de la persona o del medicamento/enfermedad/Sintoma");
		String nombre = scanner.nextLine();
		cargarArchivos();
		datos = getDatos(answerSet);
		for(Dato elDato: datos){
			int i = 0;

			while (i < elDato.getArity())
			{
				if(elDato.getArgument(i).equals(nombre))
				{
					resumen = resumen + elDato.getPredicate() + elDato.arguments.toString().replace("[", "(").replace("]",")") + "\n";
				}
				i = i + 1;
			}
		}
		return resumen;
	}

	public static String resumenPorPalabra(String aBuscar) {
		String resumen = "\nRESULTADOS A BUSCAR PARA --> " + aBuscar.toUpperCase() + "\n\n";
		cargarArchivos();
		datos = getDatos(answerSet);
		Boolean encontrado = false;
		for (Dato elDato : datos) {
				int i = 0;
				while (i < elDato.getArity()) {
					if (elDato.getArgument(i).equals(aBuscar)) {
						encontrado = true;
						resumen = resumen + elDato.getPredicate() + elDato.arguments.toString().replace("[", "(").replace("]", ")") + "\n";
					}
					i = i + 1;
				}
		}
		if(!encontrado){
			return "\n\n\n\n\nNO HAY RESULTADOS PARA LA PALABRA " + aBuscar.toUpperCase();
		}


		return resumen;
	}

	public void agregar(String aAgregar) {
		cargarArchivos();
		Scanner scanner = new Scanner(System.in);
		if(aAgregar.equals("persona"))
		{
			System.out.println("Ingresa el nombre de la persona");
			String nombre = scanner.nextLine();
			fact = new Fact("persona");
			fact.addArgument(nombre);
			database.addFact(fact);
			database.show();
		}
		if(aAgregar.equals("sintomas"))
		{
			fact = new Fact("tieneSintoma");

			System.out.println("Ingresa el nombre de la persona a asignar sintoma");
			String nombre = scanner.nextLine();
			fact.addArgument(nombre);
			System.out.println("Ingresa el nombre del sitoma");
			nombre = scanner.nextLine();
			fact.addArgument(nombre);

			database.addFact(fact);
			database.show();
		}
	}

	public static String cargarEnfermedadesSinCura() {
		cargarArchivos();
		String epidemia = "*************ESTAS ENFERMEDADES SON PELIGROSISIMAS*************\n\nSON ENFERMEDADES QUE NO HAY MEDICAMENTOS PARA \n" +
				"CURARLAS, POR LO QUE PUEDEN HACER UNA EPIDEMIA!!!\n \n";
		datos = getDatos(answerSet);
		for(Dato elDato:datos){
			if(elDato.getPredicate().equals("enfermedadSinCura")){
				epidemia = epidemia + elDato.getArgument(0).toUpperCase() + "\n";
			}
		}
		return epidemia;
	}

}//end ProjectDeductiveDatabase
