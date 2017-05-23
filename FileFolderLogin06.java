package homework6;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/FileFolderLogin06")
public class FileFolderLogin06 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileFolderLogin06() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) 
		{
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("user1") != null)
		{
			response.sendRedirect("FileFolderDisplay06");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/homework6/FileFolderLogin06.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		HttpSession session = request.getSession();
		Connection c = null;
		if (user == null || pass == null) {
			response.sendRedirect("FileFolderLogin06?error=error");
			return;
		}

		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu41";
			String username = "cs3220stu41";
			String password = "TaCMPGbl";

			c = DriverManager.getConnection(url, username, password);
			ResultSet rs;
			String sql1 = "select u.id from users_file u where u.name=? and u.pass=? ;";
			PreparedStatement preparedStatement = c.prepareStatement(sql1);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, pass);
			rs = preparedStatement.executeQuery();

			if (!rs.next()) {
				if (c != null)
					c.close();
				response.sendRedirect("FileFolderLogin06?error=error");
				return;

			} else {
				int userid = rs.getInt("id");
				session.setAttribute("user1", user);
				session.setAttribute("userid", userid);
				response.sendRedirect("FileFolderDisplay06");
				return;

			}

		} catch (SQLException e) {
			throw new ServletException(e);
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}

	}

}
