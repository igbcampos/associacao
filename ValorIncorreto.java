package associacao;

public class ValorIncorreto extends Exception {

	public ValorIncorreto(String elemento){
		super("Valor invalido para o elemento: " + elemento);
	}
}
