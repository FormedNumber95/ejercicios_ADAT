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
			DataOutputStream dis=new DataOutputStream(new FileOutputStream(new 
					File("./carpeta/Departamentos.dat")));
			dis.writeInt(1);
			dis.writeChars("Dep1");
			dis.writeChars("Loc1");
			dis.writeInt(2);
			dis.writeChars("Dep2");
			dis.writeChars("Loc2");
			dis.writeInt(3);
			dis.writeChars("Dep3");
			dis.writeChars("Loc3");
			dis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
