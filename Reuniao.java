package associacao;

import java.util.ArrayList;

public class Reuniao {

	ArrayList<Associado> frequencia = new ArrayList<Associado>();
	private String mAta;
	private long mData;
	
	public ArrayList<Associado> getFrequencia() {
		return frequencia;
	}
	
	public String getAta() {
		return mAta;
	}
	
	public long getData() {
		return mData;
	}
	
	public void setAta(String ata) {
		mAta = ata;
	}
	
	public void setData(long data) {
		mData = data;
	}
	
	public void adicionarFrequenciaAssociado(Associado associado) throws FrequenciaJaRegistrada {
		if(!(associadoPresente(associado.getNumero()))) {
			frequencia.add(associado);
		}
		else {
			throw new FrequenciaJaRegistrada();
		}
	}
	
	public boolean associadoPresente(int numero) {
		for(Associado associado : frequencia) {
			if(associado.getNumero() == numero) {
				return true;
			}
		}
		
		return false;
	}
	
	//pode ser que precise dar um throw de associado nao encontrado, mas nao tenho certeza
	//talvez um return null baste, ja que ele quer saber so usuaria estava presente, assim
	//se o metodo retornar null significa que nao estava
	/*public Associado buscar(int numero) { 
		for(Associado associado : frequencia) {
			if(associado.getNumero() == numero) {
				return associado;
			}
		}
		
		return null;
	}*/
	
}
