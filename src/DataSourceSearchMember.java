
import java.sql.*;
import java.util.ArrayList;

public class DataSourceSearchMember extends DataSourceMember{

    private static final String DB_Name= "MemberDB.db"; // file name
    private static final String JD_DB_CONNECTION= "jdbc:sqlite:./"+DB_Name; // Connection string for DB;

    // TABLE COL NAMES;
    private static final String TABLE_MEMBER= "Member";
    private static final String COL_NAME= "name";
    private static final String COL_ID= "id";
    private static final String COL_MOBILE= "mobile";
    private static final String COL_ADDRESS= "address";



    // constructor;
    public DataSourceSearchMember() {
        super(DataSourceSearchMember.JD_DB_CONNECTION);
    }

    // method for searching the goods by name
    public Member querySearch(String membername){

        Statement statement= null;
        ResultSet resultSet= null;
        Member member= null;

        // build the string, add ' ;
        StringBuilder memberBuild= new StringBuilder(membername);
        memberBuild.insert(0,"'");
        memberBuild.insert(memberBuild.length(),"'");

        try{
            statement= connection.createStatement();
            resultSet= statement.executeQuery("SELECT * FROM "+
                    TABLE_MEMBER+" WHERE name="+memberBuild);


            member= new Member();

            // set good object with data retrieved from DB;
            member.setMemberName(resultSet.getString(COL_NAME));
            member.setMemberId(resultSet.getString(COL_ID));
            member.setMemberMobile(resultSet.getString(COL_MOBILE));
            member.setMemberAddress(resultSet.getString(COL_ADDRESS));

            return member;

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

    public ArrayList<Member> showAll(){

        Statement statement= null;
        ResultSet resultSet= null;
        Member member= null;
        ArrayList<Member> memberList= new ArrayList<>();

        try{
            statement= connection.createStatement();
            resultSet= statement.executeQuery("SELECT * FROM "+
                    TABLE_MEMBER);



            while(resultSet.next()){

                member=new Member();

                member .setMemberName(resultSet.getString(COL_NAME));
                member .setMemberId(resultSet.getString(COL_ID));
                member .setMemberMobile(resultSet.getString(COL_MOBILE));
                member.setMemberAddress(resultSet.getString(COL_ADDRESS));

                memberList.add(member);
            }

            return memberList;
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
