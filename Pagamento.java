package associacao;

public class Pagamento {

	private long mData;
	private double mValor;
	
	public long getData() {
		return mData;
	}
	
	public double getValor() {
		return mValor;
	}
	
	public void setData(long data) {
		mData = data;
	}
	
	public void setValor(double valor) {
		mValor = valor;
	}
	
	public Pagamento(long data, double valor) {
		mData = data;
		mValor = valor;
	}
	
}
