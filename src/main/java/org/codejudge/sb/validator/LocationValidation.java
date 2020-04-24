package org.codejudge.sb.validator;

import org.codejudge.sb.exceptions.ApiException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LocationValidation {


    public void validate(Map<String,Double> mp) throws ApiException {
        if(mp.get("latitude")==null||mp.get("longitude")==null)
        {
            String message = "Latitude and Longitude are required parameters";
            throw new ApiException(String.format("Request input is invalid: %s", message));

        }
    }
}
