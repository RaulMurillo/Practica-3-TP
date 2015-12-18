package tp.pr3.logica;

public class MundoComplejo extends Mundo {
	// nº de células simples con las que se inicia la superficie.
	private int numSimples;
	// nº de células simples con las que se inicia la superficie.
	private int numComplejas;
	
	public MundoComplejo(int filas, int columnas, int numSimples, int numComplejas){
		super (filas, columnas);
		this.numSimples = numSimples;
		this.numComplejas = numComplejas;
	}
	
	@Override
	public void inicializaMundo() {
		this.superficie = new Superficie(filas, columnas, numSimples, numComplejas);
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
