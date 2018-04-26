package associacao;

import java.util.ArrayList;

public class ArrayListDeAssociacoes {

	ArrayList<Associacao> associacoes = new ArrayList<Associacao>();
	
	public void validarDados(Associacao associacao) throws ValorInvalido{
		if(associacao.getNome().length() == 0 || associacao.getNome() == null) {
			throw new ValorInvalido("nome de associacao");
		}
		if(associacao.getNumero() <= 0) {
			throw new ValorInvalido("numero de associacao");
		}
	}
	
	public void inserir(Associacao associacao) throws ValorInvalido {
		validarDados(associacao);
		
		associacoes.add(associacao);
	}
	
	public Associacao buscar(int numero) throws AssociacaoNaoExistente {
		for(Associacao associacao : associacoes) {
			if(associacao.getNumero() == numero) {
				return associacao;
			}
		}
		
		throw new AssociacaoNaoExistente();
	}
	
}
