package botAssociacao;

//import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

class TestePovoadorBanco {

	@Test
	void povoar() throws AssociacaoJaExistente, ValorInvalido, AssociacaoNaoExistente, 
	AssociadoJaExistente, ReuniaoJaExistente, TaxaJaExistente, AssociadoNaoExistente, 
	ReuniaoNaoExistente, FrequenciaJaRegistrada, FrequenciaIncompativel, AssociadoJaRemido, 
	TaxaNaoExistente {
		
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		
		Associacao a = new Associacao(1234, "UFPI");
		
		controle.adicionar(a);
		
		//Registrando associados
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1999, 02, 19);
		long nasc = gc.getTimeInMillis();
		
		gc.set(2017, 02, 13);
		long data = gc.getTimeInMillis();
		
		Associado a1 = new Associado(2222, "Gabriel", "94264890", data, nasc);
		Associado a2 = new Associado(1111, "Lucas da Costa", "12341234", data, nasc);
		Associado a3 = new Associado(3333, "Lucas Hiago", "43214321", data, nasc);
		Associado a4 = new Associado(4444, "Caio", "55555555", data, nasc);
		Associado a5 = new Associado(5555, "Pedro", "98989898", data, nasc);
		
		//System.out.println(nasc + " " + data);
		
		controle.adicionar(1234, a1);
		controle.adicionar(1234, a2);
		controle.adicionar(1234, a3);
		controle.adicionar(1234, a4);
		controle.adicionar(1234, a5);
		
		//Registrando TelegramIds
		DAOAssociado daoAssociado = new DAOAssociado();
		daoAssociado.setTelegramId(463732828, 1111, 1234);
		daoAssociado.setTelegramId(393558363, 2222, 1234);
		daoAssociado.setTelegramId(42093344, 5555, 1234);
		daoAssociado.setTelegramId(155562015, 3333, 1234);
		
		//Criando datas
		gc.set(2017, 02, 21);
		long data1 = gc.getTimeInMillis();
		
		gc.set(2017, 03, 20);
		long data2 = gc.getTimeInMillis();
		
		gc.set(2017, 05, 19);
		long data3 = gc.getTimeInMillis();
		
		gc.set(2017, 9, 13);
		long data4 = gc.getTimeInMillis();
		
		gc.set(2017, 9, 25);
		long data5 = gc.getTimeInMillis();
		
		gc.set(2017, 10, 30);
		long data6 = gc.getTimeInMillis();
		
		gc.set(2017, 11, 8);
		long data7 = gc.getTimeInMillis();
		
		gc.set(2018, 0, 01);
		long data8 = gc.getTimeInMillis();
		
		gc.set(2018, 1, 17);
		long data9 = gc.getTimeInMillis();
		
		gc.set(2018, 2, 19);
		long data10 = gc.getTimeInMillis();
		
		//Registrando reuniões
		Reuniao r1 = new Reuniao(data1, "Boas-vindas aos novos integrantes da associação.");
		Reuniao r2 = new Reuniao(data2, "Assembleia para decidir sobre a importância de Cálculo.");
		Reuniao r3 = new Reuniao(data3, "Discussão sobre a inclusão da materia de desenvolvimento de apps.");
		Reuniao r4 = new Reuniao(data4, "Mais uma aula de programação estruturada, brincadeirinha.");
		Reuniao r5 = new Reuniao(data5, "Jogatina no LEG.");
		Reuniao r6 = new Reuniao(data6, "Foi discutido a possibilidade de ter um almoço especial no RU.");
		Reuniao r7 = new Reuniao(data7, "Discorreu-se sobre desenvolvimento web");
		Reuniao r8 = new Reuniao(data8, "Primeira reunião do ano, apenas uma confraternização");
		Reuniao r9 = new Reuniao(data9, "Greve dos funcionários, Fora Temer!");
		Reuniao r10 = new Reuniao(data10, "Decisão sobre o adiamento das aulas em detrimento da greve dos camioneiros.");
		
		controle.adicionar(1234, r1);
		controle.adicionar(1234, r2);
		controle.adicionar(1234, r3);
		controle.adicionar(1234, r4);
		controle.adicionar(1234, r5);
		controle.adicionar(1234, r6);
		controle.adicionar(1234, r7);
		controle.adicionar(1234, r8);
		controle.adicionar(1234, r9);
		controle.adicionar(1234, r10);
	
		//Registrando frequencias
		controle.registrarFrequencia(2222, 1234, data2);
		controle.registrarFrequencia(2222, 1234, data4);
		controle.registrarFrequencia(2222, 1234, data6);
		controle.registrarFrequencia(2222, 1234, data8);
		controle.registrarFrequencia(2222, 1234, data10);
		
		controle.registrarFrequencia(1111, 1234, data1);
		controle.registrarFrequencia(1111, 1234, data3);
		controle.registrarFrequencia(1111, 1234, data4);
		controle.registrarFrequencia(1111, 1234, data5);
		controle.registrarFrequencia(1111, 1234, data6);
		controle.registrarFrequencia(1111, 1234, data7);
		controle.registrarFrequencia(1111, 1234, data8);
		controle.registrarFrequencia(1111, 1234, data10);
		
