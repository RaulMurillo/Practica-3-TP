package tp.pr3.control;

/**
 * Clase que implementa el comando ELIMINARCELULA F C con atributos f, c para
 * indicar en que casilla se eliminara la celula.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class EliminarCelula implements Comando {
	// Identificador del comando
	public final String ELIMINARCELULA = "ELIMINARCELULA";
	// Fila donde crear la celula.
	private int f;
	// Columna donde crear la celula.private int c;
	private int c;

	/**
	 * Constructor de la clase.
	 * 
	 * @param fila
	 *            Coordenada fila.
	 * @param columna
	 *            Coordenada columna.
	 */
	public EliminarCelula(int fila, int columna) {
		this.f = fila;
		this.c = columna;
	}

	@Override
	public void ejecuta(Controlador controlador) throws PosicionNoValida {
		controlador.eliminaCelula(f, c);
	}

	@Override
	public String textoAyuda() {
		return ("ELIMINARCELULA f c: Elimina la celula de la casilla (f, c)" + " si es posible");
	}

	@Override
	public Comando parsea(String[] cadenaComando) throws FormatoNumericoIncorrecto {
		if (cadenaComando.length != 3)
			return null;
		else if (!cadenaComando[0].equals(ELIMINARCELULA))
			return null;
		else {
			// Se gestionan errores tipo "eliminarcelula a 8"
			int f, c;
			try {
				f = Integer.parseInt(cadenaComando[1]);
				c = Integer.parseInt(cadenaComando[2]);
			} catch (NumberFormatException e) {
				throw new FormatoNumericoIncorrecto();
			}
			return new EliminarCelula(f, c);
		}
	}

	/**
	 * Indica la fila donde eliminar la celula.
	 * 
	 * @return Numero de la fila.
	 */
	public int getFilas() {
		return this.f;
	}

	/**
	 * Indica la columna donde eliminar la celula.
	 * 
	 * @return Numero de la columna.
	 */
	public int getColumnas() {
		return this.c;
	}
}
