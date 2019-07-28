
import java.sql.*;

public class DataSourceModifyMember extends DataSourceMember{

    private static final String DB_Name= "MemberDB.db"; // file name
    private static final String JD_DB_CONNECTION= "jdbc:sqlite:./"+DB_Name; // Connection string for DB;

    // private static int COUNT_RATING= 1;

    // TABLE COL NAMES;
    private static final String TABLE_MEMBER= "Member";
    private static final String COL_NAME= "name";
    private static final String COL_ID= "id";
    private static final String COL_MOBILE= "mobile";
    private static final String COL_ADDRESS="address";


    // constructor;
    public DataSourceModifyMember() {
        super(DataSourceModifyMember.JD_DB_CONNECTION);
    }


    // method for searching the goods by name
    public boolean updateMember(String oldVal,String newVal,String colName){

        Statement statement= null;

        // build the string, add;
        StringBuilder newValeBuild= new StringBuilder(newVal);
        newValeBuild.insert(0,"'");
        newValeBuild.insert(newValeBuild.length(),"'");

        StringBuilder oldBuild= new StringBuilder(oldVal);
        oldBuild.insert(0,"'");
        oldBuild.insert(oldBuild.length(),"'");

        try{
            statement= connection.createStatement();

            statement.execute("UPDATE "+TABLE_MEMBER+" SET "+colName+"="+newValeBuild+" WHERE "+colName+"="+oldBuild);

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



    public boolean deleteRecord(String val){

        Statement statement= null;

        // build the string, add;
        StringBuilder newValeBuild= new StringBuilder(val);
        newValeBuild.insert(0,"'");
        newValeBuild.insert(newValeBuild.length(),"'");

        try{
            statement= connection.createStatement();

            statement.execute("DELETE FROM "+TABLE_MEMBER+" WHERE "+COL_NAME+"="+newValeBuild);

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
