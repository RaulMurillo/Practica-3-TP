package tp.pr3.logica;

import java.io.BufferedReader;
import java.io.PrintWriter;

import tp.pr3.control.PalabraIncorrecta;

/**
 * Esta clase contiene a la superficie. Tambien contiene constantes con la
 * dimension de la superficie y las celulas iniciales y un atributo que indica
 * si se ha terminado o no la simulacion.
 * 
 * @version 2.2, 11/12/2015
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
	// controla cuando termina la simulacion.
	// private boolean simulacionTerminada;

	/**
	 * Constructora por defecto. Inicializa el numero de filas y columanas a 0,
	 * y la superfice a null.
	 */
	public Mundo() {
		this.filas = 0;
		this.columnas = 0;
		this.superficie = null;
	}

	/**
	 * Inicializa los atributos de acuerdo con los parametros de entrada y crea
	 * una superficie de dicho tamaño.
	 */
	public Mundo(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		this.superficie = new Superficie(filas, columnas);
		// inicializaMundo(); ???
	}

	/**
	 * Inicializa un mundo.
	 */
	public abstract void inicializaMundo();

	/**
	 * Dadas unas coordenadas, crea una celula simple en dicha posicion de la
	 * superficie.
	 * 
	 * @param f
	 *            coordenada fila.
	 * @param c
	 *            coordenada columna.
	 * @return true si se ha creado la celula.
	 */
	public boolean crearCelulaSimple(Casilla cas) {
		return superficie.crearCelulaSimple(cas);
	}

	/**
	 * Dadas unas coordenadas, elimina una celula en dicha posicion de la
	 * superficie.
	 * 
	 * @param f
	 *            coordenada fila.
	 * @param c
	 *            coordenada columna.
	 * @return true si se ha eliminado la celula.
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
	 * Para cada celula de la superficie ejecuta un paso de acuerdo a las reglas
	 * descritas.
	 */
	public void evoluciona() {
		Casilla casilla;
		boolean[][] movido = new boolean[superficie.getFilas()][superficie.getColumnas()];
		for (int i = 0; i < superficie.getFilas(); i++) {
			for (int j = 0; j < superficie.getColumnas(); j++) {
				if (movido[i][j] == false) {
					casilla = superficie.ejecutaMovimiento(i, j);
					if (casilla != null) {
						movido[casilla.getX()][casilla.getY()] = true;
					}
				}
			}
		}
	}

	/**
	 * Devuelve un String con toda la superficie.
	 */
	public String toString() {
		return superficie.toString();
	}

	public int getFilas() {
		return this.filas;
	}

	public int getColumnas() {
		return this.columnas;
	}

	// NO Se ha implementado como abstracto;
	public void guardar(PrintWriter salida) {
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
					superficie.guardar(salida, i, j);
				}
			}
		}
	}

	// Se ha implementado como abstracto;
	public abstract void cargar(BufferedReader entrada) throws PalabraIncorrecta;

}