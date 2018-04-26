package associacao;

import java.util.ArrayList;

public class ArrayListDeAssociados {

	ArrayList<Associado> associados = new ArrayList<Associado>();
	
	public void validarDados(Associado associado) throws ValorInvalido {
		if(associado.getNome() == "" || associado.getNome() == null) {
			throw new ValorInvalido("nome de associado");
		}
		if(associado.getDataAssociacao() <= 0) {
			throw new ValorInvalido("data de associacao");
		}
		if(associado.getNascimento() <= 0) {
			throw new ValorInvalido("data de nascimento");
		}
		if(associado.getNumero() <= 0) {
			throw new ValorInvalido("numero de associado");
		}
		if(associado.getTelefone() == "" || associado.getTelefone() == null) {
			throw new ValorInvalido("telefone de associado");
		}
	}
	
	public void inserir(Associado associado) throws ValorInvalido {
		validarDados(associado);
		
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
