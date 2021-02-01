package io.platosedu.controller;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.platosedu.api.BrandApi;
import io.platosedu.domain.Brand;
import io.platosedu.api.dto.BrandSaveRequest;
import io.platosedu.service.BrandService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@Controller("/brand")
public class BrandController implements BrandApi {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public HttpResponse<Brand> findById(Long id) {
        return brandService.findById(id)
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }

    @Override
    public Page<Brand> findAll(Pageable pageable) {
        return brandService.findAll(pageable);
    }

    @Override
    public HttpResponse<Brand> create(@Body @Valid BrandSaveRequest request) {
        var brand = brandService.create(request);
        return HttpResponse.created(brand, location(brand.getId()));
    }

    @Override
    @Transactional
    public HttpResponse<Brand> update(Long id, @Body @Valid BrandSaveRequest request) {
        return brandService.findById(id)
                .map(brand -> HttpResponse.ok(brandService.update(brand, request)).headers(headers -> headers.location(location(id))))
                .orElseGet(HttpResponse::notFound);
    }

    @Override
    @Transactional
    public HttpResponse<Brand> delete(Long id) {
        return brandService.findById(id)
                .map(brand -> {
                    brandService.delete(brand);
                    return HttpResponse.ok(brand);
                })
                .orElseGet(HttpResponse::notFound);
    }

    private URI location(Long id) {
        return URI.create("/brand/" + id);
    }
}
