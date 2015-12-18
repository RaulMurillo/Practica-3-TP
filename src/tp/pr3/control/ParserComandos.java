package tp.pr3.control;

/**
 * Esta clase es la encargada de parsear un array de String y construir el
 * comando al que hace referencia dicho String. Esta clase contiene un atributo
 * estático donde almacena los objetos correspondientes a los comandos de esta
 * practica.
 * 
 * @version 1.0, 09/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class ParserComandos {
	// Array con todos los comandos
	private static Comando[] comandos = { new Paso(), new EliminarCelula(0, 0), new CrearCelulaSimple(0, 0),
			new CrearCelulaCompleja(0, 0), new Iniciar(), new Vaciar(), new Salir(), new Ayuda() };

	/**
	 * Genera un String con el texto de ayuda que se muestra al seleccionar el
	 * comando Ayuda.
	 * 
	 * @return Texto de ayuda.
	 */
	static public String AyudaComandos() {
		String cadena = "";
		for (Comando i : comandos) {
			cadena += i.textoAyuda() + "\n";
		}
		return cadena;
	}

	/**
	 * Dado un array de String, devuelve el comando correspondiente.
	 * 
	 * @param cadenas
	 *            Array de String a parsear.
	 * @return Comando resultante de parsear el array de strings, o null en caso
	 *         de que no se corresponda con ningun comando.
	 */
	static public Comando parseaComando(String[] cadenas) {
		int i = 0;
		Comando comando = null;
		while (i < comandos.length && comando == null) {
			comando = comandos[i].parsea(cadenas);
			i++;
		}
		return comando;
	}
}
