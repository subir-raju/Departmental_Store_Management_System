import java.util.*;

public class Main {

    private static Scanner SC = new Scanner(System.in);
    private static boolean isAdmin = false;


    public static void main(String[] args) {

        startProgram();

    }

    private static void startProgram() {

        System.out.println("Please choose a login option:\n\n A. Admin\n B. Counter\n C. Exit");
        String userInput = SC.nextLine().toUpperCase();

        if (userInput.equals("A")) {
            isAdmin = !isAdmin;
            adminLogin();
        } else if (userInput.equals("B")) {
            countermenu();


        }
        //System.out.println("log in");
        else if (userInput.equals("C"))
            System.exit(1);
        else {
            System.out.println("Wrong Input! Try again!");
            startProgram();
        }

    }

    private static void adminLogin() {

        System.out.print("Please enter your name: ");
        String userName = SC.nextLine();

        System.out.print("Please enter your password: ");
        String passwd = SC.nextLine();

        DataSourceAdmin dbQuery = new DataSourceAdmin();

        if (!dbQuery.open()) {
            System.out.println("***Database not connected***");
        }

        Admin admin = dbQuery.queryAdminLogin(userName);

        if (admin.getUserName().equals(userName) && admin.getPasswd().equals(passwd)) {
            dbQuery.close();
            adminOptions();
        } else {
            System.out.println("Wrong password or username!!");
            adminLogin();
        }
    }

    private static void countermenu(){
        System.out.println("A.Members...");
        System.out.println("B.Non-members...");
        System.out.println("C.Back to Main Menu...");
        String input=SC.nextLine().toUpperCase();
        switch (input){
            case "A":
                SearchGoodsForCostMember();
                break;
            case "B":

                SearchGoodsForCost();
                break;
            case "C":
                startProgram();
        }
    }
    private static void SearchGoodsForCostMember() {
        // String choice="y";
        double p1;
        double total = 0;
        // while(choice.equalsIgnoreCase("y")){
        System.out.print("Please enter a Product name: ");
        String name = SC.nextLine();
        System.out.print("Enter quantity=");
        String qunatityy = SC.nextLine();

        DataSourceSearchGoods dbQuery = new DataSourceSearchGoods();

        if (!dbQuery.open()) {
            System.out.println("**Database not connected**");
        }

        Goods goods = dbQuery.querySearch(name.toLowerCase());

        if (!dbQuery.close()) {
            System.out.println("Error closing DB!");

        }
        double q1 = Double.parseDouble(goods.getGoodsquantity());
        double q=Double.parseDouble(qunatityy);
        double q2 = q1 - q;
        String q3= String.valueOf(q2);
        if (q1 == 0) {
            System.out.println("The Item is not available..");
            SearchGoodsForCost();
        } else {
            System.out.println("Products price: " + goods.getGoodsprice());
            p1 = Double.parseDouble(goods.getGoodsprice());

               //updateHelper(qunatity);

            DataSourceModifyGoods.updateGood(goods.getGoodsquantity(),q3,"quantity");
            //String s = String.valueOf(q2);
            // goods.setGoodsquantity(s);
            //System.out.println("New quantity="+s);
            total = total + (p1 * q);
                /*System.out.println("Want to continue ??(y/n)");
                String n=SC.nextLine();
               // if(choice.equalsIgnoreCase("y"))
                if(n.equalsIgnoreCase("y"))
                    SearchGoodsForCost();
                else
                    System.out.println("Total Amount="+total);
                countermenu();*/
        }
        // System.out.println("Want to continue ??(y/n)");
        String n = SC.nextLine();
        if (n.equalsIgnoreCase("y"))
            SearchGoodsForCost();
        else {
            System.out.println("Total Price="+total+" Taka");
            if(total>=500)
                System.out.println("Amount after 10% Discount=" + (total - ((total * 10) / 100)) + " Taka \n \n \n\n");
            else
                System.out.println("You got no discount...Your total price is "+total+" Taka");


            countermenu();
        }
    }



