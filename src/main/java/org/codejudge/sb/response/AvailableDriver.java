package org.codejudge.sb.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codejudge.sb.dto.DriverDTO;
import org.codejudge.sb.model.Driver;

import java.util.List;


public class AvailableDriver {
    @JsonProperty("available_cabs")
    private List<DriverDTO> driverList;

    public AvailableDriver(List<DriverDTO> driverList) {
        this.driverList = driverList;
    }

    public List<DriverDTO> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<DriverDTO> driverList) {
        this.driverList = driverList;
    }
}
