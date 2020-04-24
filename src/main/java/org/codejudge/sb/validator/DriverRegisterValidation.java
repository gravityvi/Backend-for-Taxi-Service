package org.codejudge.sb.validator;


import org.codejudge.sb.model.Driver;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;

@Component
public class DriverRegisterValidation  {


    public void validate(Driver driver, Errors errors) {
        if(driver.getPhoneNumber()==null)
        {
            return;
        }
        char  phoneNumber[] = driver.getPhoneNumber().toCharArray();

        for(int i=0;i<phoneNumber.length;i++)
        {
            if(phoneNumber[i]<'0'||phoneNumber[i]>'9'||phoneNumber.length!=10)
            {
                errors.rejectValue("phoneNumber",null,"must be valid");
                break;
            }
        }



    }


}
