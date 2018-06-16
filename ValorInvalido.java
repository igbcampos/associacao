package botAssociacao;

public class ValorInvalido extends Exception {

	public ValorInvalido(String elemento){
		super("Valor invalido para o elemento: "+ elemento);
	}
}
