package objects;

/**
 * Created by Work on 26.04.2017.
 */
public class Department {
    private Long departmentId;
    private String departmentAddress;
    private String departmentName;

    Department(){}

    public Department( String departmentAddress, String departmentName) {
        this.departmentAddress = departmentAddress;
        this.departmentName = departmentName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentAddress() {
        return departmentAddress;
    }

    public void setDepartmentAddress(String departmentAddress) {
        this.departmentAddress = departmentAddress;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
