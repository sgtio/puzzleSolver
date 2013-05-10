import java.util.ArrayList;

/**
 * 
 * @author Sergio Ruiz Peña
 * @author Enrique Garcia Gutierrez
 * 
 */

public class PuzzleSolver {
	/**
	 * Arraylist con los tableros explorados
	 */
	ArrayList<String> tableros = new ArrayList<String>();
	/**
	 * Contador de iteraciones para evitar bucles infinitos. Puesto que esta
	 * demostrado que todos los puzzles se pueden resolver con 32 movimientos o
	 * menos, este sera el mayor valor que podra tomar esta variable.
	 */
	int iteraciones = 0;
	/**
	 * Array auxiliar con los desplazamientos relativos de fila
	 */
	static int[] movFil = { -1, 1, 0, 0 }; // ud
	/**
	 * Array auxiliar con los desplazamientos relativos de columna
	 */
	static int[] movCol = { 0, 0, 1, -1 }; // rl

	int probActual;
	int probAnterior;

	/**
	 * Constructor generico
	 */
	public PuzzleSolver() {
	}

	/**
	 * Metodo que resuelve el puzzle pasado por backtracking
	 * 
	 * @param puzzle
	 *            Puzzle a resolver
	 * @param fil
	 *            Fila donde se encuentra en ese momento el hueco
	 * @param col
	 *            Columna donde se encuentra en ese momento el hueco
	 * @param solucion
	 *            Puzzle con la solucion del puzzle
	 * @return true si lo ha resuelto
	 * @throws Exception
	 */
	public boolean backtrack(Puzzle puzzle, int fil, int col, Puzzle solucion) {
		boolean exito = false;
		if (puzzle.igual(solucion))
			return true;
		int desplazamiento = -1;
		boolean repetido = false;
		while (!exito && desplazamiento != 3) {
			desplazamiento++;
			int deltaFil = fil + movFil[desplazamiento];
			int deltaCol = col + movCol[desplazamiento];
			if (deltaFil >= 0 && deltaFil < puzzle.FILAS && deltaCol >= 0 && deltaCol < puzzle.COLUMNAS) {
//				probAnterior = puzzle.probabilidad();
				puzzle = puzzle.mover(puzzle, fil, col, deltaFil, deltaCol);
				for (String pz : tableros) {
					if (puzzle.representar().equals(pz)) {
						repetido = true;
						break;
					}
				}
				if (!repetido) {
//					probActual = puzzle.probabilidad();
					tableros.add(puzzle.representar());
					if (puzzle.igual(solucion)) {
						exito = true;
					} else {
						if (iteraciones <= 50) {
							iteraciones++;
							exito = backtrack(puzzle, deltaFil, deltaCol, solucion);
							if (!exito) {
								puzzle = puzzle.mover(puzzle, fil, col, deltaFil, deltaCol);
								iteraciones--;
							}
						} else {
							puzzle.mover(puzzle, fil, col, deltaFil, deltaCol);
							tableros.remove(tableros.size()-1);
							exito = false;
						}
					}
				} else {
					puzzle.mover(puzzle, fil, col, deltaFil, deltaCol);
					repetido = false;
					exito = false;
				}
			}
		}
		if (exito) {
			System.out.println("Hueco: {" + fil + "," + col + "}");
		}
		return exito;
	}

	/**
	 * Metodo main de la clase PuzzleSolver. Resuelve el puzzle creado a partir
	 * del archivo de texto introducido.
	 * 
	 * @param args
	 *            Argumento del main
	 */
	public static void main(String[] args) {
		PuzzleSolver ps = new PuzzleSolver();
		Puzzle p = new Puzzle("puzle.txt");
		ps.tableros.add(p.representar());
		Puzzle puzleSolucion = new Puzzle(p.solucion());

		int[] posNull = p.getPosicion(p, "-");
		if (ps.backtrack(p, posNull[0], posNull[1], puzleSolucion))
			System.out.println("El Puzzle se ha resuelto correctamente");
		else
			System.out.println("No se ha encontrado solucion para el puzzle");

	}
}
