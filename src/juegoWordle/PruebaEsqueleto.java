package juegoWordle;

public class PruebaEsqueleto {
	private static boolean comprobar(String cadena){
		String cadena_min = cadena.toLowerCase();//simplifica comprobaciones
		// Comprueba si la cadena pasada cumple los requerimientos
		if (cadena.length()!=5) { // es la longitud correcta
			return false;
		}else if(!son_letras(cadena_min)) { // comprueba que son letras
			return false;
		}else if(!acaba_bien(cadena_min)) {
			return false;
		}else if(!es_palabra(cadena_min)) {
			return false;
		}
		return true;
	}
	private static boolean son_letras(String cadena) {
		// compruba si la cadeana esta compuesta por letras
		char letra = ' ';
		for(int i = 0;i<cadena.length();i++) {
			letra = cadena.charAt(i);
			if (!Character.isLetter(letra)) {
				return false; // si no es letra devuelve false
			}
		}
		return true;
	}
	private static boolean acaba_bien(String cadena) {
		//comprueba si ultima letra es q w o x
		// creada la clase para añadir o modificar el requerimiento rapido
		char[] letras_prohibidas = {'q', 'w', 'x'};
		for(int i = 0;i<letras_prohibidas.length;i++) {
			if(cadena.charAt(4)==letras_prohibidas[i]) {
				return false;
			}
		}
		return true;
		}
	private static boolean es_palabra(String cadena) {
		int consonantes=0;
		boolean vocal = false;
		char[] vocales= {'a','e','i','o','u','ú','ó','í','é','á'};
		for(int i = 0;i<cadena.length();i++) {
			for(int j = 0;j<vocales.length;j++) {
				if(cadena.charAt(i)==vocales[j]) {
					vocal=true; // si letrtaa es vocal retorna true
			}
			}
			if(!vocal) {
				consonantes++;
			}else {
				consonantes=0;
				if(i>0) {
					if (cadena.charAt(i)==cadena.charAt(i-1)) { // si son dos vocales iguales retorna false
						return false;
					}
				}
			}
			if(consonantes==3){
				return false;

			}else {
				
			}
		}
		return true;
	}
	public static void main(String[] args) {
		String [] palabras= {"carta","perro","salto","campo","norte","limon","dulce","trigo","plaza","freno","brazo",
				"clavo","grito","silla","mundo","pesca","tarde","cinto","burro","saldo"};
		
	}

}
