package juegoWordle;

import java.util.Random;
import java.util.Scanner;

public class PruebaEsqueleto {
	static Scanner scanner= new Scanner(System.in);
	static String palabraSecreta;
	static int numIntentosConsumidos;
	static int numLetrasAdivinadas;

	public static void main(String[] args) {
		String [] palabras= {"carta","perro","salto","campo","norte","limon","dulce","trigo","plaza","freno","brazo",
				"clavo","grito","silla","mundo","pesca","tarde","cinto","burro","saldo"};
		int victoriasJugador=0,victoriasMaquina=0,partidas=0;
		String palabraIntroducida,opcion;
		do {
			numIntentosConsumidos=0;
			palabraSecreta=generaPalabra(palabras);
			System.out.println(palabraSecreta);
			
			System.out.println("Bienvenid@ al juego de Wordle.\n"
					+ "El objetivo es descubrir la palabra oculta de 5 letras.\n"
					+ "Introduce una palabra de 5 letras:");
		
			do {
				System.out.print(">");
				palabraIntroducida=scanner.nextLine();
				String aciertos=compruebaLetrasAcertadas(palabraIntroducida);
				System.out.println(aciertos);
				numIntentosConsumidos++;
			}while(!haGanadoJugador(palabraIntroducida) && !haTerminadoJuego());
			
			partidas+=1;
			if(haGanadoJugador(palabraIntroducida)) {
				victoriasJugador+=1;
				System.out.println("Has ganado la partida\nTú: "+victoriasJugador+" puntos vs Máquina: "
				+victoriasMaquina+" puntos\n"
						+"Total de partidas: "+partidas);
			}else {
				victoriasMaquina+=1;
				System.out.println("Has perdido la partida\nTú: "+victoriasJugador+" puntos vs Máquina: "
				+victoriasMaquina+" puntos\n"
						+"Total de partidas: "+partidas);
			}
			System.out.println("¿Deseas jugar otra partida? s/n");
			opcion=scanner.nextLine();
			while(!opcion.equalsIgnoreCase("s") && !opcion.equalsIgnoreCase("n")){
				System.out.println("Opción incorrecta, vuelve a ingresar. (s/n)");
				opcion=scanner.nextLine();
			}
			
		}while(opcion.equalsIgnoreCase("s"));
		System.out.println("Has salido");

	}
	public static String generaPalabra(String[] palabras) {
		Random genAle= new Random();
		palabraSecreta=palabras[genAle.nextInt(palabras.length)];
		
		return palabraSecreta;
	}
	public static String compruebaLetrasAcertadas(String palabraIntroducida) {
		String aciertos="", aux="";
		for (int i=0;i<5;i++) {
			boolean estaChar=false;
			for (int j=0;j<5;j++) {
				if (palabraIntroducida.charAt(i)==palabraSecreta.charAt(j)) {
					aux+=palabraIntroducida.charAt(i);
					estaChar=true;
				}
			}
			if(!estaChar) 
				aux+="*";
			
		}
		String palabraSecretaDos=palabraSecreta.toUpperCase();
		for(int i=0;i<5;i++) {
			if((int)palabraSecretaDos.charAt(i)==(int)aux.charAt(i)-32)
				aciertos+=palabraSecretaDos.charAt(i);
			else 
				aciertos+=aux.charAt(i);
			
		}
		return aciertos;
		
	}
	public static boolean haGanadoJugador(String palabraIntroducida) {
		
		if (palabraIntroducida.equalsIgnoreCase(palabraSecreta)) 
			return true;
		
		return false;
	}
	public static boolean haTerminadoJuego() {
		
		if(numIntentosConsumidos==6)
			return true;
		
		return false;
	}
	

}
