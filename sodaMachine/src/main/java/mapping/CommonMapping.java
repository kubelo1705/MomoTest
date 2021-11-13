package mapping;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DatabaseMySql;


public class CommonMapping {
	public static int getTotalLastDayFromFile(){
		int totalLastDay=0;
		try {
			BufferedReader bufferedReader=new BufferedReader(new FileReader("src/main/java/resource/TotalPerDay.txt"));
			String temp=bufferedReader.readLine();
			String line=temp;
			while(line!=null) {
				temp=bufferedReader.readLine();
				if(temp==null)
					totalLastDay=Integer.parseInt( line.trim().split(",")[1]);
				line=temp;
			}
			bufferedReader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
		return totalLastDay;
	}
	
	public static int getLastDayFromFile(){
		int totalLastDay=0;
		try {
			BufferedReader bufferedReader=new BufferedReader(new FileReader("src/main/java/resource/TotalPerDay.txt"));
			String temp=bufferedReader.readLine();
			String line=temp;
			while(line!=null) {
				temp=bufferedReader.readLine();
				if(temp==null)
					totalLastDay=Integer.parseInt( line.trim().split(",")[0]);
				line=temp;
			}
			bufferedReader.close();
			
		} catch (IOException e) {
			
		}
		return totalLastDay;
	}
	
	public static void writeTodayToFile(int lastDay, int totalToday) {
		try {
			BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("src/main/java/resource/TotalPerDay.txt",true));
			bufferedWriter.append("\n");
			bufferedWriter.append(String.valueOf(lastDay+1));
			bufferedWriter.append(",");
			bufferedWriter.append(String.valueOf(totalToday));
			bufferedWriter.close();
		} catch (IOException e) {
			
		}
	}
	
	public static int getTotalLastDayFromDb(){
		int totalLastDay=0;
		Connection CONNECTION=DatabaseMySql.getConnection();
		String query = "select * from totalperday order by id desc limit 1";
		Statement statement;
		try {
			statement = CONNECTION.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			totalLastDay= resultSet.getInt("total");
			statement.close();
			resultSet.close();
			CONNECTION.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalLastDay;
	}
	
	public static int getLastDayFromDb() {
		int lastDay=0;
		Connection CONNECTION=DatabaseMySql.getConnection();
		String query = "select * from totalperday order by id desc limit 1";
		Statement statement;
		try {
			statement = CONNECTION.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			lastDay= resultSet.getInt("day");
			statement.close();
			resultSet.close();
			CONNECTION.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lastDay;
	}
	
	public static boolean writeTodayToDb(int lastDay,int  totalToday) {
		Connection connection = DatabaseMySql.getConnection();
		String sql = "insert into experience (day,total) values(?,?)";
		
		try {
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, lastDay);
			preparedStatement.setInt(2, totalToday);
			boolean success = preparedStatement.execute();
			preparedStatement.close();
			connection.close();
			return success;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
	}
}
