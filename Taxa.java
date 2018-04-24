package associacao;

public class Taxa {

	private int mCodigo;
	private Associacao mAssociacao;
	private String mNome;
	private int mVigencia;
	private double mValorAno;
	private int mParcelas;
	private boolean mAdministrativa;
	
	public int getCodigo() {
		return mCodigo;
	}
	
	public Associacao getAssociacao() {
		return mAssociacao;
	}
	
	public String getNome() {
		return mNome;
	}
	
	public int getVigencia() {
		return mVigencia;
	}
	
	public double getValorAno() {
		return mValorAno;
	}
	
	public int getParcelas() {
		return mParcelas;
	}
	
	public boolean getAdministrativa() {
		return mAdministrativa;
	}
	
	public double getValorDaParcela() {
		return mValorAno / mParcelas;
	}
	
	public void setCodigo(int codigo) {
		mCodigo = codigo;
	}
	
	public void setAssociacao(Associacao associacao) {
		mAssociacao = associacao;
	}
	
	public void setNome(String nome) {
		mNome = nome;
	}
	
	public void setVigencia(int vigencia) {
		mVigencia = vigencia;
	}
	
	public void setValorAno(double valorAno) {
		mValorAno = valorAno;
	}
	
	public void setParcelas(int parcelas) {
		mParcelas = parcelas;
	}
	
	public void setAdministrativa(boolean administrativa) {
		mAdministrativa = administrativa;
	}
	
	public Taxa(int codigo, Associacao associacao, String nome, int vigencia, double valorAno, int parcelas, boolean administrativa) {
		mCodigo = codigo;
		mAssociacao = associacao;
		mNome = nome;
		mVigencia = vigencia;
		mValorAno = valorAno;
		mParcelas = parcelas;
		mAdministrativa = administrativa;
	}
	
}
