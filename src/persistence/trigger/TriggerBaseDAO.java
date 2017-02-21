package persistence.trigger;

public class TriggerBaseDAO {

	//used to connect to Oracle database, might eventually be transferred to 
	//domain class so it's more generic
	  static final String DB_URL = "jdbc:oracle:thin:@ondora02.hu.nl:8521/cursus02.hu.nl";
	  static final String DB_DRIVER = "oracle.jdbc.OracleDriver";
	  static final String DB_USERNAME = "tosad_2015_2a_team4_target";
	  static final String DB_PASSWORD = "tosad_2015_2a_team4_target";

	public TriggerBaseDAO() {

		try {
			Class.forName(DB_DRIVER).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}