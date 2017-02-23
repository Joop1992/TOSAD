package persistence.businessRule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.*;

public class BusinessRuleDAO
{
	private final BusinsesRuleBaseDAO businsesRuleBaseDAO = new BusinsesRuleBaseDAO();
	
	/*-----------------------------------START PRIVATE METHODS--------------------------------------------*/
	
	// private method which retrieves multiple business rules
	private static List<BusinessRule> getMultipleRules(BusinessRuleDAO businessRuleDAO, String query) {
		List<BusinessRule> brList = new ArrayList<BusinessRule>();
		try (Connection con = DriverManager.getConnection(BusinsesRuleBaseDAO.DB_URL, BusinsesRuleBaseDAO.DB_USERNAME, BusinsesRuleBaseDAO.DB_PASSWORD)) {
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		String code = "", description = "", table = "", attribute = "", when = "";
		while(rs.next()){
			int businessRuleID = rs.getInt("businessRuleID");
			code = rs.getString("code");
			description = rs.getString("description");
			table = rs.getString("table_name");
			attribute = rs.getString("attribute");
			int businessRuleTypeID = rs.getInt("businessRuleTypeID");
			int operatorID = rs.getInt("operatorID");
			when = rs.getString("trigger_when");	
			List<Value> values = BusinessRuleDAO.getValue(businessRuleID);
			BusinessRule br = new BusinessRuleBuilder().setAtt(new Attribute(attribute)).setCode(code).setDesc(description).setTab(table).setWhen(when).createBusinessRule();
			for(Value v : values){
				br.addValue(v);
			}
			br.setRuleType(businessRuleDAO.getRuleType(businessRuleTypeID));
			br.setOperator(businessRuleDAO.getOperator(operatorID));
			brList.add(br);
		}				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return brList;
	}
	
	// private method which retrieves a single business rule
	private BusinessRule getBusinessRule(String query){
		try (Connection con = DriverManager.getConnection(BusinsesRuleBaseDAO.DB_URL, BusinsesRuleBaseDAO.DB_USERNAME, BusinsesRuleBaseDAO.DB_PASSWORD)) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			String code = "", description = "", table = "", attribute = "", when = "";
			while(rs.next()){
				int businessRuleID = rs.getInt("businessRuleID");
				code = rs.getString("code");
				description = rs.getString("description");
				table = rs.getString("table_name");
				attribute = rs.getString("attribute");
				int businessRuleTypeID = rs.getInt("businessRuleTypeID");
				int operatorID = rs.getInt("operatorID");
				when = rs.getString("trigger_when");	
				List<Value> values = getValue(businessRuleID);
				BusinessRule br = new BusinessRuleBuilder().setAtt(new Attribute(attribute)).setCode(code).setDesc(description).setTab(table).setWhen(when).createBusinessRule();
				for(Value v : values){
					br.addValue(v);
				}
				br.setRuleType(getRuleType(businessRuleTypeID));
				br.setOperator(getOperator(operatorID));
				return br;
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
	}

	// private method which retrieves One operator based on his own Id
	private Operator getOperator(int id){
		String name = "", abbrev = "";
		try (Connection con = DriverManager.getConnection(BusinsesRuleBaseDAO.DB_URL, BusinsesRuleBaseDAO.DB_USERNAME, BusinsesRuleBaseDAO.DB_PASSWORD)) {
			Statement stmt = con.createStatement();
			String query = "select name, abbreviation from Operator where operatorId = "+id;
			ResultSet rs = stmt.executeQuery(query);			
			while(rs.next()){
				name = rs.getString("name");
				abbrev = rs.getString("abbreviation");
				return Operator.createOperator(name, abbrev);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// private method which retrieves One businessRuleType based on his own Id
	private BusinessRuleType getRuleType(int id){
		try (Connection con = DriverManager.getConnection(BusinsesRuleBaseDAO.DB_URL, BusinsesRuleBaseDAO.DB_USERNAME, BusinsesRuleBaseDAO.DB_PASSWORD)) {
			Statement stmt = con.createStatement();
			String query = "select name, abbreviation from BusinessRuleType where businessRuleTypeId = "+id;
			ResultSet rs = stmt.executeQuery(query);		
			while(rs.next()){
				String name = rs.getString("name");
				String abbrev = rs.getString("abbreviation");
				return new BusinessRuleType(name, abbrev);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// private method which retrieves all the values based on the business rule id
	private static List<Value> getValue(int id) throws SQLException{
		List<Value> vList = new ArrayList<Value>();
		try(Connection con = DriverManager.getConnection(BusinsesRuleBaseDAO.DB_URL, BusinsesRuleBaseDAO.DB_USERNAME, BusinsesRuleBaseDAO.DB_PASSWORD)){
			Statement stmt = con.createStatement();
			String query = "select value from Value where businessRuleID = " + id;
			ResultSet rs = stmt.executeQuery(query);		
			while(rs.next()){
				String value = rs.getString("value");
				vList.add(new Value(value));
			}
		}
		return vList;
	}
	/*------------------------------------END PRIVATE METHODS---------------------------------------------*/
	
	
	/*------------------------------------START PUBLIC METHODS--------------------------------------------*/

	public static List<BusinessRule> getAllBusinessRules(BusinessRuleDAO businessRuleDAO){
		String query = 
				 "select businessruleid, code, description, table_name, attribute, businessruletypeid, operatorid, trigger_when from BUSINESSRULE";
		return getMultipleRules(businessRuleDAO, query);
	}
	
	public BusinessRule searchByCode(String code){
		String query = "select businessruleid, code, description, table_name, attribute, businessruletypeid, operatorid, trigger_when from BusinessRule where code = '"+code+"'";
		return getBusinessRule(query);
	}
	
	public List<BusinessRule> searchMultipleByCode(String code){
		String query = "select businessruleid, code, description, table_name, attribute, businessruletypeid, operatorid, trigger_when from BusinessRule where code LIKE '"+code+"%'";
		return getMultipleRules(this, query);
	}

	/*------------------------------------END PUBLIC METHODS----------------------------------------------*/
	
}
