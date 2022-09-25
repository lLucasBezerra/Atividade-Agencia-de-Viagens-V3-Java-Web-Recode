package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import factory.ConnectionFactory;
import model.Cliente;

public class ClienteDAO {
 //private Connection connection;
 private static Connection connection = ConnectionFactory.createConnection();
 /*
 public ClienteDAO() {
	 this.connection = new ConnectionFactory().getConnection();
 }
 */
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
 
 public void removeById(int id) {
	 //DELETE
	 String sql = "DELETE FROM cliente WHERE codCli=?";
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 stmt.setInt(1, id);
		 
		 stmt.executeUpdate();
		 stmt.close();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
 }
 //CRIADO PARA REMOVER A RELAÇÃO NA TABELA ESCOLHA, POIS COM ELA NÃO DARIA PARA REMOVER
 public void removerRelação(int id) {
	 //DELETE
	 String sql = "DELETE FROM escolher WHERE fk_codCli=?";
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 stmt.setInt(1, id);
		 
		 stmt.execute();
		 stmt.close();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
 }
 
 public void update(Cliente cliente, int id) {
	 //UPDATE
	 String sql = "UPDATE cliente SET cpf= ?, origem= ?, dataVolta= ?, dataIda= ? WHERE codCli= ?";
	 try {
	 PreparedStatement stmt = connection.prepareStatement(sql);
	 stmt.setString(1, cliente.getCpf());
	 stmt.setString(2, cliente.getOrigem());
	 stmt.setString(3, cliente.getDataVolta());
	 stmt.setString(4, cliente.getDataIda());
	 stmt.setInt(5, id);
	 
	 stmt.execute();
	 stmt.close();
	 }catch (SQLException e) {
		 e.printStackTrace();
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
 
 public void fazerRelacao(Cliente cliente) {
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
 
}




