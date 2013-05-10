/**
 * Para crear hilos de ejecucion
 * @author kikus
 */
public class Hilo {
	//En los apuntes viene como Hebra (IMPORTANTE)
	
	/* Empieza cuando hacemos start() y acaba cuando acabe el run()... no usar stop() (deprecado)
	 * Un hilo que se ha parado no se le puede volver a hacer start!!
	 * 
	 * yield() para dar paso a otro hilo en caso de que vaya a tardar mucho y que asi no acapare la cpu
	 * join() para concatenar hilos
	 * el codigo que se ejecuta en el hilo se define en run()
	 * 
	 * mirar lo de la interfaz Runnable que es un poco raro y parece importante
	 * AAAAAHHHH mirar el ejemplo de Orador: no puedes extender Persona y luego Thread, asi que implementamos Runnable y podemos asi crear Threads y ejecutar el run()
	 */
	
//	synchronized(lock) {
		/* lock es el cerrojo que evita que un thread interrumpa al otro en un momento indebido
		 * en el ejemplo de CuentaCorriente, si se llama a pagarTarjeta mientras se estaba ejecutando ingresarNomina:
		 * ingresarNomina suma 1000 al saldo y cuando no le ha dado tiempo a guardar se llama a pagarTarjeta
		 * pagarTarjeta resta 250 y guarda
		 * ingresarNomina guarda SIN ACTUALIZAR el de pagarTarjeta (fallo)
		 */
//	}
}
