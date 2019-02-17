package com.pawlinski.recipeproject.services;

import com.pawlinski.recipeproject.commands.UnitOfMeasureCommand;
import com.pawlinski.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.pawlinski.recipeproject.model.UnitOfMeasure;
import com.pawlinski.recipeproject.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UnitOfMeasureServiceImplTest {

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    UnitOfMeasureToUnitOfMeasureCommand converter = new UnitOfMeasureToUnitOfMeasureCommand();

    UnitOfMeasureService unitOfMeasureService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, converter);
    }

    @Test
    public void listAllUoms() throws Exception {
        //given
        Set<UnitOfMeasure> uoms = new HashSet<>();

        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);

        uoms.add(uom1);
        uoms.add(uom2);

        when(unitOfMeasureRepository.findAll()).thenReturn(uoms);

        //when
        Set<UnitOfMeasureCommand> uomsCommand = unitOfMeasureService.listAllUoms();

        //then
        assertEquals(2, uomsCommand.size());
        verify(unitOfMeasureRepository, times(1)).findAll();

    }
}