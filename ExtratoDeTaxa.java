package associacaoBD;

import java.util.ArrayList;

public class ExtratoDeTaxa {
	
	private Taxa mTaxa;
	private double mValorJaPagoPeloAssociado;
	private boolean mTaxaQuitada;
	ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();
	
	public Taxa getTaxa() {
		return mTaxa;
	}
	
	public double getValorJaPagoPeloAssociado() { // retorna todo o valor pago
		return mValorJaPagoPeloAssociado;
	}
	
	public boolean taxaEstaQuitada() {
		return mTaxaQuitada;
	}
	
	public void registrarPagamento(long data, double valor) throws ValorInvalido {
		if(!(mTaxaQuitada)) { //se não estiver quitada
			if(valor >= (mTaxa.getValorAno() / mTaxa.getParcelas())) { //verifica se o valor e maior ou igual a uma parcela
				if(valor <= (mTaxa.getValorAno() - mValorJaPagoPeloAssociado)) {//verifica se o valor e menor ou igual ao que falta pagar
					mValorJaPagoPeloAssociado += valor;
					pagamentos.add(new Pagamento(data, valor));
				}
			}
			else if((mTaxa.getValorAno() - mValorJaPagoPeloAssociado) < (mTaxa.getValorAno() / mTaxa.getParcelas())) {//se o valor que falta pagar for menor que o valor de uma parcela
				mValorJaPagoPeloAssociado += valor;
				pagamentos.add(new Pagamento(data, valor));
			}
			else {
				throw new ValorInvalido("valor do pagamento da taxa");
			}
		}
		else {
			throw new ValorInvalido("taxa ja quitada");
		}
	}
	
	public double somarPagamentoDeAssociado(long inicio, long fim) {
		double somaPagamento = 0;
		
		for(Pagamento pagamento : pagamentos) {
			if(pagamento.getData() >= inicio && pagamento.getData() <= fim) {
				somaPagamento += pagamento.getValor();
			}
		}
			
		return somaPagamento;
	}
	
	public ExtratoDeTaxa(Taxa taxa) {
		mTaxa = taxa;
		mValorJaPagoPeloAssociado = 0;
		mTaxaQuitada = false;
	}
	
}