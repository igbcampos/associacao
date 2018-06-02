package associacaoBD;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class MeuTesteBancoDeDados {

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
			fail("Deveria ter dado erro! Está inserindo uma associacao com nome nulo.");
		}
		catch(ValorInvalido e) {
			//Ok, era pra dar erro mesmo.
		}
		
		controle.limparBanco();
	}
	
	@Test
	public void testarCadastroAssociacaoNomeNulo() throws AssociacaoJaExistente, ValorInvalido {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1313, "Movimento Sem Terra");
		Associacao a2 = new Associacao(1331, "Central Unica dos Trabalhadores");
		Associacao a3 = new Associacao(3113, null);
		
		controle.adicionar(a1);
		controle.adicionar(a2);
		
		try {
			controle.adicionar(a3);
			fail("Deveria ter dado erro! Esta inserindo uma associacao com nome null.");
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
	public void testarCadastroAssociadoNomeNulo() throws AssociacaoJaExistente, ValorInvalido, AssociacaoNaoExistente, AssociadoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(3113, "Movimento Sem Terra");
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nascimento = gc.getTimeInMillis();
		Date hoje = new Date();
		Associado as1 = new Associado(1234, null, "3232-3232", hoje.getTime(), nascimento);
		
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
	
}
