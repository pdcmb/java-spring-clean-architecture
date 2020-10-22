package com.pdcmb.covid19stats.presentation.models.mappers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdcmb.covid19stats.domain.entities.Filter;
import com.pdcmb.covid19stats.domain.entities.Filter.Operator;
import com.pdcmb.covid19stats.domain.exceptions.FilterMalformedException;
import com.pdcmb.covid19stats.utils.DateUtils;

import org.springframework.stereotype.Component;

/**
 * Transforms Json string to Filter list
 * 
 */
@Component
public class JsonToFilter {

    /**
     * <p>Transfors json string into an array of {@link Filter} objects. </p>
     * 
     * @param json Json string to convert
     * @return An array that contains Filters
     * @throws FilterMalformedException if filter is of incorrect form
     * @throws JsonProcessingException on error during parsing json
     */
    public Filter[] map(String json){

        if (json == null || json.isEmpty())
            return null;
        Pattern fieldPattern = Pattern.compile("(^[a-z-A-Z]+)");
        Pattern operatorPattern = Pattern.compile("^\\$(.+)");
        List<Filter> filters = new ArrayList<>();
        JsonNode jsonNode;

        try {

            jsonNode = new ObjectMapper().readTree(json);
            Iterator<Entry<String,JsonNode>> iterator = jsonNode.fields();

            while(iterator.hasNext()){

                String fieldName;
                Operator operator;
                Object value;

                Entry<String,JsonNode> entry = iterator.next();

                if(fieldPattern.matcher(entry.getKey()).find()){

                    fieldName = entry.getKey();

                    if(!entry.getValue().fields().hasNext()){
                        throw new FilterMalformedException("Argument cannot be null");
                    }

                    Entry<String, JsonNode> argument = entry.getValue().fields().next();

                    Matcher matcher = operatorPattern.matcher(argument.getKey());

                    if(!matcher.find()){
                        throw new FilterMalformedException("Incorrect operator form"); 
                    }

                    operator = Operator.forAlias(matcher.group(1));

                    if(operator == null){
                        throw new FilterMalformedException("Operator " + matcher.group(1) 
                                                            + " doesn't exists");
                    }
                    JsonNode valueNode = argument.getValue();

                    if(valueNode.isArray()){

                        List<Object> valuesList = new ArrayList<>();

                        for(JsonNode objNode :valueNode){

                            if(objNode.isInt()){
                                valuesList.add(objNode.asInt());
                            } else if(DateUtils.isValidDate(objNode.asText())) {
                                valuesList.add(Instant.parse(objNode.asText()));
                            } else {
                                throw new FilterMalformedException("Invalid value passed");
                            }
                        }

                        if(valuesList.size() != 2){
                            throw new FilterMalformedException("Array needs to have exacly two elements");
                        } 
                        else{
                            filters.add(new Filter(operator, fieldName, valuesList.toArray()));
                        }
                        
                    } else {

                        if(valueNode.isInt()){
                            value = valueNode.asInt();
                        } else if(DateUtils.isValidDate(valueNode.asText())) {
                            value = Instant.parse(valueNode.asText());
                        } else {
                            throw new FilterMalformedException("Invalid value passed");
                        }

                        filters.add(new Filter(operator, fieldName, value));
                    }
                    
                }
                return filters.toArray(Filter[]::new); 
            }   
        } catch (JsonMappingException e) {
            throw new FilterMalformedException(e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }
}
