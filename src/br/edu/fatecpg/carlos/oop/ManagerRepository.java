package br.edu.fatecpg.carlos.oop;

import static java.util.logging.Level.*;

import java.util.Set;
import java.util.HashSet;
import java.util.logging.Logger;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
public class ManagerRepository extends EmployeeRepository {
    private static final Logger logger = Logger.getLogger(Employee.class.getName());

    public static final String FILE_NAME = "manager.csv";

    public static Set findAllManagers() throws RepositoryException {
        Set managers = new HashSet();
        try {
            FileReader managerFile = null;
            BufferedReader bufferedManagerFile = null;
            try {
                managerFile = new FileReader(FILE_NAME);
                bufferedManagerFile = new BufferedReader(managerFile);
                String managerRecord;
                String employeeRecord;
                Manager dummyManager = ManagerFactory.createManager();
                while((managerRecord = bufferedManagerFile.readLine()) != null) {
                    ManagerFactory.populateManagerAttributes(dummyManager, managerRecord);
                    employeeRecord = findEmployeeRecordById(dummyManager.getId());
                    Manager manager = ManagerFactory.createManager(managerRecord, employeeRecord);
                    managers.add(manager);
                }
            } finally {
                if(bufferedManagerFile != null) {
                    bufferedManagerFile.close();
                }
                if(managerFile != null) {
                    managerFile.close();
                }
            }
        } catch(IOException ioe) {
            String message = "IOError. Não foi possível encontrar registros de Gerente.";
            logger.log(SEVERE, message, ioe);
            throw new RepositoryException(message, ioe);
        }

        logger.log(INFO, "Registros de gerente retornados:" + managers.size());
        return managers;
    }
}
