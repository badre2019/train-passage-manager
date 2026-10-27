package be.infrabel.planning.application.port.in;

public interface ReportTrainDelayUseCase {
    void reportTrainDelay(Long passageId, int delayMinutes);
}
