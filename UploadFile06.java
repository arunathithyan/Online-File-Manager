package homework6;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 * Servlet implementation class UploadFile06
 */
@WebServlet("/UploadFile06")
public class UploadFile06 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile06() {
        super();
        // TODO Auto-generated constructor stub
    }

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getSession().getAttribute("user1")==null)
		{
		response.sendRedirect("FileFolderLogin06");
		return;
		}
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/homework6/UploadFile06.jsp");  
		rd.forward(request, response); 
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		//List<homework5.File> fr=(ArrayList<homework5.File>)request.getSession().getAttribute("fr06");
   	
		//homework5.File current=null;
		String id="";
		Connection c=null;

		try
		{
		    String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu41";
            String username = "cs3220stu41";
            String password = "TaCMPGbl";

            	c = DriverManager.getConnection( url, username, password );
				
			
			
				DiskFileItemFactory factory = new DiskFileItemFactory();
		        ServletContext servletContext = this.getServletConfig().getServletContext();
		        File repository = (File) servletContext.getAttribute( "javax.servlet.context.tempdir" );
		        factory.setRepository( repository );
		        ServletFileUpload upload = new ServletFileUpload( factory );
		        int count = 0;
		        String fileDir = getServletContext().getRealPath( "/WEB-INF/files" );
		        String fileName=null; 
		        java.io.File file=null;
		        int stopcount=0;
		        
		        
		        
				Integer lastId=null;
		        
		        	List<FileItem> items = upload.parseRequest( request );
		            for( FileItem item : items )
		            {
		                if( !item.isFormField() )
		                {
		                	
		                
		                    fileName = (new File(item.getName())).getName();
		                	if(stopcount!=1)
		                   	{
		                		PreparedStatement preparedStatement = c.prepareStatement("INSERT INTO filesfolder"
		    		             		+ "	(name, type, size, upl_date, parent_id, is_folder, owner_id) VALUES (?,?,?,?,?,?,?);");	
		    		             preparedStatement.setString(1, fileName);
		    		             preparedStatement.setString(2, FilenameUtils.getExtension(fileName));
		    		             preparedStatement.setLong(3, 0);
		    		             java.util.Date today = new java.util.Date();
		    		             java.sql.Timestamp date = new java.sql.Timestamp(today.getTime());
		    		             preparedStatement.setTimestamp(4,date);
		    		             if(!(id==null) && !(id.equals("")) && !(id.equals("null")))
		    		            	 preparedStatement.setInt(5, Integer.parseInt(id));
		    		            else
		    		            	 preparedStatement.setNull(5, Types.INTEGER); 	 
		    		             
		    		             preparedStatement.setBoolean(6,false);
		    		             preparedStatement.setInt(7, (int)request.getSession().getAttribute("userid"));
		    		             preparedStatement.executeUpdate();
		    					
		                		
		        		    	String sql2="select MAX(id) id_val from filesfolder";
		        				PreparedStatement preparedStatement2 = c.prepareStatement(sql2);
		        				ResultSet r=preparedStatement2.executeQuery();
		        				r.next();
		        				lastId=r.getInt("id_val");
		                		
		                		
		                		stopcount=1;
		                   	}
		                    
		                    file = new File( fileDir, String.valueOf(lastId));
		                    item.write( file );
		                    ++count;
		                }
		                else
		                {
		                	 if(item.getFieldName().equals("id"))
		                	 {
		                		 id=item.getString();   		 
		                	 }
		                }
		            }
		            
		          //fr.add(new homework4.File(fileName,FilenameUtils.getExtension(fileName),0, new Date(),current,false));  
		            
					
		            PreparedStatement preparedStatement3 = c.prepareStatement("update filesfolder SET size=? where id=?;");	
		             preparedStatement3.setLong(1, file.length());
		             preparedStatement3.setInt(2,lastId);
		             preparedStatement3.executeUpdate();
					
		            if(!(id==null) && !(id.equals("")) && !(id.equals("null")))
		    		{
		    		response.sendRedirect("FileFolderDisplay06?id="+id); 
		    		return;
		    		}
		            else
		            {
		            response.sendRedirect("FileFolderDisplay06");
			        return;
		            }
		       }
		        catch( Exception e )
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
