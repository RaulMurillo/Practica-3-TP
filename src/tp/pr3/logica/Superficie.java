package tp.pr3.logica;

import java.io.PrintWriter;

import tp.pr3.control.PalabraIncorrecta;

/**
 * Esta clase representa la superficie donde transcurre la evolucion de las
 * celulas. La superficie la vamos a representar mediante una matriz de celulas,
 * cuyo tamaño queda determinado por su numero de filas y columnas.
 * 
 * @version 2.0, 11/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */

public class Superficie {
	private Celula[][] superficie; // Matriz de células.
	private int filas; // Filas de la matriz superficie.
	private int columnas; // Columnas de la matriz superficie.

	/**
	 * Crea una superficie a partir de su numero de filas y columnas.
	 * 
	 * @param nf
	 *            El numero de filas de la matriz. Debe ser mayor que 0.
	 * @param nc
	 *            El numero de columnas de la matriz. Debe ser mayor que 0.
	 */
	public Superficie(int nf, int nc) {
		this.filas = nf;
		this.columnas = nc;
		superficie = new Celula[nf][nc];
	}

	/**
	 * Dada una casilla, crea una celula en dicha posicion.
	 * 
	 * @param casilla
	 *            casilla destino.
	 * @return true si se ha creado la celula. false si no ha sido posible.
	 */

	/**
	 * Indica las filas de la superficie.
	 * 
	 * @return numero de filas de la superficie.
	 */
	public int getFilas() {
		return filas;
	}

	/**
	 * Indica las columnas de la superficie.
	 * 
	 * @return numero de columnas de la superficie.
	 */
	public int getColumnas() {
		return columnas;
	}

	/**
	 * Dada una casilla, crea una celula simple en dicha posicion.
	 * 
	 * @param casilla
	 *            casilla destino.
	 * @return true si se ha creado, false si no.
	 */
	public boolean crearCelulaSimple(Casilla casilla) {
		if (superficie[casilla.getX()][casilla.getY()] == null) {
			superficie[casilla.getX()][casilla.getY()] = new CelulaSimple();
			return true;
		} else
			return false;
	}

	/**
	 * Dada una casilla, crea una celula compleja en dicha posicion.
	 * 
	 * @param casilla
	 *            casilla destino.
	 * @return true si se ha creado, false si no.
	 */
	public boolean crearCelulaCompleja(Casilla casilla) {
		if (superficie[casilla.getX()][casilla.getY()] == null) {
			superficie[casilla.getX()][casilla.getY()] = new CelulaCompleja();
			return true;
		} else
			return false;
	}

	/**
	 * Elimina una celula de una casilla.
	 * 
	 * @param casilla
	 *            casilla origen.
	 * @return true si eliminada.
	 */
	public boolean eliminarCelula(Casilla casilla) {
		if (superficie[casilla.getX()][casilla.getY()] != null) {
			superficie[casilla.getX()][casilla.getY()] = null;
			return true;
		} else
			return false;
	}

	/**
	 * Elimina todas las celulas de la superficie (pone a null todos los
	 * elementos del array).
	 */
	public void vaciarSuperficie() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				superficie[i][j] = null;
			}
		}
	}

	/**
	 * Permite indicar a la celula de la posición (f,c) que evolucione de
	 * acuerdo a sus reglas.
	 * 
	 * @param f
	 *            fila de la casilla.
	 * @param c
	 *            columna de la casilla.
	 * @return casilla a la que se ha movido (o null) en caso de no moverse.
	 */
	public Casilla ejecutaMovimiento(int f, int c) {
		if (superficie[f][c] != null) {
			return superficie[f][c].ejecutaMovimiento(f, c, this);
		} else
			return null;
	}

	/**
	 * Genera y devuelve un String en forma de tabla con la informacion de todas
	 * las casillas de la superficie.
	 */
	public String toString() {
		String tabla = "\n";
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (superficie[i][j] == null)
					tabla += "-";
				else
					tabla += superficie[i][j].toString();
				tabla += " ";
			}
			tabla += "\n";
		}
		return tabla;
	}

	/**
	 * Indica si una casilla contiene una celula comestible.
	 * 
	 * @param casilla
	 *            casilla a evaluar.
	 * @return true si la celula de la casilla es comestible.
	 */
	public boolean esComestible(Casilla casilla) {
		return superficie[casilla.getX()][casilla.getY()] instanceof CelulaSimple;
	}

	/**
	 * Mueve la celula de una casilla a otra.
	 * 
	 * @param origen
	 *            casilla origen.
	 * @param destino
	 *            casilla destino.
	 */
	public void moverA(Casilla origen, Casilla destino) {
		superficie[destino.getX()][destino.getY()] = superficie[origen.getX()][origen.getY()];
		eliminarCelula(origen);
	}

	/**
	 * Indica si una determinada casilla de la supesrficie esta o no vacia.
	 * 
	 * @param casilla
	 *            casilla a evaluar.
	 * @return true si la casilla esta vacia.
	 */
	public boolean vacia(Casilla casilla) {
		return (superficie[casilla.getX()][casilla.getY()] == null);
	}

	public void guardar(PrintWriter salida, int i, int j) {
		superficie[i][j].guardar(salida);
	}

	public void cargar(int i, int j, int n, int m) throws PalabraIncorrecta {
		Casilla casilla = new Casilla(i, j);
		if (m == -1)
			crearCelulaCompleja(casilla);
		else
			crearCelulaSimple(casilla);
		superficie[i][j].cargar(n, m);
	}
}
