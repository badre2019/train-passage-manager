package be.infrabel.planning.application.port.out;

import be.infrabel.planning.domain.model.TrainPassage;

import java.util.Optional;

public interface TrainPassageRepository {

    Optional<TrainPassage> findById(Long passageId);
    void save(TrainPassage passage);

}
