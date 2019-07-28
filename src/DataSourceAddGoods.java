
import java.sql.*;

public class DataSourceAddGoods extends DataSourceGoods{

    private static final String DB_Name= "GoodsDB.db"; // file name
    private static final String JD_DB_CONNECTION= "jdbc:sqlite:./"+DB_Name; // Connection string for DB;

    // TABLE COL NAMES;
    private static final String TABLE_GOODS= "Goods";
    private static final String COL_NAME= "name";
    private static final String COL_ID= "id";
    private static final String COL_QUANTITY= "quantity";
    private static final String COL_PRICE= "price";

    // constructor;
    public DataSourceAddGoods() {
        super(DataSourceAddGoods.JD_DB_CONNECTION);
    }


    // method for searching the goods by name
    public boolean insertIntoDB(String name, String id,String quantity,String price){

        Statement statement= null;

        // build the string, add ' ;
        StringBuilder nameBuild= new StringBuilder(name);
        nameBuild.insert(0,"'");
        nameBuild.insert(nameBuild.length(),"'");

        StringBuilder idBuild= new StringBuilder(id);
        idBuild.insert(0,"'");
        idBuild.insert(idBuild.length(),"'");

        StringBuilder quantityBuild= new StringBuilder(quantity);
        quantityBuild.insert(0,"'");
        quantityBuild.insert(quantityBuild.length(),"'");

        StringBuilder priceBuild= new StringBuilder(price);
        priceBuild.insert(0,"'");
        priceBuild.insert(priceBuild.length(),"'");


        try{
            statement= connection.createStatement();

            statement.execute("INSERT into "+TABLE_GOODS+"(name,id,quantity,price) values("+nameBuild+","+
                    idBuild+","+quantityBuild+","+priceBuild+")");

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

