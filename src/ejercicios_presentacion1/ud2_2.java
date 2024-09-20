package ejercicios_presentacion1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ud2_2 {
//Pensar en casa como hacerlo
	public static void generarFicheroCSV() {
		try(DataOutputStream dos=new DataOutputStream(new FileOutputStream
					("Datos_Olimpiadas/olimpiadas.csv"));
				DataInputStream dis=new DataInputStream(new FileInputStream
						("Datos_Olimpiadas/athlete_events.csv"))) {
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
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
			
			break;
		case 3:
	
			break;
		case 4:
	
			break;

		default:
			System.out.println("Opcion no valida");
			break;
		}
	}

}
