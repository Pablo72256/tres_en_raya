package tres_en_raya;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class tres_en_raya {

	private static String[][] tablero = new String [4][4];
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String ruta = "d:/";
		String fecha = new SimpleDateFormat("ddMMyyy-HHmm").format(new Date());
		String archivoLog = "";
		System.out.println("TRES EN RAYA");
		System.out.println("\nIndique el nombre del jugador 1");
		String jugador1 = sc.nextLine();
		archivoLog = archivoLog.concat((new SimpleDateFormat("dd/MM/yyy HH:mm:ss ").format(new Date())).concat("Jugador 1 es " + jugador1 + "\n"));
		System.out.println("Indique el nombre del jugador 2");
		String jugador2 = sc.nextLine();
		archivoLog = archivoLog.concat((new SimpleDateFormat("dd/MM/yyy HH:mm:ss ").format(new Date())).concat("Jugador 2 es " + jugador2 + "\n"));
		String jugadorGanadorDados = "";
		
		
		//TIRAR DADOS Y VER QUE JUGADOR EMPIEZA PRIMERO
		System.out.println("\nVeamos quien empieza primero, tiremos un dado \n");
		boolean empezar = false;
		while (!empezar) {
			int dadoJugador1 = (int)(Math.random()*6 + 1);
			int dadoJugador2 = (int)(Math.random()*6 + 1);
			System.out.println(jugador1 + ", has sacado " + dadoJugador1);
			archivoLog = archivoLog.concat((new SimpleDateFormat("dd/MM/yyy HH:mm:ss ").format(new Date())).concat(jugador1 + " ha tirado un dado, ha sacado " + dadoJugador1 + "\n"));
			System.out.println(jugador2 + ", has sacado " + dadoJugador2 + "\n");
			archivoLog = archivoLog.concat((new SimpleDateFormat("dd/MM/yyy HH:mm:ss ").format(new Date())).concat(jugador2 + " ha tirado un dado, ha sacado " + dadoJugador2 + "\n"));
		
			if (dadoJugador1 > dadoJugador2) {
				System.out.println("Empieza " + jugador1 + ".\n");
				jugadorGanadorDados = jugador1;
				empezar = true;
			}else if (dadoJugador1 < dadoJugador2) {
				System.out.println("Empieza " + jugador2 + ".\n");
				jugadorGanadorDados = jugador2;
				empezar = true;
			}else {
				System.out.println("Probemos de nuevo");
			}

		}
		
		//JUGAR LA PARTIDA
		boolean ganar = false;
		boolean partidaEmpezada = false;
		boolean coordenadasCorrectas = false;
		String figuraTablero1 = "X ";
		String figuraTablero2 = "O ";
		
		while (!ganar) {
			//INTERCAMBIAR LAS VARIABLES X | O AUTOMATICAMENTE
			String figuraTableroAux = figuraTablero1;
			figuraTablero1 = figuraTablero2;
			figuraTablero2 = figuraTableroAux;
			
			//COMPROBAR SI HA EMPEZADO LA PARTIDA, SI NO, CREAR EL TABLERO VACIO
			if (partidaEmpezada == false) {
				tablero[0][0] = "  ";
				tablero[0][1] = "1 ";
				tablero[0][2] = "2 ";
				tablero[0][3] = "3 ";
				tablero[1][0] = "A ";
				tablero[2][0] = "B ";
				tablero[3][0] = "C ";
				
				for (int x = 1; x <tablero.length ; x++) {
					for (int z = 1; z <tablero[0].length ; z++) {
						tablero[x][z] = "_ ";
					}
				}
				
				//MOSTRAR EL TABLERO
				for (int x = 0; x < tablero.length; x++) {
					System.out.println(Arrays.toString(tablero[x]));
				}
				
				partidaEmpezada = true;
				
			}else {
				//COMPROBAR SI EL USUARIO INTRODUCE LAS COORDENADAS CORRECTAS
				while (!coordenadasCorrectas) {
					System.out.println("\n" + jugadorGanadorDados +", dime coordenadas Y (ej: B)");
					String coordenadasYs = sc.nextLine();
					coordenadasYs = coordenadasYs.toUpperCase().trim();
					System.out.println("\n" + jugadorGanadorDados +", dime coordenadas X (ej: 3)");
					int coordenadasX = sc.nextInt();
					//CREAMOS COORDENADASY PARA CAMBIARLAS A INT Y LIMPIAMOS EL SCANNER
					int coordenadasY = 0;
					sc.nextLine();
					
					if (coordenadasYs.equals("A") || coordenadasYs.equals("B") || coordenadasYs.equals("C")) {
						if (coordenadasX == 1 || coordenadasX == 2 || coordenadasX == 3) {
							coordenadasCorrectas = true;
							//CAMBIAMOS LAS LETRAS POR POSICIONES NUMERICAS
							if (coordenadasYs.equals("A")) {
								coordenadasY = 1;
							}else if (coordenadasYs.equals("B")) {
								coordenadasY = 2;
							}else if (coordenadasYs.equals("C")) {
								coordenadasY = 3;
							}
							
							if (tablero[coordenadasY][coordenadasX] == "_ ") {
								tablero[coordenadasY][coordenadasX] = figuraTablero1;
								archivoLog = archivoLog.concat((new SimpleDateFormat("dd/MM/yyy HH:mm:ss ").format(new Date())).concat(jugadorGanadorDados + " ha puesto " + figuraTablero1 + " en " + coordenadasYs + coordenadasX + "\n"));
								
							}else {
								System.out.println("Esa casilla no esta libre");
								coordenadasCorrectas = false;
							}
						}else {
							System.out.println("Las coordenadas introducidas no son correctas");
						}
					}else {
						System.out.println("Las coordenadas introducidas no son correctas");
					}
					
				}
				
				coordenadasCorrectas = false;
				
				//MOSTRAR TABLERO RESULTANTE
				for (int x = 0; x < tablero.length; x++) {
					System.out.println(Arrays.toString(tablero[x]));
				}
				
				//INTERCAMBIAR LOS NOMBRES DE LOS JUGDORES AUTOMATICAMENTE
				if(jugadorGanadorDados == jugador1) {
					jugadorGanadorDados = jugador2;
					jugador2 = jugador1 ;
					jugador1 = jugadorGanadorDados;
				}else {
					jugadorGanadorDados = jugador1;
					jugador1 = jugador2;
					jugador2 = jugadorGanadorDados;
				}
				
				
				//COMPROBAR SI EXISTE TRES EN RAYA
				if((tablero[1][1] == tablero[2][2]) && (tablero[1][1] == tablero[3][3]) && (tablero[1][1] != "_ ")) {
					ganar = true;
				}else if ((tablero[1][3] == tablero[2][2]) && (tablero[1][3] == tablero[3][1]) && (tablero[1][3] != "_ ")){
					ganar = true;
				}else if ((tablero[1][1] == tablero[1][2]) && (tablero[1][1] == tablero[1][3]) && (tablero[1][1] != "_ ")){
					ganar = true;
				}else if ((tablero[2][1] == tablero[2][2]) && (tablero[2][1] == tablero[2][3]) && (tablero[2][1] != "_ ")){
					ganar = true;
				}else if ((tablero[3][1] == tablero[3][2]) && (tablero[3][1] == tablero[3][3]) && (tablero[3][1] != "_ ")){
					ganar = true;
				}else if ((tablero[1][1] == tablero[2][1]) && (tablero[1][1] == tablero[3][1]) && (tablero[1][1] != "_ ")){
					ganar = true;
				}else if ((tablero[1][2] == tablero[2][2]) && (tablero[1][2] == tablero[3][2]) && (tablero[1][2] != "_ ")){
					ganar = true;
				}else if ((tablero[1][3] == tablero[2][3]) && (tablero[1][3] == tablero[3][3]) && (tablero[1][3] != "_ ")){
					ganar = true;
				}
				
				//COMPROBAR SI LA PARTIDA ACABO SIN TRES EN RAYA
				if((tablero[1][1]!= "_ ") && (tablero[1][2]!= "_ ") && (tablero[1][3]!= "_ ") && (tablero[2][1]!= "_ ")
				&& (tablero[2][2]!= "_ ") && (tablero[2][3]!= "_ ") && (tablero[3][1]!= "_ ") 
				&& (tablero[3][2]!= "_ ") && (tablero[3][3]!= "_ ")) {
					
					//REINICIAMOS EL TABLERO
					for (int x = 1; x <tablero.length ; x++) {
						for (int z = 1; z <tablero[0].length ; z++) {
							tablero[x][z] = "_ ";
						}
					}
					
					System.out.println("\nOs quedasteis sin movimientos, empecemos de nuevo.\n");
					archivoLog = archivoLog.concat((new SimpleDateFormat("dd/MM/yyy HH:mm:ss ").format(new Date())).concat("Sin movimientos, tablero reiniciado.\n"));
					
					//MOSTRAR EL TABLERO NUEVO
					for (int x = 0; x < tablero.length; x++) {
						System.out.println(Arrays.toString(tablero[x]));
					}
					
				}
				
				//COMPROBAR SI HAY GANADOR Y FINALIZAR A PARTIDA
				if(ganar == true) {
					if(jugadorGanadorDados == jugador1) {
						System.out.println("\nEnhorabuena " + jugador2 + ", has ganado.");
						archivoLog = archivoLog.concat((new SimpleDateFormat("dd/MM/yyy HH:mm:ss ").format(new Date())).concat("Enhorabuena " + jugador2 + ", has ganado."));
					}else {
						System.out.println("\nEnhorabuena " + jugador1 + ", has ganado.");
						archivoLog = archivoLog.concat((new SimpleDateFormat("dd/MM/yyy HH:mm:ss ").format(new Date())).concat("Enhorabuena " + jugador1 + ", has ganado."));
					}
					
					//CREAR EL LOG Y AÑADIR EL CONTENIDEO DE LA PARTIDA
					try {  
						ruta = ruta.concat(fecha.concat("-log.txt"));
			            File file = new File(ruta);

			            if (!file.exists()) {
			                file.createNewFile();
			            }
			            
			            FileWriter fw = new FileWriter(file);
			            BufferedWriter bw = new BufferedWriter(fw);
			            bw.write(archivoLog);
			            bw.close();
			            
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
					
				}
				
			}
		}
		
		//CERRAR EL SCANNER
		sc.close();

	}
	
}
