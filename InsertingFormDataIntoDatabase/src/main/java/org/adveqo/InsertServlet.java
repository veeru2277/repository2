package org.adveqo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userNumber = Integer.parseInt(req.getParameter("userid")) ;
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		
		Connection connection = null ;
		PreparedStatement preparedStatement = null ;
		PrintWriter writer = resp.getWriter();
		
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
 			String url = "jdbc:mysql://localhost:3306/veerudb" ;
 			String username = "root" ;
 			String password = "root" ;
 			connection = DriverManager.getConnection(url,username,password);
 			String query = "INSERT INTO USERS VALUES (?,?,?)" ;
 			preparedStatement = connection.prepareStatement(query);
 			preparedStatement.setInt(1, userNumber);
 			preparedStatement.setString(2,firstName);
 			preparedStatement.setString(3, lastName);
 			int count = preparedStatement.executeUpdate();
 			if (count > 0) {
 				writer.println("<html><body>");
 				writer.println("<h3> record inserted successfully into database ... <br>") ;
 				writer.println("<a href='index.html'> click here </a> to goto index page") ;
 				writer.println("</h3></body></html>");
 			}
 			else 
 				writer.println("no record inserted ... ") ;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (preparedStatement != null)
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
