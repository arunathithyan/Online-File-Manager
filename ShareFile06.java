package homework6;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ShareFile06")
public class ShareFile06 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShareFile06() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid=Integer.parseInt(request.getParameter("userid"));
		int fileid=Integer.parseInt(request.getParameter("fileid"));
		
		Connection c=null;
		
		
		try
        {
		 	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu41";
            String username = "cs3220stu41";
            String password = "TaCMPGbl";

            c = DriverManager.getConnection( url, username, password );
            
             PreparedStatement preparedStatement = c.prepareStatement("INSERT INTO Shared"
             		+ "	(file_id, owner_id) VALUES (?,?);");	
             preparedStatement.setInt(1, fileid);
             preparedStatement.setInt(2, userid);
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
	}

}
