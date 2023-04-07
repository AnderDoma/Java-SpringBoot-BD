package com.br.saude.bd.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.br.saude.db.DB;
import com.br.saude.db.DbException;

public class BdConsultas {

	public void consultarDados(String valor) throws ParseException {

		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			// EXAMPLE 1:
			st = conn.prepareStatement(
					"SELECT * FROM coursejdbc.seller where id = 1;");
		
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
	            //Display values
	            System.out.print("Id: " + rs.getInt("id"));
	            System.out.print(", Name: " + rs.getString("Name"));
	            System.out.print(", Email: " + rs.getString("email"));
	            System.out.println(", BirthDate: " + rs.getDate("birthDate"));
	            System.out.println(", BaseSalary: " + rs.getDouble("baseSalary"));
	            System.out.println(", DepartmentId: " + rs.getInt("departmentId"));
	         
			}

		}
		catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} 
			catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		} 
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
