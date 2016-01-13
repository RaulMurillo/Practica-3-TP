package tp.pr3.control;

/**
 * Clase que implementa el comando crear celula compleja con atributos f, c para
 * indicar en que casilla se creara.
 * 
 * @version 1.0, 07/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class CrearCelula implements Comando {
	public final String CREARCELULA = "CREARCELULA";
	private int f;
	private int c;

	/**
	 * Constructor de la clase
	 * 
	 * @param fila
	 *            Coordenada X.
	 * @param columna
	 *            Coordenada Y.
	 */
	public CrearCelula(int fila, int columna) {
		f = fila;
		c = columna;
	}

	/**
	 * Crea una celula compleja en la posicion (f, c) si es posible, si no
	 * muestra error.
	 * 
	 * @param mundo
	 *            Mundo sobre el que se ejecuta el comando.
	 */
	public void ejecuta(Controlador controlador) throws PosicionNoValida, SeleccionNoValida {
		controlador.creaCelula(f, c);
	}

	/**
	 * Genera el codigo de ayuda referente a CrearCelulaCompleja.
	 * 
	 * @return Texto de ayuda correspondiente al comando.
	 */
	public String textoAyuda() {
		return ("CREARCELULACOMPLEJA f c: Crea una nueva celula compleja en la posicion " + "(f,c) si es posible");
	}

	/**
	 * Parsea un array de String para construir el comando que representa.
	 * 
	 * @param cadenaComando
	 *            Array de String a parsear.
	 * @return Comando CrearCelulaCompleja si el array de strings se corresponde
	 *         con este, null en otro caso.
	 */
	public Comando parsea(String[] cadenaComando) throws FormatoNumericoIncorrecto{
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
	
	public int getFilas(){
		return this.f;
	}
	
	public int getColumnas(){
		return this.c;
	}
}