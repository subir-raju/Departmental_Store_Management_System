
import java.sql.*;
import java.util.ArrayList;

public class DataSourceSearchEmplyee extends DataSourceEmployee{

    private static final String DB_Name= "EmployeeDB.db"; // file name
    private static final String JD_DB_CONNECTION= "jdbc:sqlite:./"+DB_Name; // Connection string for DB;

    // TABLE COL NAMES;
    private static final String TABLE_EMPLOYEE= "Employee";
    private static final String COL_NAME= "name";
    private static final String COL_ID= "id";
    private static final String COL_MOBILE= "mobile";
    private static final String COL_ADDRESS= "address";



    // constructor;
    public DataSourceSearchEmplyee() {
        super(DataSourceSearchEmplyee.JD_DB_CONNECTION);
    }

    // method for searching the goods by name
    public Employee querySearch(String employeename){

        Statement statement= null;
        ResultSet resultSet= null;
        Employee employee= null;

        // build the string, add ' ;
        StringBuilder employeeBuild= new StringBuilder(employeename);
        employeeBuild.insert(0,"'");
        employeeBuild.insert(employeeBuild.length(),"'");

        try{
            statement= connection.createStatement();
            resultSet= statement.executeQuery("SELECT * FROM "+
                    TABLE_EMPLOYEE+" WHERE name="+employeeBuild);


            employee= new Employee();

            // set good object with data retrieved from DB;
            employee.setEmployeeName(resultSet.getString(COL_NAME));
            employee.setEmployeeId(resultSet.getString(COL_ID));
            employee.setEmployeeMobile(resultSet.getString(COL_MOBILE));
            employee.setEmployeeAddress(resultSet.getString(COL_ADDRESS));

            return employee;

            // close everything;
        }catch(SQLException e){
            System.out.println("Query Failed "+e.getMessage());
            return null;
        }finally{

            if(resultSet!=null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("Could not close resultSet!!! "+e.getMessage());
                }

            if(statement!=null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Couldn't close Statement!");
                    e.printStackTrace();
                }
        }
    }

    public ArrayList<Employee> showAll(){

        Statement statement= null;
        ResultSet resultSet= null;
        Employee employee= null;
        ArrayList<Employee> employeeList= new ArrayList<>();

        try{
            statement= connection.createStatement();
            resultSet= statement.executeQuery("SELECT * FROM "+
                    TABLE_EMPLOYEE);



            while(resultSet.next()){

                employee= new Employee();

                employee.setEmployeeName(resultSet.getString(COL_NAME));
                employee.setEmployeeId(resultSet.getString(COL_ID));
                employee.setEmployeeMobile(resultSet.getString(COL_MOBILE));
                employee.setEmployeeAddress(resultSet.getString(COL_ADDRESS));

                employeeList.add(employee);
            }

            return employeeList;
            // close everything;
        }catch(SQLException e){
            System.out.println("Query Failed "+e.getMessage());
            return null;
        }finally{

            if(resultSet!=null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("Could not close resultSet!!! "+e.getMessage());
                }

            if(statement!=null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Couldn't close Statement!");
                    e.printStackTrace();
                }
        }
    }

}
