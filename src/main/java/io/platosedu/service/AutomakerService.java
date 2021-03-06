package io.platosedu.service;

import io.platosedu.domain.Automaker;
import io.platosedu.api.dto.AutomakerSaveRequest;
import io.platosedu.api.dto.filters.AutomakerFilters;
import io.platosedu.repository.AutomakerRepository;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Singleton
@Transactional
public class AutomakerService {
    private final AutomakerRepository automakerRepository;

    public AutomakerService(AutomakerRepository automakerRepository) {
        this.automakerRepository = automakerRepository;
    }

    public Optional<Automaker> findById(Long id) {
        return automakerRepository.findById(id);
    }

    public List<Automaker> findAll(AutomakerFilters filters) {
        return automakerRepository.findAll(filters);
    }

    public Automaker create(AutomakerSaveRequest request) {
        var automaker = new Automaker();
        automaker.setName(request.getName());
        automaker.setCountry(request.getCountry());
        return automakerRepository.save(automaker);
    }

    public Automaker update(Automaker automaker, AutomakerSaveRequest request) {
        automaker.setName(request.getName());
        automaker.setCountry(request.getCountry());
        return automakerRepository.update(automaker);
    }

    public void delete(Automaker automaker) {
        automakerRepository.delete(automaker);
    }
}
