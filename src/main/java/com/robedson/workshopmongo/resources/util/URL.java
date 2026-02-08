package com.robedson.workshopmongo.resources.util;

import java.text.SimpleDateFormat;
import java.util.Date; // <--- TEM QUE SER java.util.Date
import java.util.TimeZone;

public class URL {

	// Método para decodificar parâmetros de URL
	public static String decodeParam(String text) {
		try {
			return java.net.URLDecoder.decode(text, "UTF-8"); // Decodifica o texto usando UTF-8
		} catch (Exception e) {								  // Em caso de erro, retorna string vazia
			return "";										  // Retorna string vazia
		}
	}
	
	// Metodo para converter Data em texto -> Data do java.util.Date
	// Se a data não for válida, retorna a data padrão (defaultValue)
	public static Date converterDate(String textDate, Date defaultValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return sdf.parse(textDate);      		    // Retorna a data convertida
		} catch (Exception e) {
			return defaultValue;                  		// Retorna a data padrão em caso de erro
		}
	}
}
