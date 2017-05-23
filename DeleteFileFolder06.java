package homework6;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class DeleteFileFolder06
 */
@WebServlet("/DeleteFileFolder06")
public class DeleteFileFolder06 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFileFolder06() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int delete(ArrayList<homework6.File> fr, int id, Connection c) throws SQLException
    {
    	
    	int location=0;
    	int i=0;
    	for (;i<fr.size();i++) 
    	{
    		homework6.File f=fr.get(i);
    		if(f.getId()==id)
    			location=i;
    		if(f.getParentId()!=null && f.getParentId()==id){
    			delete(fr, f.getId(),c);
    						
    			if(!fr.get(i).isFolder())
    			{ 				
    		        String path = getServletContext().getRealPath( "/WEB-INF/files/"+fr.get(i).getId());
    				java.io.File file = new java.io.File(path);
    		         file.delete();	
    			}
    			String sql1="delete from filesfolder where id=?";
            	PreparedStatement preparedStatement = c.prepareStatement(sql1);	
				preparedStatement.setInt(1,f.getId());
				preparedStatement.executeUpdate();
    			fr.remove(f);
    			i--;
    		}
    	}
    	return location;

    }
    
    
    
    
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<homework6.File> fr= new ArrayList<homework6.File>();
		String id=request.getParameter("id");
		System.out.println(id);
		int userid=(int)request.getSession().getAttribute("userid");

		Connection c=null;
		try
        {
		 	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu41";
            String username = "cs3220stu41";
            String password = "TaCMPGbl";
            c = DriverManager.getConnection( url, username, password );
            ResultSet rs;
            Statement stmt = c.createStatement();
            
            String sql2="select f.* from filesfolder f where f.owner_id="+userid+" ;";	
     		rs=stmt.executeQuery(sql2);
    		
     		while (rs.next())
    		{
    			Integer parentId=null;
    			if(rs.getInt("parent_id")!=0)
    			parentId=rs.getInt("parent_id");
    				
    			fr.add(new homework6.File(rs.getInt("id"),
    					rs.getString("name"),
    					rs.getString("type"),
    					rs.getLong("size"),
    					rs.getTimestamp("upl_date"),
    					parentId,
    					rs.getBoolean("is_folder"),
    					rs.getInt("owner_id")));
    		}
     		 
            if(!(id==null) && !(id.equals("")))
     		{
            	
     			int i=delete(fr,Integer.parseInt(id),c);
     			if(!fr.get(i).isFolder())
     			{
     		        String path = getServletContext().getRealPath( "/WEB-INF/files/"+fr.get(i).getId());
     				java.io.File file = new java.io.File(path);
     		        boolean fileDelete = file.delete();	
     			}
     			String sql1="delete from filesfolder where id=?";
            	PreparedStatement preparedStatement = c.prepareStatement(sql1);	
				preparedStatement.setInt(1,Integer.parseInt(id));
				preparedStatement.executeUpdate();
    			fr.remove(i);
     		}
     		// response.sendRedirect("FileFolderDisplay06?id="+parentid);  

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
