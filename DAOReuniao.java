package associacaoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
