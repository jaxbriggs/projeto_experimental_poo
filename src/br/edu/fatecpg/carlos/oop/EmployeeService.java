package br.edu.fatecpg.carlos.oop;

import java.util.logging.Logger;
import static java.util.logging.Level.*;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
public class EmployeeService {

    private static final Logger logger = Logger.getLogger(Employee.class.getName());

    public Employee createEmployee(String name, String contactNumber,
                                    BusinessUnit businessUnit, Manager manager) throws EmployeeServiceException {
        String id = createNewEmployeeId();
        Employee employee = EmployeeFactory.createEmployee(id, name, contactNumber, businessUnit, manager);
        try {
            EmployeeRepository.createEmployee(employee);
        } catch(RepositoryException re) {
            String message = "Empregado criado com sucesso:" + employee;
            logger.log(SEVERE, message);
            throw new EmployeeServiceException(message, re);
        }
        logger.log(INFO, "Empregado criado com sucesso:" + employee);
        return employee;
    }

    private String createNewEmployeeId() {
        return EmployeeRepository.createNewEmployeeId();
    }

    public void giveCredit(String employeeId, int point) throws EmployeeServiceException {
        Employee employee = null;
        try {
            employee = EmployeeRepository.findEmployeeById(employeeId);
        } catch(RepositoryException re) {
            String message = "Não pode dar pontos para o empregado com ID:" + employeeId;
            logger.log(SEVERE, message);
            throw new EmployeeServiceException(message, re);
        }
        employee.darPontos(point);
        EmployeeRepository.updateEmployee(employee);
        logger.log(INFO, "Pontos entregues com sucesso:" + employee);
    }
}