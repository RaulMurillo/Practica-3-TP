package tp.pr3.control;

import tp.pr3.logica.*;

/**
 * Clase que implementa el comando Jugar.
 * 
 * @version 1.0, 18/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class Jugar extends Comando {
	public final String JUGAR = "JUGAR";
	private Mundo mundo;
	private int n; // Numero de filas.
	private int m; // Numero de columnas.
	private int s; // Numero de celulas simples.
	private int c; // Numero de celulas complejas.

	/**
	 * Constructor simple de la clase. Inicializa el atributo mundo como uno
	 * simple.
	 * 
	 * @param n
	 *            Numero de filas.
	 * @param m
	 *            Numero de columnas.
	 * @param s
	 *            Numero de celulas simples.
	 */
	public Jugar(int n, int m, int s) {
		this.n = n;
		this.m = m;
		this.s = s;
		this.mundo = new MundoSimple(n, m, s);
	}

	/**
	 * Constructor complejo de la clase. Inicializa el atributo mundo como uno
	 * comlejo.
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
		this.n = n;
		this.m = m;
		this.s = s;
		this.c = c;
		this.mundo = new MundoComplejo(n, m, s, c);
	}

	/**
	 * Inicia la simulacion con una nueva configuracion del juego
	 * correspondiente, con una superficie de N filas y M columnas.
	 * 
	 * @param mundo
	 *            Mundo sobre el que se ejecuta el comando.
	 */
	public void ejecuta(Controlador controlador) {
		/*
		 * controlador.juega(this.mundo);
		 */
	}

	/**
	 * Genera el codigo de ayuda referente a Jugar.
	 * 
	 * @return Texto de ayuda correspondiente al comando.
	 */
	public String textoAyuda() {
		return ("JUGAR SIMPLE N M S: Inicia la simulacion con una nueva "
				+ "configuracion del juego simple, con una superficie "
				+ "de N filas y M columnas, y con S celulas simples " + "colocadas de forma aleatoria\n"
				+ "JUGAR COMPLEJA N M S C: Inicia la simulacion con una nueva "
				+ "configuracion del juego complejo, con una superficie "
				+ "de N filas y M columnas, y con S celulas simples "
				+ "y C celulas complejas colocadas de forma aleatoria");
	}

	/**
	 * Parsea un array de String para construir el comando que representa.
	 * 
	 * @param cadenaComando
	 *            Array de String a parsear.
	 * @return Comando Iniciar si el array de strings se corresponde con este,
	 *         null en otro caso.
	 */
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando[0].equals(JUGAR)) {
			if (cadenaComando.length == 5 && cadenaComando[1].equals("SIMPLE")) {
				int n, m, s;
				try {
					n = Integer.parseInt(cadenaComando[2]);
					m = Integer.parseInt(cadenaComando[3]);
					s = Integer.parseInt(cadenaComando[4]);
				} catch (NumberFormatException e) { // Gestion de errores
					return null;
				}
				return new Jugar(n, m, s);
			} else if (cadenaComando.length == 6 && cadenaComando[1].equals("COMPLEJO")) {
				int n, m, s, c;
				try {
					n = Integer.parseInt(cadenaComando[2]);
					m = Integer.parseInt(cadenaComando[3]);
					s = Integer.parseInt(cadenaComando[4]);
					c = Integer.parseInt(cadenaComando[5]);
				} catch (NumberFormatException e) { // Gestion de errores
					return null;
				}
				return new Jugar(n, m, s, c);
			} else
				return null;
		} else
			return null;
	}

}
