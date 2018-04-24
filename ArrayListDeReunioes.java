package associacao;

import java.util.ArrayList;
import java.util.Date;

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
	
	public double calcularFrequencia(int numero, Date inicio, Date fim) {
		int presencas = 0, total = 0;
		
		for(Reuniao reuniao : reunioes) {
			Date data = new Date(reuniao.getData()); //converte a data da reuniao para Date
			
			if((data.after(inicio) && data.before(fim)) || (data.equals(inicio) || data.equals(fim))) {
				if(reuniao.associadoPresente(numero)) {
					presencas++;
				}
				
				total++;
			}
		}
		
		return presencas / total;
	}
	
}
