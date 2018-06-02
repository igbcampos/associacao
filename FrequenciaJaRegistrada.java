package associacaoBD;

public class FrequenciaJaRegistrada extends Exception {

	public FrequenciaJaRegistrada() {
		super("Já foi registrada frequencia nesta reunião.");
	}
}
