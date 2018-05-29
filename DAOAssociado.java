package associacaoBD;

import java.sql.*;

public class DAOAssociado {
	
	public void inserir(int associacao, Associado associado) {
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "insert into associado (numero, nome, telefone, nascimento, data, associacao) values "
					+ "(" + associado.getNumero() + ", '" + associado.getNome() + "', '" + associado.getTelefone() + "', " 
					+ associado.getNascimento() + ", " + associado.getDataAssociacao() + ", " + associacao + ")";
			System.out.println(comando);
			statement.executeUpdate(comando);
			statement.close();
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
				
				associado = new Associado(numeroAssociado, nome, telefone, nascimento, data);
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
