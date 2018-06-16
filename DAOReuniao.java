package botAssociacao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOReuniao {

	public void inserir(int associacao, Reuniao reuniao) {
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "insert into reuniao (data, ata, associacao) values (" + reuniao.getData() + ", '" + reuniao.getAta() + "', " + associacao + ")";
			System.out.println(comando);
			statement.executeUpdate(comando);
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Reuniao buscar(int numeroAssociacao, long data) throws ReuniaoNaoExistente {
		Reuniao reuniao = null;
		
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "select * from reuniao where data = " + data + " and associacao = " + numeroAssociacao;
			System.out.println(comando);
			ResultSet rs = statement.executeQuery(comando);
			
			if(rs.next()) {
				String ata = rs.getString("ata");
				reuniao = new Reuniao(data, ata);
			}
			
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(reuniao == null)
			throw new ReuniaoNaoExistente();
		
		return reuniao;
	}
	
	//eu não estou vendo onde estou usando o numeroAssociado, mas não vou tirar, vai que.
	public int quantidadeDeReunioes(int numeroAssociacao, int numeroAssociado, long inicio, long fim) {
		int reunioes = 0;
		
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "select * from reuniao where associacao = " + numeroAssociacao;
			ResultSet rs= statement.executeQuery(comando);
			
			while(rs.next()) {
				long data = rs.getLong("data");
				
				if(data >= inicio && data <= fim) {
					reunioes++;
				}
			}
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return reunioes;
	}
	
	//usado no bot para ter acesso a todas as reunioes de uma associacao
	public ArrayList<Long> reunioesExistentes(int associacao, long inicio, long fim) {
		ArrayList<Long> reunioes = new ArrayList<Long>();
		
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "select * from reuniao where associacao = " + associacao 
					+ " and data >= " + inicio + " and data <= " + fim;
			ResultSet rs = statement.executeQuery(comando);
			
			while(rs.next()) {
				long data = rs.getLong("data");
				reunioes.add(data);
			}
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return reunioes;
	}
	
	public void limpar() {
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "delete from reuniao";
			statement.executeUpdate(comando);
			
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
