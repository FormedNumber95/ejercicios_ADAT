package ejercicios_presentacion1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Clase actividad3.
 */
public class actividad3 {

	/**
	 * Metodo que recibe el identificador de un empleado y lo busca en el 
	 * fichero, si no esta muestra un mensaje de error, y si esta, enseña todos
	 *  sus datos
	 *
	 * @param args El identificador del empleado en la posicion 0
	 */
	public static void main(String[] args) {
		try {
			RandomAccessFile file = new RandomAccessFile(new File
					("./carpeta/aleatorio.dat"), "r");
			int identificador = Integer.parseInt(args[0]);
			int posicion = (identificador - 1 ) * 36;
			if (posicion >= file.length () ) {
				System.out.println("ID: "+identificador+
						", NO EXISTE EMPLEADO...");
			}
			else{
				file.seek (posicion); 
				int id=file.readInt();
				char apellido=file.readChar();
				int dep=file.readInt();
				double salario=file.readDouble();
				System.out.println("ID: "+id+", Apellido: "+apellido+
						", Departamento: "+dep+", Salario: "+salario);
			}
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
