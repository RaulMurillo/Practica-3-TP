package tp.pr3.logica;

import java.io.PrintWriter;
import java.util.Scanner;

import tp.pr3.control.PalabraIncorrecta;

/**
 * Clase abstracta que contiene a la superficie. Tambien contiene variables con
 * la dimension de la superficie y las celulas simples iniciales.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */

abstract public class Mundo {
	protected Superficie superficie;
	// nº de filas de la superficie.
	protected int filas;
	// nº de columnas de la superficie.
	protected int columnas;
	// nº de celulas simples iniciales de la superficie.
	protected int numSimples;

	/**
	 * Constructora por defecto. Inicializa el numero de filas y columnas a 0, y
	 * la superfice a null.
	 */
	public Mundo() {
		this.filas = 0;
		this.columnas = 0;
		this.superficie = null;
	}

	/**
	 * Constructora de la clase con argumentos. Inicializa los atributos de
	 * acuerdo con los parametros de entrada y crea una superficie de dicho
	 * tamaño.
	 *
	 * @param filas
	 *            Numero de filas que tendra la superficie del mundo.
	 * @param columnas
	 *            Numero de columnas que tendra la superficie del mundo.
	 */
	public Mundo(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		this.superficie = new Superficie(filas, columnas);
	}

	/**
	 * Inicializa un mundo del tipo correspondiente según el valor de los
	 * argumentos.
	 */
	public abstract void inicializaMundo();

	/**
	 * Dadas una casilla, crea una celula simple en dicha posicion de la
	 * superficie.
	 * 
	 * @param casilla
	 *            Casilla donde crear la celula.
	 * @return true si se ha podido crear, false en caso contrario.
	 */
	public boolean crearCelulaSimple(Casilla casilla) {
		return superficie.crearCelulaSimple(casilla);
	}

	/**
	 * Dadas unas coordenadas, elimina la celula que se encuentre en dicha
	 * posicion de la superficie.
	 * 
	 * @param f
	 *            Coordenada fila.
	 * @param c
	 *            Coordenada columna.
	 * @return true si se ha eliminado la celula, false en caso contrario.
	 */
	public boolean eliminarCelula(int f, int c) {
		if (!(f >= 0 && f < superficie.getFilas() && c >= 0 && c < superficie.getColumnas()))
			return false;
		else {
			Casilla cas = new Casilla(f, c);
			return superficie.eliminarCelula(cas);
		}
	}

	/**
	 * Vacia la superficie.
	 */
	public void vaciarMundo() {
		superficie.vaciarSuperficie();
	}

	/**
	 * Para cada celula de la superficie ejecuta un paso de acuerdo con las
	 * reglas descritas.
	 */
	public void evoluciona() {
		Casilla casilla;
		boolean[][] movido = new boolean[superficie.getFilas()][superficie.getColumnas()];
		for (int i = 0; i < superficie.getFilas(); i++) {
			for (int j = 0; j < superficie.getColumnas(); j++) {
				if (movido[i][j] == false) {
					casilla = superficie.ejecutaMovimiento(i, j);
					if (casilla != null) {
						movido[casilla.getFila()][casilla.getColumna()] = true;
					}
				}
			}
		}
	}

	/**
	 * Devuelve un String con el contenido de la superficie.
	 */
	public String toString() {
		return superficie.toString();
	}

	/**
	 * Indica el numero de filas del mundo.
	 * 
	 * @return Numero de filas.
	 */
	public int getFilas() {
		return this.filas;
	}

	/**
	 * Indica el numero de columnas del mundo.
	 * 
	 * @return Numero de columnas.
	 */
	public int getColumnas() {
		return this.columnas;
	}

	/**
	 * Dado un flujo de escritura, guarda la informacion del mundo actual.
	 * 
	 * @param salida
	 *            Flujo de escritura.
	 */
	public void guardar(PrintWriter salida) {
		// Para evitar dos implementaciones concretas que solo difieren en una
		// instruccion, se ha optado por implementar este metodo de forma
		// abstracta, haciendo distincion de casos para los tipos de mundo.
		if (this instanceof MundoSimple)
			salida.println("simple");
		else
			salida.println("complejo");
		salida.println(filas);
		salida.println(columnas);
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				Casilla casilla = new Casilla(i, j);
				if (!superficie.vacia(casilla)) {
					salida.print(i + " " + j + " ");
					superficie.guardarCelula(salida, i, j);
				}
			}
		}
	}

	/**
	 * Dado un flujo de lectura, se carga la información de un mundo.
	 * 
	 * @param entrada
	 *            Flujo de lectura.
	 * @throws PalabraIncorrecta
	 */
	public abstract void cargar(Scanner entrada) throws PalabraIncorrecta;

}