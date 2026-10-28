package be.infrabel.planning.adapter.out.persistence;

import be.infrabel.planning.domain.model.TrainPassage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class InMemoryTrainPassageRepositoryTest {
    @Test
    void shouldSaveAndFindTrainPassageById() {
        // Arrange
        InMemoryTrainPassageRepository repository =
                new InMemoryTrainPassageRepository();

        TrainPassage passage = new TrainPassage(1L);

        // Act
        repository.save(passage);

        // Assert
        assertThat(repository.findById(1L))
                .contains(passage);
    }

    @Test
    void shouldReturnEmptyWhenTrainPassageDoesNotExist() {
        // Arrange
        InMemoryTrainPassageRepository repository =
                new InMemoryTrainPassageRepository();

        // Act & Assert
        assertThat(repository.findById(99L)).isEmpty();
    }
}
