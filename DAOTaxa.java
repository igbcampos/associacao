package botAssociacao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOTaxa {

	public void inserir(int associacao, Taxa taxa) {
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "insert into taxa (nome, vigencia, valor, parcelas, administrativa, associacao) "
					+ "values ('" + taxa.getNome() + "', " + taxa.getVigencia() + ", " + taxa.getValorAno() 
					+ ", " + taxa.getParcelas() + ", " + taxa.getAdministrativa() + ", " + associacao + ")";
			System.out.println(comando);
			statement.executeUpdate(comando);
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Taxa buscar(int numeroAssociacao, String nome, int vigencia) throws TaxaNaoExistente {
		Taxa taxa = null;
		
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "select * from taxa where nome = '" + nome + "' and vigencia = " 
			+ vigencia + " and associacao = " + numeroAssociacao;
			System.out.println(comando);
			ResultSet rs = statement.executeQuery(comando);
			
			if(rs.next()) {
				double valor = rs.getDouble("valor");
				int parcelas = rs.getInt("parcelas");
				boolean administrativa = rs.getBoolean("administrativa");
				taxa = new Taxa(nome, vigencia, valor, parcelas, administrativa);
			}
			
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(taxa == null)
			throw new TaxaNaoExistente();
		
		return taxa;
	}
	
	public double calcularTotalDeTaxas(int numeroAssociacao, int vigencia) {
		double totalDeTaxas = 0;

		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "select valor from taxa where vigencia = " 
			+ vigencia + " and associacao = " + numeroAssociacao;
			System.out.println(comando);
			ResultSet rs = statement.executeQuery(comando);
			
			while(rs.next()) {
				double valorAno = rs.getInt("valor");
				totalDeTaxas += valorAno;
			}
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return totalDeTaxas;
	}
	
	//usado no bot para ter acesso a todas as taxas de uma associacao
	public ArrayList<Taxa> taxasExistentes(int associacao, int vigencia) {
		ArrayList<Taxa> taxas = new ArrayList<Taxa>();
		
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "select * from taxa where associacao = " + associacao + " and vigencia = " + vigencia;
			ResultSet rs = statement.executeQuery(comando);
			
			while(rs.next()) {
				String nome = rs.getString("nome");
				double valor = rs.getDouble("valor");
				int parcelas = rs.getInt("parcelas");
				boolean administrativa = rs.getBoolean("administrativa");
				
				Taxa taxa = new Taxa(nome, vigencia, valor, parcelas, administrativa);
				taxas.add(taxa);
			}
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return taxas;
	}
	
	public void limpar() {
		try {
			Connection conexao = Conexao.getConexao();
			Statement statement = conexao.createStatement();
			
			String comando = "delete from taxa";
			statement.executeUpdate(comando);
			
			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
