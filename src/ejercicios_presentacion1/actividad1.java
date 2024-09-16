package ejercicios_presentacion1;

import java.io.File;

/**
 * Clase actividad1.
 */
public class actividad1 {

	/**
	 * Metodo al que se le pasa un argumento por la linea de comandos y te 
	 * dice los archivos que se encuentran en la ruta marcada
	 *
	 * @param args la ruta del directorio en la posicion 0
	 */
	public static void main(String[] args) {
		File f=new File(args[0]);
		String[] archivos=f.list();
		for(int i=0;i<archivos.length;i++) {
			System.out.println(archivos[i]);
		}
	}

}
