package associacao;

import java.util.Date;

public interface InterfaceAssociacao {

		public double calcularFrequencia(int codigoAssociado, int numAssociacao, Date inicio, Date fim) throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente;
		
	    public void registrarPagamento(int numAssociacao, String taxa, int vigencia, int numAssociado, Date data, double valor) throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente, ValorIncorreto;
	    
	    public double somarPagamentoDeAssociado (int numAssociacao, int numAssociado, String nomeTaxa, int vigencia, Date inicio, Date fim) throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente;
	    
	    public double calcularTotalDeTaxas (int numAssociacao, int vigencia) throws AssociacaoNaoExistente, TaxaNaoExistente;

		public void adicionar(Associacao assoc) throws AssociacaoJaExistente, ValorIncorreto;
	    
	    public void adicionar(Associacao assoc, Associado a) throws AssociacaoNaoExistente, AssociadoJaExistente;

		public void adicionar(Associacao assoc, Reuniao r) throws AssociacaoNaoExistente, ReuniaoJaExistente;

		public void adicionar(Associacao assoc, Taxa t) throws AssociacaoNaoExistente, TaxaJaExistente;
}
