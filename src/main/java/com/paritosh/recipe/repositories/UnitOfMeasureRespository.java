package com.paritosh.recipe.repositories;

import com.paritosh.recipe.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRespository extends CrudRepository<UnitOfMeasure,Long> {

Optional<UnitOfMeasure> findByUom(String uom);


}
