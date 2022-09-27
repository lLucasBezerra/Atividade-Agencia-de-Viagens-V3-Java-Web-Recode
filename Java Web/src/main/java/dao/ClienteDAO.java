package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import model.Cliente;
import model.Destino;
import model.Promocoes;
import model.Voo;

public class ClienteDAO {
 private static Connection connection = ConnectionFactory.createConnection();

 
 
 public static void save(Cliente cliente) {
	 //CREATE
	 String sql = "INSERT INTO cliente VALUES(null, ?, ?, ?, ?)";
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 stmt.setString(1, cliente.getCpf());
		 stmt.setString(2, cliente.getOrigem());
		 stmt.setString(3, cliente.getDataVolta());
		 stmt.setString(4, cliente.getDataIda());
		 
		 stmt.executeUpdate();
		 stmt.close();
		 System.out.println("cliente salvo com sucesso");
	 }catch (SQLException e) {
		 System.out.println("não foi possivel salver os dados "+ e.getMessage());
	 }
 }
 //IMPORTANTE
 public static void removeById(int id) {
	 //DELETE
	 String sql = "DELETE FROM cliente WHERE codCli=?";
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 stmt.setInt(1, id);
		 
		 stmt.executeUpdate();
		 stmt.close();
		 System.out.println("cliente deletado");
	 }catch (SQLException e) {
		 System.out.println("não foi possivel deletar " + e.getMessage());
	 }
 }
 //CRIADO PARA REMOVER A RELAÇÃO NA TABELA ESCOLHA, POIS COM ELA NÃO DARIA PARA REMOVER
 //IMPORTANTE
 public static void removerRelação(int id) {
	 //DELETE
	 String sql = "DELETE FROM escolher WHERE fk_codCli=?";
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 stmt.setInt(1, id);
		 
		 stmt.executeUpdate();
		 stmt.close();
		 System.out.println("deletadando relação");
	 }catch (SQLException e) {
		 System.out.println("não foi possivel deletar relação "+e.getMessage());
	 }
 }
 //IMPORTANTE
 public static void update(Cliente cliente) {
	 //UPDATE
	 String sql = "UPDATE cliente SET cpf= ?, origem= ?, dataVolta= ?, dataIda= ? WHERE codCli= ?";
	 try {
	 PreparedStatement stmt = connection.prepareStatement(sql);
	 stmt.setString(1, cliente.getCpf());
	 stmt.setString(2, cliente.getOrigem());
	 stmt.setString(3, cliente.getDataVolta());
	 stmt.setString(4, cliente.getDataIda());
	 stmt.setInt(5, cliente.getId());
	 
	 stmt.executeUpdate();
	 stmt.close();
	 System.out.println("Cliente atualizado");
	 }catch (SQLException e) {
		 System.out.println("não foi possivel atualizar cliente "+ e.getMessage());
	 }
	 
 }
 
 public ResultSet getClientes() throws SQLException{
	 //READ (TUDO)
	 String sql = "SELECT * FROM cliente";
	 Statement stmt = null;
	 try {
		 stmt = connection.createStatement();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
	 return stmt.executeQuery(sql);
 }
 
 public ResultSet getIdCliente(int id) throws SQLException{
	 //READ (UNICO)
	 String sql = "SELECT * FROM cliente WHERE codCli="+id;
	 ResultSet resultado = null;
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 resultado = stmt.executeQuery(sql);
		 
		 if(resultado.next()) {
			 System.out.println("ID -- >"+resultado.getInt(1));
			 System.out.println("CPF -- >"+resultado.getString(2));
			 System.out.println("LOCAL DE ORIGEM -- >"+resultado.getString(3));
			 System.out.println("DATA DE VOLTA -- >"+resultado.getDate(4)); 
			 System.out.println("DATA DE IDA -- >"+resultado.getDate(5)); 
		 }
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
	 return resultado;
 }
 //IMPORTANTE
  public static Cliente findCli(int id) {
	 String sql = "SELEC * FROM cliente WHERE codCli=?";
	 
	 try {
		 Statement stmt = connection.createStatement();
		 ResultSet rs = stmt.executeQuery(sql);
		 Cliente cli = new Cliente();
		 while(rs.next()) {
			 cli.setId(rs.getInt("codCli"));
			 cli.setCpf(rs.getString("cpf"));
			 cli.setOrigem(rs.getString("origem"));
			 cli.setDataIda(rs.getString("dataVolta"));
			 cli.setDataVolta(rs.getString("dataIda"));
		 }
		 System.out.println("cliente encontrado");
		 return cli;
	 }catch(SQLException e){
		 System.out.println("não foi possível encontrar cliente. " + e.getMessage());
		 return null;
	 }
 }
 //IMPORTANTE
 public static void pegarID(Cliente cliente) {
	 //PEGANDO O ID PARA PODER FAZER O METODO fazerRelacao()
	 String sql = "SELECT codCli FROM cliente WHERE cpf="+cliente.getCpf();
	 ResultSet resultado = null;
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 resultado = stmt.executeQuery(sql);
		 
		 if(resultado.next()) {
			 cliente.setId(resultado.getInt(1));
		 }
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
 }
 //IMPORTANTE
 public static void fazerRelacao(Cliente cliente) {
	 //PARA FAZER RELAÇÃO MUITOS PARA MUITOS
	 String sql = "INSERT INTO escolher VALUES(?,?,?)";
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 stmt.setInt(1, cliente.getId());
		 stmt.setInt(2, cliente.getDestino().getId());
		 stmt.setInt(3, cliente.getDestino().getVoo().getId());
		 
		 stmt.execute();
		 stmt.close();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
	 
 }
 
//IMPORTANTE
public static Cliente mostrarTudo(Cliente cliente){
	 String sql = "SELECT * FROM escolher\r\n"
	 		+ "INNER JOIN cliente ON cliente.codCli = escolher.fk_codCli\r\n"
	 		+ "INNER JOIN destinos ON destinos.codDest = escolher.fk_codDest\r\n"
	 		+ "INNER JOIN voo ON voo.codVoo = escolher.fk_codVoo WHERE fk_codCli ="+ cliente.getId();
	 try {
		 Statement stmt = connection.createStatement();
		 ResultSet rs = stmt.executeQuery(sql);
		 Cliente cli = new Cliente();
		 while(rs.next()) {
			 //desnecessarios
			 int fkcli = rs.getInt("fk_codCli");
			 int fkdest = rs.getInt("fk_codDest");
			 int fkvoo = rs.getInt("fk_codVoo");
			 
			 cli.setId(rs.getInt("codCli"));
			 cli.setCpf(rs.getString("cpf"));
			 cli.setOrigem(rs.getString("origem"));
			 cli.setDataVolta(rs.getString("dataVolta"));
			 cli.setDataIda(rs.getString("dataIda"));
			 
			 Destino dest = new Destino();
			 cli.setDestino(dest);
			 cli.getDestino().setId(rs.getInt("codDest"));
			 cli.getDestino().setPais(rs.getString("paises"));
			 cli.getDestino().setCidade(rs.getString("cidade"));
			 cli.getDestino().setObraR(rs.getString("obraR"));
			 
			 Promocoes promo = new Promocoes();
			 cli.getDestino().setPromo(promo);
			 cli.getDestino().getPromo().setId(rs.getInt("fk_codPromo"));
			
			 Voo voo = new Voo();
			 cli.getDestino().setVoo(voo);
			 cli.getDestino().getVoo().setId(rs.getInt("codVoo"));
			 cli.getDestino().getVoo().setCompanhia(rs.getString("companhiaV"));
			 cli.getDestino().getVoo().setPrecoVoo(rs.getDouble("preco"));
			 
			 
		 }
		 System.out.println("informações sobre o cliente pegue");
		 return cli;
	 }catch(SQLException e) {
		 System.out.println("não foi possível encontrar cliente" + e.getMessage());
		 return null;
	 }
}
//IMPORTANTE
public static void atualizarRelação(Cliente cliente) {
	 //DELETE
	 String sql = "UPDATE escolher SET fk_codDest=?, fk_codVoo=? WHERE fk_codCli=?";
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 stmt.setInt(1, cliente.getDestino().getId());
		 stmt.setInt(2, cliente.getDestino().getVoo().getId());
		 stmt.setInt(3, cliente.getId());
		 
		 stmt.executeUpdate();
		 stmt.close();
		 System.out.println("relação atualizada");
	 }catch (SQLException e) {
		 System.out.println("não foi possível atualizar "+ e.getMessage());
	 }
}
 
}




