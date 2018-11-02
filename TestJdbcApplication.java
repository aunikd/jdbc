import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestJdbcApplication {

	public static void main(String[] args) {
		Employee e = new Employee();
		Scanner sc = new Scanner(System.in);
		List<Employee> lst = new ArrayList<Employee>();
		lst.add(e);
		
		EmployeeJDBC ed = new EmployeeJDBC();
		System.out.println("Enter eid,ename and esal");
		e.setEmpid(sc.nextInt());
		e.setEname(sc.next());
		e.setEsal(sc.nextDouble());
		lst.add(e);
		ed.saveData(lst);
	}

}