    private static void SearchGoodsForCost(){
        // String choice="y";
        double p1;
        double total=0;
        // while(choice.equalsIgnoreCase("y")){
        System.out.print("Please enter a Product name: ");
        String name = SC.nextLine();
        System.out.print("Enter quantity=");
        double qunatity=SC.nextDouble();

        DataSourceSearchGoods dbQuery = new DataSourceSearchGoods();

        if (!dbQuery.open()) {
            System.out.println("**Database not connected**");
        }

        Goods goods = dbQuery.querySearch(name);

        if (!dbQuery.close()) {
            System.out.println("Error closing DB!");

        }
        double q1=Double.parseDouble(goods.getGoodsquantity());
        double q2=q1-qunatity;
        if(q1==0){
            System.out.println("The Item is not available..");
            SearchGoodsForCost();
        }

        else{
            System.out.println("Products price: " + goods.getGoodsprice());
            p1=Double.parseDouble(goods.getGoodsprice());


            String s=String.valueOf(q2);
            goods.setGoodsquantity(s);
            //System.out.println("New quantity="+s);
            total=total+(p1*qunatity);
                /*System.out.println("Want to continue ??(y/n)");
                String n=SC.nextLine();
               // if(choice.equalsIgnoreCase("y"))
                if(n.equalsIgnoreCase("y"))
                    SearchGoodsForCost();
                else
                    System.out.println("Total Amount="+total);
                countermenu();*/
        }
        System.out.println("Want to continue ??(y/n)");
        String n=SC.nextLine();
        if(n.equalsIgnoreCase("y"))
            SearchGoodsForCost();
        else
            System.out.println("Total Amount="+total);
        countermenu();
    }

    // }
    private static void adminOptions() {

        System.out.println("Options are below: ");
        System.out.println(" A. Goods: \n B. Employees\n C. Members \n D. Go back to main menu \n");

        String userInput1 = SC.nextLine().toUpperCase();

        switch (userInput1) {

            case "A":
                System.out.println("Options are below: ");
                System.out.println(" A. Add Goods: \n B.  Update Goods:\n C.Search Goods\nD.Delete Goods \nE. Go back to main menu \n");
                String userInput2 = SC.nextLine().toUpperCase();
                switch (userInput2) {
                    case "A":
                        AddGoods();
                        break;
                    case "B":
                        updateGoods();
                        break;
                    case "C":
                        SearchGoods();
                        break;
                    case "D":
                        DeleteGoods();
                        break;
                    case "E":
                        startProgram();
                        break;
                    default:
                        System.out.println("Invalid Input..");
                        adminOptions();
                }
                break;
            case "B":
                System.out.println("Options are below: ");
                System.out.println(" A. Add Employees: \n B. Update Employee info: \nC.Search Employee info\nD.Delete Employee info E. Go back to main menu \n");
                String userInput3 = SC.nextLine().toUpperCase();
                switch (userInput3) {
                    case "A":
                        AddEmployee();
                        break;
                    case "B":
                        UpdateEmployee();
                        break;
                    case "C":
                        SearchEmployee();
                        break;
                    case "D":
                        DeleteEmployee();
                        break;
                    case "E":
                        startProgram();
                        break;
                    default:
                        System.out.println("Invalid Input..");
                        adminOptions();
                }


                break;
            case "C":

                System.out.println("Options are below: ");
                System.out.println(" A. Add Member: \n B. Update Members info: \nC.Search Members info\nD.Delete Members info \nE. Go back to main menu \n");
                String userInput4=SC.nextLine().toUpperCase();
                switch(userInput4){
                    case "A":
                        AddMembers();
                        break;
                    case "B":
                        UpdateMembers();
                        break;
                    case "C":
                        SearchMembers();
                        break;
                    case"D":
                        DeleteMembers();
                        break;
                    case "E":
                        startProgram();
                        break;
                    default:
                        System.out.println("Invalid Input..");
                        adminOptions();
                }
                break;
            case "D":
                startProgram();
                break;
            default:
                System.out.println("Wrong input!");
                adminOptions();
        }

    }

    // admin options;

    private static void SearchGoods() {

        System.out.print("Please enter a Product name: ");

        String name = SC.nextLine();

        DataSourceSearchGoods dbQuery = new DataSourceSearchGoods();

        if (!dbQuery.open()) {
            System.out.println("***Database not connected***");
        }

        Goods goods = dbQuery.querySearch(name);

        if (!dbQuery.close()) {
            System.out.println("Error closing DB!");
        }

        System.out.println("Name: " + goods.getGoodsname());
        System.out.println("Products ID: " + goods.getGoodsID());
        System.out.println("Products quantity: " + goods.getGoodsquantity());
        System.out.println("Products price: " + goods.getGoodsprice());


        if (isAdmin)
            adminOptions();
        else
            System.out.println();
        //userOptions();
    }





