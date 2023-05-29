package com.helloworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.helloword.util.ConnectionUtil;
import com.helloworld.model.Category;
import com.mysql.cj.xdevapi.PreparableStatement;

public class CategoryDAO {
	
	public List<Category> getAllCategory() {
		try (
			Connection conn = ConnectionUtil.getConnection();
			// SQL
			Statement stt = conn.createStatement();
			ResultSet resultSet = stt.executeQuery("SELECT id, name FROM category;");
		) {
			List<Category> result = new ArrayList<Category>();
			while(resultSet.next()) {
				Integer id = resultSet.getInt(1);
				String name = resultSet.getString("name");
				
				Category category = new Category(id, name);
				result.add(category);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	public List<Category> getCategoryByName(String namePattern) {
		// SQL Injection => Loss data
		// "validaoisdjasdjo" UNION SELECT id as id, password as name FROM users;
		try (
			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement stt = conn.prepareStatement("SELECT id, name FROM category c WHERE c.name like ? ");
		) {
			stt.setString(1, namePattern);
			ResultSet resultSet = stt.executeQuery();
			
			List<Category> result = new ArrayList<Category>();
			while(resultSet.next()) {
				Integer id = resultSet.getInt(1);
				String name = resultSet.getString("name");
				
				Category category = new Category(id, name);
				result.add(category);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	public Category getCategoryById(Integer id) {
		return null;
	}
	
	public boolean updateCategoryById(Category category, Integer id) {
		return false;
	}
	
	public int deleteCategoryById(Integer id) {
		try (
			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement stt = conn.prepareStatement("DELETE FROM category c WHERE c.id = ? ");
		) {
			stt.setInt(1, id);
			int rowAffected = stt.executeUpdate();
			return rowAffected;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
