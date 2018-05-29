package associacaoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
