package associacao;

public class AssociadoRemido extends Associado {

	private long mDataRemissao;
	
	public long getDataRemissao() {
		return mDataRemissao;
	}
	
	public void setDataRemissao(long dataRemissao) {
		mDataRemissao = dataRemissao;
	}
	
	public AssociadoRemido(int numero, String nome, String telefone, long dataAssociacao, long nascimento, long dataRemissao) {
		super(numero, nome, telefone, dataAssociacao, nascimento);
		mDataRemissao = dataRemissao;
	}
	
}
