package botAssociacao;

import java.sql.*;
import java.util.ArrayList;

public class DAOFrequencia {

	public void inserir(int associacao, int associado, long reuniao) {
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "insert into frequencia (data, associado, associacao) values "
					+ "(" + reuniao + ", " + associado + ", " + associacao + ")";
			System.out.println(comando);
			statement.executeUpdate(comando);
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void buscar(int numeroAssociacao, int numeroAssociado, long reuniao) throws FrequenciaJaRegistrada {
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "select * from frequencia where associado = " + numeroAssociado 
					+ " and associacao = " + numeroAssociacao + " and data = " + reuniao;
			ResultSet rs = statement.executeQuery(comando);
			
			if(rs.next()) {
				throw new FrequenciaJaRegistrada();
			}
			statement.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int quantidadeDePresencas(int numeroAssociacao, int numeroAssociado, long inicio, long fim) {
		int presencas = 0;
		
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "select * from frequencia where associacao = " + numeroAssociacao
					+ " and associado = " + numeroAssociado;
			ResultSet rs = statement.executeQuery(comando);
			
			while(rs.next()) {
				long data = rs.getLong("data");
				
				if(data >= inicio && data <= fim) {
					presencas++;
				}
			}
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return presencas;
	}
	
	//usado no bot para ter acesso a todas as presencas de um associado
	public ArrayList<Long> frequenciasRegistradas(int associado, int associacao, long inicio, long fim) {
		ArrayList<Long> presencas = new ArrayList<Long>();
			
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "select * from frequencia where associado = " + associado 
					+ " and associacao = " + associacao + " and data >= " + inicio + " and data <= " + fim;
			ResultSet rs = statement.executeQuery(comando);
			
			while(rs.next()) {
				long data = rs.getLong("data");
				presencas.add(data);
			}
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return presencas;
	}
	
	public void limpar() {
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "delete from frequencia";
			statement.executeUpdate(comando);
			
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
