package botAssociacao;

import java.sql.*;

public class DAOAssociado {
	
	public void inserir(int associacao, Associado associado) {
		long dataDeRemissao = -1;
		
		if(associado instanceof AssociadoRemido) {
			dataDeRemissao = ((AssociadoRemido) associado).getDataRemissao();
		}
		
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "insert into associado (numero, nome, telefone, nascimento, data, remissao, associacao) values "
				+ "(" + associado.getNumero() + ", '" + associado.getNome() + "', '" 
				+ associado.getTelefone() + "', " + associado.getNascimento() + ", " 
				+ associado.getDataAssociacao() + ", " + dataDeRemissao + ", " + associacao + ")";
			
			System.out.println(comando);
			statement.executeUpdate(comando);
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setTelegramId(long telegramId, int associado, int associacao) {
		try {	
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "update associado set telegram = " + telegramId + " where numero = " + associado + " and associacao = " + associacao;
			System.out.println(comando);
			statement.executeUpdate(comando);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Associado buscar(int numeroAssociacao, int numeroAssociado) throws AssociadoNaoExistente {
		Associado associado = null;
		
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "select * from associado where numero = " + numeroAssociado + " and associacao = " + numeroAssociacao;
			ResultSet rs= statement.executeQuery(comando);
			
			if(rs.next()) {
				String nome = rs.getString("nome");
				String telefone = rs.getString("telefone");
				long nascimento = rs.getLong("nascimento");
				long data = rs.getLong("data"); // data de associacao
				long dataDeRemissao = rs.getLong("remissao");
				
				if(dataDeRemissao != -1) {
					associado = new AssociadoRemido(numeroAssociado, nome, telefone, data, nascimento, dataDeRemissao);
				}
				else {					
					associado = new Associado(numeroAssociado, nome, telefone, data, nascimento);
				}
			}
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(associado == null)
			throw new AssociadoNaoExistente();
		
		return associado;
	}
	
	public Associado buscarTelegramId(long telegramId) {
		Associado associado = null;

		try {	
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "select * from associado where telegram = " + telegramId;
			ResultSet rs = statement.executeQuery(comando);
			
			if(rs.next()) {
				int numeroAssociado = rs.getInt("numero");
				String nome = rs.getString("nome");
				String telefone = rs.getString("telefone");
				long nascimento = rs.getLong("nascimento");
				long data = rs.getLong("data"); // data de associacao
				long dataDeRemissao = rs.getLong("remissao");
				
				if(dataDeRemissao != -1) {
					associado = new AssociadoRemido(numeroAssociado, nome, telefone, data, nascimento, dataDeRemissao);
				}
				else {					
					associado = new Associado(numeroAssociado, nome, telefone, data, nascimento);
				}
				associado.setAssociacao(rs.getInt("associacao"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return associado;
	}
	
	public void limpar() {
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "delete from associado";
			statement.executeUpdate(comando);
			
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
