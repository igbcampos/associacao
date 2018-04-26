package associacao;

import java.util.ArrayList;

public class ArrayListDeReunioes {

	ArrayList<Reuniao> reunioes = new ArrayList<Reuniao>();
	
	public void inserir(Reuniao reuniao) {
		reunioes.add(reuniao);
	}
	
	public Reuniao buscar(long data) throws ReuniaoNaoExistente {
		for(Reuniao reuniao : reunioes) {
			if(reuniao.getData() == data) {
				return reuniao;
			}
		}
		
		throw new ReuniaoNaoExistente();
	}
	
	public void adicionarFrequenciaAssociado(Associado associado, long dataReuniao) throws FrequenciaJaRegistrada, ReuniaoNaoExistente {
		Reuniao reuniao = buscar(dataReuniao);
		
		reuniao.adicionarFrequenciaAssociado(associado);
	}
	
	public double calcularFrequencia(int numero, long inicio, long fim) {
		int presencas = 0, total = 0;
		
		for(Reuniao reuniao : reunioes) {
			long data = reuniao.getData();
			
			if(data >= inicio && data <= fim) {
				if(reuniao.associadoPresente(numero)) {
					presencas++;
				}
				
				total++;
			}
		}
		
		return presencas / total;
	}
	
}
