package tp.pr3.control;

import tp.pr3.logica.*;

/**
 * Clase que implementa el comando JUGAR.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class Jugar implements Comando {
	// Identificador del comando
	public final String JUGAR = "JUGAR";
	// Mundo sobre el que se cambiará el juego
	private Mundo mundo;

	/**
	 * Constructor simple de la clase. Inicializa el atributo mundo como un
	 * mundo simple.
	 * 
	 * @param n
	 *            Numero de filas.
	 * @param m
	 *            Numero de columnas.
	 * @param s
	 *            Numero de celulas simples.
	 */
	public Jugar(int n, int m, int s) {
		this.mundo = new MundoSimple(n, m, s);
	}

	/**
	 * Constructor complejo de la clase. Inicializa el atributo mundo como un
	 * mundo complejo.
	 * 
	 * @param n
	 *            Numero de filas.
	 * @param m
	 *            Numero de columnas.
	 * @param s
	 *            Numero de celulas simples.
	 * @param c
	 *            Numero de celulas complejas.
	 */
	public Jugar(int n, int m, int s, int c) {
		this.mundo = new MundoComplejo(n, m, s, c);
	}

	@Override
	public void ejecuta(Controlador controlador) {
		controlador.juega(this.mundo);
	}

	@Override
	public String textoAyuda() {
		return ("JUGAR SIMPLE N M S: Inicia la simulacion con una nueva "
				+ "configuracion del juego simple, con una superficie "
				+ "de N filas y M columnas, y con S celulas simples " + "colocadas de forma aleatoria\n"
				+ "JUGAR COMPLEJA N M S C: Inicia la simulacion con una nueva "
				+ "configuracion del juego complejo, con una superficie "
				+ "de N filas y M columnas, y con S celulas simples "
				+ "y C celulas complejas colocadas de forma aleatoria");
	}

	@Override
	public Comando parsea(String[] cadenaComando) throws ErrorDeInicializacion, FormatoNumericoIncorrecto {
		if (cadenaComando[0].equals(JUGAR)) {
			if (cadenaComando.length == 5 && cadenaComando[1].equals("SIMPLE")) {
				int n, m, s;
				try {
					n = Integer.parseInt(cadenaComando[2]);
					m = Integer.parseInt(cadenaComando[3]);
					s = Integer.parseInt(cadenaComando[4]);
					if (s > n * m)
						throw new ErrorDeInicializacion();
				} catch (NumberFormatException e) {
					throw new FormatoNumericoIncorrecto();
				}
				return new Jugar(n, m, s);
			} else if (cadenaComando.length == 6 && cadenaComando[1].equals("COMPLEJO")) {
				int n, m, s, c;
				try {
					n = Integer.parseInt(cadenaComando[2]);
					m = Integer.parseInt(cadenaComando[3]);
					s = Integer.parseInt(cadenaComando[4]);
					c = Integer.parseInt(cadenaComando[5]);
					if (s + c > n * m)
						throw new ErrorDeInicializacion();
				} catch (NumberFormatException e) {
					throw new FormatoNumericoIncorrecto();
				}
				return new Jugar(n, m, s, c);
			} else
				return null;
		} else
			return null;
	}
}
