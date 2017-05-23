package homework6;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
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
 * Servlet implementation class FileFolderDisplay06
 */
@WebServlet("/FileFolderDisplay06")
public class FileFolderDisplay06 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileFolderDisplay06() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException
   	{ 	super.init(config);
   	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getSession().getAttribute("user1")==null)
		{
		response.sendRedirect("FileFolderLogin06");
		return;
		}
		Connection c=null;
		List<File> fr=new ArrayList<File>();
		List<Users> ur=new ArrayList<Users>();
		String id=request.getParameter("id");
		String parentid="";
		String currentname="ROOT";
		
		try
        {
		 	
		    String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu41";
            String username = "cs3220stu41";
            String password = "TaCMPGbl";
          	c = DriverManager.getConnection( url, username, password );
            ResultSet rs;
            Statement stmt = c.createStatement();
        
		
		int userid=(int)request.getSession().getAttribute("userid");
		
		System.out.println(id);
		if(id!=null && (id.equals("1")))
		{	
			String sql2="select f.* from filesfolder f where f.id in (select s.file_id from Shared s where s.owner_id="+userid+");";
			rs=stmt.executeQuery(sql2);	
		
		}	
		else
		{	String sql2="select f.* from filesfolder f where f.owner_id="+userid+" or f.owner_id=0;";	
			rs=stmt.executeQuery(sql2);
		
		}
		
		while (rs.next())
		{
			Integer parentId=null;
			if(rs.getInt("parent_id")!=0)
			parentId=rs.getInt("parent_id");
				
			fr.add(new File(rs.getInt("id"),
					rs.getString("name"),
					rs.getString("type"),
					rs.getLong("size"),
					rs.getTimestamp("upl_date"),
					parentId,
					rs.getBoolean("is_folder"),
					rs.getInt("owner_id")));
		}
		
		request.setAttribute("fr06", fr);
		
		String sql1="select * from users_file";
		rs=stmt.executeQuery(sql1);
		while (rs.next())
		{
		ur.add(new Users(rs.getInt("id"),rs.getString("name")));
		}
		
		
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
		
		request.setAttribute("ur06", ur);
		
				
		if(id!=null && !id.equals("") && !id.equals("null"))
		{
			for (File subFolders : fr) 
			{	
					if(subFolders.getParentId()!=null && subFolders.getId()==Integer.parseInt(id))
							parentid=String.valueOf(subFolders.getParentId());
								
					if(subFolders.getId()==Integer.parseInt(id))
							currentname=subFolders.getName();
			
			}	
		}
		request.setAttribute("parentid", parentid);
		request.setAttribute("currentname", currentname);
	
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/homework6/FileFolderDisplay06.jsp");  
		rd.forward(request, response); 
		return;
		
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
