package tp.pr3.control;

import tp.pr3.logica.Mundo;

/**
 * Clase que implementa el comando eliminar celula con atributos f, c para
 * indicar de que casilla se eliminara.
 * 
 * @version 1.0, 07/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class EliminarCelula extends Comando {
	public final String ELIMINARCELULA = "ELIMINARCELULA";
	private int f;
	private int c;

	/**
	 * Constructor de la clase.
	 * 
	 * @param fila
	 *            Coordenada X.
	 * @param columna
	 *            Coordenada Y.
	 */
	public EliminarCelula(int fila, int columna) {
		f = fila;
		c = columna;
	}

	/**
	 * Elimina la celula de la casilla (f,c) si es posible, si no muestra un
	 * mensaje de error.
	 * 
	 * @param mundo
	 *            Mundo sobre el que se ejecuta el comando.
	 */
	public void ejecuta(Mundo mundo) {
		if (!mundo.eliminarCelula(f, c)) {
			System.out.println("No se pudo eliminar la celula, " + "posicion no valida");
		}
	}

	/**
	 * Genera el codigo de ayuda referente a EliminarCelula.
	 * 
	 * @return Texto de ayuda correspondiente al comando.
	 */
	public String textoAyuda() {
		return ("ELIMINARCELULA f c: Elimina la celula de la casilla (f, c)" + " si es posible");
	}

	/**
	 * Parsea un array de String para construir el comando que representa.
	 * 
	 * @param cadenaComando
	 *            Array de String a parsear.
	 * @return Comando EliminarCelula si el array de strings se corresponde con
	 *         este, null en otro caso.
	 */
	public Comando parsea(String[] cadenaComando) throws NumberFormatException, IndicesFueraDeRango {
		if (cadenaComando.length != 3)
			return null;
		else if (!cadenaComando[0].equals(ELIMINARCELULA))
			return null;
		else {
			// Se gestionan errores tipo "eliminarcelula a 8"
			int f, c;
			f = Integer.parseInt(cadenaComando[1]);
			c = Integer.parseInt(cadenaComando[2]);
			return new EliminarCelula(f, c);
		}
	}
}
