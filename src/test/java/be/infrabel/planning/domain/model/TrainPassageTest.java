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


    @Test
    void shouldStartWithScheduledStatus() {
        TrainPassage passage = new TrainPassage();

        assertThat(passage.getStatus())
                .isEqualTo(PassageStatus.SCHEDULED);
    }


    @Test
    void shouldBecomeDelayedWhenPositiveDelayIsReported() {
        TrainPassage passage = new TrainPassage();

        passage.reportDelay(5);

        assertThat(passage.getStatus())
                .isEqualTo(PassageStatus.DELAYED);
    }


    @Test
    void shouldNotAllowDelayChangesWhenPassageIsCompleted() {
        TrainPassage passage = new TrainPassage();

        passage.complete();

        assertThatThrownBy(() -> passage.reportDelay(5))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Cannot modify a completed passage");
    }


    @Test
    void shouldPreserveStateWhenDelayChangeIsRejected() {
        TrainPassage passage = new TrainPassage();
        passage.reportDelay(5);
        passage.complete();

        assertThatThrownBy(() -> passage.reportDelay(10))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Cannot modify a completed passage");

        assertThat(passage.getDelayMinutes()).isEqualTo(5);
        assertThat(passage.getStatus())
                .isEqualTo(PassageStatus.COMPLETED);
    }


    @Test
    void shouldRemainScheduledWhenDelayIsZero() {
        TrainPassage passage = new TrainPassage();

        passage.reportDelay(0);

        assertThat(passage.getDelayMinutes()).isZero();
        assertThat(passage.getStatus())
                .isEqualTo(PassageStatus.SCHEDULED);
    }



}