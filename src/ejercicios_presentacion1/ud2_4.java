package ejercicios_presentacion1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Clase ud2_4.
 */
public class ud2_4 {

	/**
	 * Metodo main que visualiza el menu y lanza el metodo dependiendo 
	 * de la opcion elegida por el usuario.
	 *
	 * @param args Los argumentos que recive por consola, ninguno
	 */
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		System.out.println("1. Crear fichero serializable de olimpiadas");
		System.out.println("2. Añadir edición olímpica");
		System.out.println("3. Buscar olimpiadas por sede");
		System.out.println("4. Eliminar edición olímpica");
		int opcion=input.nextInt();
		input.nextLine();
		switch(opcion) {
			case 1:
				crearFichero();
				break;
			case 2:
				System.out.println("Dime el año");
				int anio=input.nextInt();
				input.nextLine();
				System.out.println("Dime la temporada");
				String temp=input.nextLine();
				System.out.println("Dime la ciudad");
				String ciudad=input.nextLine();
				aniadirEdicionOlimpica(anio,temp,ciudad);
				break;
			case 3:
				System.out.println("Dime la sede de las olimpiadas");
				String sede=input.nextLine();
				buscarOlimpiadas(sede);
				break;
			case 4:
				System.out.println("Dime el año de la edicion");
				anio=input.nextInt();
				input.nextLine();
				do {
					System.out.println("Dime la temporada:\n1. Summer\n2."
							+ " Winter");
					temp=input.nextLine();
				}while(!temp.equals("1")&&!temp.equals("2"));
				if(temp.equals("1")) {
					temp="\"Summer\"";
				}
				else{
					temp="\"Winter\"";
				}
				eliminarEdicionOlimpica(anio,temp);
				break;
			default:
				break;
		}
	}

	/**
	 * Eliminar edicion olimpica.
	 *
	 * @param anio El anio
	 * @param temp La temporada
	 */
	private static void eliminarEdicionOlimpica(int anio, String temp) {
		boolean borrado=false;
		ArrayList<Olimpiada> lstOlimpiadas=crearLstOlimpiadas();
		Iterator<Olimpiada> it=lstOlimpiadas.iterator();
		while(it.hasNext()){
			Olimpiada ol=it.next();
			if(ol.anio==anio&&ol.temporada.equals(temp)) {
				it.remove();
				borrado=true;
			}
		}
		if(!borrado) {
			System.out.println("No se ha encontrado ninguna edicion de ese año y "
					+ "esa temporada");
		}else {
			try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(
					"Datos_Olimpiadas/olimpiadas.bin"))){
				Iterator<Olimpiada> it2=lstOlimpiadas.iterator();
				while(it2.hasNext()) {
					oos.writeObject(it2.next());
				}
				oos.writeObject(null);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Buscar olimpiadas.
	 *
	 * @param sede La sede
	 */
	private static void buscarOlimpiadas(String sede) {
		ArrayList<Olimpiada> lstOlimpiadas=crearLstOlimpiadas();
		for(Olimpiada ol:lstOlimpiadas) {
			if(ol.ciudad.contains(sede)) {
				System.out.println(ol);
			}
		}
	}

	/**
	 * Aniadir edicion olimpica.
	 *
	 * @param anio El anio
	 * @param temp La temporada
	 * @param ciudad La ciudad
	 */
	private static void aniadirEdicionOlimpica(int anio, String temp, String ciudad) {
		ArrayList<Olimpiada> lstOlimpiadas = crearLstOlimpiadas();
		lstOlimpiadas.add(new Olimpiada(anio, anio+" "+temp, temp, ciudad));
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("Datos_Olimpiadas/olimpiadas.bin"))){
			Iterator<Olimpiada> it=lstOlimpiadas.iterator();
			while(it.hasNext()) {
				oos.writeObject(it.next());
			}
			oos.writeObject(null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crear lst olimpiadas.
	 *
	 * @return La lista de las olimpiadas
	 */
	static ArrayList<Olimpiada> crearLstOlimpiadas() {
		ArrayList<Olimpiada> lstOlimpiadas=new ArrayList<Olimpiada>();
		try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream("Datos_Olimpiadas/olimpiadas.bin"))){
			Olimpiada leida=(Olimpiada) ois.readObject();
			while(leida!=null) {
				lstOlimpiadas.add(leida);
				leida=(Olimpiada) ois.readObject();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return lstOlimpiadas;
	}

	/**
	 * Crear fichero olimpuadas basado en un xml.
	 */
	private static void crearFichero() {
		 
		 try {
			 XMLReader procesadorXML=XMLReaderFactory.createXMLReader();
			 GestorContenido2 gestor=new GestorContenido2();
			 procesadorXML.setContentHandler(gestor);
			 InputSource fileXML=new InputSource("Datos_Olimpiadas/olimpiadas.xml");
			procesadorXML.parse(fileXML);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

}

/**
 * Clase Olimpiada.
 */
class Olimpiada implements Serializable{
	
	/** El anio. */
	int anio;
	
	/** Los juegos. */
	String juegos;
	
	/** La temporada. */
	String temporada;
	
	/** La ciudad. */
	String ciudad;
	
	/**
	 * Constructor olimpiada.
	 *
	 * @param anio El anio
	 * @param juegos Los juegos
	 * @param temporada La temporada
	 * @param ciudad La ciudad
	 */
	Olimpiada(int anio,String juegos,String temporada,String ciudad){
		this.anio=anio;
		this.juegos=juegos;
		this.temporada=temporada;
		this.ciudad=ciudad;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Año: "+anio+", Juegos: "+juegos+", Temporada: "+temporada+", Ciudad: "+ciudad;
	}
}

/**
 * Clase GestorContenido2.
 */
class GestorContenido2 extends DefaultHandler {
	
	/** La lista de olimpiadas. */
	ArrayList<Olimpiada> lstOlimpiadas=new ArrayList<Olimpiada>();
	
	/** El anio. */
	int anio;
	
	/** Los juegos. */
	String juegos;
	
	/** La temporada. */
	String temporada;
	
	/** La ciudad. */
	String ciudad;
	
	/** El esJuegos. */
	boolean esJuegos=false;
	
	/** El esTemporada. */
	boolean esTemporada=false;
	
	/** El esCiudad. */
	boolean esCiudad=false;
	int cont=0;
	
	/**
	 * Start element.
	 *
	 * @param uri El uri
	 * @param localName El local name
	 * @param qName El q name
	 * @param attributes Los attributes
	 * @throws SAXException El SAX exception
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(qName.equals("Olimpiada")) {
			cont++;
			try {
				anio=Integer.parseInt(attributes.getValue("year"));
			}catch(NumberFormatException e) {
				System.out.println(cont);
			}
			
		}
		if(qName.equals("juegos")) {
			esJuegos=true;
		}
		if(qName.equals("temporada")) {
			esTemporada=true;
		}
		if(qName.equals("ciudad")) {
			esCiudad=true;
		}
	}
	
	/**
	 * Characters.
	 *
	 * @param ch El ch
	 * @param start El indice de inicio
	 * @param length El length
	 * @throws SAXException El SAX exception
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(esJuegos) {
			juegos=new String(ch, start, length).trim();
			esJuegos=false;
		}
		if(esTemporada) {
			temporada=new String(ch, start, length).trim();
			esTemporada=false;
		}
		if(esCiudad) {
			ciudad=new String(ch, start, length).trim();
			esCiudad=false;
		}
	}
	
	/**
	 * End element.
	 *
	 * @param uri El uri
	 * @param localName El local name
	 * @param qName El q name
	 * @throws SAXException El SAX exception
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("Olimpiada")) {
			lstOlimpiadas.add(new Olimpiada(anio, juegos, temporada, ciudad));
		}
	}
	
	/**
	 * End document.
	 *
	 * @throws SAXException El SAX exception
	 */
	@Override
	public void endDocument() throws SAXException {
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("Datos_Olimpiadas/olimpiadas.bin"))){
			Iterator<Olimpiada> it=lstOlimpiadas.iterator();
			while(it.hasNext()) {
				oos.writeObject(it.next());
			}
			oos.writeObject(null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
