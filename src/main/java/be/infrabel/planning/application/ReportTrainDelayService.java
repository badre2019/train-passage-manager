package be.infrabel.planning.application;

import be.infrabel.planning.application.port.in.ReportTrainDelayUseCase;
import be.infrabel.planning.application.port.out.TrainPassageRepository;
import be.infrabel.planning.domain.model.TrainPassage;

public class ReportTrainDelayService implements ReportTrainDelayUseCase {

    private final TrainPassageRepository repository;

    public ReportTrainDelayService(TrainPassageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void reportTrainDelay(Long passageId, int delayMinutes) {
        TrainPassage passage = repository.findById(passageId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Train passage not found: " + passageId
                        ));

        passage.reportDelay(delayMinutes);
    }
}
