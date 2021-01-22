package diecast.collector.api.controller;

import diecast.collector.api.api.BrandApi;
import diecast.collector.api.dto.BrandSaveRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@MicronautTest
public class BrandControllerTest {
    @Inject
    @Client("/brand")
    BrandApi client;

    @Test
    public void createBrand_created_whenBodyIsValid() {
        var request = new BrandSaveRequest("Hot Wheels");
        var response = client.create(request);
        assertThat(response.code()).isEqualTo(HttpStatus.CREATED.getCode());
        var body = response.body();
        assertThat(body).isNotNull();
        assertThat(body.getId()).isNotNull();
        assertThat(body.getName()).isEqualTo("Hot Wheels");
    }

    @Test
    public void createBrand_Exception_whenNameIsNull() {
        var request = new BrandSaveRequest(null);
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> client.create(request))
                .withMessage("create.request.name: must not be blank");
    }

    @Test
    public void createBrand_Exception_whenNameIsBlank() {
        var request = new BrandSaveRequest("");
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> client.create(request))
                .withMessage("create.request.name: must not be blank");
    }

    @Test
    public void updateBrand_ok_whenBodyIsValid() {
        var responseCreated = client.create(new BrandSaveRequest("Hot Weels"));
        assertThat(responseCreated.code()).isEqualTo(HttpStatus.CREATED.getCode());
        var itemCreated = responseCreated.body();
        assertThat(itemCreated).isNotNull();
        var responseUpdated = client.update(itemCreated.getId(), new BrandSaveRequest("Hot Wheels"));
        assertThat(responseUpdated.code()).isEqualTo(HttpStatus.OK.getCode());
        var itemUpdated = responseUpdated.body();
        assertThat(itemUpdated).isNotNull();
        assertThat(itemUpdated.getName()).isEqualTo("Hot Wheels");
    }

    @Test
    public void updateBrand_notFound_whenBrandDoesNotExists() {
        var request = new BrandSaveRequest("Maisto");
        var response = client.update(999, request);
        assertThat(response.code()).isEqualTo(HttpStatus.NOT_FOUND.getCode());
    }

    @Test
    public void delete_ok_whenBrandExists() {
        var responseCreated = client.create(new BrandSaveRequest("California Collectibles"));
        assertThat(responseCreated.code()).isEqualTo(HttpStatus.CREATED.getCode());
        var itemCreated = responseCreated.body();
        assertThat(itemCreated).isNotNull();
        var responseDeleted = client.delete(itemCreated.getId());
        assertThat(responseDeleted.code()).isEqualTo(HttpStatus.OK.getCode());
    }

    @Test
    public void delete_notFound_whenBrandDoesNotExists() {
        var response = client.delete(999);
        assertThat(response.code()).isEqualTo(HttpStatus.NOT_FOUND.getCode());
    }

    @Test
    public void getById_ok_whenBrandExists() {
        var responseCreated = client.create(new BrandSaveRequest("Hot Wheels"));
        assertThat(responseCreated.code()).isEqualTo(HttpStatus.CREATED.getCode());
        var itemCreated = responseCreated.body();
        assertThat(itemCreated).isNotNull();
        var responseFound = client.getById(itemCreated.getId());
        assertThat(responseFound.code()).isEqualTo(HttpStatus.OK.getCode());
    }

    @Test
    public void getById_notFound_whenBrandDoesNotExists() {
        var response = client.getById(999);
        assertThat(response.code()).isEqualTo(HttpStatus.NOT_FOUND.getCode());
    }
}
