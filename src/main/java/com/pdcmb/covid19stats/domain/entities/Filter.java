package com.pdcmb.covid19stats.domain.entities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.pdcmb.covid19stats.domain.exceptions.FilterMalformedException;

public class Filter {

    public enum Operator {
        GREATER("gt"), LESS("lt"), EQUAL("eq"), BETWEEN("bt");

        private static final Map<String, Operator> aliases;

        private final String alias;

        static {
            aliases = new HashMap<String, Operator>();
            for (Operator operator : EnumSet.allOf(Operator.class)) {
                aliases.put(operator.alias, operator);
            }
        }

        private Operator(String alias) {
            this.alias = alias;
        }

        public String alias() {
            return this.alias;
        }

        public static Operator forAlias(String alias) {
            return aliases.get(alias);
        }
    }

    private Operator operator;

    private String fieldName;
    private Method getter;
    private Object value;

    public Filter(Operator operator, String fieldName, Object value) {
        try {
            if (operator.equals(Operator.BETWEEN)) {
                if (!value.getClass().isArray()) {
                    throw new FilterMalformedException("Between operator requires an array");
                } else if (!((Object[]) value)[0].getClass().equals(Data.class.getDeclaredField(fieldName).getType())) {
                    throw new FilterMalformedException("Field " + fieldName + " needs to be an array of " 
                            + Data.class.getDeclaredField(fieldName).getType().getSimpleName() + " type");
                }
            } else if (!value.getClass().equals(Data.class.getDeclaredField(fieldName).getType())) {
                throw new FilterMalformedException("Field " + fieldName + " needs to be of " 
                + Data.class.getDeclaredField(fieldName).getType().getSimpleName() + " type");
            }
            this.getter = Data.class.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
            this.operator = operator;
            this.fieldName = fieldName;
            this.value = value;
        } catch (NoSuchFieldException ex) {
            throw new FilterMalformedException("Field " + fieldName + " doesn't exists");
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException(ex.getLocalizedMessage());
        } catch (SecurityException ex){
            throw new RuntimeException(ex.getMessage());
        }

    }

    public Boolean apply(Data data) {
        Object obj;
        try {
            obj = getter.invoke(data);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e.getMessage());
        }
        switch (this.operator) {
            case EQUAL:
                return evaluateEqual(obj);
            case GREATER:
                return evaluateGreater(obj);
            case LESS:
                return evaluateLess(obj);
            case BETWEEN:
                return evaluateBeetwen(obj);
            default:
                throw new RuntimeException();
        }
    }

    private Boolean evaluateEqual(Object obj){
        return this.value.equals(obj);
    }

    private Boolean evaluateGreater(Object obj){
        if(obj instanceof Instant){
            if(((Instant)obj).compareTo((Instant)value) > 0)
                return true;
            else return false;
        } else{
            return (Integer)obj > (Integer)value;
        }
            
    }

    private Boolean evaluateLess(Object obj){
        if(obj instanceof Instant){
            if(((Instant)obj).compareTo((Instant)value) < 0)
                return true;
            else return false;
        } else{
            return (Integer)obj < (Integer)value;
        }
    }
    private Boolean evaluateBeetwen(Object obj){
        if(obj instanceof Instant){
            if( ((Instant)obj).compareTo(((Instant[])value)[0]) > 0 
                && ((Instant)obj).compareTo(((Instant[])value)[1]) < 0 )
                return true;
            else return false;
        } else{
            return ((Integer)obj > ((Integer[])obj)[0]) && ((Integer)obj < ((Integer[])obj)[1]);
        }
    }

    public Operator getOperator() {
        return this.operator;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public Method getGetter() {
        return this.getter;
    }

    public Object getValue() {
        return this.value;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Filter)) {
            return false;
        }
        Filter filter = (Filter) o;
        return Objects.equals(operator, filter.operator) && Objects.equals(fieldName, filter.fieldName) && Objects.equals(getter, filter.getter) && Objects.equals(value, filter.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operator, fieldName, getter, value);
    }
    
}
