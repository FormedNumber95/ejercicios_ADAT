package ejercicios_presentacion1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("Datos_Olimpiadas/olimpiadas.xml"));
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
	
	private static void crearXmlDeportistas() {
		HashMap<String, ArrayList<String>> mapa=new HashMap<String,
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
            			mapa.put(id+","+nombre+","+sexo+","+altura+","+peso, new ArrayList<String>());
            		}
            		mapa.get(id+","+nombre+","+sexo+","+altura+","+peso).add(
            				deporte+","+edad+","+noc+","+equipo+","+juegos+","+
            		ciudad+","+evento+","+medalla);
            	}
            }
            DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
			Document doc=docBuilder.newDocument();
			Element deportistas=doc.createElement("deportistas");
			doc.appendChild(deportistas);
			for(Entry<String,ArrayList<String>>entrada:
        		mapa.entrySet()){
				Element deportista=doc.createElement("deportista");
				String[] persona=entrada.getKey().split(",");
				for(int i=0;i<persona.length;i++) {
					if(persona[i].charAt(0)=='\"') {
						persona[i]=persona[i].substring(1,persona[i].length()-1);
					}
					Element person=null;
					if(values[i].equals("\"ID\"")) {
						deportista.setAttribute("id", persona[i]);
					}else {
						if(values[i].charAt(0)=='\"') {
               			 person=doc.createElement(values[i].
               					 substring(1,values[i].length()-1));
               		}else {
               			 person=doc.createElement(values[i]);
               		}
               		person.appendChild(doc.createTextNode(
               				persona[i]));
          			 	deportista.appendChild(person);
					}
				}
				deportistas.appendChild(deportista);
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
			break;
		}
		
	}
}
