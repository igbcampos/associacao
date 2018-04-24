package associacao;

import java.util.ArrayList;
import java.util.Date;

public class ExtratoDeTaxa extends Taxa {

	ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();
	private double mValorTotalPagoPeloAssociado;
	private double mValorExcedenteDePagamentosAnteriores;
	private int mQuantidadeDeParcelasPagas;
	private boolean mTaxaQuitada;
	
	public double getValorTotalPagoPeloAssociado() { // retorna todo o valor pago
		return mValorTotalPagoPeloAssociado;
	}
	
	public double getValorExcedenteDePagamentosAnteriores() {
		return mValorExcedenteDePagamentosAnteriores;
	}
	
	public boolean taxaEstaQuitada() {
		return mTaxaQuitada;
	}
	
	public void registrarPagamento(Date data, double valor) throws ValorIncorreto {
		//verificar se a taxa nao esta quitada
		if(mTaxaQuitada == false) {//basta usar !mTaxaQuitada, eu acho
			if(valor < getValorDaParcela()) {
				throw new ValorIncorreto("valor abaixo do valor da parcela da taxa");
			}
			else if(valor > getValorAno() - mValorTotalPagoPeloAssociado) {
				throw new ValorIncorreto("valor ultrapassa o valor restante da taxa a ser paga");
			}
			
			//aqui só deve executar se o valor for >= já que não deve passar do throw no caso acima
			pagamentos.add(new Pagamento(data, valor)); //adicionando um pagamento ao vetor
			mValorTotalPagoPeloAssociado += valor;
			valor -= getValorDaParcela();
			mQuantidadeDeParcelasPagas++;

			if(mValorExcedenteDePagamentosAnteriores != 0) {
				if(valor + mValorExcedenteDePagamentosAnteriores >= getValorDaParcela()) {
					mQuantidadeDeParcelasPagas++;
					mValorExcedenteDePagamentosAnteriores -= getValorDaParcela();
				}
			}
			
			if(mQuantidadeDeParcelasPagas == getParcelas()) {
				mTaxaQuitada = true;
			}
		}
	}
	
	public double somarPagamentoDeAssociado(Date inicio, Date fim) {
		double somaPagamento = 0;
		
		for(Pagamento pagamento : pagamentos) {
			if((pagamento.getData().after(inicio) && pagamento.getData().before(fim)) || 
					(pagamento.getData().equals(inicio) || pagamento.getData().equals(fim))) {
				somaPagamento += pagamento.getValor();
			}
		}
			
		return somaPagamento;
	}
	
	public ExtratoDeTaxa(int codigo, Associacao associacao, String nome, int vigencia, double valorAno, int parcelas,
			boolean administrativa) {
		super(codigo, associacao, nome, vigencia, valorAno, parcelas, administrativa);
		
		mValorTotalPagoPeloAssociado = 0;
		mValorExcedenteDePagamentosAnteriores = 0;
		mQuantidadeDeParcelasPagas = 0;
		mTaxaQuitada = false;
	}
	
}