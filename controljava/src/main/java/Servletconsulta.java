
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.AccesoDatos;
import modelo.Movimiento;

/**
 * Servlet implementation class Servletconsulta
 */
@WebServlet({ "/Servletconsulta", "/procesarconsulta" })
public class Servletconsulta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servletconsulta() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cod_cliente = request.getParameter("cod_cliente");
		String importeMin = request.getParameter("impMin");
		String importeMax = request.getParameter("impMax");

		int importeMaximo = -1;  // Valores incorreptos
		int importeMinimo =  1;
		boolean error = false;

		String msg;

		/*
		 * 
		 * COMPLELETAR
		 */

		try {
			importeMinimo = Integer.parseInt(importeMin);
			importeMaximo = Integer.parseInt(importeMax);
		} catch (NumberFormatException ex) {
			error = true;
		}

		if (error || importeMaximo < importeMinimo) {
			msg = "Los importes introducidos no son correctos ";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/infoerror.jsp").forward(request, response);
			return;
		}

		AccesoDatos mimodelo = AccesoDatos.initModelo();

		if (!mimodelo.hayMovimientos(cod_cliente)) {
			msg = "El código de cliente " + cod_cliente + " no se encuentra en la base de datos ";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/infoerror.jsp").forward(request, response);
			return;
		} else {
			ArrayList<Movimiento> lista = mimodelo.obtenerListaMovimientos(cod_cliente, importeMaximo, importeMinimo);

			if (lista.size() == 0) {
				msg = "No hay movimientos del cliente con código: " + cod_cliente;
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/WEB-INF/infoerror.jsp").forward(request, response);
			} else {

				request.setAttribute("listaMovimiento", lista);
				request.getRequestDispatcher("/WEB-INF/informovimiento.jsp").forward(request, response);
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
