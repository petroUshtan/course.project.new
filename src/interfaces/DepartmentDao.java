package interfaces;

import objects.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Work on 18.04.2017.
 */
public interface DepartmentDao {

    public void addDepartment(Department department) throws SQLException;
    public void deleteDepartment(Department department) throws SQLException;
    public Department getDepartment(Long id) throws SQLException;
    public List<Department>  getDepartment() throws SQLException;
    public void clearTable() throws SQLException;
}
