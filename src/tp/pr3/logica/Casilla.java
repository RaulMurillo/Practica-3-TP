package tp.pr3.logica;

/**
 * Esta clase representa las casillas que formaran la superficie. Contiene
 * atributos privados para indicar la posicion da cada casilla en la matriz
 * superficie.
 * 
 * @version 2.0, 11/12/2015
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
	 * @return Coord. X
	 */
	public int getX() {
		return f;
	}

	/**
	 * Indica la coordenada vertical de una casilla.
	 * 
	 * @return Coord. Y
	 */
	public int getY() {
		return c;
	}

	/**
	 * Devuelve en un String la posicion de una casilla en formato "(f,c)".
	 */
	public String toString() {
		return "(" + f + "," + c + ")";
	}
}
