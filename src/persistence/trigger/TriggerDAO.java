package persistence.trigger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TriggerDAO extends TriggerBaseDAO{
	
	public String insertTrigger(String trigger){
		String errorOrSuccessMessage = "Trigger(s) succesfully inserted!";
		try(Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)){
			Statement stmt = con.createStatement();
			stmt.executeUpdate(trigger);
		}catch(SQLException sqle){
			//if there is an error, pass it on to our error message
			errorOrSuccessMessage = sqle.getMessage();
		}
		return errorOrSuccessMessage;
	}
	
	//retrieves all trigger names
	public ArrayList<String> getAllTriggers(){
		ArrayList<String> triggerNameList = new ArrayList<String>();
		try(Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)){
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user_triggers");
			while(rs.next()){
				triggerNameList.add(rs.getString("TRIGGER_NAME"));
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return triggerNameList;
	}
	
	//checks if there is a trigger with the same code without the numbers on the end
	public List<String> getExistingTriggers(String code){
		List<String> triggers = new ArrayList<String>();
		String codeMinusNumbers = code.substring(0,code.length() - 3);
		String query = "select trigger_name from user_triggers where trigger_name LIKE '"+codeMinusNumbers+"%'";
		
		try(Connection con = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD)){
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				triggers.add(rs.getString("trigger_name"));
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return triggers;
	}
}
