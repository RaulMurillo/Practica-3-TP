package tp.pr3.logica;

/**
 * Clase que representa las casillas que formaran la superficie. Contiene
 * atributos privados para indicar la posicion da cada casilla en la matriz
 * superficie.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */

public class Casilla {
	private int f; // Coordenada horizontal de la casilla.
	private int c; // Coordenada vertical de la casilla.

	/**
	 * Crea una casilla nueva a partir de las coordenadas dadas.
	 * 
	 * @param x
	 *            Coordenada horizontal de la casilla.
	 * @param y
	 *            Coordenada vertical de la casilla.
	 */
	public Casilla(int x, int y) {
		this.f = x;
		this.c = y;
	}

	/**
	 * Indica la coordenada horizontal de una casilla.
	 * 
	 * @return Coordenada fila.
	 */
	public int getFila() {
		return f;
	}

	/**
	 * Indica la coordenada vertical de una casilla.
	 * 
	 * @return Coordenada columna.
	 */
	public int getColumna() {
		return c;
	}

	/**
	 * Devuelve en un String la posicion de una casilla en formato "(f,c)".
	 */
	public String toString() {
		return "(" + f + "," + c + ")";
	}
}
