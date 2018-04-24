/* No momento, o que eu lembro que falta é: verificar se a data do ultimo pagamento de
taxa de um associado e um mes antes da sua data de remissao, caso seja remido, e o 
ValorInvalido quando algo nao for preenchido corretamente, que eu nao sei como faz.
*/
package associacao;

import java.util.Date;

public class ControleAssociacao implements InterfaceAssociacao {

	private ArrayListDeAssociacoes associacoes;
	private ArrayListDeTaxas taxas;
	
	//eu acho que nao precisaria da excecao reuniaoNaoExistente
	public double calcularFrequencia(int codigoAssociado, int numAssociacao, Date inicio, Date fim)
			throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente {
		Associacao associacao = associacoes.buscar(numAssociacao);
		
		return associacao.calcularFrequencia(codigoAssociado, inicio, fim);
	}

	public void registrarPagamento(int numAssociacao, String taxa, int vigencia, int numAssociado, Date data,
			double valor) throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente, ValorIncorreto {
		Associacao associacao = associacoes.buscar(numAssociacao);
		
		associacao.registrarPagamento(taxa, vigencia, numAssociado, data, valor);
	}

	public double somarPagamentoDeAssociado(int numAssociacao, int numAssociado, String nomeTaxa, int vigencia,
			Date inicio, Date fim) throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente {
		Associacao associacao = associacoes.buscar(numAssociacao);
		
		return associacao.somarPagamentoDeAssociado(numAssociado, nomeTaxa, vigencia, inicio, fim);
	}

	/*da forma que eu estou fazendo, eu acho que nao precisa de TaxaNaoExistente
	ja que eu nao procuro uma taxa especifica, mas percorro o vetor de taxas em busca
	dos valores das taxas existentes, caso nao tivesse nenhuma taxa, nao acho que seria
	exatamente uma TaxaInexistente, ou seria, ja que nao tem nenhuma? */
	public double calcularTotalDeTaxas(int numAssociacao, int vigencia)
			throws AssociacaoNaoExistente, TaxaNaoExistente {
		Associacao associacao = associacoes.buscar(numAssociacao);
		
		return taxas.calcularTotalDeTaxas(associacao.getNumero(), vigencia);
	}

	public void adicionar(Associacao assoc) throws AssociacaoJaExistente, ValorIncorreto {
		try {
			associacoes.buscar(assoc.getNumero());
		}
		catch(AssociacaoNaoExistente e) {
			associacoes.inserir(assoc);
		}
	}
	
	public void adicionar(Associacao assoc, Associado a) throws AssociacaoNaoExistente, AssociadoJaExistente {
		Associacao associacao;
		
		associacao = associacoes.buscar(assoc.getNumero());
		//assumindo que se a associacao nao existir vai dar uma excecao e o metodo para aqui, se isso nao ocorre o metodo continua
		associacao.adicionar(a);
	}

	public void adicionar(Associacao assoc, Reuniao r) throws AssociacaoNaoExistente, ReuniaoJaExistente {
		Associacao associacao;
		
		associacao = associacoes.buscar(assoc.getNumero());
		//assumindo que se a associacao nao existir vai dar uma excecao e o metodo para aqui, se isso nao ocorre o metodo continua
		associacao.adicionar(r);
	}

	/*nao esta, em tese, permitindo inserir uma taxa com mesmo nome mas vigencia
	diferente, mas parece que pode haver taxa com mesmo nome e vigencias diferentes */
	public void adicionar(Associacao assoc, Taxa t) throws AssociacaoNaoExistente, TaxaJaExistente {
		Associacao associacao;
		
		associacao = associacoes.buscar(assoc.getNumero());
		//assumindo que se a associacao nao existir vai dar uma excecao e o metodo para aqui, se isso nao ocorre o metodo continua
		try {
			taxas.buscar(t.getNome());
			throw new TaxaJaExistente();
		}
		catch(TaxaNaoExistente e) {
			taxas.inserir(t);
			associacao.adicionarTaxaNoExtratoDosAssociados(t);
		}
	}
	
}