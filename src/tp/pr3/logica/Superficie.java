package tp.pr3.logica;

import java.io.PrintWriter;
import java.util.Scanner;

import tp.pr3.control.PalabraIncorrecta;

/**
 * Clase que representa la superficie donde transcurre la evolucion de las
 * celulas. La superficie la vamos a representar mediante una matriz de celulas,
 * cuyo tamaño queda determinado por su numero de filas y columnas.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */

public class Superficie {
	private Celula[][] superficie; // Matriz de células.
	private int filas; // Numero de filas de la matriz superficie.
	private int columnas; // Numero de columnas de la matriz superficie.

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
	 * Indica las filas de la superficie.
	 * 
	 * @return Numero de filas de la superficie.
	 */
	public int getFilas() {
		return filas;
	}

	/**
	 * Indica las columnas de la superficie.
	 * 
	 * @return Numero de columnas de la superficie.
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
		if (superficie[casilla.getFila()][casilla.getColumna()] == null) {
			superficie[casilla.getFila()][casilla.getColumna()] = new CelulaSimple();
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
		if (superficie[casilla.getFila()][casilla.getColumna()] == null) {
			superficie[casilla.getFila()][casilla.getColumna()] = new CelulaCompleja();
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
		if (superficie[casilla.getFila()][casilla.getColumna()] != null) {
			superficie[casilla.getFila()][casilla.getColumna()] = null;
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
	 * Indica si una casilla contiene una celula comestible (simple).
	 * 
	 * @param casilla
	 *            Casilla a evaluar.
	 * @return true si la celula de la casilla es comestible.
	 */
	public boolean esComestible(Casilla casilla) {
		return superficie[casilla.getFila()][casilla.getColumna()] instanceof CelulaSimple;
	}

	/**
	 * Mueve la celula de una casilla a otra.
	 * 
	 * @param origen
	 *            Casilla origen.
	 * @param destino
	 *            Casilla destino.
	 */
	public void moverA(Casilla origen, Casilla destino) {
		superficie[destino.getFila()][destino.getColumna()] = superficie[origen.getFila()][origen.getColumna()];
		eliminarCelula(origen);
	}

	/**
	 * Indica si una determinada casilla de la supesrficie esta o no vacia.
	 * 
	 * @param casilla
	 *            Casilla a evaluar.
	 * @return true si la casilla esta vacia.
	 */
	public boolean vacia(Casilla casilla) {
		return (superficie[casilla.getFila()][casilla.getColumna()] == null);
	}

	/**
	 * Dados un flujo de escritura y unas coordenadas, guarda la información de
	 * la célula que ocupa dicha casilla.
	 * 
	 * @param salida
	 *            Flujo de escritura.
	 * @param i
	 *            Valor de la coordenada fila.
	 * @param j
	 *            Valor de la coordenada columna.
	 */
	public void guardarCelula(PrintWriter salida, int i, int j) {
		superficie[i][j].guardar(salida);
	}

	/**
	 * Dados un flujo de lectura, una casilla y una célula, lee la información
	 * de una célula, la carga en la dada y se asigna la celula a la casilla
	 * dada.
	 * 
	 * @param entrada
	 *            Flujo de lectura.
	 * @param casilla
	 *            Casilla donde se debe asignar la celula.
	 * @param celula
	 *            Celula que se debe cargar.
	 * @throws PalabraIncorrecta
	 */
	public void cargarCelula(Scanner entrada, Casilla casilla, Celula celula) throws PalabraIncorrecta {
		celula.cargar(entrada);
		superficie[casilla.getFila()][casilla.getColumna()] = celula;
	}
}
