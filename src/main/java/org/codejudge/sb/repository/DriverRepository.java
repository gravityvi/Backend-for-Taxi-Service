package org.codejudge.sb.repository;

import org.codejudge.sb.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DriverRepository extends  JpaRepository<Driver,Long> {


}
