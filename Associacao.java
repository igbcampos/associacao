package associacaoBD;

public class Associacao {

	private int mNumero;
	private String mNome;
	private ArrayListDeAssociados associados = new ArrayListDeAssociados();
	private ArrayListDeTaxas taxas = new ArrayListDeTaxas();
	private ArrayListDeReunioes reunioes = new ArrayListDeReunioes();
	
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
	
	public void adicionar(Associado associado) throws AssociadoJaExistente, ValorInvalido {
		try {
			associados.buscar(associado.getNumero());
			throw new AssociadoJaExistente();
		}
		catch(AssociadoNaoExistente e) {
			associados.inserir(associado);
		}
	}
	
	public void adicionar(Reuniao reuniao) throws ReuniaoJaExistente, ValorInvalido {
		try {
			reunioes.buscar(reuniao.getData());
			throw new ReuniaoJaExistente();
		}
		catch(ReuniaoNaoExistente e) {
			reunioes.inserir(reuniao);
		}
	}
	
	public void adicionar(Taxa taxa) throws TaxaJaExistente, ValorInvalido {
		try {
			taxas.buscar(taxa.getNome(), taxa.getVigencia());
			throw new TaxaJaExistente();
		}
		catch(TaxaNaoExistente e) {
			taxa.setAssociacao(new Associacao(mNumero, mNome));
			
			taxas.inserir(taxa);
			associados.adicionarTaxaNoExtratoDosAssociados(taxa);
		}
	}

	public double calcularFrequencia(int codigo, long inicio, long fim) throws AssociadoNaoExistente, ReuniaoNaoExistente {
		Associado associado = associados.buscar(codigo);
		
		return reunioes.calcularFrequencia(associado.getNumero(), inicio, fim);
	}
	
	public void registrarFrequencia(int codigoAssociado, long dataReuniao) throws AssociadoNaoExistente, ReuniaoNaoExistente, FrequenciaJaRegistrada {
		Associado associado = associados.buscar(codigoAssociado);
		
		reunioes.adicionarFrequenciaAssociado(associado, dataReuniao);		
	}
	
	public void registrarPagamento(String taxa, int vigencia, int numAssociado, long data, double valor) throws AssociadoJaRemido, AssociadoNaoExistente, TaxaNaoExistente, ValorInvalido {
		Associado associado = associados.buscar(numAssociado);
		
		if(associado instanceof AssociadoRemido && taxas.buscar(taxa, vigencia).getAdministrativa()) {//se for administrativa e o associado for remido da um throw
			throw new AssociadoJaRemido();
		}
		
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
