package be.infrabel.planning.adapter.out.persistence;

import be.infrabel.planning.application.port.out.TrainPassageRepository;
import be.infrabel.planning.domain.model.TrainPassage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryTrainPassageRepository
        implements TrainPassageRepository {

    private final Map<Long, TrainPassage> passages = new HashMap<>();

    @Override
    public void save(TrainPassage passage) {
        passages.put(passage.getId(), passage);
    }

    @Override
    public Optional<TrainPassage> findById(Long passageId) {
        return Optional.ofNullable(passages.get(passageId));
    }
}
