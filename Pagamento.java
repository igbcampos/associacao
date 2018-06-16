package botAssociacao;

public class Pagamento {

	private long mData;
	private double mValor;
	private String mNomeTaxa;
	private int mVigencia;
	
	public long getData() {
		return mData;
	}
	
	public double getValor() {
		return mValor;
	}
	
	public String getNomeTaxa() {
		return mNomeTaxa;
	}
	
	public int getVigencia() {
		return mVigencia;
	}
	
	public void setData(long data) {
		mData = data;
	}
	
	public void setValor(double valor) {
		mValor = valor;
	}
	
	public void setNomeTaxa(String nomeTaxa) {
		mNomeTaxa = nomeTaxa;
	}
	
	public void setVigencia(int vigencia) {
		mVigencia = vigencia;
	}
	
	public Pagamento(long data, double valor, String nomeTaxa, int vigencia) {
		mData = data;
		mValor = valor;
		mNomeTaxa = nomeTaxa;
		mVigencia = vigencia;
	}
	
}
