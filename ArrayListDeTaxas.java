package associacao;

import java.util.ArrayList;

public class ArrayListDeTaxas {

	ArrayList<Taxa> taxas = new ArrayList<Taxa>();
	
	public void inserir(Taxa taxa) {
		taxas.add(taxa);
	}
	
	public Taxa buscar(String nome) throws TaxaNaoExistente{
		for(Taxa taxa : taxas) {
			if(taxa.getNome().equals(nome)) {
				return taxa;
			}
		}
		
		throw new TaxaNaoExistente();
	}
	
	public double calcularTotalDeTaxas(int numeroAssociacao, int vigencia) throws TaxaNaoExistente {
		double totalDeTaxas = 0;
		
		for(Taxa taxa : taxas) {
			if(taxa.getAssociacao().getNumero() == numeroAssociacao && taxa.getVigencia() == vigencia) {
				totalDeTaxas += taxa.getValorAno() / taxa.getParcelas();
			}
		}
		
		return totalDeTaxas;
	}
	
}
