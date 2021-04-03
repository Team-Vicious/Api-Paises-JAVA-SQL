package com.laboratorio.api.paises;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.laboratorio.api.paises.entity.Pais;

public class ConexionPaises {

	public void ingresarPaises() throws ParseException {
		
		JSONParser parser = new JSONParser();  
		
		
		
		String paisString;
		//String codigoPais;
		
		//Pais pais = new Pais();
		
		for (int i = 1; i < 301; i++) {
			
			if ((paisString = conexion(i)) != null) {
				//paisString = conexion(i);
				
				//JSONObject json = (JSONObject) parser.parse(paisString);
				JSONArray jsonArray = (JSONArray)parser.parse(paisString);
				
				JSONObject json=(JSONObject) jsonArray.get(0);
				String reference = (String) json.get("name");
				
				//JSONObject getSth = (JSONObject) json.get("name");
				//JSONObject getSth = (JSONObject)(json.get("name"));
				
				//Object obj  = parser.parse(paisString);

				//JSONObject jsonObject = jsonTree.getAsJsonObject();

				//codigoPais = (String)(jsonObject.get("f1"));
				 //pais.setNombrePais((getSth.toString())); 
				 System.out.println("---------------------------------"+reference+"---------------------------------");
				 System.out.println("JSON DEL PAIS:\n" + paisString);
				 //System.out.println(obj);
				 //JsonElement f2 = jsonObject.get("f1");
			}
			
		}
	}
	
	public String conexion(int id) {

		String url = "https://restcountries.eu/rest/v2/callingcode/"+id;
		String respuesta = "";
		try {
			if (peticionHttpGet(url) != null) {
				respuesta = peticionHttpGet(url);
				//System.out.println("La respuesta es:\n" + respuesta);
				return respuesta;
			}else {
				return null;
			}
			
			
		} catch (Exception e) {
			// Manejar excepción
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public String peticionHttpGet(String urlParaVisitar) throws Exception {
		// Esto es lo que vamos a devolver
		StringBuilder resultado = new StringBuilder();
		// Crear un objeto de tipo URL
		URL url = new URL(urlParaVisitar);

		HttpURLConnection conexion;
		BufferedReader rd;
		try {
			// Abrir la conexión e indicar que será de tipo GET
			conexion = (HttpURLConnection) url.openConnection();
			conexion.setRequestMethod("GET");
			
			// Búferes para leer
			rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		} catch (Exception e) {
			return null;
		}
		
		
		
		
		
		String linea;
		// Mientras el BufferedReader se pueda leer, agregar contenido a resultado
		while ((linea = rd.readLine()) != null) {
			resultado.append(linea);
		}
		// Cerrar el BufferedReader
		rd.close();
		// Regresar resultado, pero como cadena, no como StringBuilder
		return resultado.toString();
	}
}
