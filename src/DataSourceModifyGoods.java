
import java.sql.*;

public class DataSourceModifyGoods extends DataSourceGoods{

    private static final String DB_Name= "GoodsDB.db"; // file name
    private static final String JD_DB_CONNECTION= "jdbc:sqlite:./"+DB_Name; // Connection string for DB;

    // private static int COUNT_RATING= 1;

    // TABLE COL NAMES;
    private static final String TABLE_GOODS= "Goods";
    private static final String COL_NAME= "name";
    private static final String COL_ID= "id";
    private static final String COL_QUANTITY= "quantity";
    private static final String COL_PRICE= "price";


    // constructor;
    public DataSourceModifyGoods() {
        super(DataSourceModifyGoods.JD_DB_CONNECTION);
    }


    // method for searching the goods by name
    public boolean updateGoods(String oldVal,String newVal,String colName){

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

            statement.execute("UPDATE "+TABLE_GOODS+" SET "+colName+"="+newValeBuild+" WHERE "+colName+"="+oldBuild);

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

            statement.execute("DELETE FROM "+TABLE_GOODS+" WHERE "+COL_NAME+"="+newValeBuild);

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
    public static boolean updateGood(String oldVal,String newVal,String colName){

        Statement statement= null;

        // build the string, add;
        StringBuilder newValeBuild= new StringBuilder(newVal);
        newValeBuild.insert(0,"'");
        newValeBuild.insert(newValeBuild.length(),"'");

        StringBuilder oldBuild= new StringBuilder(oldVal);
        oldBuild.insert(0,"'");
        oldBuild.insert(oldBuild.length(),"'");

        try{
            statement= con.createStatement();

            statement.execute("UPDATE "+TABLE_GOODS+" SET "+colName+"="+newValeBuild+" WHERE "+colName+"="+oldBuild);

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
