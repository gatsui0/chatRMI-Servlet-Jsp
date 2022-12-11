package remote.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.getRequestDispatcher("/register.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        PrintWriter printer = res.getWriter();
        printer.println("ola");
         try {
             Db db = new Db();
             db.connectionDataBase();

        //VERIFICAÇÃO DE CARACTERES
         String name = req.getParameter("name");
             String login = req.getParameter("login");
             String password = req.getParameter("password");
    
             if(name == null) res.sendError(406, "Nome nao pode ser nulo!");
           if(login == null) res.sendError(406, "login nao pode ser nulo!");
             if(password == null) res.sendError(406, "password nao pode ser nulo!");
    
             if(name.length() < 3) res.sendError(406, "senha fraca");
            if(login.length() < 3) res.sendError(406, "senha fraca");
             if(password.length() < 3) res.sendError(406, "senha fraca");

             //VERIFICANDO SE USER EXISTE NO BANCO DE DADOS
             boolean user = db.existUser(login);
             if(user) res.sendError(401, "Esse login já existe!");
             if(!user) db.createUser(name, login, password);
      
            res.setStatus(200);
            req.setAttribute("message", "fhijfgh");

         } catch (Exception e) {
             e.printStackTrace();
             res.sendError(502, "senha fraca");
         }
  }

}
