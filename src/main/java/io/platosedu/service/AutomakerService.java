package io.platosedu.service;

import io.platosedu.domain.Automaker;
import io.platosedu.dto.AutomakerSaveRequest;
import io.platosedu.dto.filters.AutomakerFilters;
import io.platosedu.repository.AutomakerRepository;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class AutomakerService {
    private final AutomakerRepository automakerRepository;

    public AutomakerService(AutomakerRepository automakerRepository) {
        this.automakerRepository = automakerRepository;
    }

    public Optional<Automaker> getById(Integer id) {
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
        return automakerRepository.save(automaker);
    }

    public void delete(Automaker automaker) {
        automakerRepository.delete(automaker);
    }
}
