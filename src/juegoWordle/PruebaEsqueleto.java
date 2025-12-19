package juegoWordle;

import java.util.Random;
import java.util.Scanner;

public class PruebaEsqueleto {
	static Scanner scanner = new Scanner(System.in);
	static String palabraSecreta;
	static int numIntentosConsumidos;
	static int numLetrasAdivinadas;

	public static void main(String[] args) {
		String[] palabras = { "carta", "perro", "salto", "campo", "norte", "limon", "dulce", "trigo", "plaza", "freno",
				"brazo", "clavo", "grito", "silla", "mundo", "pesca", "tarde", "cinto", "burro", "saldo" };
		int victoriasJugador = 0, victoriasMaquina = 0, partidas = 0;
		String palabraIntroducida, opcion;
		do {
			numIntentosConsumidos = 0;
			palabraSecreta ="casar";//generaPalabra(palabras);
			//System.out.println(palabraSecreta);

			System.out.println(
					"Bienvenid@ al juego de Wordle.\n" + "El objetivo es descubrir la palabra oculta de 5 letras.\n"
							+ "Introduce una palabra de 5 letras:");

			do {
				System.out.print(">");
				palabraIntroducida = scanner.nextLine();
				if (comprobar(palabraIntroducida)) {
					String aciertos = compruebaLetrasAcertadas(palabraIntroducida);
					System.out.println(aciertos);
					numIntentosConsumidos++;
				} else {
					System.out.println("La palabra introducida no es válida, ingrese denuevo");
				}
			} while (!haGanadoJugador(palabraIntroducida) && !haTerminadoJuego());

			partidas += 1;
			if (haGanadoJugador(palabraIntroducida)) {
				victoriasJugador += 1;
				System.out.println("Has ganado la partida\nTú: " + victoriasJugador + " puntos vs Máquina: "
						+ victoriasMaquina + " puntos\n" + "Total de partidas: " + partidas);
			} else {
				victoriasMaquina += 1;
				System.out.println("Has perdido la partida\nTú: " + victoriasJugador + " puntos vs Máquina: "
						+ victoriasMaquina + " puntos\n" + "Total de partidas: " + partidas);
			}
			System.out.println("¿Deseas jugar otra partida? s/n");
			opcion = scanner.nextLine();
			while (!opcion.equalsIgnoreCase("s") && !opcion.equalsIgnoreCase("n")) {
				System.out.println("Opción incorrecta, vuelve a ingresar. (s/n)");
				opcion = scanner.nextLine();
			}

		} while (opcion.equalsIgnoreCase("s"));
		System.out.println("Fin del Juego");

	}

	private static boolean comprobar(String cadena) {
		String cadena_min = cadena.toLowerCase();// simplifica comprobaciones
		// Comprueba si la cadena pasada cumple los requerimientos
		if (cadena.length() != 5) { // es la longitud correcta
			return false;
		} else if (!son_letras(cadena_min)) { // comprueba que son letras
			return false;
		} else if (!acaba_bien(cadena_min)) {
			return false;
		} else if (!es_palabra(cadena_min)) {
			return false;
		}
		return true;
	}

	private static boolean son_letras(String cadena) {
		// compruba si la cadeana esta compuesta por letras
		char letra = ' ';
		for (int i = 0; i < cadena.length(); i++) {
			letra = cadena.charAt(i);
			if (!Character.isLetter(letra)) {
				return false; // si no es letra devuelve false
			}
		}
		return true;
	}

	private static boolean acaba_bien(String cadena) {
		// comprueba si ultima letra es q w o x
		// creada la clase para añadir o modificar el requerimiento rapido
		char[] letras_prohibidas = { 'q', 'w', 'x' };
		for (int i = 0; i < letras_prohibidas.length; i++) {
			if (cadena.charAt(4) == letras_prohibidas[i]) {
				return false;
			}
		}
		return true;
	}

	private static boolean es_palabra(String cadena) {
		int consonantes = 0;
		boolean vocal = false;
		char[] vocales = { 'a', 'e', 'i', 'o', 'u', 'ú', 'ó', 'í', 'é', 'á' };
		for (int i = 0; i < cadena.length(); i++) {
			for (int j = 0; j < vocales.length; j++) {
				if (cadena.charAt(i) == vocales[j]) {
					vocal = true; // si letrtaa es vocal retorna true
				}
			}
			if (!vocal) {
				consonantes++;
			} else {
				consonantes = 0;
				if (i > 0) {
					if (cadena.charAt(i) == cadena.charAt(i - 1)) { // si son dos vocales iguales retorna false
						return false;
					}
				}
			}
			if (consonantes == 3) {
				return false;

			} else {

			}
			vocal=false;
		}
		return true;
	}

	public static String generaPalabra(String[] palabras) {
		Random genAle = new Random();
		palabraSecreta = palabras[genAle.nextInt(palabras.length)];

		return palabraSecreta;
	}

	public static String compruebaLetrasAcertadas(String palabraIntroducida) {
		String aciertos = "", aux = "";
		for (int i = 0; i < 5; i++) {
			boolean estaChar = false, repite = false;
			for (int j = 0; j < 5; j++) {
				if (palabraSecreta.indexOf(palabraSecreta.charAt(j)) == palabraSecreta
						.lastIndexOf(palabraSecreta.charAt(j))) {
					if (palabraIntroducida.charAt(i) == palabraSecreta.charAt(j)) {
						aux += palabraIntroducida.charAt(i);
						estaChar = true;
					}
				} else if (palabraSecreta.indexOf(palabraSecreta.charAt(j)) != palabraSecreta
						.lastIndexOf(palabraSecreta.charAt(j))) {
					if (!repite) {
						if (palabraIntroducida.charAt(i) == palabraSecreta.charAt(j)) {
							aux += palabraIntroducida.charAt(i);
							estaChar = true;
							repite = true;
						}
					}
				}
			}
			if (!estaChar)
				aux += "*";
		}
		String palabraSecretaDos = palabraSecreta.toUpperCase();
		for (int i = 0; i < 5; i++) {
			if ((int) palabraSecretaDos.charAt(i) == (int) aux.charAt(i) - 32)
				aciertos += palabraSecretaDos.charAt(i);
			else
				aciertos += aux.charAt(i);

		}
		return aciertos;

	}

	public static boolean haGanadoJugador(String palabraIntroducida) {

		if (palabraIntroducida.equalsIgnoreCase(palabraSecreta))
			return true;

		return false;
	}

	public static boolean haTerminadoJuego() {

		if (numIntentosConsumidos == 6)
			return true;

		return false;
	}

}