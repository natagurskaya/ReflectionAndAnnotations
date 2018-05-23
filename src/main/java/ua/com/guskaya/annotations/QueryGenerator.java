package ua.com.guskaya.annotations;

import java.lang.reflect.Field;

public class QueryGenerator {
    public String generateGetAll(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Table.class)) {
            StringBuilder query = new StringBuilder("SELECT ");
            boolean flag = false;
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class)) {
                    if (flag) {
                        query.append(", ");
                    }
                    query.append(field.getAnnotation(Column.class).name());
                    flag = true;
                }
            }
            if (!flag) {
                throw new RuntimeException("No columns in the table found.");
            }
            query.append(" FROM ");
            query.append(clazz.getAnnotation(Table.class).name());
            query.append(";");
            return query.toString();
        }
        throw new RuntimeException("It's not a table!");
    }

    public String generateInsert(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Table.class)) {
            StringBuilder query = new StringBuilder("INSERT INTO ");
            query.append(clazz.getAnnotation(Table.class).name());
            query.append(" (");
            boolean flag = false;
            int count = 0;
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class)) {
                    if (flag) {
                        query.append(", ");
                    }
                    query.append(field.getAnnotation(Column.class).name());
                    flag = true;
                    count++;
                }
            }
            if (!flag) {
                throw new RuntimeException("No columns found");
            }
            query.append(") VALUES (");
            boolean valuesFlag = false;
            for (int i = 0; i < count; i++) {
                if (valuesFlag) {
                    query.append(", ");
                }
                query.append("?");
                valuesFlag = true;
            }
            query.append(");");
            return query.toString();
        }
        throw new RuntimeException("It's not a table!");
    }

    public String generateGetById(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Table.class)) {
            StringBuilder query = new StringBuilder("SELECT ");
            boolean flag = false;
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class)) {
                    if (flag) {
                        query.append(", ");
                    }
                    query.append(field.getAnnotation(Column.class).name());
                    flag = true;
                }
            }
            if (!flag) {
                throw new RuntimeException("No columns found");
            }
            query.append(" FROM ");
            query.append(clazz.getAnnotation(Table.class).name());
            query.append(" WHERE id = ?;");
            return query.toString();
        }
        throw new RuntimeException("It's not a table!");
    }

    public String generateDelete(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Table.class)) {
            return "DELETE FROM " + clazz.getAnnotation(Table.class).name() +
                    " WHERE id = ?;";
        }
        throw new RuntimeException("It's not a table!");
    }
}
