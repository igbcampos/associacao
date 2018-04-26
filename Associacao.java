package associacao;

public class Associacao {

	private int mNumero;
	private String mNome;
	private ArrayListDeAssociados associados;
	private ArrayListDeTaxas taxas;
	private ArrayListDeReunioes reunioes;
	
	public int getNumero() {
		return mNumero;
	}
	
	public String getNome() {
		return mNome;
	}
	
	public void setNumero(int numero) {
		mNumero = numero;
	}
	
	public void setNome(String nome) {
		mNome = nome;
	}
	
	public void adicionar(Associado associado) throws AssociadoJaExistente {
		try {
			associados.buscar(associado.getNumero());
			throw new AssociadoJaExistente();
		}
		catch(AssociadoNaoExistente e) {
			associados.inserir(associado);
		}
	}
	
	public void adicionar(Reuniao reuniao) throws ReuniaoJaExistente {
		try {
			reunioes.buscar(reuniao.getData());
			throw new ReuniaoJaExistente();
		}
		catch(ReuniaoNaoExistente e) {
			reunioes.inserir(reuniao);
		}
	}
	
	public void adicionar(Taxa taxa) throws TaxaJaExistente {
		try {
			taxas.buscar(taxa.getNome());
			throw new TaxaJaExistente();
		}
		catch(TaxaNaoExistente e) {
			taxas.inserir(taxa);
			associados.adicionarTaxaNoExtratoDosAssociados(taxa);
		}
	}

	//eu acho que nao precisaria da excecao reuniaoNaoExistente	
	public double calcularFrequencia(int codigo, long inicio, long fim) throws AssociadoNaoExistente, ReuniaoNaoExistente {
		Associado associado = associados.buscar(codigo);
		
		return reunioes.calcularFrequencia(associado.getNumero(), inicio, fim);
	}
	
	public void registrarFrequencia(int codigoAssociado, long dataReuniao) throws AssociadoNaoExistente, ReuniaoNaoExistente, FrequenciaJaRegistrada {
		Associado associado = associados.buscar(codigoAssociado);
		
		reunioes.adicionarFrequenciaAssociado(associado, dataReuniao);		
	}
	
	public void registrarPagamento(String taxa, int vigencia, int numAssociado, long data, double valor) throws AssociadoNaoExistente, TaxaNaoExistente, ValorInvalido {
		Associado associado = associados.buscar(numAssociado);
		
		associado.registrarPagamento(taxa, vigencia, data, valor);
	}
	
	public double somarPagamentoDeAssociado(int numeroDoAssociado, String nomeDaTaxa, int vigencia, long inicio, long fim) throws AssociadoNaoExistente, TaxaNaoExistente {
		Associado associado = associados.buscar(numeroDoAssociado);
		
		return associado.somarPagamentoDeAssociado(nomeDaTaxa, vigencia, inicio, fim);
	}
	
	public double calcularTotalDeTaxas(int numeroAssociacao, int vigencia) throws TaxaNaoExistente{
		return taxas.calcularTotalDeTaxas(numeroAssociacao, vigencia);
	}
	
	public Associacao(int numero, String nome) {
		mNumero = numero;
		mNome = nome;
	}
	
}
