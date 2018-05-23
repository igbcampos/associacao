package associacao;

import java.util.ArrayList;

public class Associado {

	private int mNumero;
	private String mNome;
	private String mTelefone;
	private long mNascimento;
	private long mDataAssociacao;
	private ArrayList<ExtratoDeTaxa> extratoDeTaxas = new ArrayList<ExtratoDeTaxa>();
	
	public int getNumero() {
		return mNumero;
	}
	
	public String getNome() {
		return mNome;
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
		for(ExtratoDeTaxa extratoDeTaxa : extratoDeTaxas) {
			if(extratoDeTaxa.getTaxa().getNome().equals(nomeTaxa) && extratoDeTaxa.getTaxa().getVigencia() == vigencia) {
				return extratoDeTaxa.somarPagamentoDeAssociado(inicio, fim);
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
		extratoDeTaxas.add(new ExtratoDeTaxa(taxa));
	}
	
	public void registrarPagamento(String nomeTaxa, int vigencia, long data, double valor) throws ValorInvalido, TaxaNaoExistente {
		for(ExtratoDeTaxa extratoDeTaxa : extratoDeTaxas) {
			if(extratoDeTaxa.getTaxa().getNome().equals(nomeTaxa) && extratoDeTaxa.getTaxa().getVigencia() == vigencia) {
				extratoDeTaxa.registrarPagamento(data, valor);
				return;
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
