package br.edu.fatecpg.carlos.oop;

import java.util.StringTokenizer;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
public class ManagerFactory extends EmployeeFactory {

    public static Manager createManager() {
        return new Manager();
    }

    public static Manager createManager(String managerRecord, String employeeRecord) {
        Manager manager = createManager();
        populateEmployeeAttributes(manager, employeeRecord);
        populateManagerAttributes(manager, managerRecord);
        return manager;
    }

    public static Manager createManager(String managerRecord) {
        Manager manager = createManager();
        populateManagerAttributes(manager, managerRecord);
        return manager;
    }

    public static void populateManagerAttributes(Manager manager, String managerRecord) {
        if(managerRecord == null) {
            throw new IllegalArgumentException("String inválida passada para a criação de registro de gerente");
        }
        StringTokenizer managerAttributes = new StringTokenizer(managerRecord, ",");

        if(managerAttributes.countTokens() != 2) {
            throw new IllegalArgumentException("String inválida passada para a criação de registro de gerente");
        }

        manager.setId(managerAttributes.nextToken().trim());
        manager.setManagingProject(managerAttributes.nextToken().trim());
    }
}
