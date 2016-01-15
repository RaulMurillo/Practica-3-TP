package tp.pr3.control;

/**
 * Clase que implementa el comando CREARCELULA F C con atributos f, c para
 * indicar en que casilla se creara la celula.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class CrearCelula implements Comando {
	// Identificador del comando
	public final String CREARCELULA = "CREARCELULA";
	// Fila donde crear la celula.
	private int f;
	// Columna donde crear la celula.
	private int c;

	/**
	 * Constructor de la clase
	 * 
	 * @param fila
	 *            Coordenada fila.
	 * @param columna
	 *            Coordenada columna.
	 */
	public CrearCelula(int fila, int columna) {
		this.f = fila;
		this.c = columna;
	}

	@Override
	public void ejecuta(Controlador controlador) throws PosicionNoValida, SeleccionNoValida {
		controlador.creaCelula(f, c);
	}

	@Override
	public String textoAyuda() {
		return ("CREARCELULACOMPLEJA f c: Crea una nueva celula compleja en la posicion " + "(f,c) si es posible");
	}

	@Override
	public Comando parsea(String[] cadenaComando) throws FormatoNumericoIncorrecto {
		if (cadenaComando.length != 3)
			return null;
		else if (!cadenaComando[0].equals(CREARCELULA))
			return null;
		else {
			// Se gestionan errores tipo "crearcelula a 8"
			int f, c;
			try {
				f = Integer.parseInt(cadenaComando[1]);
				c = Integer.parseInt(cadenaComando[2]);
			} catch (NumberFormatException e) {
				throw new FormatoNumericoIncorrecto();
			}
			return new CrearCelula(f, c);
		}
	}

	/**
	 * Indica la fila donde crear la celula.
	 * 
	 * @return Numero de la fila.
	 */
	public int getFilas() {
		return this.f;
	}

	/**
	 * Indica la columna donde crear la celula.
	 * 
	 * @return Numero de la columna.
	 */
	public int getColumnas() {
		return this.c;
	}
}