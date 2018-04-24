package associacao;

import java.util.ArrayList;

public class ArrayListDeAssociacoes {

	ArrayList<Associacao> associacoes = new ArrayList<Associacao>();
	
	public void inserir(Associacao associacao) {
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
