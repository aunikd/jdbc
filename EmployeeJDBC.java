import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeJDBC {
	Connection con;
	PreparedStatement ps;
	int i;
	
	public Connection myConnection(){
		//1.load driver
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","Newuser123");
			System.out.println("Connection to db..");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("catch connection..");
		} catch (SQLException e) {
			System.out.println("catch connection..2");
			e.printStackTrace();
		}
		return con;
	}
	
	public int saveData(List<Employee> lst){
		try {
			con=myConnection();
			Employee e = new Employee();
			ps=con.prepareStatement("insert into LTIEMP values(?,?,?)");
			ps.setInt(1,lst.get(0).getEmpid());
			ps.setString(2,lst.get(0).getEname());
			ps.setDouble(3,lst.get(0).getEsal());
			i = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("test11");
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println("global.."+e);
		}
		return i;
	}
    	
	public int deleteRecord(List<Employee> lst){
		try {
			con=myConnection();
			ps=con.prepareStatement("delete from LTIEMP where empno=?");
			ps.setInt(1,lst.get(0).getEmpid());
			i=ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	public void searchRecord(int eno){
		try {
			con=myConnection();
			ps=con.prepareStatement
					("select * from LTIEMP where empno=?");
			ps.setInt(1,eno);;
			ResultSet rs=ps.executeQuery();
			rs.next();
			System.out.println
			(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3));
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void displayAll(){
		String str="select * from LTIEMP";
		try {
			con=myConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(str);

			ResultSetMetaData rsmd=rs.getMetaData();
			for(int i=1;i<=rsmd.getColumnCount();i++){
				System.out.print(rsmd.getColumnName(i)+"\t");
			}
			System.out.println("\n--------------------------------------");
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

