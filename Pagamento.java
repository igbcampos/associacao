package associacao;

import java.util.Date;

public class Pagamento {

	private Date mData;
	private double mValor;
	
	public Date getData() {
		return mData;
	}
	
	public double getValor() {
		return mValor;
	}
	
	public void setData(Date data) {
		mData = data;
	}
	
	public void setValor(double valor) {
		mValor = valor;
	}
	
	public Pagamento(Date data, double valor) {
		mData = data;
		mValor = valor;
	}
	
}
