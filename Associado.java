package botAssociacao;

public class Associado {

	private int mNumero;
	private long mTelegramId;
	private String mNome;
	private String mTelefone;
	private long mNascimento;
	private long mDataAssociacao;
	private int mAssociacao;
	
	public int getNumero() {
		return mNumero;
	}
	
	public long getTelegramId() {
		return mTelegramId;
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
	
	public int getAssociacao() {
		return mAssociacao;
	}
	
	public void setNumero(int numero) {
		mNumero = numero;
	}
	
	public void setTelegramId(long telegramId) {
		mTelegramId = telegramId;
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
	
	public void setAssociacao(int associacao) {
		mAssociacao = associacao;
	}
	
	public Associado(int numero, String nome, String telefone, long dataAssociacao, long nascimento) {
		mNumero = numero;
		mTelegramId = -1;
		mNome = nome;
		mTelefone = telefone;
		mDataAssociacao = dataAssociacao;
		mNascimento = nascimento;
	}
	
}
