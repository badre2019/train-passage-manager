package be.infrabel.planning.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TrainPassageTest {

    @Test
    void shouldRejectNegativeDelay() {

        TrainPassage passage = new TrainPassage();
        assertThatThrownBy(() ->
                passage.reportDelay(-5)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldAcceptPositiveDelay() {
        TrainPassage passage = new TrainPassage();
        assertThatCode(() ->
                passage.reportDelay(5)).doesNotThrowAnyException();
    }

    @Test
    void shouldStoreReportedDelay() {
        TrainPassage passage = new TrainPassage();

        passage.reportDelay(5);
        assertThat(passage.getDelayMinutes()).isEqualTo(5);
    }

    @Test
    void shouldUpdateDelayWhenNewDelayIsReported() {
        TrainPassage passage = new TrainPassage();

        passage.reportDelay(5);
        passage.reportDelay(10);

        assertThat(passage.getDelayMinutes()).isEqualTo(10);
    }

}