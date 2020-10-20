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
 */
@Component
public class FilterMapper {

    public List<Filter> transform(String json){
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

                    Matcher matcher = operatorPattern.matcher(entry.getValue().fields().next().getKey());

                    if(matcher.find()){

                        operator = Operator.forAlias(matcher.group(1));

                        if(operator == null){
                            throw new FilterMalformedException("Operator " + matcher.group(1) + " doesn't exists");
                        }

                        JsonNode valueNode = entry.getValue().fields().next().getValue();

                        if(valueNode.isInt()){
                            value = valueNode.asInt();
                        } else if(DateUtils.isValidDate(valueNode.asText())) {
                            value = Instant.parse(valueNode.asText());
                        } else {
                            throw new FilterMalformedException("Invalid value passed");
                        }
                    } else {
                        throw new FilterMalformedException("Operator needs to be $operator"); 
                    }
                    filters.add(new Filter(operator, fieldName, value));
                }
                return filters; 
            }   
        } catch (JsonMappingException e) {
            throw new FilterMalformedException(e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }
}
