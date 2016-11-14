package br.edu.fatecpg.carlos;

import static org.junit.Assert.assertNotNull;

import java.util.Set;
import java.util.Iterator;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import br.edu.fatecpg.carlos.oop.BusinessUnit;
import br.edu.fatecpg.carlos.oop.BusinessUnitRepository;
import br.edu.fatecpg.carlos.oop.Employee;
import br.edu.fatecpg.carlos.oop.EmployeeRepository;
import br.edu.fatecpg.carlos.oop.EmployeeService;
import br.edu.fatecpg.carlos.oop.EmployeeServiceException;
import br.edu.fatecpg.carlos.oop.Manager;
import br.edu.fatecpg.carlos.oop.ManagerRepository;
import br.edu.fatecpg.carlos.oop.RepositoryException;

/**
 * TODO
 *
 * @author Carlos Henrique
 * 
 */
public class EmployeeServiceTestCase {

    private Employee employee;
    
    @Test
    public void setUp() throws IOException {
        // Creating necessary business unit and manager records
        File businessUnitsFile = new File(BusinessUnitRepository.FILE_NAME);
        businessUnitsFile.delete();
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(businessUnitsFile));
            pw.println("1, Setor Bancário");
            pw.println("2, Telecomunicações");
            pw.println("3, Corporativo");
        } finally {
            if(pw != null) {
                pw.close();
            }
        }

        File employeesFile = new File(EmployeeRepository.FILE_NAME);
        employeesFile.delete();

        File managersFile = new File(ManagerRepository.FILE_NAME);
        managersFile.delete();

        try {
            pw = new PrintWriter(new FileWriter(employeesFile));
            pw.println("1, Maria, 99185-6492, -1, 1, 2");
            pw.println("2, João, 3487-5412, -1, 1, 1");
        } finally {
            if(pw != null) {
                pw.close();
            }
        }

        try {
            pw = new PrintWriter(new FileWriter(managersFile));
            pw.println("1, Antônio");
            pw.println("2, Garcia");
        } finally {
            if(pw != null) {
                pw.close();
            }
        }

    }
    
    @Test
    public void testEmployeeCreation() throws RepositoryException,
                                              EmployeeServiceException {
        BusinessUnit businessUnit = null;
        Manager manager = null;

        // This simulates user choosing one from the available business units.
        Set businessUnits = BusinessUnitRepository.findAllBusinessUnits();
        Iterator businessUnitsIterator = businessUnits.iterator();
        if(businessUnitsIterator.hasNext()) {
            businessUnit = (BusinessUnit)businessUnitsIterator.next();
        }

        // This simulates user choosing one from the available managers.
        Set managers = ManagerRepository.findAllManagers();
        Iterator managersIterator = managers.iterator();
        if(managersIterator.hasNext()) {
            manager = (Manager)managersIterator.next();
        }

        assertNotNull(businessUnit);
        assertNotNull(manager);

        EmployeeService employeeService = new EmployeeService();
        employee = employeeService.createEmployee("Carlos Henrique", "99171-5485", businessUnit, manager);

    }
    
    @Test
    public void testEmployeeCredit() throws RepositoryException, EmployeeServiceException {
        testEmployeeCreation();
        EmployeeService employeeService = new EmployeeService();
        employeeService.giveCredit(employee.getId(), 5);

    }
}
