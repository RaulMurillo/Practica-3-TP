package tp.pr3.control;

import tp.pr3.logica.*;

/**
 * Clase que implementa el comando crear celula compleja con atributos f, c para
 * indicar en que casilla se creara.
 * 
 * @version 1.0, 07/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class CrearCelulaCompleja extends Comando {
	public final String CREARCELULACOMPLEJA = "CREARCELULACOMPLEJA";
	private int f;
	private int c;

	/**
	 * Constructor de la clase
	 * 
	 * @param fila
	 *            Coordenada X.
	 * @param columna
	 *            Coordenada Y.
	 */
	public CrearCelulaCompleja(int fila, int columna) {
		f = fila;
		c = columna;
	}

	/**
	 * Crea una celula compleja en la posicion (f, c) si es posible, si no
	 * muestra error.
	 * 
	 * @param mundo
	 *            Mundo sobre el que se ejecuta el comando.
	 */
	public void ejecuta(Mundo mundo) {
		if (!mundo.crearCelulaCompleja(f, c)) {
			System.out.println("No se pudo crear la celula, " + "posición no válida");
		}
	}

	/**
	 * Genera el codigo de ayuda referente a CrearCelulaCompleja.
	 * 
	 * @return Texto de ayuda correspondiente al comando.
	 */
	public String textoAyuda() {
		return ("CREARCELULACOMPLEJA f c: Crea una nueva celula compleja en la posicion " + "(f,c) si es posible");
	}

	/**
	 * Parsea un array de String para construir el comando que representa.
	 * 
	 * @param cadenaComando
	 *            Array de String a parsear.
	 * @return Comando CrearCelulaCompleja si el array de strings se corresponde
	 *         con este, null en otro caso.
	 */
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando.length != 3)
			return null;
		else if (!cadenaComando[0].equals(CREARCELULACOMPLEJA))
			return null;
		else {
			// Se gestionan errores tipo "crearcelula a 8"
			int f, c;
			try {
				f = Integer.parseInt(cadenaComando[1]);
				c = Integer.parseInt(cadenaComando[2]);
			} catch (NumberFormatException e) { // Gestion de errores
				return null;
			}
			return new CrearCelulaCompleja(f, c);
		}
	}
}