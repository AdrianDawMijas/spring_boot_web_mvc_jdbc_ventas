package org.iesvdm.mapper;

import org.springframework.beans.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.beans.PropertyAccessorFactory;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CustomNestedRowMapper<E> implements RowMapper<E> {

    private Class<E> targetClass;

    public CustomNestedRowMapper(Class<E> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public E mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        E mappedObject = BeanUtils.instantiate(this.targetClass);
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(mappedObject);

        beanWrapper.setAutoGrowNestedPaths(true);

        ResultSetMetaData metadata = resultSet.getMetaData();
        int columnCount = metadata.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            try {
                String columnName = JdbcUtils.lookupColumnName(metadata, i);
                Object columnValue = JdbcUtils.getResultSetValue(resultSet, i, Class.forName(metadata.getColumnClassName(i)));

                beanWrapper.setPropertyValue(columnName, columnValue);

            } catch (TypeMismatchException | NotWritablePropertyException | ClassNotFoundException exception) {
                // Ignore
            }
        }

        return mappedObject;
    }
}
