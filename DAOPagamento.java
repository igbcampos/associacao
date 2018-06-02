package associacaoBD;

import java.sql.*;

public class DAOPagamento {
	
	public void inserir(int associacao, int associado, long data, double valor, String nome, int vigencia) {
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "insert into pagamento (data, valor, nome, vigencia, associado, associacao) "
					+ "values (" + data + ", " + valor + ", '" + nome + "', " + vigencia + ", " 
					+ associado + ", " + associacao + ")";
			System.out.println(comando);
			statement.executeUpdate(comando);
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public double somarPagamentos(int associacao, int associado, String nome, int vigencia) {
		double total = 0;
		
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "select valor from pagamento where associacao = " + associacao
					+ " and associado = " + associado + " and nome = '" + nome + "' and vigencia = "
					+ vigencia;
			System.out.println(comando);
			ResultSet rs = statement.executeQuery(comando);
			
			while(rs.next()) {
				double valor = rs.getInt("valor");
				total += valor;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return total;
	}
	
	public void registrarPagamento(int associacao, int associado, long data, double valor, String nome, int vigencia) throws ValorInvalido, TaxaNaoExistente, AssociadoNaoExistente, AssociadoJaRemido {
		DAOAssociado daoAssociado = new DAOAssociado();
		Associado assoc = daoAssociado.buscar(associacao, associado);
		DAOTaxa daoTaxa = new DAOTaxa();
		Taxa taxa = daoTaxa.buscar(associacao, nome, vigencia);
		
		if(assoc instanceof AssociadoRemido && taxa.getAdministrativa()) {
			throw new AssociadoJaRemido();
		}
		
		double valorJaPago = somarPagamentos(associacao, associado, nome, vigencia);
				
		if(valorJaPago < taxa.getValorAno()) { //se não estiver quitada
			if(valor >= (taxa.getValorAno() / taxa.getParcelas())) { //verifica se o valor e maior ou igual a uma parcela
				if(valor <= (taxa.getValorAno() - valorJaPago)) {//verifica se o valor e menor ou igual ao que falta pagar
					inserir(associacao, associado, data, valor, nome, vigencia); //adiciona o pagamento no banco de dados
				}
			}
			else if((taxa.getValorAno() - valorJaPago) < (taxa.getValorAno() / taxa.getParcelas())) {//se o valor que falta pagar for menor que o valor de uma parcela
				inserir(associacao, associado, data, valor, nome, vigencia); //adiciona o pagamento no banco de dados
			}
			else {
				throw new ValorInvalido("valor do pagamento da taxa");
			}
		}
		else {
			throw new ValorInvalido("taxa ja quitada");
		}
	}
	
	public double somarPagamentoDeAssociado(int associacao, int associado, String nome, int vigencia, long inicio, long fim) {
		double total = 0;
		
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "select data, valor from pagamento where associacao = " + associacao
					+ " and associado = " + associado + " and nome = '" + nome + "' and vigencia = "
					+ vigencia;
			System.out.println(comando);
			ResultSet rs = statement.executeQuery(comando);
			
			while(rs.next()) {
				long data = rs.getLong("data");
				double valor = rs.getInt("valor");
				
				if(data >= inicio && data <= fim) {
					total += valor;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return total;
	}
	
	public void limpar() {
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "delete from pagamento";
			statement.executeUpdate(comando);
			
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
