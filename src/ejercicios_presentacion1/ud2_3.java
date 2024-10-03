package ejercicios_presentacion1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class ud2_3 {
	
	private static void crearXmlOlimpiadas() {
		try(BufferedReader br=new BufferedReader(new FileReader("Datos_Olimpiadas/olimpiadas.csv"))){
			DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
	        Document doc=docBuilder.newDocument();
	        Element rootElement=doc.createElement("Olimpiadas");
            doc.appendChild(rootElement);
            String line;
            String[] headers=null;
            int lineNumber=0;
            while ((line=br.readLine())!=null) {
                String[] values=line.split(",");
                if (lineNumber==0) {
                    headers=values;
                    for(int i=0;i<headers.length;i++) {
                    	if(headers[i].equals("\"Games\"")) {
                    		headers[i]="juegos";
                    	}else {
                    		if(headers[i].equals("\"Season\"")) {
                    			headers[i]="temporada";
                    		}else {
                    			if(headers[i].equals("\"City\"")) {
                    				headers[i]="ciudad";
                    			}
                    		}
                    	}
                    }
                } else {
                	Element rowElement=doc.createElement("Olimpiada");
                	for (int i=0;i<values.length;i++) {
                		Element columnElement=null;
                		if(headers[i].equals("\"Year\"")) {
                			 rowElement.setAttribute("year", values[i]);
                		}else {
	                		if(headers[i].charAt(0)=='\"') {
	                			 columnElement=doc.createElement(headers[i].
	                					 substring(1,headers[i].length()-1));
	                		}else {
	                			 columnElement=doc.createElement(headers[i]);
	                		}
	                		columnElement.appendChild(doc.createTextNode(
	                				values[i]));
               			 	rowElement.appendChild(columnElement);
                		}
                        
                    }
                	rootElement.appendChild(rowElement);
                }
                lineNumber++;
            }
        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new 
        		File("Datos_Olimpiadas/olimpiadas.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	class Deportista {
		String id;
		String nombre;
		String sexo;
		String altura;
		String peso;
		String deporte;
		String edad;
		String noc;
		String equipo;
		String juegos;
		String ciudad;
		String evento;
		String medalla;
		//TODO Acabar La clase Deportista para luego añadir los objetos al xml y la lgica de añadir objetos al mapa, tiene que estar dentro de la clase xmlDeportistaIterable
		//ver si asi se arregla el problema de velocidad y el problema de solo leer 451 de las >135k lineas
	}
	class XmlDeportistaIterable implements Iterable, Iterator {
		public Iterator iterator() {
			return this;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Deportista next() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	private static void crearXmlDeportistas() {
		HashMap<String, ArrayList<String>> mapa=new HashMap<String,
    			ArrayList<String>>();
		HashMap<String, ArrayList<String>> mapaDeportes=new HashMap<String,
				ArrayList<String>>();
		try (BufferedReader br = new BufferedReader(new FileReader(
				"Datos_Olimpiadas/athlete_events.csv"))) {
			String linea;
            String leido = br.readLine();
            String[] values=null;
            if (leido!=null) {
            	int indiceID=-1;
            	int indiceName=-1;
            	int indiceSex=-1;
            	int indiceHeight=-1;
            	int indiceWeight=-1;
            	int indiceSport=-1;
            	int indiceAge=-1;
            	int indiceNOC=-1;
            	int indiceTeam=-1;
            	int indiceGames=-1;
            	int indiceCity=-1;
            	int indiceEvent=-1;
            	int indiceMedal=-1;
            	values=leido.split(",");
            	for(int i=0;i<values.length;i++) {
            		String valor=values[i];
            		if(valor.equals("\"ID\"")) {
            			indiceID=i;
            		}
            		if(valor.equals("\"Name\"")) {
            			indiceName=i;
            		}
            		if(valor.equals("\"Sex\"")) {
            			indiceSex=i;
            		}
            		if(valor.equals("\"Height\"")) {
            			indiceHeight=i;
            		}
            		if(valor.equals("\"Weight\"")) {
            			indiceWeight=i;
            		}
            		if(valor.equals("\"Sport\"")) {
            			indiceSport=i;
            		}
            		if(valor.equals("\"Age\"")) {
            			indiceAge=i;
            		}
            		if(valor.equals("\"NOC\"")) {
            			indiceNOC=i;
            		}
            		if(valor.equals("\"Team\"")) {
            			indiceTeam=i;
            		}
            		if(valor.equals("\"Games\"")) {
            			indiceGames=i;
            		}
            		if(valor.equals("\"City\"")) {
            			indiceCity=i;
            		}
            		if(valor.equals("\"Event\"")) {
            			indiceEvent=i;
            		}
            		if(valor.equals("\"Medal\"")) {
            			indiceMedal=i;
            		}
            	}
            	while ((linea=br.readLine()) != null) {
            		String[] values2=linea.split(",");
            		for(int i=0;i<values2.length;i++) {
            			if(values2[i].contains("\"")) {
            				values2[i]=values2[i].substring(1,values2[i].length()-1);
            			}
            		}
            		String id=values2[indiceID];
            		String nombre=values2[indiceName];
            		String sexo=values2[indiceSex];
            		String altura=values2[indiceHeight];
            		String peso=values2[indiceWeight];
            		String deporte=values2[indiceSport];
            		String edad=values2[indiceAge];
            		String noc=values2[indiceNOC];
            		String equipo=values2[indiceTeam];
            		String juegos=values2[indiceGames];
            		String ciudad=values2[indiceCity];
            		String evento=values2[indiceEvent];
            		String medalla=values2[indiceMedal];
            		if(!mapa.containsKey(id)) {
            			mapa.put(id+","+nombre+","+sexo+","+altura+","+peso,
            					new ArrayList<String>());
            		}
            		mapa.get(id+","+nombre+","+sexo+","+altura+","+peso).add(
            				deporte+","+edad+","+noc+","+equipo+","+juegos+","+
            		ciudad+","+evento+","+medalla);
            		if(!mapaDeportes.containsKey(deporte)) {
						mapaDeportes.put(deporte, new ArrayList<String>());
					}
					mapaDeportes.get(deporte).add(edad+","+noc+","+equipo+","+
					juegos+","+ciudad+","+evento+","+medalla);
            	}
            }
            for(int i=0;i<values.length;i++) {
            	if(values[i].charAt(0)=='\"') {
            		values[i]=values[i].substring(1,values[i].length()-1);
            	}
            }
            DocumentBuilderFactory docFactory=DocumentBuilderFactory.
            		newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
			Document doc=docBuilder.newDocument();
			Element deportistas=doc.createElement("deportistas");
			doc.appendChild(deportistas);
			int cont=1;
			for(Entry<String,ArrayList<String>>entrada:
        		mapa.entrySet()){
				Element deportista=doc.createElement("deportista");
				String[] persona=entrada.getKey().split(",");
				for(int i=0;i<persona.length;i++) {
					if(persona[i].charAt(0)=='\"') {
						persona[i]=persona[i].substring(1,persona[i].
								length()-1);
					}
				}
				deportista.setAttribute("id", persona[0]);
				aniadeElemento(doc, deportista, "nombre", persona[1]);
				aniadeElemento(doc, deportista, "sexo", persona[2]);
				aniadeElemento(doc, deportista, "altura", persona[3]);
				aniadeElemento(doc, deportista, "peso", persona[4]);
				for(String juego:mapa.get(persona[0]+","+persona[1]+","
				+persona[2]+","+persona[3]+","+persona[4])) {
					Element deporte=doc.createElement("deporte");
					String[] elementosDeporte= juego.split(",");
					for(int i=0;i<elementosDeporte.length;i++) {
						if(elementosDeporte[i].charAt(0)=='\"') {
							elementosDeporte[i]=elementosDeporte[i].substring
									(1, elementosDeporte[i].length()-1);
						}
					}
					
					deporte.setAttribute("nombre", elementosDeporte[0]);
					aniadirParticipacion(mapaDeportes, doc, deporte, elementosDeporte);
					deportista.appendChild(deporte);
				}
  			 	deportistas.appendChild(deportista);
  			 	System.out.println(cont);
  			 	cont++;
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(new File("Datos_Olimpiadas/deportisas.xml"));
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(source, result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	private static void aniadirParticipacion(HashMap<String, ArrayList<String>> mapaDeportes, Document doc, Element deporte, String[] elementosDeporte) {
		 StringBuilder juegosBuilder = new StringBuilder();
		 ArrayList<String> depos=mapaDeportes.get(elementosDeporte[0]);
		 Iterator<String> iterator = depos.iterator();
		while(iterator.hasNext()) {
			iterator.next();
			Element participacion=doc.createElement("participacion");
			participacion.setAttribute("age",elementosDeporte[1]);
			Element equipo=doc.createElement("equipo");
			equipo.setAttribute("abbr", elementosDeporte[2]);
			equipo.setTextContent(elementosDeporte[3]);
			participacion.appendChild(equipo);
			 juegosBuilder.setLength(0);
		        juegosBuilder.append(elementosDeporte[4]).append(" - ").append(elementosDeporte[5]);
			aniadeElemento(doc, participacion, "juegos", juegosBuilder.toString());
			aniadeElemento(doc, participacion, "evento", elementosDeporte[6]);
			aniadeElemento(doc, participacion, "medalla",elementosDeporte[7]);
			deporte.appendChild(participacion);
			equipo=null;
			participacion=null;
		}
		System.gc();
	}
	
	private static void aniadeElemento(Document doc, Element rowElement, String header, String texto) {
		Element elemento=doc.createElement(header);
		elemento.appendChild(doc.createTextNode(texto));
		rowElement.appendChild(elemento);
	}
	
	private static void mostrarListado() {
		 try {
			XMLReader lectorXML=XMLReaderFactory.createXMLReader();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		System.out.println("1. Crear fichero XML de olimpiadas");
		System.out.println("2. Crear un fichero XML de deportistas");
		System.out.println("3. Listado de olimpiadas");
		int opcion=input.nextInt();
		input.nextLine();
		switch (opcion) {
		case 1:
			crearXmlOlimpiadas();
			break;
		case 2:
			crearXmlDeportistas();
			break;
		case 3:
			mostrarListado();
			break;
		}
		input.close();
	}
}
