package com.comodo.vaa25;

import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;

public class ModifiedPostgresqlDataTypeFactory extends PostgresqlDataTypeFactory {

    public ModifiedPostgresqlDataTypeFactory() {
        super();
    }

    @Override
    public boolean isEnumType(final String sqlTypeName) {
        return "gender".equalsIgnoreCase(sqlTypeName) || super.isEnumType(sqlTypeName);
    }

}
