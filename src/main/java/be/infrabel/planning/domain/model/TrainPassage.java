package be.infrabel.planning.domain.model;

public class TrainPassage {

    private final Long id;
    private int delayMinutes = 0;
    private PassageStatus status = PassageStatus.SCHEDULED;

    public TrainPassage(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Train passage ID must be positive"
            );
        }

        this.id = id;
    }

    public void reportDelay(int delayMinutes) {
        if (status == PassageStatus.COMPLETED) {
            throw new IllegalStateException(
                    "Cannot modify a completed passage"
            );
        }
        if (delayMinutes < 0) {
            throw new IllegalArgumentException(
                    "Delay cannot be negative"
            );
        }

        this.delayMinutes = delayMinutes;

        if (delayMinutes > 0) {
            this.status = PassageStatus.DELAYED;
        }
    }


    public int getDelayMinutes() {
        return delayMinutes;
    }

    public PassageStatus getStatus(){
        return status;
    }


    public void complete() {
        this.status = PassageStatus.COMPLETED;
    }

    public Long getId() {
        return id;
    }

}


