package homework6;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileFolderReg06
 */
@WebServlet("/FileFolderReg06")
public class FileFolderReg06 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FileFolderReg06() {
        super();
    
    }


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getSession().getAttribute("user1")!=null)
		{
		response.sendRedirect("FileFolderDisplay06");
		return;
		}
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/homework6/FileFolderReg06.jsp");  
		rd.forward(request, response); 	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		List<Users> ur=new ArrayList<Users>();
		String user=request.getParameter("user");
		String pass=request.getParameter("pass");
		String error="";
		Connection c=null;
		try
        {
		 	
		 
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu41";
            String username = "cs3220stu41";
            String password = "TaCMPGbl";

            c = DriverManager.getConnection( url, username, password );
             
            //Statement stmt = c.createStatement();
            
            PreparedStatement preparedStatement1 = c.prepareStatement("select * from users_file where name=?");
            preparedStatement1.setString(1, user);
            ResultSet chck=preparedStatement1.executeQuery();
            boolean value=chck.next();
            System.out.println(user);
            System.out.println(value);
            	
      /*  while(rs.next())
        {
        	ur.add(new Users(rs.getString("name"),rs.getString("pass")));
        }*/
        
        /*if(pass==null || pass.trim().length()==0)
		{
			error="perror=perror";
		}*/
			
		/*for (Users users : ur) 
		{
			if(users.getUname().equals(user))
			{
				if(error.trim().length()!=0)
				error+="&uerror=uerror";
				else
				error+="uerror=uerror";
			}
		}*/
		
		/*if(error.trim().length()!=0)
		{
		if( c != null ) c.close();	
		response.sendRedirect("FileFolderReg06?"+error);
		return;
		}
		else
		{*/
            if(!value)
            {
            	System.out.println("inside");
            PreparedStatement preparedStatement = c.prepareStatement("INSERT INTO users_file"
	             		+ "	(name,pass) VALUES (?,?);");	
	             preparedStatement.setString(1, user);
	             preparedStatement.setString(2, pass);
	             preparedStatement.executeUpdate();
	             response.setContentType( "text/plain" );
                 response.getWriter().print( 0 ); 
	             if( c != null ) c.close();     
	 		}
            else
            {
            	  response.setContentType( "text/plain" );
                  response.getWriter().print( 1 );
            }
	       
		//}
        
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
