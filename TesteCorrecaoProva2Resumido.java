package associacaoBD;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class TesteCorrecaoProva2Resumido {

	@Test
	public void testarCadastroNormalAssociacao() throws AssociacaoJaExistente, ValorInvalido {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1313, "Movimento Sem Terra");
		controle.adicionar(a1);
		
		try {
			controle.adicionar(a1);
			fail("Deveria ter dado erro! Foi inserida uma associacao que ja existia.");
		}
		catch(AssociacaoJaExistente e) {
			//Ok, era pra dar erro mesmo.
		}		
		controle.limparBanco();
	}

	@Test
	public void testarCadastroAssociacaoNomeVazio() throws AssociacaoJaExistente, ValorInvalido {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1313, "");	
		try {
			controle.adicionar(a1);
			fail("Deveria ter dado erro! Esta inserindo uma associacao com nome nulo.");
		}
		catch(ValorInvalido e) {
			//Ok, era pra dar erro mesmo.
		}	
		controle.limparBanco();
	}
	
	@Test
	public void testarCadastroAssociado() throws AssociacaoJaExistente, ValorInvalido, AssociacaoNaoExistente, AssociadoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1313, "Movimento Sem Terra");
		Associacao a2 = new Associacao(1331, "Central Unica dos Trabalhadores");
		Associacao a3 = new Associacao(3113, "Partido dos Trabalhadores");
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nascimento = gc.getTimeInMillis();
		Date hoje = new Date();
		Associado as1 = new Associado(1234, "Lula", "3232-3232", hoje.getTime(), nascimento);
		Associado as2 = new Associado(1111, "Dilma", "1234-5678", hoje.getTime(), nascimento);
		Associado as3 = new Associado(4321, "Fabiola", "3222-2121", hoje.getTime(), nascimento);
		
		controle.adicionar(a1);
		controle.adicionar(a2);
		controle.adicionar(a3);
		
		controle.adicionar(1313, as1);
		controle.adicionar(3113, as2);
		controle.adicionar(1331, as3);
		
		try {
			controle.adicionar(1313, as1);
			fail("Deveria ter dado erro! Esta inserindo um associado que ja foi cadastrado.");
		}
		catch(AssociadoJaExistente e) {
			//Ok, era pra dar erro mesmo.
		}
		
		controle.limparBanco();
	}
	
	@Test
	public void testarCadastroAssociadoNomeVazio() throws AssociacaoJaExistente, ValorInvalido, AssociacaoNaoExistente, AssociadoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(3113, "Central Unica dos Trabalhadores");
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nascimento = gc.getTimeInMillis();
		Date hoje = new Date();
		Associado as1 = new Associado(1234, "", "3232-3232", hoje.getTime(), nascimento);
		
		controle.adicionar(a1);
		
		try {
			controle.adicionar(3113, as1);
			fail("Deveria ter dado erro! Esta inserindo uma associado com nome vazio.");
		}
		catch(ValorInvalido e) {
			//Ok, era pra dar erro mesmo.
		}
		
		controle.limparBanco();
	}
	
	
	@Test
	public void testarCadastroReuniao() throws AssociacaoNaoExistente, ValorInvalido, ReuniaoJaExistente, AssociacaoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(3113, "Partido dos Trabalhadores");
		
		Date hoje = new Date();
		Reuniao r1 = new Reuniao(hoje.getTime(), "Lula livre para que tenhamos Lula 2018.");
		
		controle.adicionar(a1);
		
		controle.adicionar(3113, r1);
		
		try {
			controle.adicionar(3113, r1);
			fail("Deveria ter dado erro! Esta inserindo uma reuniao que ja foi cadastrada.");
		}
		catch(ReuniaoJaExistente e) {
			//Ok, era pra dar erro mesmo.
		}		
		controle.limparBanco();
	}
	
	@Test
	public void testarCadastroReuniaoDataInvalida() throws AssociacaoNaoExistente, ValorInvalido, ReuniaoJaExistente, AssociacaoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(3113, "Partido dos Trabalhadores");
		
		Reuniao r1 = new Reuniao(-1, "O Golpe, Fora Temer.");
		
		controle.adicionar(a1);
		
		try {
			controle.adicionar(3113, r1);
			fail("Deveria ter dado erro! Esta inserindo uma reuniao com data invalida.");
		}
		catch(ValorInvalido e) {
			//Ok, era pra dar erro mesmo.
		}
		
		controle.limparBanco();
	}
	
	@Test
	public void testarCadastroTaxa() throws AssociacaoJaExistente, ValorInvalido, AssociacaoNaoExistente, TaxaNaoExistente, TaxaJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(3113, "Movimento Sem Terra");
		
		Taxa t1 = new Taxa("Lula livre", 2018, 1000.00, 12, false);
		
		controle.adicionar(a1);
		controle.adicionar(3113, t1);
		
		try {
			controle.adicionar(3113, t1);
			fail("Deveria ter dado erro! Esta inserindo uma taxa qua ja foi inserida.");
		}
		catch(TaxaJaExistente e) {
			//Ok, era pra dar erro mesmo.
		}
		
		controle.limparBanco();
	}
	
	@Test
	public void testarCalcularTotalDeTaxas() throws AssociacaoJaExistente, ValorInvalido, AssociacaoNaoExistente, TaxaNaoExistente, TaxaJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(3113, "Partido dos Trabalhadores");
		
		Taxa t1 = new Taxa("Lula livre", 2018, 1000.00, 12, false);
		Taxa t2 = new Taxa("Pao com mortadela", 2018, 200, 2, true);
		
		controle.adicionar(a1);
		controle.adicionar(3113, t1);		
		controle.adicionar(3113, t2);
		double totalDeTaxas = controle.calcularTotalDeTaxas(3113, 2018);
		assertEquals(1200, totalDeTaxas, 0.001);
		
		controle.limparBanco();
	}	

	@Test
	public void testarCalculoDeFrequencia() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2018, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		Reuniao reuniao1 = new Reuniao(data1, "Aumento de taxas");
		controle.adicionar(1306, reuniao1);
		controle.registrarFrequencia(1, 1306, data1);

		Reuniao reuniao2 = new Reuniao(data1 + 1000000, "Confirmacao de aumento");
		controle.adicionar(1306, reuniao2);

		double freq = controle.calcularFrequencia(1, 1306, data1, data1 + 1000000);
		assertEquals(0.5, freq, 0.01);
		controle.limparBanco();
	}

	@Test
	public void testarPagamento() throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente,
			AssociacaoJaExistente, ValorInvalido, AssociadoJaExistente, TaxaJaExistente, AssociadoJaRemido {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2018, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		// Remissao em 01/03/2018
		gc.set(2018, 02, 01);
		long remissao = gc.getTimeInMillis();
		Associado associado2 = new AssociadoRemido(2, "Raimundo", "3232-3232", data1, nasc, remissao);
		controle.adicionar(1306, associado2);

		Taxa taxa1 = new Taxa("Manutencao", 2018, 600, 12, true);
		controle.adicionar(1306, taxa1);

		Taxa taxa2 = new Taxa("Reforma", 2018, 1200, 12, false);
		controle.adicionar(1306, taxa2);

		Date hoje = new Date();

		try {
			controle.registrarPagamento(1306, "Manutencao", 2018, 1, hoje.getTime(), 49);
			fail("Nao deveria deixar pagar esse valor, pois 49 esta abaixo do valor da parcela, que deve ser o valor mÃŒnimo a ser pago");
		} catch (ValorInvalido e) {
			// Ok. Barrou pagamento abaixo da parcela!
		}
		controle.registrarPagamento(1306, "Manutencao", 2018, 1, hoje.getTime(), 60);
		controle.registrarPagamento(1306, "Reforma", 2018, 1, hoje.getTime(), 100);

		double valor = controle.somarPagamentoDeAssociado(1306, 1, "Manutencao", 2018, hoje.getTime(),
				hoje.getTime());
		assertEquals(60, valor, 0.01);
		double valor2 = controle.somarPagamentoDeAssociado(1306, 1, "Reforma", 2018, hoje.getTime(), hoje.getTime());
		assertEquals(100, valor2, 0.01);

		try {
			controle.registrarPagamento(1306, "Manutencao", 2018, 2, hoje.getTime(), 60);
			fail("Nao deveria deixar pagar esse valor, pois este associado Ãˆ remido");
		} catch (AssociadoJaRemido e) {
			// Ok. Barrou pagamento de taxa adminsitrativa!
		}

		controle.registrarPagamento(1306, "Reforma", 2018, 2, hoje.getTime(), 100);
		valor = controle.somarPagamentoDeAssociado(1306, 2, "Manutencao", 2018, hoje.getTime(), hoje.getTime());
		assertEquals(0, valor, 0.01);
		valor2 = controle.somarPagamentoDeAssociado(1306, 2, "Reforma", 2018, hoje.getTime(), hoje.getTime());
		assertEquals(100, valor2, 0.01);
		controle.registrarPagamento(1306, "Reforma", 2018, 2, hoje.getTime(), 1080);
		valor2 = controle.somarPagamentoDeAssociado(1306, 2, "Reforma", 2018, hoje.getTime(), hoje.getTime());
		assertEquals(1180, valor2, 0.01);
		controle.registrarPagamento(1306, "Reforma", 2018, 2, hoje.getTime(), 20);
		valor2 = controle.somarPagamentoDeAssociado(1306, 2, "Reforma", 2018, hoje.getTime(), hoje.getTime());
		assertEquals(1200, valor2, 0.01);
		controle.limparBanco();
	}	
}
