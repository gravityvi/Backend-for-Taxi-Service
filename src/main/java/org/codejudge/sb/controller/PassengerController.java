package org.codejudge.sb.controller;


import org.codejudge.sb.dto.DriverDTO;
import org.codejudge.sb.response.AvailableDriver;
import org.codejudge.sb.exceptions.ApiException;
import org.codejudge.sb.model.Driver;
import org.codejudge.sb.repository.DriverRepository;
import org.codejudge.sb.response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.codejudge.sb.validator.LocationValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;




@RestController
@RequestMapping("/api/v1/passenger")
public class PassengerController {


    @Autowired
    private LocationValidation locationValidation;

    @Autowired
    private DriverRepository driverRepository;

    private double distance = 4;

    private static double haversine(double lat1, double lon1,double lat2, double lon2)
    {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }


    @PostMapping("/available_cabs")
    public ResponseEntity<?> getAvailableCars(@RequestBody Map<String,Double> mp) throws ApiException {
        locationValidation.validate(mp);
        List<Driver> drivers= driverRepository.findAll();
        List<DriverDTO> availDrivers = new ArrayList<>();
        double lon = mp.get("longitude");
        double lat = mp.get("latitude");
        for(Driver d:drivers)
        {
            if(d.getLatitude()!=null&&d.getLongitude()!=null)
            {
                double latitude = d.getLatitude();
                double longitude = d.getLongitude();

                double dist =haversine(lat,lon,latitude,longitude);

               if(dist<=4.5)
               {
                   availDrivers.add(new DriverDTO(d.getName(),d.getPhoneNumber(),d.getCarNumber()));
               }
            }
        }

        if(availDrivers.size()==0)
        {
            String message = "No cabs available!";
            return ResponseEntity.ok().body(new Message(message));

        }
        else
        {
            AvailableDriver availableDriver = new AvailableDriver(availDrivers);
            return ResponseEntity.ok().body(new AvailableDriver(availDrivers));
        }

    }

}
