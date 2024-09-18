package ejercicios_presentacion1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Clase ejercicio4.
 */
public class ejercicio4 {

	/**
	 * Metodo que copia un archivo que se recive por consola en otro que 
	 * tambien se recibe por consola
	 *
	 * @param args En el 0 el archivo origen y en el 1 el archvo destino
	 */
	public static void main(String[] args) {
		try {
			FileInputStream fis=new FileInputStream(args[0]);
			FileOutputStream fos=new FileOutputStream(args[1]);
			while(fis.available()>0) {
				fos.write(fis.read());
			}
			fis.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
