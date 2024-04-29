package DesignPatterns.Structural;

//Provides a surrogate or placeholder for another object to control access to it.
// Frequently employed for implementing lazy initialization, access control, and logging.

import javax.xml.bind.ValidationException;

interface EmployeeDAO {
    void create() throws ValidationException;
}

class EmployeeDOAImpl implements EmployeeDAO { //IS-A

    @Override
    public void create() {
        System.out.println("Employee obj created");
    }
}

class EmployeeDAOProxy implements EmployeeDAO { //IS-A

    EmployeeDAO employeeDAO; //HAS-A
    boolean isAdmin;

    EmployeeDAOProxy(EmployeeDAO employeeDAO, boolean isAdmin) {
        this.employeeDAO = employeeDAO;
        this.isAdmin = isAdmin;
    }

    @Override
    public void create() throws ValidationException {
        if(isAdmin) {
            employeeDAO.create();
        } else {
            throw new ValidationException("Don't have access");
        }
    }
}

public class Proxy {

    public static void main(String[] args) {
        EmployeeDAO employeeDAOImpl = new EmployeeDOAImpl();
        EmployeeDAO employeeDAOProxy = new EmployeeDAOProxy(employeeDAOImpl, false);
        try {
            employeeDAOProxy.create();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
