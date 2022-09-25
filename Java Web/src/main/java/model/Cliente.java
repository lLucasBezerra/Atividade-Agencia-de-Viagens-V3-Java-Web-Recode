package model;


public class Cliente {
	private int id;
	private String cpf;
	private String email; //ainda pretendo usar o email, mas por enquanto usarei o cpf
	private String origem;
	
	private Destino destino; //fk
	
	private String dataVolta;
	private String dataIda;
	
	//GET E SET
	//ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	//EMAIL
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//ORIGEM
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	//DESTINO
	public Destino getDestino() {
		return destino;
	}
	public void setDestino(Destino destino) {
		this.destino = destino;
	}
	//DATA DE VOLTA
	public String getDataVolta() {
		return dataVolta;
	}
	public void setDataVolta(String string) {
		this.dataVolta = string;
	}
	//DATA DE IDA
	public String getDataIda() {
		return dataIda;
	}
	public void setDataIda(String string) {
		this.dataIda = string;
	}
	//CPF
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
