package model;

public class Destino {
	//ATRIBUTOS
	private int id;
	private String pais;
	private String cidade;
	private String obraR;
	private Voo voo; //fk
	private Promocoes promo;
	
	
	//GET AND SET
	//ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//PAIS
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	//CIDADE
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	//OBRA(serie/filme) RELACIONADA
	public String getObraR() {
		return obraR;
	}
	public void setObraR(String obraR) {
		this.obraR= obraR;
	}
	
	//VOO
	public Voo getVoo() {
		return voo;
	}
	public void setVoo(Voo voo) {
		this.voo = voo;
	}
	//PROMO
	public Promocoes getPromo() {
		return promo;
	}
	public void setPromo(Promocoes promo) {
		this.promo = promo;
	}
}
