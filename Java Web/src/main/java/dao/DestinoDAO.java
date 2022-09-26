package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import model.Destino;
import model.Promocoes;

public class DestinoDAO {
 //private Connection connection;
 private static Connection connection = ConnectionFactory.createConnection();
 /*
 public DestinoDAO() {
	 this.connection = new ConnectionFactory().getConnection();
 }*/
 
 public void save(Destino destino) {
	 //CREATE
	 String sql = "INSERT INTO destinos(paises,cidade,obraR) VALUES(?,?,?)";
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 stmt.setString(1, destino.getPais());
		 stmt.setString(2, destino.getCidade());
		 stmt.setString(3, destino.getObraR());
		 
		 stmt.executeUpdate();
		 stmt.close();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
 }
 public void removeById(int id) {
	 //DELETE
	 String sql = "DELETE FROM destinos WHERE codDest=?";
	 try {
	 PreparedStatement stmt = connection.prepareStatement(sql);
	 stmt.setInt(1, id);
	 
	 stmt.executeUpdate();
	 stmt.close();
	 }catch (SQLException e) {
		 //ISSO TUDO PARA Q NÃO OCORRA O MESMO PROBLEMA COM O CLIENTE
		 System.out.println("[ERROR] ocorreu um erro ao tentar remover a destino: " +e.getMessage());
		 System.out.println("--- TEREMOS QUE REMOVER A ASSOCIAÇÃO A UM(S) DETERMINADO CLIENTE ---");
		 System.out.println("removendo . . .");
		 String delete =  "DELETE FROM escolher WHERE fk_codDest=?";
		 try {
			PreparedStatement stmt2 = connection.prepareStatement(delete);
			stmt2.execute();
			stmt2.close();
			System.out.println("--- RELAÇÃO RETIRADA, TENTE NOVAMENTE ---");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 }
 } 
 
 public void update(Destino destino, int id) {
	 //UPDATE															
	 String sql = "UPDATE destinos SET paises = ?, cidade = ?, obraR = ?  WHERE codDest = ?";
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 stmt.setString(1, destino.getPais());
		 stmt.setString(2, destino.getCidade());
		 stmt.setString(3, destino.getObraR());
		 stmt.setInt(4, id);
		 
		 stmt.execute();
		 stmt.close();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
 }
 
 
 
 public void adcUmaPromo(int idDest, int idPromo) {
	 //UPDATE															
	 String sql = "UPDATE destinos SET fk_codPromo = ? WHERE codDest = ?";
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 stmt.setInt(1, idPromo);
		 stmt.setInt(2, idDest);
		 
		 
		 
		 stmt.execute();
		 stmt.close();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
 }
 
 public ResultSet getDestinos() throws SQLException {
	 //READ (TUDO)
	 String sql = "SELECT * FROM destinos";
	 Statement stmt = null;
	 try {
		 stmt = connection.createStatement();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
	 return stmt.executeQuery(sql);
	 
 }
 //IMPORTANTE
 public static List<Destino> findDest(){
	 String sql = "SELECT * FROM destinos";
	 List<Destino> destinos = new ArrayList<Destino>();
	 try {
		 Statement stmt = connection.createStatement();
		 ResultSet rs = stmt.executeQuery(sql);
		 while(rs.next()) {
			 Destino dest = new Destino();
			 dest.setId(rs.getInt("codDest"));
			 dest.setPais(rs.getString("paises"));
			 dest.setCidade(rs.getString("cidade"));
			 dest.setObraR(rs.getString("obraR"));
			 Promocoes promo = new Promocoes();
			 dest.setPromo(promo);
			 dest.getPromo().setId(rs.getInt("fk_codPromo"));
			 
			 destinos.add(dest);
		 }
		 System.out.println("destino encontrado");
		 return destinos;
	 }catch(SQLException e) {
		 System.out.println("não foi possível encontrar tal destino " + e.getMessage());
		 return null;
	 }
 }
}



