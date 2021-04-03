package com.laboratorio.api.paises;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.laboratorio.api.paises.entity.Pais;
import com.laboratorio.api.paises.services.PaisServiceImpl;
@Transactional
public class ConexionPaises extends PaisServiceImpl{

	public void ingresarPaises() throws Exception {

		JSONParser parser = new JSONParser();

		String paisString;
		
		Pais pais = new Pais();
		
		//EntityManager
	
		
		

		for (int i = 1; i < 301; i++) {

			if ((paisString = conexion(i)) != null) {
				
				//traer el json y obtener el primer valor(objeto pais)
				JSONArray jsonArray = (JSONArray) parser.parse(paisString);

				JSONObject json = (JSONObject) jsonArray.get(0);
				
				
				//obtener los callinCodes que es otro array dentro del json y obtener el primer valor(el numero codigo del pais)
				JSONArray jsonArray2 = (JSONArray) json.get("callingCodes");
				
				//obtener arreglo que contiene latitud y longitud
				JSONArray jsonArray3 = (JSONArray) json.get("latlng");

				
				//Long num = new Long(i);
				//asignar atributos al objeto
				//pais.setId(num);
				pais.setCodigoPais(Integer.parseInt((String)jsonArray2.get(0)));
				pais.setNombrePais((String) json.get("name"));
				pais.setCapitalPais((String) json.get("capital"));
				pais.setRegión((String) json.get("region"));
				pais.setPoblación(Long.parseLong(String.valueOf(json.get("population"))));
				pais.setLatitud(Float.parseFloat(String.valueOf(jsonArray3.get(0))));
				pais.setLongitud(Float.parseFloat(String.valueOf(jsonArray3.get(1))));
				
				//mostrar
				System.out.println("------------------------------------------------");
				System.out.println("Pais: "+pais.getNombrePais()
						+"\nCapital: "+pais.getCapitalPais()
						+"\nCodigoPais: "+pais.getCodigoPais()
						+"\nRegion: "+pais.getRegión()
						+"\nPoblacion: "+pais.getPoblación()
						+"\nLatitud: "+pais.getLatitud()
						+"\nLongitud: "+pais.getLongitud()
						);
				
				//guardar en la db
				try {
					// create a mysql database connection
				      String myDriver = "com.mysql.cj.jdbc.Driver";
				      String myUrl = "jdbc:mysql://localhost:3306/paisesDB";
				      Class.forName(myDriver);
				      Connection conn = DriverManager.getConnection(myUrl, "root", "");
				    
				      // create a sql date object so we can use it in our INSERT statement
				      //Calendar calendar = Calendar.getInstance();
				      
				      //java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

				      // the mysql insert statement
				      String query = " insert into pais (capital_pais, codigo_pais, latitud, longitud, nombre_pais, población, región)"
				        + " values (?, ?, ?, ?, ?, ?, ?)";

				      // create the mysql insert preparedstatement
				      PreparedStatement preparedStmt = conn.prepareStatement(query);
				      preparedStmt.setString(1, pais.getCapitalPais());
				      preparedStmt.setInt(2, pais.getCodigoPais());
				      preparedStmt.setFloat(3, pais.getLatitud());
				      preparedStmt.setFloat(4, pais.getLongitud());
				      preparedStmt.setString(5, pais.getNombrePais());
				      preparedStmt.setLong(6, pais.getPoblación());
				      preparedStmt.setString(7, pais.getRegión());
				      

				      // execute the preparedstatement
				      preparedStmt.execute();
				      
				      conn.close();
					
					
					System.out.println("==========GUARDADO!!!===========");
				} catch (Exception e) {
					
					System.out.println(e.getMessage());
				}
							
			}

		}
	}

	public String conexion(int id) {

		String url = "https://restcountries.eu/rest/v2/callingcode/" + id;
		String respuesta = "";
		try {
			if (peticionHttpGet(url) != null) {
				respuesta = peticionHttpGet(url);
				
				return respuesta;
			} else {
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
