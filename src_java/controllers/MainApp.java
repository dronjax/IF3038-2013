package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainApp
 */
@WebServlet("/MainApp")
public class MainApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String appName = "MOA";
    public static final String appTagline = "Multiuser Online Agenda";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainApp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String page = ("".equals((String)request.getAttribute("page")))? "index" : (String)request.getAttribute("page");
		
		Class<?>[] param_handler = new Class[2];
		param_handler[0] = HttpServletRequest.class;
		param_handler[1] = HttpServletResponse.class;
		
		try {
			Method method;
			method = this.getClass().getMethod(page, param_handler);				
			method.invoke(this, request, response);			
		} catch (NoSuchMethodException e) {
			// TODO Redirect to error page
			System.out.println("-----------------");
			System.out.println(page);
			System.out.println("-----------------");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher("pages/index.jsp").forward(request, response);
	}
	
	public void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter pw = response.getWriter();
		pw.println(request.getLocalAddr()+"<br>"+request.getLocalName());
		pw.println(request.getRequestURL());
		pw.close();
	}
}