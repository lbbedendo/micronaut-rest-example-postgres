package io.platosedu.service;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.platosedu.domain.Brand;
import io.platosedu.dto.BrandSaveRequest;
import io.platosedu.repository.BrandRepository;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.Optional;

@Singleton
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Page<Brand> findAll(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }

    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }

    @Transactional
    public Brand create(BrandSaveRequest request) {
        var brand = new Brand();
        brand.setName(request.getName());
        return brandRepository.save(brand);
    }

    @Transactional
    public Brand update(Brand brand, BrandSaveRequest request) {
        brand.setName(request.getName());
        return brandRepository.update(brand);
    }

    @Transactional
    public void delete(Brand brand) {
        brandRepository.delete(brand);
    }
}
