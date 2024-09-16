package ejercicios_presentacion1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ejercicio2 {

	public static void main(String[] args) {
		try {
			RandomAccessFile raf=new RandomAccessFile(new File
					("./carpeta/Departamentos.dat"), "rw");
			int identificador = Integer.parseInt(args[0]);
			//TODO pensar como hacer para sacar la longitud de los 2 String
			long posicion = (identificador -1 ) * (Integer.BYTES);
			if (posicion >= raf.length () ) {
				System.out.println("ID: "+identificador+
						", NO EXISTE EMPLEADO...");
			}
			else {
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
