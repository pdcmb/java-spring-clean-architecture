package com.pdcmb.covid19stats.domain.entities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.pdcmb.covid19stats.domain.exceptions.FilterMalformedException;

/**
 * A class that rappresents a filter 
 * 
 * @author Mateusz Ziomek
 */
public class Filter {

    /**
     *<p> Enum that hold logical operations</p>
     *<p> An instance can be also retrieved using {@link #forAlias(String)} specifying alias
     * of the operator </p>
     * 
     * Aliases are: <b>gt</b>, <b>lt</b>, <b>eq</b>, <b>bt</b>
     * 
     * @author Mateusz Ziomek
     */
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

        /**
         * Returns an {@link Operator} given alias
         * 
         * @param alias Alias of opertor to retrieve
         * @return Operator instance with given alias
         */
        public static Operator forAlias(String alias) {
            return aliases.get(alias);
        }
    }

    private Operator operator;

    /**
     * Name of the field to which apply this filter
     */
    private String fieldName;

    /**
     * Name of the getter method used to get specified field
     */
    private Method getter;

    /**
     * Value compared to
     */
    private Object value;

    /**
     * 
     * @param operator {@link Operator} used by this filter
     * @param fieldName Name of the field to compare to 
     * @param value A value to which data will be compared to
     */
    public Filter(Operator operator, String fieldName, Object value) {
        try {
            if (operator.equals(Operator.BETWEEN)) {
                if (!value.getClass().isArray()) {
                    throw new FilterMalformedException("Between operator requires an array");
                }
                else if ((((Object[])value).length != 2) ) {
                    throw new FilterMalformedException("Between operator requires "
                                                    + " an array with exacly two values");
                } 
                else if (!((Object[]) value)[0].getClass().equals(Data.class.getDeclaredField(fieldName).getType())) {
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
            throw new NoSuchFieldError("Field " + fieldName + " doesn't exists");
        } catch (NoSuchMethodException ex) {
            throw new NoSuchFieldError("Field " + fieldName + " doesn't exists");
        } catch (SecurityException ex){
            throw new RuntimeException(ex.getMessage());
        }

    }

    /**
     * <p>Applies this filter to {@link Data} instance passed ad argument. 
     * It compares specified field value of the data instance to the value stored inside 
     * this filter instance. </p>
     * 
     * <p>For instance, with "greater" operator it returns true if specified field of the provided Data
     * instance is greater that value stored </p>
     * @param data Data object to which apply this filter
     * @return A boolean, result of the comparision
     */
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
            if(((Instant)obj).compareTo(((Instant[])value)[0]) > 0 
                && ((Instant)obj).compareTo(((Instant[])value)[1]) < 0 )
                return true;
            else return false;
        } else{
            return ((Integer)obj > ((Integer[])value)[0]) && ((Integer)obj < ((Integer[])value)[1]);
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
