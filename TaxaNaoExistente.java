package associacaoBD;

public class TaxaNaoExistente extends Exception {
	
	public TaxaNaoExistente(){
		super("Taxa nao existente.");
    }
}
