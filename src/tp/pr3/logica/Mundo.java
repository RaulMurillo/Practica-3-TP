package tp.pr3.logica;

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
		inicializaMundo();
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
	public boolean crearCelulaSimple(int f, int c) {
		if (!(f >= 0 && f < superficie.getFilas() && c >= 0 && c < superficie.getColumnas()))
			return false;
		else {
			Casilla cas = new Casilla(f, c);
			return superficie.crearCelulaSimple(cas);
		}
	}

	/**
	 * Dadas unas coordenadas, crea una celula compleja en dicha posicion de la
	 * superficie.
	 * 
	 * @param f
	 *            coordenada fila.
	 * @param c
	 *            coordenada columna.
	 * @return true si se ha creado la celula.
	 */
	public boolean crearCelulaCompleja(int f, int c) {
		if (!(f >= 0 && f < superficie.getFilas() && c >= 0 && c < superficie.getColumnas()))
			return false;
		else {
			Casilla cas = new Casilla(f, c);
			return superficie.crearCelulaCompleja(cas);
		}
	}

	public abstract boolean crearCelula(int f, int c);
	/*
	 * 
	 */

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

	/**
	 * Indica si la simulacion ha terminado.
	 * 
	 * @return true si la simulacion ha terminado.
	 */
	/*
	 * public boolean getSimulacionTerminada() { return
	 * this.simulacionTerminada; }
	 */

	/**
	 * Cambia el valor del booleano de simulación a true.
	 */
	/*
	 * public void setSimulacionTerminadaTrue() { this.simulacionTerminada =
	 * true; }
	 */

	public abstract void guardar();

	public void cargar() {
		/*
		 * 
		 */
	}

}