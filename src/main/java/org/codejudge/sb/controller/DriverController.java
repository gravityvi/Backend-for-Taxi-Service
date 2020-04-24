package org.codejudge.sb.controller;


import org.apache.catalina.connector.Response;
import org.codejudge.sb.exceptions.ApiException;
import org.codejudge.sb.model.Driver;
import org.codejudge.sb.model.Location;
import org.codejudge.sb.repository.DriverRepository;
import org.codejudge.sb.validator.DriverRegisterValidation;
import org.codejudge.sb.validator.LocationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/driver")
public class DriverController {

    @Bean
    public MessageSource messageSource () {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("ValidationMessages");
        return messageSource;
    }

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private LocationValidation locationValidation;


    @Autowired
    private DriverRegisterValidation driverRegisterValidation;

    @PostMapping("/register")
    public ResponseEntity<?> registerDriver(@RequestBody @Validated Driver driver, Errors errors) throws Exception {


        driverRegisterValidation.validate(driver,errors);
        if (errors.hasErrors()) {

            String message = errors.getFieldError().getField();
            message+=" ";
            message+=errors.getFieldError().getDefaultMessage();
            throw new ApiException(String.format("Request input is invalid: %s", message));
        }
            driverRepository.save(driver);
            return new ResponseEntity<>(driver, HttpStatus.CREATED);


        }

      @PostMapping("/{id}/sendLocation")
    public ResponseEntity<?> sendLocation(@PathVariable long id,@RequestBody Map<String,Double> reqBody) throws ApiException {

          Driver d=driverRepository.getOne(id);
          String message = "";
         if(d==null)
         {
             message = "No driver found with the ID";
             throw new ApiException(String.format("Request input is invalid: %s", message));
         }

         locationValidation.validate(reqBody);

         d.setLongitude(reqBody.get("longitude"));
         d.setLatitude(reqBody.get("latitude"));

         driverRepository.save(d);
         return new ResponseEntity<>(null,HttpStatus.ACCEPTED);
      }




    }





