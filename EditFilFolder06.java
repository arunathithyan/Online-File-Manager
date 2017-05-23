package homework6;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class EditFilFolder06
 */
@WebServlet("/EditFileFolder06")
public class EditFilFolder06 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditFilFolder06() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		if(request.getSession().getAttribute("user1")==null)
		{
		response.sendRedirect("FileFolderLogin06");
		return;
		}
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/homework6/EditFileFolder06.jsp");  
		rd.forward(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("filename");
		String id=request.getParameter("id");
		String shared=request.getParameter("shared");
		Connection c=null;
		try
        {
		 	
		 
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu41";
            String username = "cs3220stu41";
            String password = "TaCMPGbl";

            	c = DriverManager.getConnection( url, username, password );
              	String sql1="update filesfolder SET name=? where id=?";
            	PreparedStatement preparedStatement = c.prepareStatement(sql1);	
				preparedStatement.setString(1,name);
				preparedStatement.setInt(2,Integer.parseInt(id));
				preparedStatement.executeUpdate();
				int idd=Integer.parseInt(id);
				
				if(shared==null || shared.trim().equals(""))
				{	
				String sql2="select parent_id from filesfolder where id= ? ";
				PreparedStatement preparedStatement2 = c.prepareStatement(sql2);
				preparedStatement2.setInt(1,idd);
				ResultSet r=preparedStatement2.executeQuery();
				String value=null;
				while(r.next())
				{
				value=r.getString("parent_id");
				break;
				}
				if(value!=null)
					id=String.valueOf(value);
				else
					id="";
				}
				else
					id=shared;
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

	     response.sendRedirect("FileFolderDisplay06?id="+id);
	}
}
	
	