    private static void AddGoods() {

        System.out.print("Enter a Good name: ");
        String name = SC.nextLine();

        System.out.print("Enter a Good's Id: ");
        String id = SC.nextLine();

        System.out.print("Enter a Good's quantity: ");
        String quantity = SC.nextLine();

        System.out.print("Enter a Good's price: ");
        String price = SC.nextLine();

        DataSourceAddGoods dbQuery = new DataSourceAddGoods();

        if (!dbQuery.open()) {
            System.out.println("***Error Opening DB***");
        }

        if (dbQuery.insertIntoDB(name, id, quantity, price)) {
            System.out.println("Successfully inserted into DB");
        } else {
            System.out.println("Error inserting into DB!");
        }

        if (!dbQuery.close()) {
            System.out.println("Error closing DB!");
        } else {
            System.out.println("DB Closed!");
        }

        adminOptions();
    }

    private static void updateGoods() {

        System.out.print("Please enter what Coloumn you want to update: \nname\nid\nquantity\nprice ");
        String colName = SC.nextLine();

        updateHelper(colName);

    }

    private static void updateHelper(String colName) {

        System.out.print("Please enter old value: ");
        String oldVal = SC.nextLine();
        System.out.print("Please enter new value: ");
        String newVal = SC.nextLine();

        DataSourceModifyGoods dbQuery = new DataSourceModifyGoods();

        if (!dbQuery.open()) {
            System.out.println("Error opening DB");
        }

        if (dbQuery.updateGoods(oldVal, newVal, colName)) {
            System.out.println("Updated successfully!");
        } else {
            System.out.println("Error updating value!");
        }

        if (!dbQuery.close()) {
            System.out.println("Error closing DB");
        } else {
            System.out.println("DB closed successfully!");

        }
    }

    private static void DeleteGoods() {

        System.out.print("Please enter Products  name to delete: ");

        String name = SC.nextLine();

        DataSourceModifyGoods dbQuery = new DataSourceModifyGoods();

        if (!dbQuery.open()) {
            System.out.println("Error opening DB");
        }

        if (dbQuery.deleteRecord(name)) {
            System.out.println("Deleted successfully!");
        } else {
            System.out.println("Error deleting value!");
        }

        if (!dbQuery.close()) {
            System.out.println("Error closing DB");
        } else {
            System.out.println("DB closed successfully!");

        }
    }

    private static void AddEmployee() {

        System.out.print("Enter an Employee name: ");
        String name = SC.nextLine();

        System.out.print("Enter an EMployee's Id: ");
        String id = SC.nextLine();

        System.out.print("Enter an Employee's Mobile no: ");
        String mobile = SC.nextLine();

        System.out.print("Enter an EMployee's address: ");
        String address = SC.nextLine();

        DataSourceAddEmployee dbQuery = new DataSourceAddEmployee();

        if (!dbQuery.open()) {
            System.out.println("***Error Opening DB***");
        }

        if (dbQuery.insertIntoDB(name, id, mobile, address)) {
            System.out.println("Successfully inserted into DB");
        } else {
            System.out.println("Error inserting into DB!");
        }

        if (!dbQuery.close()) {
            System.out.println("Error closing DB!");
        } else {
            System.out.println("DB Closed!");
        }

        adminOptions();
    }
    private static void SearchEmployee() {

        System.out.print("Please enter a Product name: ");

        String name = SC.nextLine();

        DataSourceSearchEmplyee dbQuery = new DataSourceSearchEmplyee();

        if (!dbQuery.open()) {
            System.out.println("***Database not connected***");
        }

        Employee employee = dbQuery.querySearch(name);

        if (!dbQuery.close()) {
            System.out.println("Error closing DB!");
        }

        System.out.println("Name: " +employee.getEmployeeName());
        System.out.println("Employee ID: " + employee.getEmployeeId());
        System.out.println("Employee mobile no: " + employee.getEmployeeMobile());
        System.out.println("Employee Adress" + employee.getEmployeeAddress());


        if (isAdmin)
            adminOptions();
        else
            System.out.println();
        //userOptions();
    }
    private static void UpdateEmployee() {

        System.out.print("Please enter what Coloumn you want to update: \nname\nid\nmobile\naddress ");
        String colName = SC.nextLine();

        updateHelperEmp(colName);

    }

