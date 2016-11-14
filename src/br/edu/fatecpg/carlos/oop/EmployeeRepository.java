package br.edu.fatecpg.carlos.oop;

import java.io.*;
import java.util.logging.Logger;
import java.util.Random;

import static java.util.logging.Level.*;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
public class EmployeeRepository {

    private static final Logger logger = Logger.getLogger(Employee.class.getName());

    private static Random employeeIdGenerator;
    public static final String FILE_NAME = "employee.csv";

    public static String createNewEmployeeId() {
        if(employeeIdGenerator == null) {
            employeeIdGenerator = new Random(1000);
        }
        return String.valueOf(employeeIdGenerator.nextInt());
    }

    public static void createEmployee(Employee employee) throws RepositoryException {
        try {
            PrintWriter employeeWriter = null;
            try {
                StringBuffer employeeRecord = new StringBuffer();
                employeeRecord.append(employee.getId()).append(",");
                employeeRecord.append(employee.getNome()).append(",");
                employeeRecord.append(employee.getNumeroContato()).append(",");
                employeeRecord.append(employee.getPontos()).append(",");
                employeeRecord.append(employee.getBusinessUnit().getId()).append(",");
                employeeRecord.append(employee.getManager().getId());

                employeeWriter = new PrintWriter(new FileWriter(FILE_NAME, true));
                employeeWriter.println(employeeRecord.toString());
            } finally {
                if(employeeWriter != null) {
                    employeeWriter.close();
                }
            }
        } catch(IOException ioe) {
            String message = "IOError. Unable to create Employee record:" + employee;
            logger.log(SEVERE, message, ioe);
            throw new RepositoryException(message, ioe);
        }
        logger.log(FINE, "Persisted employee record:" + employee);
    }

    public static String findEmployeeRecordById(String id) throws RepositoryException {
        // Procura registro correspondente no arquivo employee.csv
        String employeeRecord = null;
        try {
            FileReader employeeFile = null;
            BufferedReader bufferedEmployeeFile = null;
            try {
                employeeFile = new FileReader(FILE_NAME);
                bufferedEmployeeFile = new BufferedReader(employeeFile);
                Employee dummyEmployee = EmployeeFactory.createEmployee();
                while((employeeRecord = bufferedEmployeeFile.readLine()) != null) {
                    EmployeeFactory.populateEmployeeAttributes(dummyEmployee, employeeRecord);
                    if(id.equals(dummyEmployee.getId())) {
                        break;
                    }
                    employeeRecord = null;
                }
            } finally {
                if(bufferedEmployeeFile != null) {
                    bufferedEmployeeFile.close();
                }
                if(employeeFile != null) {
                    employeeFile.close();
                }
            }
        } catch(IOException ioe) {
            String message = "IOError. Não foi possível encontrar o registro do empregado com id:" + id;
            logger.log(SEVERE, message, ioe);
            throw new RepositoryException(message, ioe);
        }

        logger.log(INFO, "Registro de empregado é "+ employeeRecord + " para o id:" + id);
        
        //Será nulo caso nenhum empregado seja encontrado pelo id fornecido
        return employeeRecord;
    }

    public static Employee findEmployeeById(String id) throws RepositoryException {
        Employee employee = null;
        String employeeRecord = findEmployeeRecordById(id);
        if(employeeRecord != null) {
            employee = EmployeeFactory.createEmployee(employeeRecord);
        }
        return employee;
    }

    public static void updateEmployee(Employee employee) {
        logger.log(INFO, "Registro de empregado atualizado :" + employee);
    }
}
