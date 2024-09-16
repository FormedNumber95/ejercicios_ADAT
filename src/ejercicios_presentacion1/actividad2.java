package ejercicios_presentacion1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase actividad2.
 */
public class actividad2 {

	/**
	 * Metodo que recive la ruta de un archivo y te imprime por pantalla su 
	 * contenido
	 *
	 * @param args la ruta del archico en la posicion 0
	 */
	public static void main(String[] args) {
		try {
			BufferedReader br=new BufferedReader(new FileReader(new File(args[0])));
			String leido=br.readLine();
			while(leido!=null) {
				System.out.println(leido);
				leido=br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el archivo");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error de IO");
			e.printStackTrace();
		}

	}

}
