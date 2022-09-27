package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClienteDAO;
import model.Cliente;
import model.Destino;
import model.Voo;

/**
 * Servlet implementation class UpdateAndDelete
 */
@WebServlet(urlPatterns ={ "/confirmar", "/excluir", "/editar" })
public class UpdateAndDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Cliente cli = new Cliente();
	Destino dest = new Destino();
	Voo voo = new Voo();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAndDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		cli.setId(Integer.parseInt(request.getParameter("idCliente")));
		cli.setCpf(request.getParameter("cpf"));
		cli.setOrigem(request.getParameter("origem"));
		cli.setDataVolta(request.getParameter("dataVolta"));
		cli.setDataIda(request.getParameter("dataIda"));
		
		cli.setDestino(dest);
		cli.getDestino().setId(Integer.parseInt(request.getParameter("idDestino")));
		
		cli.getDestino().setVoo(voo);
		cli.getDestino().getVoo().setId(Integer.parseInt(request.getParameter("idVoo")));
		
		
		
		
		String action= request.getServletPath();
		System.out.println(action);
		if (action.equals("/confirmar")) {
			ClienteDAO.update(cli);
		}else if(action.equals("/editar")) {
			ClienteDAO.update(cli);
			ClienteDAO.atualizarRelação(cli);
		}else if(action.equals("/excluir")) {
			ClienteDAO.removeById(cli.getId());
			ClienteDAO.removerRelação(cli.getId());
		}
		
		response.sendRedirect("index.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
