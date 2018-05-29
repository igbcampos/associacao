/* No momento, o que eu lembro que falta �: verificar se a data do ultimo pagamento de
taxa de um associado e um mes antes da sua data de remissao, caso seja remido, e o 
ValorInvalido quando algo nao for preenchido corretamente, que eu nao sei como faz.
*/
package associacaoBD;

public class MinhaAssociacao implements InterfaceAssociacao {

	private ArrayListDeAssociacoes associacoes = new ArrayListDeAssociacoes();
	
	//eu acho que nao precisaria da excecao reuniaoNaoExistente
	public double calcularFrequencia(int codigoAssociado, int numAssociacao, long inicio, long fim)
			throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente {
		Associacao associacao = associacoes.buscar(numAssociacao);
		
		return associacao.calcularFrequencia(codigoAssociado, inicio, fim);
	}

	public void registrarFrequencia(int codigoAssociado, int numAssociacao, long dataReuniao)
			throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente, FrequenciaJaRegistrada,
			FrequenciaIncompativel {
		Associacao associacao = associacoes.buscar(numAssociacao);
		
		associacao.registrarFrequencia(codigoAssociado, dataReuniao);
	}

	public void registrarPagamento(int numAssociacao, String taxa, int vigencia, int numAssociado, long data,
			double valor)
			throws AssociacaoNaoExistente, AssociadoNaoExistente, AssociadoJaRemido, TaxaNaoExistente, ValorInvalido {
		Associacao associacao = associacoes.buscar(numAssociacao);
		
		associacao.registrarPagamento(taxa, vigencia, numAssociado, data, valor);
		
	}

	public double somarPagamentoDeAssociado(int numAssociacao, int numAssociado, String nomeTaxa, int vigencia,
			long inicio, long fim) throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente {
		Associacao associacao = associacoes.buscar(numAssociacao);
		
		return associacao.somarPagamentoDeAssociado(numAssociado, nomeTaxa, vigencia, inicio, fim);
	}

	//da forma que eu estou fazendo, eu acho que nao precisa de TaxaNaoExistente
	public double calcularTotalDeTaxas(int numAssociacao, int vigencia)
			throws AssociacaoNaoExistente, TaxaNaoExistente {
		Associacao associacao = associacoes.buscar(numAssociacao);
		
		return associacao.calcularTotalDeTaxas(associacao.getNumero(), vigencia);
	}
	
	public void validarDados(Associacao associacao) throws ValorInvalido{
		if(associacao.getNome() == "" || associacao.getNome() == null) {
			throw new ValorInvalido("nome de associacao");
		}
		if(associacao.getNumero() <= 0) {
			throw new ValorInvalido("numero de associacao");
		}
	}

	public void adicionar(Associacao assoc) throws AssociacaoJaExistente, ValorInvalido {
		validarDados(assoc);
		DAOAssociacao daoAssociacao = new DAOAssociacao();
		
		try {
			daoAssociacao.buscar(assoc.getNumero());
			throw new AssociacaoJaExistente();
		}
		catch(AssociacaoNaoExistente e) {
			daoAssociacao.inserir(assoc);
		}
	}

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
	
	public void adicionar(int associacao, Associado a)
			throws AssociacaoNaoExistente, AssociadoJaExistente, ValorInvalido {
		validarDados(a);
		DAOAssociacao daoAssociacao = new DAOAssociacao();
		DAOAssociado daoAssociado = new DAOAssociado();
		daoAssociacao.buscar(associacao);
		
		try {
			daoAssociado.buscar(associacao, a.getNumero());
			throw new AssociadoJaExistente();
		}
		catch(AssociadoNaoExistente e) {
			daoAssociado.inserir(associacao, a);
		}
	}
	
	public void validarDados(Reuniao reuniao) throws ValorInvalido{
		if(reuniao.getData() <= 0) {
			throw new ValorInvalido("data da reuniao");
		}
		if(reuniao.getAta() == "" || reuniao.getAta() == null) {
			throw new ValorInvalido("ata da reuniao");
		}
	}
	
	public void adicionar(int associacao, Reuniao r) throws AssociacaoNaoExistente, ReuniaoJaExistente, ValorInvalido {
		validarDados(r);
		DAOAssociacao daoAssociacao = new DAOAssociacao();
		DAOReuniao daoReuniao = new DAOReuniao();
		daoAssociacao.buscar(associacao);
		
		try {
			daoReuniao.buscar(associacao, r.getData());
			throw new ReuniaoJaExistente();
		}
		catch(ReuniaoNaoExistente e) {
			daoReuniao.inserir(associacao, r);
		}
	}

	public void validarDados(Taxa taxa) throws ValorInvalido {
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
	
	public void adicionar(int associacao, Taxa t) throws AssociacaoNaoExistente, TaxaJaExistente, ValorInvalido {
		validarDados(t);
		DAOAssociacao daoAssociacao = new DAOAssociacao();
		DAOTaxa daoTaxa = new DAOTaxa();
		daoAssociacao.buscar(associacao);
		
		try {
			daoTaxa.buscar(associacao, t.getNome(), t.getVigencia());
			throw new TaxaJaExistente();
		}
		catch(TaxaNaoExistente e) {
			daoTaxa.inserir(associacao, t);
		}
	}
	
	public void limparBanco() {
		DAOAssociacao daoAssociacao = new DAOAssociacao();
		DAOAssociado daoAssociado = new DAOAssociado();
		DAOReuniao daoReuniao = new DAOReuniao();
		DAOTaxa daoTaxa = new DAOTaxa();
		
		daoTaxa.limpar();
		daoReuniao.limpar();
		daoAssociado.limpar();
		daoAssociacao.limpar();
	}
	
}