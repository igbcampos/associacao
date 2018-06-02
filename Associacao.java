package associacaoBD;

public class Associacao {

	private int mNumero;
	private String mNome;
	
	public int getNumero() {
		return mNumero;
	}
	
	public String getNome() {
		return mNome;
	}
	
	public void setNumero(int numero) {
		mNumero = numero;
	}
	
	public void setNome(String nome) {
		mNome = nome;
	}
	
	public Associacao(int numero, String nome) {
		mNumero = numero;
		mNome = nome;
	}
	
}
