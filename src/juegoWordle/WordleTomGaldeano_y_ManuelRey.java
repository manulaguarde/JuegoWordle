// realizado por Tomas Galdeano Y Manuel Rey
package Practicas;

import java.util.Random;
import java.util.Scanner;

public class Wordle {
	static Scanner scanner = new Scanner(System.in);
	static String palabraSecreta;
	static int numIntentosConsumidos;
	static int numLetrasAdivinadas;

	public static void main(String[] args) {
		// realizado por Tomas Galdeano Y Manuel Rey
		String[] palabras = { "carta", "perro", "salto", "campo", "norte", "limon", "dulce", "trigo", "plaza", "freno",
				"brazo", "clavo", "grito", "silla", "mundo", "pesca", "tarde", "cinto", "burro", "saldo" };
		int victoriasJugador = 0, victoriasMaquina = 0, partidas = 0;
		String palabraIntroducida, opcion;
		do {
			numIntentosConsumidos = 0;
			palabraSecreta = "burro"; // generaPalabra(palabras);
			// System.out.println(palabraSecreta);

			System.out.println(
					"Bienvenid@ al juego de Wordle.\n" + "El objetivo es descubrir la palabra oculta de 5 letras.\n"
							+ "Introduce una palabra de 5 letras:");

			do { // juego pricipal sale si se gana o pierde partida
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
			if (haGanadoJugador(palabraIntroducida)) { // control de estadisticas si gana jugador o no
				victoriasJugador += 1;
				System.out.println("Has ganado la partida\nTú: " + victoriasJugador + " puntos vs Máquina: "
						+ victoriasMaquina + " puntos\n" + "Total de partidas: " + partidas);
			} else {
				victoriasMaquina += 1;
				System.out.println("Has perdido la partida\nTú: " + victoriasJugador + " puntos vs Máquina: "
						+ victoriasMaquina + " puntos\n" + "Total de partidas: " + partidas);
			}
			System.out.println("¿Deseas jugar otra partida? s/n"); // control fin de bucle y si se juega otra vez o no
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
		// comprueba si la palaba es mas o menos una parlabra
		int consonantes = 0;
		boolean vocal = false;
		char[] vocales = { 'a', 'e', 'i', 'o', 'u', 'ú', 'ó', 'í', 'é', 'á' };
		for (int i = 0; i < cadena.length(); i++) {
			for (int j = 0; j < vocales.length; j++) {
				if (cadena.charAt(i) == vocales[j]) {
					vocal = true; // si letra es vocal retorna true
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
			vocal = false;
		}
		return true;
	}

	public static String generaPalabra(String[] palabras) {
		// genera una palabra aleatoria de la lista
		Random genAle = new Random();
		palabraSecreta = palabras[genAle.nextInt(palabras.length)];

		return palabraSecreta;
	}

	public static String compruebaLetrasAcertadas(String palabraIntroducida) {
		String aciertos = "";
		palabraIntroducida = palabraIntroducida.toLowerCase();
		char[] letters = new char[5]; // varaible para controlar las letras ya acertadas
		char[] ans = new char[5]; // respuesta
		for (int i = 0; i < palabraIntroducida.length(); i++) {
			letters[i] = palabraSecreta.charAt(i);
			ans[i] = '*';
		}
		palabraIntroducida = palabraIntroducida.toUpperCase();
		palabraSecreta = palabraSecreta.toUpperCase();
		for (int i = 0; i < 5; i++) { // si acerto letra en lugar corecto se pone en ans y se elimina de letters para poder controlar las letras introducidas
			if (palabraIntroducida.charAt(i) == palabraSecreta.charAt(i)) {
				ans[i] = palabraSecreta.charAt(i);
				letters[i] = '*';
			}
		}
		palabraIntroducida = palabraIntroducida.toLowerCase();
		palabraSecreta = palabraSecreta.toLowerCase();
		for (int i = 0; i < 5; i++) { // aqui se añaden las letraas adivinadas pero no en lugar correcto. 
			boolean estaChar = false;
			for (int j = 0; j < palabraIntroducida.length(); j++) { // se mira si esta en leters para poder dar pista de letras multiples si estan en palabra introducida
				if (letters[j] == palabraIntroducida.charAt(i) && !estaChar && ans[i] == '*') { 
					estaChar = true;
					ans[i] = letters[j];
					letters[j] = '*';
				}
				estaChar = false;
			}
		}
		for (int i = 0; i < 5; i++) { //convierto ans en string en aciertos
			aciertos += ans[i];
		}
		return aciertos;
	}

	public static boolean haGanadoJugador(String palabraIntroducida) {
		// comprueba si ha ha ganado el jugador
		if (palabraIntroducida.equalsIgnoreCase(palabraSecreta))
			return true;

		return false;
	}

	public static boolean haTerminadoJuego() {
		// comprueba si ha a acabado el juego
		if (numIntentosConsumidos == 6)
			return true;

		return false;
	}

}