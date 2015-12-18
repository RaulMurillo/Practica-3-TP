package tp.pr3.logica;

public class MundoSimple extends Mundo {
	// nº de células con las que se inicia la superficie.
	private int numCelulas;

	public MundoSimple(int filas, int columnas, int numCelulas) {
		super(filas, columnas);
		this.numCelulas = numCelulas;
	}

	@Override
	public void inicializaMundo() {
		this.superficie = new Superficie(filas, columnas, numCelulas, 0);
	}

	@Override
	public boolean crearCelula(int f, int c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void guardar() {
		// TODO Auto-generated method stub

	}

}
