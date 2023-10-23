package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OperatorServiceImplTest {

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Mock
    private OperatorRepository operatorRepository;

    @BeforeEach
    void setUp() {
        // Reset Mockito's internal state before each test
        reset(operatorRepository);
    }

    @Test
    void addOperator() {
        Operator operatorToSave = new Operator();
        when(operatorRepository.save(operatorToSave)).thenReturn(operatorToSave);

        Operator savedOperator = operatorService.addOperator(operatorToSave);

        assertNotNull(savedOperator);
        assertEquals(operatorToSave, savedOperator);
        verify(operatorRepository, times(1)).save(operatorToSave);
    }

    @Test
    void retrieveOperator() {
        Long operatorId = 1L;
        Operator expectedOperator = new Operator();
        when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(expectedOperator));

        Operator retrievedOperator = operatorService.retrieveOperator(operatorId);

        assertNotNull(retrievedOperator);
        assertEquals(expectedOperator, retrievedOperator);
    }

    @Test
    void retrieveOperatorNotFound() {
        Long operatorId = 1L;
        when(operatorRepository.findById(operatorId)).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> operatorService.retrieveOperator(operatorId));
    }

    @Test
    void updateOperator() {
        Operator operatorToUpdate = new Operator();
        when(operatorRepository.save(operatorToUpdate)).thenReturn(operatorToUpdate);

        Operator updatedOperator = operatorService.updateOperator(operatorToUpdate);

        assertNotNull(updatedOperator);
        assertEquals(operatorToUpdate, updatedOperator);
        verify(operatorRepository, times(1)).save(operatorToUpdate);
    }

    @Test
    void deleteOperator() {
        Long operatorId = 1L;

        operatorService.deleteOperator(operatorId);

        verify(operatorRepository, times(1)).deleteById(operatorId);
    }
}
