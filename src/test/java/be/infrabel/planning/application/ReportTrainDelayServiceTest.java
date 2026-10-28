package be.infrabel.planning.application;

import be.infrabel.planning.application.port.out.TrainPassageRepository;
import be.infrabel.planning.domain.model.PassageStatus;
import be.infrabel.planning.domain.model.TrainPassage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class ReportTrainDelayServiceTest {

    private ReportTrainDelayService service;

    private final TrainPassageRepository repository =
            mock(TrainPassageRepository.class);

    @BeforeEach
    void setUp() {
        service = new ReportTrainDelayService(repository);
    }

    @Test
    void shouldReportDelayForExistingPassage() {
        // Arrange:
        TrainPassage passage = new TrainPassage(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(passage));
        // Act:
        service.reportTrainDelay(1L, 5);

        // Assert:
        assertThat(passage.getDelayMinutes()).isEqualTo(5);
        assertThat(passage.getStatus()).isEqualTo(PassageStatus.DELAYED);
    }


    @Test
    void shouldThrowExceptionWhenPassageDoesNotExist() {
        // Arrange
        when(repository.findById(99L))
                .thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> service.reportTrainDelay(99L, 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Train passage not found: 99");

        verify(repository).findById(99L);
    }

}
