package ejercicios_presentacion1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ud2_2 {
	public static void generarFicheroCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("Datos_Olimpiadas/athlete_events.csv"));
             BufferedWriter bw = new BufferedWriter(new FileWriter( "Datos_Olimpiadas/olimpiadas.csv"))) {
            String linea;
            String leido = br.readLine();
            if (leido != null) {
            	int indiceGames=-1;
            	int indiceYear=-1;
            	int indiceSeason=-1;
            	int indiceCity=-1;
            	String[] values = leido.split(",");
            	for(int i=0;i<values.length;i++) {
            		String valor=values[i];
            		if(valor.equals("\"Games\"")) {
            			indiceGames=i;
            		}
            		if(valor.equals("\"Year\"")) {
            			indiceYear=i;
            		}
            		if(valor.equals("\"Season\"")) {
            			indiceSeason=i;
            		}
            		if(valor.equals("\"City\"")) {
            			indiceCity=i;
            		}
            	}
                bw.write("\"Games\",\"Year\",\"Season\",\"City\"\n");
                while ((linea = br.readLine()) != null) {
                    String games=values[indiceGames];
                    String year=values[indiceYear];
                    String season=values[indiceSeason];
                    String city=values[indiceCity];

                    bw.write(games + "," + year + "," + season + "," + city + "\n");
                }
                System.out.println("Fichero generado");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private static void mostrarDeportistas(String cadenaABuscar) {
		try (BufferedReader br = new BufferedReader(new FileReader("Datos_Olimpiadas/athlete_events.csv"))) {
			String linea;
            String leido = br.readLine();
            if (leido != null) {
            	int indiceID=-1;
            	int indiceName=-1;
            	int indiceSex=-1;
            	int indiceAge=-1;
            	int indiceHeight=-1;
            	int indiceWeight=-1;
            	String[] values = leido.split(",");
            	for(int i=0;i<values.length;i++) {
            		String valor=values[i];
            		if(valor.equals("\"ID\"")) {
            			indiceID=i;
            		}
            		if(valor.equals("\"Name\"")) {
            			indiceName=i;
            		}
            		if(valor.equals("\"Sex\"")) {
            			indiceSex=i;
            		}
            		if(valor.equals("\"Age\"")) {
            			indiceAge=i;
            		}
            		if(valor.equals("\"Height\"")) {
            			indiceHeight=i;
            		}
            		if(valor.equals("\"Weight\"")) {
            			indiceWeight=i;
            		}
            	}
            	int i=0;
            	while ((linea = br.readLine()) != null) {
            		String nombre=values[indiceName];
            		if(nombre.contains(cadenaABuscar)) {
	            		 i++;
	                     values = linea.split(",");
	                     String ID=values[indiceID];
	                     String sexo=values[indiceSex];
	                     String edad=values[indiceAge];
	                     String altura=values[indiceHeight];
	                     String peso=values[indiceWeight];
	                     System.out.println("ID: "+ID+", Nombre: "+nombre+
	                    		 ", Sexo: "+sexo+", Edad: "+edad+", Altura: "+
	                    		 altura+", Peso: "+peso);
            		}
                 }
            	 if(i==0) {
            		 System.out.println("No se ha encontrado a ningun "
            		 		+ "deportista que contenga "+cadenaABuscar+" en "
            		 				+ "el nombre");
            	 }
            	
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void buscarPorDeporteYAnio(String deporte,String anio,int temporada) {
		//Sport,Year,Season
		String temp="Winter";
		if(temporada==1) {
			temp="Summer";
		}
		try (BufferedReader br = new BufferedReader(new FileReader("Datos_Olimpiadas/athlete_events.csv"))) {
			String linea;
            String leido = br.readLine();
            if (leido != null) {
            	int indiceSport=-1;
            	int indiceYear=-1;
            	int indiceSeason=-1;
            	int indiceName=-1;
            	int indiceEvent=-1;
            	int indiceMedal=-1;
            	int indiceGames=-1;
            	int indiceCity=-1;
            	String[] values = leido.split(",");
            	for(int i=0;i<values.length;i++) {
            		String valor=values[i];
            		if(valor.equals("\"Sport\"")) {
            			indiceSport=i;
            		}
            		if(valor.equals("\"Year\"")) {
            			indiceYear=i;
            		}
            		if(valor.equals("\"Season\"")) {
            			indiceSeason=i;
            		}
            		if(valor.equals("\"Name\"")) {
            			indiceName=i;
            		}
            		if(valor.equals("\"Event\"")) {
            			indiceEvent=i;
            		}
            		if(valor.equals("\"Medal\"")) {
            			indiceMedal=i;
            		}
            		if(valor.equals("\"Games\"")) {
            			indiceGames=i;
            		}
            		if(valor.equals("\"City\"")) {
            			indiceCity=i;
            		}
            	}
            	System.out.println(deporte);
            	HashMap<String,ArrayList<String>> mapa=new HashMap<String,
            			ArrayList<String>>();
            	while ((linea = br.readLine()) != null) {
            		values = linea.split(",");
            		if(values[indiceSport].equals("\""+deporte+"\"")&&
            				(values[indiceYear].equals(anio))&&
            				(values[indiceSeason].equals("\""+temp+"\""))) {
	                     String nombre=values[indiceName];
	                     String evento=values[indiceEvent];
	                     String medalla=values[indiceMedal];
	                     String juegos=values[indiceGames];
	                     String ciudad=values[indiceCity];
	                     if(!mapa.containsKey(juegos+" "+ciudad)) {
	                    	 mapa.put(juegos+" "+ciudad,
	                    			 new ArrayList<String>());
	                     }
	                     mapa.get(juegos+" "+ciudad).add("Nombre: "+nombre+
	                    		 ", Evento: "+evento+", Medalla: "+medalla);
            		}//TODO Comprobar que esté bien y hacer el syso correspondiente para mostrar los jugadores o el mensaje de no haber encontrado
            	}
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		System.out.println("1. Generar fichero csv de olimpiadas");
		System.out.println("2. Buscar deportista");
		System.out.println("3. Buscar deportistas por deporte y olimpiada");
		System.out.println("4. Añadir deportista");
		int opcion=input.nextInt();
		switch (opcion) {
		case 1:
			generarFicheroCSV();
			break;
		case 2:
			System.out.println("Dime a quien quieres buscar");
			String persona=input.next();
			mostrarDeportistas(persona);
			break;
		case 3:
			System.out.println("Dime el deprote");
			String deporte=input.next();
			System.out.println("Dime el año");
			String anio=input.next();
			int temporada=-1;
			do {
				System.out.println("Dime la temporada\n1 Summer\n2 Winter)");
				temporada=input.nextInt();
			}while(temporada!=1&&temporada!=2);
			
			buscarPorDeporteYAnio(deporte, anio, temporada);
			break;
		case 4:
	
			break;

		default:
			System.out.println("Opcion no valida");
			break;
		}
	}
}
