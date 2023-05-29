package com.helloword.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.helloworld.dao.CategoryDAO;
import com.helloworld.model.Category;

/**
 * Servlet implementation class Category
 */
@WebServlet("/Category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO = new CategoryDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchPattern = request.getParameter("searchPattern");
		List<Category> categories;
		if (Objects.isNull(searchPattern)) {
			categories = categoryDAO.getAllCategory();
		} else {
			categories = categoryDAO.getCategoryByName("%" +searchPattern + "%");
		}
		
		PrintWriter out = response.getWriter();
		out.append("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"ISO-8859-1\">\r\n"
				+ "<title>Category Management</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "	<form action=\"\" method=\"get\">\r\n"
				+ "		<label>Search Pattern</label>\r\n"
				+ "		<input type=\"text\" name=\"searchPattern\">\r\n"
				+ "		<input type=\"submit\" name=\"Search\" value=\"Search\">\r\n"
				+ "	</form>"
				+ "<table>\r\n"
				+ "		<tr>\r\n"
				+ "			<td>Id</td>\r\n"
				+ "			<td>Name</td>\r\n"
				+ "			<td>Action</td>\r\n"
				+ "		</tr>");
		
		for (Category category : categories) {
			out.append("<tr>");
			
			out.append("<td>");
			out.append(category.getId().toString());
			out.append("</td>");

			out.append("<td>");
			out.append(category.getName());
			out.append("</td>");

			out.append("<td>");
			out.append("<a href=\"CategoryAdd\">Add</a>");
			out.append("<form action=\"\" method=\"post\"><input type=\"text\" name=\"id\" value=\""+ category.getId() +"\" hidden=\"true\"><input type=\"submit\" name=\"Delete\" value=\"Delete\"></form>");
			out.append("</td>");
			
			out.append("</tr>");
		}
		
		out.append("</table>\r\n"
				+ "</body>\r\n"
				+ "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String delete = request.getParameter("Delete");
		String id = request.getParameter("id");
		if (Objects.nonNull(delete) && delete.equals("Delete")
				&& Objects.nonNull(id)) {
			try {
				Integer categoryId = Integer.parseInt(id);
				categoryDAO.deleteCategoryById(categoryId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		doGet(request, response);
	}

}
