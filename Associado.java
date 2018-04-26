package associacao;

import java.util.ArrayList;

public class Associado {

	private int mNumero;
	private String mNome;
	private String mEndereco;
	private String mTelefone;
	private long mNascimento;
	private long mDataAssociacao;
	ArrayList<ExtratoDeTaxa> extratoDeTaxas = new ArrayList<ExtratoDeTaxa>();
	
	public int getNumero() {
		return mNumero;
	}
	
	public String getNome() {
		return mNome;
	}
	
	public String getEndereco() {
		return mEndereco;
	}
	
	public String getTelefone() {
		return mTelefone;
	}
	
	public long getNascimento() {
		return mNascimento;
	}
	
	public long getDataAssociacao() {
		return mDataAssociacao;
	}
	
	public double somarPagamentoDeAssociado(String nomeTaxa, int vigencia, long inicio, long fim) throws TaxaNaoExistente {
		for(ExtratoDeTaxa taxa : extratoDeTaxas) {
			if(taxa.getNome().equals(nomeTaxa) && taxa.getVigencia() == vigencia) {
				return taxa.somarPagamentoDeAssociado(inicio, fim);
			}
		}
		
		throw new TaxaNaoExistente();
	}
	
	public void setNumero(int numero) {
		mNumero = numero;
	}
	
	public void setNome(String nome) {
		mNome = nome;
	}
	
	public void setEndereco(String endereco) {
		mEndereco = endereco;
	}
	
	public void setTelefone(String telefone) {
		mTelefone = telefone;
	}
	
	public void setNascimento(long nascimento) {
		mNascimento = nascimento;
	}
	
	public void setDataAssociacao(long dataAssociacao) {
		mDataAssociacao = dataAssociacao;
	}
	
	public void inserirTaxaNoExtrato(Taxa taxa) {
		int codigo = taxa.getCodigo();
		Associacao associacao = taxa.getAssociacao();
		String nome = taxa.getNome();
		int vigencia = taxa.getVigencia();
		double valorAno = taxa.getValorAno();
		int parcelas = taxa.getParcelas();
		boolean administrativa = taxa.getAdministrativa();
		
		extratoDeTaxas.add(new ExtratoDeTaxa(codigo, associacao, nome, vigencia, valorAno, parcelas, administrativa));
	}
	
	public void registrarPagamento(String nomeTaxa, int vigencia, long data, double valor) throws ValorInvalido, TaxaNaoExistente {
		for(ExtratoDeTaxa taxa : extratoDeTaxas) {
			if(taxa.getNome().equals(nomeTaxa) && taxa.getVigencia() == vigencia) {
				taxa.registrarPagamento(data, valor);
			}
		}
		
		throw new TaxaNaoExistente();
	}
	
	public Associado(int numero, String nome, String telefone, long dataAssociacao, long nascimento) {
		mNumero = numero;
		mNome = nome;
		mTelefone = telefone;
		mDataAssociacao = dataAssociacao;
		mNascimento = nascimento;
	}
	
}
