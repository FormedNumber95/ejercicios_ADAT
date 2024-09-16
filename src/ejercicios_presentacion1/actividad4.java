package ejercicios_presentacion1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Clase actividad4.
 */
public class actividad4 {

	/**
	 * Metodo que recibe un identificador de empleado y un importe y si el 
	 * empleado existe suba el salario del empleado el importe introducido
	 *
	 * @param args El identificador del empleado en la posicion 0 y la subida
	 * de sueldo en la posicion 1
	 */
	public static void main(String[] args) {
		try {
			RandomAccessFile file = new RandomAccessFile(new File
					("./carpeta/aleatorio.dat"), "rw");
			int identificador = Integer.parseInt(args[0]);
			long posicion = (identificador -1 ) * 36;
			if (posicion >= file.length () ) {
				System.out.println("ID: "+identificador+
						", NO EXISTE EMPLEADO...");
			}
			else {
				posicion+=28;
				file.seek(posicion);
				double salarioAnterior=file.readDouble();
				file.seek(posicion);
				file.writeDouble(salarioAnterior+Double.parseDouble(args[1]));
				
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
