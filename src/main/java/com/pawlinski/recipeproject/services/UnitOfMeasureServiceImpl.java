package com.pawlinski.recipeproject.services;

import com.pawlinski.recipeproject.commands.UnitOfMeasureCommand;
import com.pawlinski.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.pawlinski.recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand converter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.converter = converter;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {

        return StreamSupport.stream(unitOfMeasureRepository.findAll() //StreamSupport convenient way to swap itetator for something that I can stream against
                .spliterator(), false)
                .map(converter::convert)
                .collect(Collectors.toSet());
    }
}
