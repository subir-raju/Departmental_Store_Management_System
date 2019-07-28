public class Employee {
    private String employeeName;
    private String employeeId;
    private String employeeMobile;
    private String employeeaddress;

    public Employee() {
    }

    public Employee(String employeeName, String employeeId, String employeeMobile, String employeeAddress) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.employeeMobile= employeeMobile;
        this.employeeaddress= employeeAddress;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeMobile() {
        return employeeMobile;
    }

    public void setEmployeeMobile(String employeeMobile) {
        this.employeeMobile = employeeMobile;
    }

    public String getEmployeeAddress() {
        return employeeaddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeaddress = employeeAddress;
    }
}
