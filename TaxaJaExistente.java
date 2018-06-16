package botAssociacao;

public class TaxaJaExistente extends Exception {

	public TaxaJaExistente() {
		super("Taxa ja cadastrada na associacao para essa vigencia.");
	}
}
