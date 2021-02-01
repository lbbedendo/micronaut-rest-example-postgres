package io.platosedu.service;

import io.platosedu.domain.Automaker;
import io.platosedu.dto.AutomakerSaveRequest;
import io.platosedu.dto.filters.AutomakerFilters;
import io.platosedu.repository.AutomakerRepository;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Singleton
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

    @Transactional
    public Automaker create(AutomakerSaveRequest request) {
        var automaker = new Automaker();
        automaker.setName(request.getName());
        automaker.setCountry(request.getCountry());
        return automakerRepository.save(automaker);
    }

    @Transactional
    public Automaker update(Automaker automaker, AutomakerSaveRequest request) {
        automaker.setName(request.getName());
        automaker.setCountry(request.getCountry());
        return automakerRepository.update(automaker);
    }

    @Transactional
    public void delete(Automaker automaker) {
        automakerRepository.delete(automaker);
    }
}
