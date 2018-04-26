package associacao;

import java.util.ArrayList;
import java.util.Date;

public class ArrayListDeAssociados {

	ArrayList<Associado> associados = new ArrayList<Associado>();
	
	public void inserir(Associado associado) {
		associados.add(associado);
	}
	
	public Associado buscar(int numero) throws AssociadoNaoExistente {
		for(Associado associado : associados) {
			if(associado.getNumero() == numero) {
				return associado;
			}
		}
		
		throw new AssociadoNaoExistente();
	}
	
	public void adicionarTaxaNoExtratoDosAssociados(Taxa taxa) {
		for(Associado associado : associados) {
			if(associado instanceof AssociadoRemido) {
				if(!(taxa.getAdministrativa())) {
					associado.inserirTaxaNoExtrato(taxa);
				}
			}
			else {
				associado.inserirTaxaNoExtrato(taxa);
			}
		}
	}
	
	/*public void registrarPagamento(String taxa, int vigencia, int numAssociado, Date data, double valor) throws AssociadoNaoExistente, TaxaNaoExistente, ValorIncorreto {
		Associado associado = buscar(numAssociado);
		
		associado.registrarPagamento(taxa, vigencia, data, valor);
	}
	
	public double somarPagamentoDeAssociado(int numeroDoAssociado, String nomeDaTaxa, int vigencia, Date inicio, Date fim) throws AssociadoNaoExistente, TaxaNaoExistente {
		Associado associado = buscar(numeroDoAssociado);
		
		return associado.somarPagamentoDeAssociado(nomeDaTaxa, vigencia, inicio, fim);
	}*/
	
}
