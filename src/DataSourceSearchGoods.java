
import java.sql.*;
import java.util.ArrayList;

public class DataSourceSearchGoods extends DataSourceGoods{

    private static final String DB_Name= "GoodsDB.db"; // file name
    private static final String JD_DB_CONNECTION= "jdbc:sqlite:./"+DB_Name; // Connection string for DB;

    // TABLE COL NAMES;
    private static final String TABLE_GOODS= "Goods";
    private static final String COL_NAME= "name";
    private static final String COL_ID= "id";
    private static final String COL_QUANTITY= "quantity";
    private static final String COL_PRICE= "price";



    // constructor;
    public DataSourceSearchGoods() {
        super(DataSourceSearchGoods.JD_DB_CONNECTION);
    }

    // method for searching the goods by name
    public  Goods  querySearch(String goodsname){

        Statement statement= null;
        ResultSet resultSet= null;
        Goods goods= null;

        // build the string, add ' ;
        StringBuilder goodsBuild= new StringBuilder(goodsname);
        goodsBuild.insert(0,"'");
        goodsBuild.insert(goodsBuild.length(),"'");

        try{
            statement= connection.createStatement();
            resultSet= statement.executeQuery("SELECT * FROM "+
                    TABLE_GOODS+" WHERE name="+goodsBuild);


            goods= new Goods();

            // set good object with data retrieved from DB;
            goods.setGoodsname(resultSet.getString(COL_NAME));
            goods.setGoodsID(resultSet.getString(COL_ID));
            goods.setGoodsquantity(resultSet.getString(COL_QUANTITY));
            goods.setGoodsprice(resultSet.getString(COL_PRICE));

            return goods;

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

    public ArrayList<Goods> showAll(){

        Statement statement= null;
        ResultSet resultSet= null;
        Goods goods= null;
        ArrayList<Goods> goodsList= new ArrayList<>();

        try{
            statement= connection.createStatement();
            resultSet= statement.executeQuery("SELECT * FROM "+
                    TABLE_GOODS);



            while(resultSet.next()){

                goods= new Goods();

                goods.setGoodsname(resultSet.getString(COL_NAME));
                goods.setGoodsID(resultSet.getString(COL_ID));
                goods.setGoodsquantity(resultSet.getString(COL_QUANTITY));
                goods.setGoodsprice(resultSet.getString(COL_PRICE));

                goodsList.add(goods);
            }

            return goodsList;
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
