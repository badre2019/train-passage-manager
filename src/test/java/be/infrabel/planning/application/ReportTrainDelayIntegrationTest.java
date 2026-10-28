
package be.infrabel.planning.application;

import be.infrabel.planning.adapter.out.persistence.InMemoryTrainPassageRepository;
import be.infrabel.planning.domain.model.PassageStatus;
import be.infrabel.planning.domain.model.TrainPassage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReportTrainDelayIntegrationTest {

    @Test
    void shouldReportDelayUsingRealInMemoryRepository() {
        // Arrange
        InMemoryTrainPassageRepository repository =
                new InMemoryTrainPassageRepository();

        TrainPassage passage = new TrainPassage(1L);
        repository.save(passage);

        ReportTrainDelayService service =
                new ReportTrainDelayService(repository);

        // Act
        service.reportTrainDelay(1L, 5);

        // Assert
        TrainPassage savedPassage = repository.findById(1L)
                .orElseThrow();

        assertThat(savedPassage.getDelayMinutes()).isEqualTo(5);
        assertThat(savedPassage.getStatus())
                .isEqualTo(PassageStatus.DELAYED);
    }
}

