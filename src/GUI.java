import java.awt.*;


public class GUI {
	
	public static void main (String[] args) {
		Frame fr = new Frame();
		Menu m = new Menu();
		fr.add(fr); //Hay que anadir componentes (menu no lo es??)
		
		//Clase anonima (IMPORTANTE)
		Canvas c = new Canvas() {
			void paint() {
				//Definimos lo que queremos que haga el paint de la clase Canvas
				//Es lo mismo que crear una clase nueva con "extends Canvas" 
			}
		};
		
		FlowLayout l = new FlowLayout();
		fr.setLayout(l /*uno de los de abajo*/);
		//FlowLayout ordena automaticamente elementos en la pantalla (si haces mas pequeña la ventana, pasan automaticamente a la siguiente linea)
		//GridLayout(fil,col) mete cada objeto q vayamos metiendo en el siguiente sitio de la "grid"
		//BorderLayout deja elegir entre norte sur este oeste o centro
		//GridBagLayout, un "grid" pero con restricciones (un boton en 2 casillas, etc), que se definen en GridBagConstrains y se aplican con setConstrains. con anchor "anclamos" el elemento en la pos que queramos de la casilla. con weightx/y podemos cambiar el ancho y alto independientemente en cada celda... con todos estos metodos hacemos un GridLayout normal pero que no ocupe todo el espacio de la pantalla
		//CardLayout cambia de una ventana a otra (muy cutre)
		fr.setLayout(null); //asi podemos poner cada cosa donde queramos
		
		
		//Insets nos permite establecer los margenes
		
		
		//Los Listener tienen metodos predefinidos, los Adapter vacios para editarlos (??)
		//mouseDragged(MouseEvent) para si arrastras algo con el raton (util!!)
		//InputEvent sirve para saber si alguna tecla estaba pulsada mientras haciamos otra cosa (mover raton, pulsar teclado...)
		
		//OJO!! Cuando pintamos si no hacemos bien el repaint se borrara lo que hemos pintado si por ejemplo ponemos otra ventana encima o cambiamos el tamaño de la ventana
		
				
		/*
		 * utilizar setMinimunSize setPreferredSize y setMinimunSize que en awt no estan
		 * 
		 * para pintar mejor definir paintComponent, que pinta en componente
		 * 
		 * en vez de add() es frame.getContentPane().add()
		 * 
		 * 
		 */
	}
}
