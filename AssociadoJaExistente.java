package associacaoBD;

public class AssociadoJaExistente extends Exception {
	
	public AssociadoJaExistente() {
		super("Associado ja registrado na associacao.");
	}
}
