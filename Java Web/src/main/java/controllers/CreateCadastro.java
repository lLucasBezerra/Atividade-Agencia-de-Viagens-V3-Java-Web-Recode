package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClienteDAO;
import dao.DestinoDAO;
import dao.VooDAO;
import model.Cliente;
import model.Destino;
import model.Voo;

/**
 * Servlet implementation class CreateCadastro
 */
@WebServlet(urlPatterns ={"/Cadastrando", "/cadastrandoII"})
public class CreateCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//para tentar usar o id dele
	List<Cliente> clientes = new ArrayList<Cliente>();
	Cliente cli = new Cliente();
	Destino dest = new Destino();
	Voo voo = new Voo();
	
	
//TIVE QUE CRIAR TUDO EM APENAS UMA SERVLET POIS NÃO TENHO CERTEZA COMO PEGARIA OS DADOS DO OBJETO CLI PARA OUTRA SERVLET
	
	
    public CreateCadastro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//PARA USAR O METODO READ DAS TABELAS DESTINO E VOO
		String action= request.getServletPath();
		
		if(action.equals("/Cadastrando")) {
		List<Destino> destinos = DestinoDAO.findDest(); 
	    request.setAttribute("destinos", destinos);
	    List<Voo> voos = VooDAO.findVoo();
	    request.setAttribute("voos", voos);
		
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher("cadastroRapido.jsp");
		requestDispatcher.forward(request, response);
		
		//PARA PEGAR OQ FALTAVA PARA CONFIRMAR O CADASTRO E LEVAR PARA A PROXIMA PAGINA DE UPDATE OU DELETE
		}else if(action.equals("/cadastrandoII")) {
			//PEGANDO O ID DO DESTINO SELECIONADOO
			String d[] = request.getParameterValues("idDest");
			for(int i=0; i < d.length; i++) {
				if(d[i] != null) {
					String idDest = d[i];
					dest.setId(Integer.parseInt(idDest));
					cli.setDestino(dest);
					System.out.println("destino "+cli.getDestino().getId());
					
				}
			}
			//PEGANDO O ID DO VOO SELECIONADOO 
			String v[] = request.getParameterValues("idVoo");
			for (int j=0; j < v.length; j++) {
				if(v[j] != null) {
				String idVoo = v[j];
				voo.setId(Integer.parseInt(idVoo));
				cli.getDestino().setVoo(voo);
				System.out.println("Voo "+ cli.getDestino().getVoo().getId());
				}
			}

			//FAZENDO RELAÇÃO E PEGANDO T O D O S  O S  D A D O S DO CLIENTE
			ClienteDAO.fazerRelacao(cli);
			Cliente cliente = ClienteDAO.mostrarTudo(cli);
			request.setAttribute("cliente", cliente);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("confirmarCadastro.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//CRIANDO CLIENTE
		cli.setCpf(request.getParameter("cpf"));
		cli.setOrigem(request.getParameter("origem"));
		cli.setDataIda(request.getParameter("dataIda"));
		cli.setDataVolta(request.getParameter("dataVolta"));
		
		ClienteDAO.save(cli);
		ClienteDAO.pegarID(cli);
		System.out.println(cli.getId());
		
		clientes.add(cli);
		
		doGet(request, response);
	}

}
