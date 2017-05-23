package homework6;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadFile06
 */
@WebServlet("/DownloadFile06")
public class DownloadFile06 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadFile06() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	List<homework5.File> fr=(ArrayList<homework5.File>)request.getSession().getAttribute("fr06");
			String id=request.getParameter("id");	
			String name=request.getParameter("name");
		    String filename="";
			String path = getServletContext()
	            .getRealPath( "/WEB-INF/files/"+name );
	      Connection c =null;
			try
	        {
			 	
			 
	            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu41";
	            String username = "cs3220stu41";
	            String password = "TaCMPGbl";

	            	c = DriverManager.getConnection( url, username, password );
	            	String sql2="select name from filesfolder where id= ? ";
					PreparedStatement preparedStatement2 = c.prepareStatement(sql2);
					preparedStatement2.setInt(1,Integer.parseInt(name));
					ResultSet r=preparedStatement2.executeQuery();
					r.next();
					filename=r.getString("name");
					
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
			
			
	
	     
	        
	        File file = new File( path );
	      // response.setContentType("image/jpg" );
	        response.setHeader( "Content-Length", "" + file.length() );
	        response.setHeader( "Content-Disposition",
	            "'attachment; filename="+filename+"'" );

	        // Binary files need to read/written in bytes.
	        FileInputStream in = new FileInputStream( file );
	        OutputStream out = response.getOutputStream();
	        byte buffer[] = new byte[2048];
	        int bytesRead;
	        while( (bytesRead = in.read( buffer )) > 0 )
	            out.write( buffer, 0, bytesRead );
	        in.close();
	}
}
