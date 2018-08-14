package com.comodo.vaa25;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.EnumType;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.Types.OTHER;

public class PostgreSqlEnumType extends EnumType {
    @Override
    public void nullSafeSet(
            final PreparedStatement st, final Object value, final int index, final SessionImplementor session
    ) throws HibernateException, SQLException {
        super.nullSafeSet(st, value, index, session);
        if (value == null) {
            st.setNull(index, OTHER);
        } else {
            st.setObject(index, value.toString(), OTHER);
        }
    }
}