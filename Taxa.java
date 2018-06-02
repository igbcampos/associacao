package associacaoBD;

public class Taxa {

	private String mNome;
	private int mVigencia;
	private double mValorAno;
	private int mParcelas;
	private boolean mAdministrativa;
		
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
	
	public Taxa(String nome, int vigencia, double valorAno, int parcelas, boolean administrativa) {
		mNome = nome;
		mVigencia = vigencia;
		mValorAno = valorAno;
		mParcelas = parcelas;
		mAdministrativa = administrativa;
	}
	
}
