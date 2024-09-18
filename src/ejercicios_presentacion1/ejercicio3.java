package ejercicios_presentacion1;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Clase ejercicio3.
 */
public class ejercicio3 {

	/**
	 * Metodo que comprueba si hay departamentos suficientes como el que se le 
	 * pasa por parametro y si es asi, borra el que recibe y deja el archivo 
	 * con el resto
	 *
	 * @param args el departamento que se quiere borrar
	 */
	public static void main(String[] args) {
		try {
			RandomAccessFile raf=new RandomAccessFile(new File
					("./carpeta/Departamentos.dat"), "r");
			int identificador = Integer.parseInt(args[0]);
			long posicion=0;
			int cantidad=0;
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
				raf.close();
			}
			else {
				raf.close();
				
				//lectura del fichero para su edicion
				raf=new RandomAccessFile(new File
						("./carpeta/Departamentos.dat"), "r");
				posicion=0;
				ArrayList<Integer> lstID=new ArrayList<Integer>();
				ArrayList<String> lstNombre=new ArrayList<String>();
				ArrayList<String> lstLocalidad=new ArrayList<String>();
				raf.seek(posicion);
				for(int i=0;i<Integer.parseInt(args[0])-1;i++) {
					lstID.add(raf.readInt());
					posicion+=Integer.BYTES;
					String nombre="";
					char leido='\n';
					while(leido!='\t') {
						posicion+=Character.BYTES;
						leido=raf.readChar();
						nombre+=leido;
					}
					lstNombre.add(nombre);
					leido='\n';
					String localidad="";
					while(leido!='\t') {
						posicion+=Character.BYTES;
						leido=raf.readChar();
						localidad+=leido;
					}
					lstLocalidad.add(localidad);
					cantidad++;
				}
				raf.readInt();
				posicion+=Integer.BYTES;
				char leido='\n';
				while(leido!='\t') {
					leido=raf.readChar();
					posicion+=Character.BYTES;
				}
				posicion+=Character.BYTES;
				leido='\n';
				while(leido!='\t') {
					leido=raf.readChar();
					posicion+=Character.BYTES;
				}
				posicion+=Character.BYTES;
				while(posicion<raf.length()) {
					lstID.add(raf.readInt());
					posicion+=Integer.BYTES;
					String nombre="";
					leido='\n';
					while(leido!='\t') {
						posicion+=Character.BYTES;
						leido=raf.readChar();
						nombre+=leido;
					}
					lstNombre.add(nombre);
					leido='\n';
					String localidad="";
					while(leido!='\t') {
						posicion+=Character.BYTES;
						leido=raf.readChar();
						localidad+=leido;
					}
					lstLocalidad.add(localidad);
					cantidad++;
				}
				raf.close();
				DataOutputStream dos=new DataOutputStream(new FileOutputStream
						("./carpeta/Departamentos.dat"));
				for (int i=0;i<lstID.size();i++) {
					dos.writeInt(lstID.get(i));
					dos.writeChars(lstNombre.get(i));
					dos.writeChars(lstLocalidad.get(i));
				}
				dos.close();
				System.out.println(cantidad);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

}
