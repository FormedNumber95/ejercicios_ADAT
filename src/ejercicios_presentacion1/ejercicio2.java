package ejercicios_presentacion1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Clase ejercicio2.
 */
public class ejercicio2 {

	/**
	 * Metdodo que cambia el nombre y la localidad del departametno que se pasa
	 *  por consola, por el nombre y la localidad que pasa
	 *
	 * @param args El id del departamento a modificar en el 0, el nombre en el 
	 * 1 y la localidad en el 2
	 */
	public static void main(String[] args) {
		try {
			RandomAccessFile raf=new RandomAccessFile(new File
					("./carpeta/Departamentos.dat"), "rw");
			int identificador = Integer.parseInt(args[0]);
			long posicion=0;
			for(int i=0;i<Integer.parseInt(args[0])-1;i++) {
				raf.readInt();
				posicion+=Integer.BYTES;
				char leido='\n';
				while(leido!='\t') {
					leido=raf.readChar();
					posicion+=Character.BYTES;
				}
				leido='\n';
				while(leido!='\t') {
					leido=raf.readChar();
					posicion+=Character.BYTES;
				}
			}
			if (posicion >= raf.length () ) {
				System.out.println("ID: "+identificador+
						", NO EXISTE EMPLEADO...");
			}
			else {
				raf.readInt();
				posicion+=Integer.BYTES;
				char leido='\n';
				while(leido!='\t') {
					leido=raf.readChar();
				}
				leido='\n';
				while(leido!='\t') {
					leido=raf.readChar();
				}
				String resto=raf.readLine();
				raf.seek(posicion);
				raf.writeChars(args[1]+'\t');
				raf.writeChars(args[2]+'\t');
				raf.writeChars(resto);
			}
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
