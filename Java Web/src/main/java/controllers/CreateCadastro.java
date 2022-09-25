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
import model.Cliente;
import model.Destino;

/**
 * Servlet implementation class CreateCadastro
 */
@WebServlet("/Cadastrando")
public class CreateCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//para tentar usar o id dele
	List<Cliente> clientes = new ArrayList<Cliente>();
	Cliente cli = new Cliente();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCadastro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		List<Destino> destinos = DestinoDAO.findDest(); 
	    request.setAttribute("destinos", destinos);
	    
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher("cadastroRapido.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
