
import java.sql.*;

public class DataSourceAddEmployee extends DataSourceEmployee{

    private static final String DB_Name= "EmployeeDB.db"; // file name
    private static final String JD_DB_CONNECTION= "jdbc:sqlite:./"+DB_Name; // Connection string for DB;

    // TABLE COL NAMES;
    private static final String TABLE_EMPLOYEE= "Employee";
    private static final String COL_NAME= "name";
    private static final String COL_ID= "id";
    private static final String COL_MOBILE= "mobile";
    private static final String COL_ADDRESS= "address";

    // constructor;
    public DataSourceAddEmployee() {
        super(DataSourceAddEmployee.JD_DB_CONNECTION);
    }


    // method for searching the goods by name
    public boolean insertIntoDB(String name, String id,String mobile,String address){

        Statement statement= null;

        // build the string, add ' ;
        StringBuilder nameBuild= new StringBuilder(name);
        nameBuild.insert(0,"'");
        nameBuild.insert(nameBuild.length(),"'");

        StringBuilder idBuild= new StringBuilder(id);
        idBuild.insert(0,"'");
        idBuild.insert(idBuild.length(),"'");

        StringBuilder mobileBuild= new StringBuilder(mobile);
        mobileBuild.insert(0,"'");
        mobileBuild.insert(mobileBuild.length(),"'");

        StringBuilder addressBuild= new StringBuilder(address);
        addressBuild.insert(0,"'");
        addressBuild.insert(addressBuild.length(),"'");


        try{
            statement= connection.createStatement();

            statement.execute("INSERT into "+TABLE_EMPLOYEE+"(name,id,mobile,address) values("+nameBuild+","+
                    idBuild+","+mobileBuild+","+addressBuild+")");

            return true;

        }catch(SQLException e){
            System.out.println("Query Failed "+e.getMessage());

            return false;
        }finally{

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