		controle.registrarFrequencia(3333, 1234, data1);
		controle.registrarFrequencia(3333, 1234, data4);
		controle.registrarFrequencia(3333, 1234, data5);
		controle.registrarFrequencia(3333, 1234, data6);
		controle.registrarFrequencia(3333, 1234, data8);
		controle.registrarFrequencia(3333, 1234, data9);
		
		controle.registrarFrequencia(4444, 1234, data1);
		controle.registrarFrequencia(4444, 1234, data2);
		controle.registrarFrequencia(4444, 1234, data3);
		controle.registrarFrequencia(4444, 1234, data4);
		controle.registrarFrequencia(4444, 1234, data5);
		
		controle.registrarFrequencia(5555, 1234, data2);
		controle.registrarFrequencia(5555, 1234, data3);
		controle.registrarFrequencia(5555, 1234, data4);
		controle.registrarFrequencia(5555, 1234, data6);
		controle.registrarFrequencia(5555, 1234, data8);
		controle.registrarFrequencia(5555, 1234, data10);
				
		//Registrando taxas
		Taxa t1 = new Taxa("Ficha do RU", 2017, 200, 10, true);
		Taxa t2 = new Taxa("Xerox", 2018, 100, 5, true);
		Taxa t3 = new Taxa("Pastel", 2018, 500, 10, false);
		
		controle.adicionar(1234, t1);
		controle.adicionar(1234, t2);
		controle.adicionar(1234, t3);
		
		//Registrando pagamentos
		controle.registrarPagamento(1234, "Ficha do RU", 2017, 2222, data1, 20);
		controle.registrarPagamento(1234, "Ficha do RU", 2017, 2222, data2, 40);
		controle.registrarPagamento(1234, "Xerox", 2018, 2222, data3, 20);
		controle.registrarPagamento(1234, "Xerox", 2018, 2222, data4, 20);
		controle.registrarPagamento(1234, "Pastel", 2018, 2222, data5, 50);
		controle.registrarPagamento(1234, "Pastel", 2018, 2222, data6, 50);
		controle.registrarPagamento(1234, "Pastel", 2018, 2222, data7, 50);
		controle.registrarPagamento(1234, "Pastel", 2018, 2222, data8, 100);
		
		controle.registrarPagamento(1234, "Ficha do RU", 2017, 1111, data1, 20);
		controle.registrarPagamento(1234, "Ficha do RU", 2017, 1111, data2, 40);
		controle.registrarPagamento(1234, "Xerox", 2018, 1111, data3, 20);
		controle.registrarPagamento(1234, "Xerox", 2018, 1111, data4, 40);
		controle.registrarPagamento(1234, "Xerox", 2018, 1111, data5, 20);
		controle.registrarPagamento(1234, "Xerox", 2018, 1111, data6, 20);
		controle.registrarPagamento(1234, "Pastel", 2018, 1111, data7, 50);
		
		controle.registrarPagamento(1234, "Ficha do RU", 2017, 3333, data1, 100);
		controle.registrarPagamento(1234, "Ficha do RU", 2017, 3333, data2, 100);
		controle.registrarPagamento(1234, "Pastel", 2018, 3333, data9, 50);
		controle.registrarPagamento(1234, "Pastel", 2018, 3333, data10, 200);
		
		controle.registrarPagamento(1234, "Ficha do RU", 2017, 4444, data1, 20);
		controle.registrarPagamento(1234, "Ficha do RU", 2017, 4444, data2, 20);
		controle.registrarPagamento(1234, "Ficha do RU", 2017, 4444, data3, 20);
		controle.registrarPagamento(1234, "Ficha do RU", 2017, 4444, data4, 20);
		controle.registrarPagamento(1234, "Xerox", 2018, 4444, data5, 20);
		controle.registrarPagamento(1234, "Xerox", 2018, 4444, data6, 40);
		controle.registrarPagamento(1234, "Xerox", 2018, 4444, data7, 20);
		controle.registrarPagamento(1234, "Xerox", 2018, 4444, data8, 20);
		controle.registrarPagamento(1234, "Pastel", 2018, 4444, data9, 50);
		controle.registrarPagamento(1234, "Pastel", 2018, 4444, data10, 50);
		
		controle.registrarPagamento(1234, "Ficha do RU", 2017, 5555, data1, 20);
		controle.registrarPagamento(1234, "Ficha do RU", 2017, 5555, data2, 40);
		controle.registrarPagamento(1234, "Xerox", 2018, 5555, data3, 20);
		controle.registrarPagamento(1234, "Xerox", 2018, 5555, data4, 40);
		controle.registrarPagamento(1234, "Xerox", 2018, 5555, data5, 20);
		controle.registrarPagamento(1234, "Xerox", 2018, 5555, data6, 20);
		controle.registrarPagamento(1234, "Pastel", 2018, 5555, data7, 50);
		controle.registrarPagamento(1234, "Pastel", 2018, 5555, data8, 50);
	}

}
