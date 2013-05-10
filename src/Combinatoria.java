import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * 
 * @author Sergio Ruiz Peña
 * @author Enrique Garcia Gutierrez
 * 
 */

public class Combinatoria {
	/**
	 * Enteros que indican los 'n' elementos cogidos de 'm' en 'm'
	 */
	int n, m;
	/**
	 * Valor maximo de 'n'
	 */
	static int NMAX = 100;
	/**
	 * Valor maximo de 'm'
	 */
	static int MMAX = 100;
	/**
	 * Tabla para el calculo con algoritmo dinamico
	 */
	static double tabla [][];
	
	/**
	 * Constructor. Crea un numero combinatorio de 'n' elementos tomados de 'm' en 'm'. 
	 * En caso de estar mal definido, lanza una excepcion
	 * @param n 
	 * @param m
	 * @throws Exception Si los valores de 'n' o de 'm' son ilegales
	 */
	public Combinatoria(int n, int m) throws Exception{
		if (n > NMAX || m > MMAX)
			throw new Exception("Valores maximos: 'n' = " + NMAX + ", 'm' = " + MMAX + ".");
		if (n < 0 || m < 0)
			throw new Exception("Ni 'n' ni 'm' pueden ser negativos.");
		if (m > n)
			throw new Exception("El valor de 'm' no puede ser mayor que el de 'n'.");
		else {
			this.n = n;
			this.m = m;
		}
	}
	
	/**
	 * Devuelve un numero combinatorio aleatorio
	 * @throws Exception Si los valores de 'n' o de 'm' son ilegales
	 */
	public Combinatoria randomCombinatoria() throws Exception {
		Random r = new Random();
		int n = r.nextInt(50);
		int m = r.nextInt(50);
		if(n<m)
			return new Combinatoria(m,n);
		else
			return new Combinatoria(n,m);
	}
	
	/**
	 * Calcula el resultado de la combinatoria pasada por el metodo recursivo. Ademas guarda en la tabla de resultados todos los resultados intermedios calculados
	 * @param c Numero combinatorio que se quiere calcular
	 * @return Resultado final
	 * @throws Exception Si los valores de 'n' o de 'm' son ilegales
	 */
	public double calcularRecursivo (Combinatoria c) throws Exception {
		
		//Si el numero combinatorio esta mal definido, devuelve -1
		//Si el valor ya esta en la tabla, lo devuelve sin calcularlo
		if (tabla[c.n][c.m] != -1)
			return tabla [c.n][c.m];
		
		//Si el valor no esta en la tabla, calcula el valor
		else {
//			if (c.m < 0 || c.n < 0) {
//				System.err.println("Ni 'n' ni 'm' pueden ser negativos.");
//				return 0;
//			}
			if (c.m == 0 || c.n == c.m) {
				tabla[c.n][c.m] = 1;
				return 1;
			}
			else if (c.m == 1) {
				tabla[c.n][c.m] = c.n;
				return c.n;
			}
			else {
				double res = calcularRecursivo(new Combinatoria(c.n-1,c.m-1)) + calcularRecursivo(new Combinatoria(c.n-1,c.m));
				tabla[c.n][c.m] = res;
				return res;
			}
		}
	}
	
	/**
	 * Devuelve el factorial del numero indicado
	 * @param n Numero del cual queremos calcular el factorial
	 * @return Factorial del numero n
	 * @throws Exception Si el numero es negativo
	 */
	public double factorial(int n) throws Exception {
		if(n < 0)
			throw new Exception("Numeros negativos. No puedo calcularlo");
		else if (n==1 || n==0)
			return 1;
		else
			return n*factorial(n-1);
	}
	
	/**
	 * Calcula el resultado de la combinatoria por el metodo de calculo de factoriales.
	 * @return Resultado final
	 * @throws Exception Al tratar de calcular un factorial de un numero negativo
	 */
	public double calcularFactorial() throws Exception {
		if(this.m == 0 || this.n == this.m)
			return 1;
		else if (this.m == 1)
			return this.n;
		else {
			double numerador = factorial(this.n);
			double denominador = factorial(this.n-this.m)*factorial(this.m);
			return numerador/denominador;
		}
	}
	
	/**
	 * Calcula la cantidad deseada de numeros combinatorios (generados aleatoriamente) para
	 * comparar el tiempo de calculo necesario para cada uno de los dos metodos de calculo
	 * programados: recursivo y mediante factoriales.
	 * @param max Cantidad de numeros combinatorios que queremos generar y calcular 
	 * @throws Exception Si los valores de 'n' o de 'm' son ilegales
	 */
	public void compruebaTiempo(int max) throws Exception {
		long tiempoinic;
		long tiempofin;
		long tiempo;
		tiempoinic = System.currentTimeMillis();
		for (int i = 0; i < max; i++){
			Combinatoria ci = randomCombinatoria();
			ci.calcularFactorial();
		}
		tiempofin = System.currentTimeMillis();
		tiempo = tiempofin - tiempoinic;
		System.out.println("El tiempo de calculo por el metodo factorial es de " + tiempo + " ms");
	
		tiempoinic= System.currentTimeMillis();
		for (int i=0;i<max; i++){
			Combinatoria ci = randomCombinatoria();
			ci.calcularRecursivo(ci);
		}
		tiempofin = System.currentTimeMillis();
		tiempo = tiempofin - tiempoinic;
		System.out.println("El tiempo de calculo por el metodo recursivo es de " + tiempo + " ms");
	}
	
	

	/**
	 * Metodo main de la clase Combinatoria
	 * @param args Argumentos del main
	 * @throws Exception En caso de error
	 */
	public static void main (String [] args) throws Exception {
		//Inicializamos lectura desde teclado
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader (isr);
        //Inicializamos el array de resultados a -1
		tabla = new double[NMAX][MMAX];
		for (int n = 0; n < NMAX; n++) {
			for (int m = 0; m < MMAX; m++) {
				tabla [n][m] = -1;
			}
		}
		
		System.out.print("Por favor, introduzca el valor n: ");
		int leidoN = Integer.parseInt(br.readLine());
		System.out.print("Por favor, introduzca el valor m: ");
		int leidoM = Integer.parseInt(br.readLine());
		Combinatoria c = new Combinatoria(leidoN, leidoM);
		System.out.println("\nCalculando por el metodo recursivo con array");
		System.out.println("El resultado es " + c.calcularRecursivo(c));
		System.out.println("\nCalculando por el metodo factorial");
		System.out.println("El resultado es " + c.calcularFactorial());
		System.out.println("\n�Desea realizar una prueba de eficiencia de los 2 metodos? (y/n)");
		String answer = br.readLine();
		
		if(answer.startsWith("y")){
			System.out.print("\nIntroduzca el numero de operaciones combinatorias que el ordenador debe calcular: ");
			int numRepeticiones = Integer.parseInt(br.readLine());
			c.compruebaTiempo(numRepeticiones);
		}
	}
}
