package ejercicios_presentacion1;


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Clase ejercicio1.
 */
public class ejercicio1 {

	/**
	 * Metodo que crea un archivo Departamentos.dat y le aniade 3 departamentos
	 *
	 * @param args No recibe nada
	 */
	public static void main(String[] args) {
		try {
			DataOutputStream dos=new DataOutputStream(new FileOutputStream(new 
					File("./carpeta/Departamentos.dat")));
			dos.writeInt(1);
			dos.writeChars("Dep1\t");
			dos.writeChars("Loc1\t");
			dos.writeInt(2);
			dos.writeChars("Dep2\t");
			dos.writeChars("Loc2\t");
			dos.writeInt(3);
			dos.writeChars("Dep3\t");
			dos.writeChars("Loc3\t");
			dos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
