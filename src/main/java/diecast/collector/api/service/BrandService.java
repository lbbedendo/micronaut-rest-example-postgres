package diecast.collector.api.service;

import diecast.collector.api.domain.Brand;
import diecast.collector.api.dto.BrandSaveRequest;
import diecast.collector.api.repository.BrandRepository;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    public Optional<Brand> getById(Integer id) {
        return brandRepository.findById(id);
    }

    public Brand create(BrandSaveRequest request) {
        var brand = new Brand();
        brand.setName(request.getName());
        return brandRepository.save(brand);
    }

    public Brand update(Brand brand, BrandSaveRequest request) {
        brand.setName(request.getName());
        return brandRepository.save(brand);
    }

    public void delete(Brand brand) {
        brandRepository.delete(brand);
    }
}
