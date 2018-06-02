package associacaoBD;

public class Associado {

	private int mNumero;
	private String mNome;
	private String mTelefone;
	private long mNascimento;
	private long mDataAssociacao;
	
	public int getNumero() {
		return mNumero;
	}
	
	public String getNome() {
		return mNome;
	}
	
	public String getTelefone() {
		return mTelefone;
	}
	
	public long getNascimento() {
		return mNascimento;
	}
	
	public long getDataAssociacao() {
		return mDataAssociacao;
	}
	
	public void setNumero(int numero) {
		mNumero = numero;
	}
	
	public void setNome(String nome) {
		mNome = nome;
	}
	
	public void setTelefone(String telefone) {
		mTelefone = telefone;
	}
	
	public void setNascimento(long nascimento) {
		mNascimento = nascimento;
	}
	
	public void setDataAssociacao(long dataAssociacao) {
		mDataAssociacao = dataAssociacao;
	}
	
	public Associado(int numero, String nome, String telefone, long dataAssociacao, long nascimento) {
		mNumero = numero;
		mNome = nome;
		mTelefone = telefone;
		mDataAssociacao = dataAssociacao;
		mNascimento = nascimento;
	}
	
}
