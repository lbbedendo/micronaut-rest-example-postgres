package diecast.collector.api.controller;

import diecast.collector.api.api.AutomakerApi;
import diecast.collector.api.api.ModelApi;
import diecast.collector.api.domain.Automaker;
import diecast.collector.api.domain.Model;
import diecast.collector.api.dto.AutomakerSaveRequest;
import diecast.collector.api.dto.ModelSaveRequest;
import diecast.collector.api.enums.ModelScale;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@MicronautTest
public class ModelControllerTest {
    @Inject
    @Client("/model")
    ModelApi client;
    @Inject
    @Client("/automaker")
    AutomakerApi automakerClient;

    @Test
    public void createModel_ok_whenBodyIsValid() {
        var automaker = createAutomaker("Porsche", "Germany");
        var request = new ModelSaveRequest();
        request.setName("911 GT3");
        request.setModelYear(2018);
        request.setAutomakerId(automaker.getId());
        request.setScale(ModelScale.SCALE_1_64);
        var response = client.create(request);
        assertThat(response.code()).isEqualTo(HttpStatus.CREATED.getCode());
    }

    @Test
    public void createModel_Exception_whenNameIsNull() {
        var request = new ModelSaveRequest();
        request.setModelYear(2018);
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> client.create(request))
                .withMessage("create.request.name: must not be empty");
    }

    @Test
    public void createModel_Exception_whenNameIsBlank() {
        var request = new ModelSaveRequest();
        request.setName("");
        request.setModelYear(2018);
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> client.create(request))
                .withMessage("create.request.name: must not be empty");
    }

    @Test
    public void updateModel_ok_whenBodyIsValid() {
        var automaker = createAutomaker("BMW", "Germany");
        var model = createModel("M240i", 2018, automaker);
        var request = new ModelSaveRequest();
        request.setName("M2 Competition");
        request.setModelYear(2019);
        request.setScale(ModelScale.SCALE_1_64);
        request.setAutomakerId(automaker.getId());
        var response = client.update(model.getId(), request);
        assertThat(response.code()).isEqualTo(HttpStatus.OK.getCode());
        var body = response.body();
        assertThat(body).isNotNull();
        assertThat(body.getId()).isEqualTo(model.getId());
        assertThat(body.getName()).isEqualTo("M2 Competition");
        assertThat(body.getScale()).isEqualTo(ModelScale.SCALE_1_64);
        assertThat(body.getAutomaker()).isNotNull();
    }

    @Test
    public void updateModel_notFound_whenModelDoesNotExists() {
        var request = new ModelSaveRequest();
        request.setName("Quattro");
        request.setModelYear(1986);
        request.setScale(ModelScale.SCALE_1_64);
        var response = client.update(999, request);
        assertThat(response.code()).isEqualTo(HttpStatus.NOT_FOUND.getCode());
    }

    @Test
    public void delete_ok_whenModelExists() {
        var automaker = createAutomaker("Chevrolet", "United States");
        var model = createModel("Vectra", 2006, automaker);
        var response = client.delete(model.getId());
        assertThat(response.code()).isEqualTo(HttpStatus.OK.getCode());
    }

    @Test
    public void delete_notFound_whenModelDoesNotExists() {
        var response = client.delete(999);
        assertThat(response.code()).isEqualTo(HttpStatus.NOT_FOUND.getCode());
    }

    @Test
    public void getById_ok_whenModelExists() {
        var automaker = createAutomaker("McLaren", "England");
        var model = createModel("Senna", 2019, automaker);
        var response = client.getById(model.getId());
        assertThat(response.code()).isEqualTo(HttpStatus.OK.getCode());
    }

    @Test
    public void getById_notFound_whenModelDoesNotExists() {
        var response = client.getById(999);
        assertThat(response.code()).isEqualTo(HttpStatus.NOT_FOUND.getCode());
    }

    private Automaker createAutomaker(String name, String country) {
        var request = new AutomakerSaveRequest(name, country);
        var response = automakerClient.create(request);
        return response.body();
    }

    private Model createModel(String name,
                              Integer modelYear,
                              Automaker automaker) {
        var request = new ModelSaveRequest();
        request.setName(name);
        request.setModelYear(modelYear);
        request.setAutomakerId(automaker.getId());
        var response = client.create(request);
        return response.body();
    }
}
