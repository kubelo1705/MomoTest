package mapping;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.databasemysql;
import model.Product;

public class ProductMapping {
	public static List<Product> getProductsFromFile(){
		List<Product> products=new ArrayList<>();
		try {
			BufferedReader bufferedReader=new BufferedReader(new FileReader("src/main/java/resource/Products.txt"));
			String line=bufferedReader.readLine();
			while(line!=null) {
				String [] attributes=line.trim().split(",");
				products.add(new Product(attributes[0],Integer.parseInt(attributes[1])));
				line=bufferedReader.readLine();
			}
			bufferedReader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
		return products;
	}
	public static List<Product> getProductsFromDb(){
		List<Product> products=new ArrayList<>();
		Connection CONNECTION=databasemysql.getConnection();
		String query = "select * from products";
		Statement statement;
		try {
			statement = CONNECTION.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				Product product=new Product(resultSet.getString("name"), resultSet.getInt("price"));
				products.add(product);
			}
			statement.close();
			resultSet.close();
			CONNECTION.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
}