    private static void updateHelperEmp(String colName) {

        System.out.print("Please enter old value: ");
        String oldVal = SC.nextLine();
        System.out.print("Please enter new value: ");
        String newVal = SC.nextLine();

        DataSourceModifyEmployee dbQuery = new DataSourceModifyEmployee();

        if (!dbQuery.open()) {
            System.out.println("Error opening DB");
        }

        if (dbQuery.updateEmployee(oldVal, newVal, colName)) {
            System.out.println("Updated successfully!");
        } else {
            System.out.println("Error updating value!");
        }

        if (!dbQuery.close()) {
            System.out.println("Error closing DB");
        } else {
            System.out.println("DB closed successfully!");

        }
    }
    private static void DeleteEmployee() {

        System.out.print("Please enter Employee name to delete: ");

        String name = SC.nextLine();

        DataSourceModifyEmployee dbQuery = new DataSourceModifyEmployee();

        if (!dbQuery.open()) {
            System.out.println("Error opening DB");
        }

        if (dbQuery.deleteRecord(name)) {
            System.out.println("Deleted successfully!");
        } else {
            System.out.println("Error deleting value!");
        }

        if (!dbQuery.close()) {
            System.out.println("Error closing DB");
        } else {
            System.out.println("DB closed successfully!");

        }
    }


    private static void AddMembers() {

        System.out.print("Enter an Member name: ");
        String name = SC.nextLine();

        System.out.print("Enter an Member's Id: ");
        String id = SC.nextLine();

        System.out.print("Enter an Member's Mobile no: ");
        String mobile = SC.nextLine();

        System.out.print("Enter an Member's address: ");
        String address = SC.nextLine();

        DataSourceAddMember dbQuery = new DataSourceAddMember();

        if (!dbQuery.open()) {
            System.out.println("***Error Opening DB***");
        }

        if (dbQuery.insertIntoDB(name, id, mobile, address)) {
            System.out.println("Successfully inserted into DB");
        } else {
            System.out.println("Error inserting into DB!");
        }

        if (!dbQuery.close()) {
            System.out.println("Error closing DB!");
        } else {
            System.out.println("DB Closed!");
        }

        adminOptions();
    }
    private static void SearchMembers() {

        System.out.print("Please enter a Member name: ");

        String name = SC.nextLine();

        DataSourceSearchMember dbQuery = new DataSourceSearchMember();

        if (!dbQuery.open()) {
            System.out.println("***Database not connected***");
        }

        Member member = dbQuery.querySearch(name);

        if (!dbQuery.close()) {
            System.out.println("Error closing DB!");
        }

        System.out.println("Name:: " +member.getMemberName());
        System.out.println("Member ID:: " + member.getMemberId());
        System.out.println("Member's mobile no:: " + member.getMemberMobile());
        System.out.println("Member's Adress:: " + member.getMemberAddress());


        if (isAdmin)
            adminOptions();
        else
            System.out.println();
        //userOptions();
    }
    private static void UpdateMembers() {

        System.out.print("Please enter what Coloumn you want to update: \nname\nid\nmobile\naddress ");
        String colName = SC.nextLine();

        updateHelperMem(colName);

    }

    private static void updateHelperMem(String colName) {

        System.out.print("Please enter old value: ");
        String oldVal = SC.nextLine();
        System.out.print("Please enter new value: ");
        String newVal = SC.nextLine();

        DataSourceModifyMember dbQuery = new DataSourceModifyMember();

        if (!dbQuery.open()) {
            System.out.println("Error opening DB");
        }

        if (dbQuery.updateMember(oldVal, newVal, colName)) {
            System.out.println("Updated successfully!");
        } else {
            System.out.println("Error updating value!");
        }

        if (!dbQuery.close()) {
            System.out.println("Error closing DB");
        } else {
            System.out.println("DB closed successfully!");

        }
    }
    private static void DeleteMembers() {

        System.out.print("Please enter Member's name to delete: ");

        String name = SC.nextLine();

        DataSourceModifyMember dbQuery = new DataSourceModifyMember();

        if (!dbQuery.open()) {
            System.out.println("Error opening DB");
        }

        if (dbQuery.deleteRecord(name)) {
            System.out.println("Deleted successfully!");
        } else {
            System.out.println("Error deleting value!");
        }

        if (!dbQuery.close()) {
            System.out.println("Error closing DB");
        } else {
            System.out.println("DB closed successfully!");

        }
    }
}