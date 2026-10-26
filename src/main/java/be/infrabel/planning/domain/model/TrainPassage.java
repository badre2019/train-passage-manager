package be.infrabel.planning.domain.model;

public class TrainPassage {

    private int delayMinutes = 0;

    public void reportDelay(int delayMinutes) {
        if(delayMinutes < 0) {
            throw new IllegalArgumentException(
                    "Delay cannot be negative"
            );
        }

        this.delayMinutes = delayMinutes;
    }


    public int getDelayMinutes() {
        return delayMinutes;
    }
}


