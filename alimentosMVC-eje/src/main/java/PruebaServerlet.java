

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alimentosMVC.AccesoDatos;
import alimentosMVC.Alimento;

/**
 * Servlet implementation class PruebaServerlet
 */
@WebServlet({ "/listar", "/consultar" })
public class PruebaServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PruebaServerlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AccesoDatos mimodelo = AccesoDatos.initModelo();

		// En función de la URL de la petición obtengo los datos del modelo y se los
		// envio a la vista JSP
		if (request.getServletPath().equals("/listar")) {

			// Obtengo la lista de producto
			ArrayList<Alimento> resultado = mimodelo.ObtenerAlimentos();
			// Añado el atributo a la petición
			request.setAttribute("listaAlimentos", resultado);
			// Invoco a la vista que muestra las lista de producto
			// El directorio /WEB-INF no es accesible directamente desde el navegador
			request.getRequestDispatcher("/WEB-INF/listar.jsp").forward(request, response);
		}

		else if (request.getServletPath().equals("/consultar")) {
			// Simplicación no hay comprobación de errores
			String idstring = request.getParameter("id");
			int idint = Integer.parseInt(idstring);
			// Obtengo el producto a partir de su id
			Alimento resultado = mimodelo.getAlimento(idint);
			// Añado el atributo a la petición.
			request.setAttribute("alimentoItem", resultado);
			request.getRequestDispatcher("consultarAlimento.jsp").forward(request, response);
		}

		else {
			response.sendRedirect("index.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
