package br.edu.fatecpg.carlos.oop;

import java.util.Set;
import java.util.HashSet;
import java.util.logging.Logger;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import static java.util.logging.Level.*;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
public class BusinessUnitRepository {

    private static final Logger logger = Logger.getLogger(Employee.class.getName());

    public static final String FILE_NAME = "business-units.csv";

    public static Set findAllBusinessUnits() throws RepositoryException {
        Set businessUnits = new HashSet();
        try {
            FileReader businessUnitFile = null;
            BufferedReader bufferedBusinessUnitFile = null;
            try {
                businessUnitFile = new FileReader(FILE_NAME);
                bufferedBusinessUnitFile = new BufferedReader(businessUnitFile);
                String businessUnitRecord;
                while((businessUnitRecord = bufferedBusinessUnitFile.readLine()) != null) {
                    BusinessUnit businessUnit = BusinessUnitFactory.createBusinessUnit(businessUnitRecord);
                    businessUnits.add(businessUnit);
                }
            } finally {
                if(bufferedBusinessUnitFile != null) {
                    bufferedBusinessUnitFile.close();
                }
                if(businessUnitFile != null) {
                    businessUnitFile.close();
                }
            }
        } catch(IOException ioe) {
            String message = "IOError. Não foi possível encontrar registros Business Unit.";
            logger.log(SEVERE, message, ioe);
            throw new RepositoryException(message, ioe);
        }

        logger.log(INFO, "Registros de Gerente retornados:" + businessUnits.size());
        return businessUnits;
    }

}
