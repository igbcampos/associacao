package associacao;

import java.util.ArrayList;

public class ArrayListDeTaxas {

	ArrayList<Taxa> taxas = new ArrayList<Taxa>();

	public void validarDados(Taxa taxa) throws ValorInvalido {
		if (taxa.getAssociacao() == null) {
			throw new ValorInvalido("associacao da taxa");
		}
		if (taxa.getNome() == "" || taxa.getNome() == null) {
			throw new ValorInvalido("nome da taxa");
		}
		if (taxa.getVigencia() <= 0) {
			throw new ValorInvalido("vigencia da taxa");
		}
		if (taxa.getValorAno() <= 0) {
			throw new ValorInvalido("valor anula da taxa");
		}
		if (taxa.getParcelas() <= 0) {
			throw new ValorInvalido("numeros de parcelas da taxa");
		}
	}

	public void inserir(Taxa taxa) throws ValorInvalido {
		validarDados(taxa);
		
		taxas.add(taxa);
	}

	public Taxa buscar(String nome, int vigencia) throws TaxaNaoExistente {
		for (Taxa taxa : taxas) {
			if (taxa.getNome().equals(nome) && taxa.getVigencia() == vigencia) {
				return taxa;
			}
		}

		throw new TaxaNaoExistente();
	}

	public double calcularTotalDeTaxas(int numeroAssociacao, int vigencia) throws TaxaNaoExistente {
		double totalDeTaxas = 0;

		for (Taxa taxa : taxas) {
			if (taxa.getAssociacao().getNumero() == numeroAssociacao && taxa.getVigencia() == vigencia) {
				totalDeTaxas += taxa.getValorAno();
			}
		}

		return totalDeTaxas;
	}

}
