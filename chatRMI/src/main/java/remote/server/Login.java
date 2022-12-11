package remote.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub


         try {
             Db db2 = new Db();
             db2.connectionDataBase();

        //VERIFICAÇÃO DE CARACTERES

             String login = req.getParameter("login");
             String password = req.getParameter("password");
    

           if(login == null) res.sendError(406, "login nao pode ser nulo!");
            if(password == null) res.sendError(406, "password nao pode ser nulo!");
    

            if(login.length() < 3) res.sendError(406, "senha fraca");
            if(password.length() < 3) res.sendError(406, "senha fraca");

             //VERIFICANDO SE USER EXISTE NO BANCO DE DADOS
//             boolean existlogin = db.login(login, password);
//             if(existlogin) res.sendRedirect("chat.html");
//             if(!existlogin){
//            	 req.setAttribute("error", "login inválido!");
//            	 req.getRequestDispatcher("login.html").forward(req,res);
//             }
      
         } catch (Exception e) {
             e.printStackTrace();
             res.sendError(502, "login inexistente");
         }
  }

		

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    // Get the redirect location URL from the request
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

}
