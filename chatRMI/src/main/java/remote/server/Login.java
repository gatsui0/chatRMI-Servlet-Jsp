package remote.server;

import java.io.IOException;
//import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import remote.client.Client;
import remote.server.rmi.Server;

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


    public void init() {
        try {
//            LocateRegistry.createRegistry(4444);
//            Naming.rebind("rmi://localhost:4444/remote",new Server());
//            System.out.println("Server Started ...");
            Registry registry = LocateRegistry.createRegistry(4444);
            registry.rebind("remote", new Server());
            System.out.println("Server Started ...");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub


         try {
             Db db = new Db();
             db.connectionDataBase();

        //VERIFICAÇÃO DE CARACTERES

             String login = req.getParameter("login");
             String password = req.getParameter("password");
    

           if(login == null) res.sendError(406, "login nao pode ser nulo!");
            if(password == null) res.sendError(406, "password nao pode ser nulo!");
    

            if(login.length() < 3) res.sendError(406, "senha fraca");
            if(password.length() < 3) res.sendError(406, "senha fraca");

             //VERIFICANDO SE USER EXISTE NO BANCO DE DADOS
             boolean existlogin = db.login(login, password);
             if(existlogin) {
                 String nameDB = db.getName(login);
                 Client client = new Client(nameDB);
            	// Armazena o objeto em uma sessão
            	 HttpSession session = req.getSession();
            	 session.setAttribute("objeto", client);
            	 

                 res.sendRedirect("/chatRMI/chat");
             }
             if(!existlogin){

            	 res.sendError(502, "login inexistente");
             }
      
         } catch (Exception e) {
             e.printStackTrace();
             
         }
  }

		

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    // Get the redirect location URL from the request
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

}
