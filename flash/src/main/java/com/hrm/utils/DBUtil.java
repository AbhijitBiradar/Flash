package com.hrm.utils;

public class DBUtil {
	
	//create a connection
	//select data
	//update data
	//insert data
	//delete data
	
	
	public Connection con;
	public Statement stmt;
	
	public Statement getStatement() throws ClassNotFoundException, SQLException{
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String connection = "jdbc:mysql://localhost:3306/customer";
			String userName = "root";
			String password = "password";
			Class.forName(driver);
			con = DriverManager.getConnection(connection, userName, password);
			stmt = con.createStatement();
			return stmt;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	public void insertData(String query) throws ClassNotFoundException, SQLException{
		Statement sta = getStatement();
		sta.executeUpdate(query);
	}
	
	public ResultSet getData(String query) throws ClassNotFoundException, SQLException{
		ResultSet data = getStatement().executeQuery(query);
		return data;
	}
	
	public void updateData(String query) throws ClassNotFoundException, SQLException{
		getStatement().executeUpdate(query);
		
	}
	
	public static string TestDataFileConnection()
    {
        var fileName = ConfigurationManager.AppSettings["TestDataSheetPath"];
        var con = string.Format(@"Provider=Microsoft.ACE.OLEDB.12.0;Data Source = {0}; Extended Properties=Excel 12.0;", fileName);
        return con;
    }

    public static UserData GetTestData(string keyName)
    {
        using (var connection = new OleDbConnection(TestDataFileConnection()))
        {
            connection.Open();
            var query = string.Format("select * from [DataSet$] where key='{0}'", keyName);
            var value = connection.Query<UserData>(query).FirstOrDefault();
            connection.Close();
            return value;
        }
    }
}
