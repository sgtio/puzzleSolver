import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Sergio Ruiz Peña
 * @author Enrique Garcia Gutierrez
 * 
 */
public class Puzzle {

	static int FILAS = 3;
	static int COLUMNAS = 3;
	/**
	 * Array bidimensional de Strings que representa el tablero del puzzle
	 */
	private String[][] tabla = new String[FILAS][COLUMNAS];
	/**
	 * Tablero con la solucion
	 */
	String[][] solucion = new String[FILAS][COLUMNAS];
	/**
	 * Tabla de letras permitidas. El hueco se representa con "-"
	 */
	private static String[] letrasPermitidas = { "A", "B", "C", "D", "E", "F",
			"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
			"T", "U", "V", "W", "X", "Y", "Z", "-" };
	/**
	 * Tabla de numeros permitidos. El hueco se representa con "-"
	 */
	private static String[] numerosPermitidos = { "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
			"18", "19", "-" };

	/**
	 * Constructor de la clase Puzzle que clona el puzzle pasado por argumento
	 * 
	 * @param puzzle
	 *            Puzzle a clonar
	 */
	public Puzzle(Puzzle puzzle) {
		for (int fil = 0; fil < FILAS; fil++) {
			for (int col = 0; col < COLUMNAS; col++) {
				tabla[fil][col] = puzzle.getTabla()[fil][col];
			}
		}
	}

	/**
	 * Constructor que clona un Puzzle a partir de un array bidimensional
	 * 
	 * @param tablaPuzzle
	 *            Array bidimensional representativo del Puzzle a clonar
	 */
	public Puzzle(String[][] tablaPuzzle) {
		for (int fil = 0; fil < FILAS; fil++) {
			for (int col = 0; col < COLUMNAS; col++) {
				tabla[fil][col] = tablaPuzzle[fil][col];
			}
		}
	}

