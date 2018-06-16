package botAssociacao;

import java.sql.*;

public class DAOAssociacao {

	public void inserir(Associacao associacao) {
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "insert into associacao (numero, nome) values (" + associacao.getNumero() + ", '" + associacao.getNome() + "')";
			System.out.println(comando);
			statement.executeUpdate(comando);
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Associacao buscar(int numero) throws AssociacaoNaoExistente {
		Associacao associacao = null;
		
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "select * from associacao where numero = " + numero;
			System.out.println(comando);
			ResultSet rs = statement.executeQuery(comando);
			
			if(rs.next()) {
				String nome = rs.getString("nome");
				associacao = new Associacao(numero, nome);
			}
			
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(associacao == null)
			throw new AssociacaoNaoExistente();
		
		return associacao;
	}
	
	public void limpar() {
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "delete from associacao";
			statement.executeUpdate(comando);
			
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
