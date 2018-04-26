package associacao;

public class ValorInvalido extends Exception {

	public ValorInvalido(String elemento){
		super("Valor inválido para o elemento: "+ elemento);
	}
}
