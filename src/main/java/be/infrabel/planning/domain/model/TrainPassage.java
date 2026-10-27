package be.infrabel.planning.domain.model;

public class TrainPassage {

    private int delayMinutes = 0;
    private PassageStatus status = PassageStatus.SCHEDULED;

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

}


