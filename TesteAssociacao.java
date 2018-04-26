package associacao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class TesteAssociacao {

	@Test
	public void testarCadastroDeAssociacao() throws AssociacaoJaExistente, ValorInvalido {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "");	
		
		//Tentativa de cadastrar com sem nome
		try {
			controle.adicionar(a1);
			fail("Deveria ter dado erro!");
		} catch (ValorInvalido e) {
			// Ok. Era pra dar erro mesmo!
		}

		a1.setNome("Cruzeiro do Sul V");
		controle.adicionar(a1);
		
		try {
			controle.adicionar(a1);
			fail("Deveria ter dado erro!");
		} catch (AssociacaoJaExistente e) {
			// Ok, era pra dar erro mesmo!
		}	
	}

	@Test
	public void testarCadastroDeAssociado() throws AssociacaoJaExistente, ValorInvalido, AssociacaoNaoExistente, AssociadoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");	
		controle.adicionar(a1);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		Date hoje = new Date();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", hoje.getTime(), nasc);
		controle.adicionar(1306, associado1);
		try {
			controle.adicionar(1306, associado1);
			fail("Era pra ter dado erro!");
		} catch (AssociadoJaExistente e) {
          // Ok, era pra ter dado erro!
		}
	}
	
	@Test
	public void testarCadastroDeReuniao() throws AssociacaoNaoExistente, ValorInvalido, ReuniaoJaExistente, AssociacaoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");	
		controle.adicionar(a1);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(2018, 01, 01);
		long dataReuni = gc.getTimeInMillis();

		Reuniao reuniao1 = new Reuniao(dataReuni, "Aumento de taxas");
		controle.adicionar(1306, reuniao1);
		try {
			controle.adicionar(1306, reuniao1);
			fail("Era pra ter dado erro!");
		} catch (ReuniaoJaExistente e) {
          // Ok, era pra ter dado erro!
		}
	}
	
	@Test
	public void testarCadastroDeTaxa() throws AssociacaoNaoExistente, AssociacaoJaExistente, ValorInvalido, TaxaJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");	
		controle.adicionar(a1);
		Taxa taxa1 = new Taxa("Manutenção", 2018, 600, 12, true);
		controle.adicionar(1306, taxa1);
		try {
			controle.adicionar(1306, taxa1);
			fail("Era pra ter dado erro!");
		} catch (TaxaJaExistente e) {
          // Ok, era pra ter dado erro!
		}
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

		Reuniao reuniao2 = new Reuniao(data1+1000000, "Confirmação de aumento");
		controle.adicionar(1306, reuniao2);
		
		double freq = controle.calcularFrequencia(1, 1306, data1, data1+1000000);
		assertEquals(0.5, freq, 0.01);
	}

	@Test
	public void testarPagamento() throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente, AssociacaoJaExistente, ValorInvalido, AssociadoJaExistente, TaxaJaExistente, AssociadoJaRemido {
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
		
		// Remissão em 01/03/2018
		gc.set(2018, 02, 01);
		long remissao = gc.getTimeInMillis();
		Associado associado2 = new AssociadoRemido(2, "Raimundo", "3232-3232", data1, nasc, remissao);
		controle.adicionar(1306, associado2);

		Taxa taxa1 = new Taxa("Manutenção", 2018, 600, 12, true);
		controle.adicionar(1306, taxa1);

		Taxa taxa2 = new Taxa("Reforma", 2018, 1200, 12, false);
		controle.adicionar(1306, taxa2);
		
		Date hoje = new Date();
		
		try {
			controle.registrarPagamento(1306, "Manutenção", 2018, 1, hoje.getTime(), 49);
			fail("Não deveria deixar pagar esse valor");
		} catch (ValorInvalido e) {
			// Ok. Barrou pagamento abaixo da parcela!
		}
		controle.registrarPagamento(1306, "Manutenção", 2018, 1, hoje.getTime(), 60);
		controle.registrarPagamento(1306, "Reforma", 2018, 1, hoje.getTime(), 100);
		
		double valor = controle.somarPagamentoDeAssociado(1306, 1, "Manutenção", 2018, hoje.getTime(), hoje.getTime());
		assertEquals(60, valor, 0.01);
		double valor2 = controle.somarPagamentoDeAssociado(1306, 1, "Reforma", 2018, hoje.getTime(), hoje.getTime());
		assertEquals(100, valor2, 0.01);

		try {
			controle.registrarPagamento(1306, "Manutenção", 2018, 2, hoje.getTime(), 60);
			fail("Não deveria deixar pagar esse valor");
		} catch (AssociadoJaRemido e) {
			// Ok. Barrou pagamento de taxa adminsitrativa!
		}
		
		controle.registrarPagamento(1306, "Reforma", 2018, 2, hoje.getTime(), 100);
		valor = controle.somarPagamentoDeAssociado(1306, 2, "Manutenção", 2018, hoje.getTime(), hoje.getTime());
		assertEquals(0, valor, 0.01);
		valor2 = controle.somarPagamentoDeAssociado(1306, 2, "Reforma", 2018, hoje.getTime(), hoje.getTime());
		assertEquals(100, valor2, 0.01);
		controle.registrarPagamento(1306, "Reforma", 2018, 2, hoje.getTime(), 1080);
		valor2 = controle.somarPagamentoDeAssociado(1306, 2, "Reforma", 2018, hoje.getTime(), hoje.getTime());
		assertEquals(1180, valor2, 0.01);
		controle.registrarPagamento(1306, "Reforma", 2018, 2, hoje.getTime(), 20);
		valor2 = controle.somarPagamentoDeAssociado(1306, 2, "Reforma", 2018, hoje.getTime(), hoje.getTime());
		assertEquals(1200, valor2, 0.01);
   }
}
