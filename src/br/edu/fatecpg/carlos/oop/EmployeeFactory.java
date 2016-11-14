package br.edu.fatecpg.carlos.oop;

import java.util.StringTokenizer;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
public class EmployeeFactory {

    public static Employee createEmployee() {
        return new Employee();
    }

    public static Employee createEmployee(String id, String name, String contactNumber, BusinessUnit businessUnit, Manager manager) {
        Employee employee = new Employee();
        employee.setId(id.trim());
        employee.setNome(name.trim());
        employee.setNumeroContato(contactNumber.trim());
        employee.setBusinessUnit(businessUnit);
        employee.setManager(manager);
        return employee;
    }

    public static Employee createEmployee(String employeeRecord) {
        return createEmployee(employeeRecord, null, null);
    }

    public static Employee createEmployee(String employeeRecord, BusinessUnit businessUnit, Manager manager) {
        Employee employee = createEmployee();
        populateEmployeeAttributes(employee, employeeRecord, businessUnit, manager);
        return employee;
    }

    public static void populateEmployeeAttributes(final Employee employee, final String employeeRecord) {
        populateEmployeeAttributes(employee,  employeeRecord, null, null);
    }

    public static void populateEmployeeAttributes(final Employee employee, final String employeeRecord,
                                                                        BusinessUnit businessUnit, Manager manager) {
        if(employeeRecord == null) {
            throw new IllegalArgumentException("String inválida passada para a criação de registro de empregado");
        }
        StringTokenizer employeeAttributes = new StringTokenizer(employeeRecord, ",");

        if(employeeAttributes.countTokens() != 6) {
            throw new IllegalArgumentException("String inválida passada para a criação de registro de empregado:" + employeeRecord);
        }

        employee.setId(employeeAttributes.nextToken().trim());
        employee.setNome(employeeAttributes.nextToken().trim());
        employee.setNumeroContato(employeeAttributes.nextToken().trim());
        employee.setPontos(Integer.parseInt(employeeAttributes.nextToken().trim()));
    }

}