	/**
	 * Constructor de la clase Puzzle a partir de un archivo de texto
	 * 
	 * @param archivo
	 *            Nombre del archivo, ej: "partida.txt" NOTA: Este debe
	 *            encontrarse en la carpeta del proyecto
	 */
	public Puzzle(String archivo) {
		FileReader fr;
		String linea, elem;
		String[] trozo;
		try {
			fr = new FileReader(new File(archivo));
			BufferedReader br = new BufferedReader(fr);
			linea = br.readLine();
			int fila = 0;

			while (linea != null) {
				trozo = linea.split(",");

				if (trozo.length != COLUMNAS)
					throw new Exception("Columnas erroneas");

				for (int columna = 0; columna < COLUMNAS; columna++) {
					elem = trozo[columna];

					if (estaPermitido(elem))
						this.tabla[fila][columna] = elem;

					else
						throw new Exception("Caracter no permitido: " + elem);
				}

				fila++;
				linea = br.readLine();
				if ((fila >= FILAS & linea != null)
						|| (fila < FILAS & linea == null))
					throw new Exception("Filas erroneas");
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Devuelve el tablero representativo del puzzle
	 * 
	 * @return Un array bidimensional con la tabla
	 */
	public String[][] getTabla() {
		return this.tabla;
	}

	/**
	 * Metodo para buscar un elemento en el puzzle
	 * 
	 * @param elem
	 *            Elemento que queremos buscar
	 * @return Un array con la fila y columna donde se encuentra el elemento
	 *         int[]={fila,columna}. Si no existe, devuelve {-1,-1}
	 */
	int[] getPosicion(Puzzle puz, String elem) {
		for (int fila = 0; fila < FILAS; fila++) {
			for (int columna = 0; columna < COLUMNAS; columna++) {
				if (puz.tabla[fila][columna].equals(elem)) {
					int[] pos = { fila, columna };
					return pos;
				}
			}
		}
		int[] pos = { -1, -1 };
		return pos;
	}

	/**
	 * Metodo para meter un elemento en la posicion indicada
	 * 
	 * @param posFila
	 *            Fila destino
	 * @param posCol
	 *            Columna destino
	 * @param ficha
	 *            Elemento a insertar
	 */
	public void setPosicion(int posFila, int posCol, String ficha) {
		tabla[posFila][posCol] = ficha;
	}

	/**
	 * Comprueba si dos puzzles son iguales
	 * 
	 * @return true si todos los elementos de los puzzles son iguales
	 */
	public boolean igual(Puzzle p) {
		for (int fila = 0; fila < FILAS; fila++) {
			for (int columna = 0; columna < COLUMNAS; columna++) {
				if (!p.tabla[fila][columna].equals(this.tabla[fila][columna]))
					return false;
			}
		}
		return true;
	}

	/**
	 * Comprueba si el string es un numero
	 * 
	 * @param s
	 *            String a comprobar
	 * @return true si es un numero y false si es una letra
	 */
	public boolean esNumero(String s) {
		try {
			if (!s.trim().equals("-")) {
				Integer.parseInt(s.trim());
				return true;
			} else {
				Integer.parseInt(this.tabla[0][1].trim());
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Comprueba si un caracter esta permitido
	 * 
	 * @param texto
	 *            Elemento que queremos comprobar
	 * @return true si esta permitido
	 */
	boolean estaPermitido(String texto) {
		for (int i = 0; i < letrasPermitidas.length; i++) {
			if (letrasPermitidas[i].equals(texto)) {
				return true;
			}
		}
		for (int i = 0; i < numerosPermitidos.length; i++) {
			if (numerosPermitidos[i].equals(texto)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Devuelve una lista con las piezas que se pueden mover: l (left), r
	 * (right), u (up) y d (down)
	 */
	ArrayList<String> movimientosPosibles() {
		int[] posNull = getPosicion(this, "-");
		ArrayList<String> list = new ArrayList<String>();
		if (posNull[1] != 0)
			list.add("l");
		if (posNull[0] != 0)
			list.add("u");
		if (posNull[1] != COLUMNAS - 1)
			list.add("r");
		if (posNull[0] != FILAS - 1)
			list.add("d");
		return list;
	}

	/**
	 * Rellena el array solucion con la solucion del puzzle actual
	 * 
	 * @return El String[][] con la solucion
	 */
	String[][] solucion() {
		int valor = 1000;
		int sigValor = -1;
		boolean sonNumeros = false;
		if (esNumero(this.tabla[0][0]))
			sonNumeros = true;
		// rellenamos array solucion
		for (int fil = 0; fil < FILAS; fil++) {
			for (int col = 0; col < COLUMNAS; col++) {
				// recorremos array tabla para ir buscando los valores minimos
				for (int i = 0; i < FILAS; i++) {
					for (int j = 0; j < COLUMNAS; j++) {
						int valActual = valor(tabla[i][j]);
						if (valActual < valor && valActual > sigValor) {
							valor = valActual;
						}
					}
				}
				sigValor = valor;
				if (sonNumeros)
					solucion[fil][col] = numerosPermitidos[valor];
				else
					solucion[fil][col] = letrasPermitidas[valor];
				valor = 1000;
			}
		}
		return solucion;
	}

	/**
	 * Devuelve un valor numerico para cada caracter del tablero segun su
	 * posicion en el array correspondiente (letrasPermitidas[] o
	 * numerosPermitidos[] segun corresponda). De esta forma, cuanto mayor sea
	 * el caracter, mayor sera su valor.
	 * 
	 * @param s
	 *            Caracter a buscar
	 * @return Entero con su valor correspondiente o -1 si no existe
	 */
	int valor(String s) {
		if (esNumero(this.tabla[0][0])) {
			for (int i = 0; i < numerosPermitidos.length; i++) {
				if (numerosPermitidos[i].equals(s))
					return i;
			}
		} else {
			for (int i = 0; i < letrasPermitidas.length; i++) {
				if (letrasPermitidas[i].equals(s))
					return i;
			}
		}
		return -1;
	}

	/**
	 * Mueve una ficha a la casilla (fil,col) a la casilla (deltaFil,deltaCol)
	 * 
	 * @param p
	 *            Puzzle en el que se realiza el movimiento
	 * @param fil
	 *            Entero con la fila
	 * @param col
	 *            Entero con la columna
	 * @param deltaFil
	 *            Entero con la fila objetivo
	 * @param deltaCol
	 *            Entero con la columna objetivo
	 * @return El puzzle con el movimiento realizado
	 */
	Puzzle mover(Puzzle p, int fil, int col, int deltaFil, int deltaCol) {
		String aux = p.tabla[fil][col];
		p.tabla[fil][col] = p.tabla[deltaFil][deltaCol];
		p.tabla[deltaFil][deltaCol] = aux;
		return p;
	}

	/**
	 * Convierte el puzzle actual en una representacion esquematica en forma de
	 * string
	 * 
	 * @return El string correspondiente al puzzle
	 */
	String representar() {
		String texto = "";
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				texto = texto + this.tabla[i][j];
			}
		}
		return texto;
	}

//	/**
//	 * Similitud del puzzle con la solucion.
//	 * 
//	 * @return
//	 */
//	int probabilidad() {
//		int prob = 100;
//		for (int i = 0; i < FILAS; i++) {
//			for (int j = 0; j < COLUMNAS; j++) {
//				// if (!this.tabla[i][j].equals("-")) {
//				prob = prob - distancia(this.tabla[i][j]);
//				// }
//			}
//		}
//		return prob;
//	}
//
//	/**
//	 * Distancia entre la posicion de la ficha y su posicion en la solcuion
//	 * 
//	 * @param ficha
//	 * @return
//	 */
//	int distancia(String ficha) {
//		int[] pos = getPosicion(this, ficha);
//		int[] sol = getPosicion(new Puzzle(this.solucion), ficha);
//		int dist = Math.abs(pos[0] - sol[0]) + Math.abs(pos[1] - sol[1]);
//		return dist;
//	}
	

	
	/**
	 * Mezcla las fichas del puzzle pasado por argumentos rotando las piezas de 4 en 4 un numero aleatorio de veces
	 * @param puzzle
	 * @return
	 */
	Puzzle mezclar (Puzzle puzzle) {
		int giros;
		String aux;
		int numeroDeVeces = (new Random()).nextInt(2) + 1; //Recorreremos todo el puzzle 1 o 2 veces
		for (int veces = 1; veces <= numeroDeVeces; veces++) {
//		boolean valido = false;
//		while (!valido) {
			for (int columna = 0; columna < COLUMNAS-1; columna++) {
				for (int fila = 0; fila < FILAS-1; fila++) {
					giros = (new Random()).nextInt(3) + 1; //Daremos 1, 2 o 3 giros desde esa casilla
					for (int i=1; i<=giros; i++) {
						aux = puzzle.tabla[fila][columna];
						puzzle.tabla[fila][columna] = puzzle.tabla[fila+1][columna];
						puzzle.tabla[fila+1][columna] = puzzle.tabla[fila+1][columna+1];
						puzzle.tabla[fila+1][columna+1] = puzzle.tabla[fila][columna+1];
						puzzle.tabla[fila][columna+1] = aux;
						//Aqui habria que hacer un paint o algo asi para que se viese la animacion de las piezas mezclandose (opcional) 
					}
				}
			}
//			valido = puzzleValido(puzzle);
		}
//		}
		return puzzle;
	}
	
//	private boolean puzzleValido (Puzzle p) {
//		int contador = 0;
//		for (int i = 0; i < FILAS*COLUMNAS - 1; i++) {
//			if(esNumero(p.tabla[0][0])){
//				
//			}
//		}
//	}
	
	public static void main(String[] args) {
		Puzzle p = new Puzzle ("puzle.txt");
		System.out.println("Puzle inicial: " + p.representar());
		System.out.println("Puzle mezclado: " + (p.mezclar(p)).representar());
		System.out.println("Puzle mezclado: " + (p.mezclar(p)).representar());
		System.out.println("Puzle mezclado: " + (p.mezclar(p)).representar());
	}
}
