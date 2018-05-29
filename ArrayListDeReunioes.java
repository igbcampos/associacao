package associacaoBD;

import java.util.ArrayList;

public class ArrayListDeReunioes {

	ArrayList<Reuniao> reunioes = new ArrayList<Reuniao>();
	
	public void validarDados(Reuniao reuniao) throws ValorInvalido{
		if(reuniao.getData() <= 0) {
			throw new ValorInvalido("data da reuniao");
		}
		if(reuniao.getAta() == "" || reuniao.getAta() == null) {
			throw new ValorInvalido("ata da reuniao");
		}
	}
	
	public void inserir(Reuniao reuniao) throws ValorInvalido {
		validarDados(reuniao);
		
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
	
	public double calcularFrequencia(int numero, long inicio, long fim) throws ReuniaoNaoExistente {
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
		
		if(total == 0) {
			throw new ReuniaoNaoExistente();
		}
		
		return (double)presencas / total;
	}
	
}
