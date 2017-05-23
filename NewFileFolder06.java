package homework6;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class NewFileFolder06
 */
@WebServlet("/NewFileFolder06")
public class NewFileFolder06 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NewFileFolder06() {
        super();
    
    }
    
    public void init(ServletConfig config) throws ServletException
   	{ 	super.init(config);
   	}

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		
		if(request.getSession().getAttribute("user1")==null)
		{
		response.sendRedirect("FileFolderLogin06");
		return;
		}
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/homework6/NewFileFolder06.jsp");  
		rd.forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//List<File> fr=(ArrayList<File>)request.getSession().getAttribute("fr06");
		//List<File> f=new ArrayList<File>();
		String name=request.getParameter("filename");
		//Integer current=null;
		String id=request.getParameter("id");
		Connection c=null;
		Integer id1=0;
		if(!(id==null) && !(id.equals("")) && !(id.equals("null")))
			id1=Integer.parseInt(id);
		
		try
        {
		 	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu41";
            String username = "cs3220stu41";
            String password = "TaCMPGbl";

            c = DriverManager.getConnection( url, username, password );
             ResultSet rs;
             Statement stmt = c.createStatement();
             PreparedStatement preparedStatement = c.prepareStatement("INSERT INTO filesfolder"
             		+ "	(name, type, size, upl_date, parent_id, is_folder, owner_id) VALUES (?,?,?,?,?,?,?);");	
             preparedStatement.setString(1, name);
             preparedStatement.setString(2, null);
             preparedStatement.setInt(3, 0);
             
             java.util.Date today = new java.util.Date();
             java.sql.Timestamp date = new java.sql.Timestamp(today.getTime());
             preparedStatement.setTimestamp(4,date);
            
             if(id1==0)
             preparedStatement.setNull(5, Types.INTEGER);
             else
            	 preparedStatement.setInt(5, id1);	 
             preparedStatement.setBoolean(6,true);
             preparedStatement.setInt(7, (int)request.getSession().getAttribute("userid"));
             preparedStatement.executeUpdate();
        }
		
		catch( SQLException e )
	    {
	        throw new ServletException( e );
	    }
		finally
		{
		    try
		    {
		        if( c != null ) c.close();
		    }
		    catch( SQLException e )
		    {
		        throw new ServletException( e );
		    }
		}
		response.setContentType( "text/plain" );
        response.getWriter().print( request.getParameter("id") ); 
			//response.sendRedirect("FileFolderDisplay06?id="+request.getParameter("id"));  
	         
	}

}
