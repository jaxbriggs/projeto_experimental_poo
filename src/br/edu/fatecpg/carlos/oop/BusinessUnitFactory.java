package br.edu.fatecpg.carlos.oop;

import java.util.StringTokenizer;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
public class BusinessUnitFactory {

    public static BusinessUnit createBusinessUnit() {
        return new BusinessUnit();
    }

    public static BusinessUnit createBusinessUnit(String businessUnitRecord) {

        BusinessUnit businessUnit = createBusinessUnit();

        if(businessUnitRecord == null) {
            throw new IllegalArgumentException("String inválida passada para a criação de registro de business unit");
        }

        StringTokenizer businessUnitAttributes = new StringTokenizer(businessUnitRecord, ",");

        if(businessUnitAttributes.countTokens() != 2) {
            throw new IllegalArgumentException("String inválida passada para a criação de registro de business unit");
        }

        businessUnit.setId(businessUnitAttributes.nextToken());
        businessUnit.setNome(businessUnitAttributes.nextToken());
        return businessUnit;

    }
}
