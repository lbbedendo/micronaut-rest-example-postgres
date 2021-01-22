package diecast.collector.api.repository;

import diecast.collector.api.domain.Automaker;
import diecast.collector.api.domain.Collection;
import diecast.collector.api.domain.Model;
import diecast.collector.api.dto.response.ModelQuantityByAutomakerResponse;
import diecast.collector.api.dto.response.ModelQuantityByCollectionResponse;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.inject.Inject;
import java.util.List;

import static diecast.collector.api.enums.ModelScale.SCALE_1_64;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@MicronautTest
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class ModelRepositoryTest {
    @Inject
    ModelRepository modelRepository;
    @Inject
    AutomakerRepository automakerRepository;
    @Inject
    CollectionRepository collectionRepository;

    @BeforeAll
    public void setUp() {
        populateData();
    }

    private void populateData() {
        var honda = automakerRepository.save(new Automaker("Honda", "Japan"));
        var datsun = automakerRepository.save(new Automaker("Datsun", "Japan"));
        var porsche = automakerRepository.save(new Automaker("Porsche", "Germany"));
        var factoryFresh = collectionRepository.save(new Collection("Factory Fresh"));
        var wanganMidnight = collectionRepository.save(new Collection("Wangan Midnight"));
        modelRepository.saveAll(List.of(
                new Model("Civic Type R", 2016, SCALE_1_64, null, honda, factoryFresh, null),
                new Model("CRX", 1988, SCALE_1_64, null, honda, factoryFresh, null)));
        modelRepository.saveAll(List.of(
                new Model("240z", 1970, SCALE_1_64, null, datsun, wanganMidnight, null),
                new Model("510", 1974, SCALE_1_64, null, datsun, null, null)));
        modelRepository.save(new Model("911", 2018, SCALE_1_64, null, porsche, factoryFresh, null));
        modelRepository.save(new Model("SP2", null, SCALE_1_64, null, null, null, null));
    }

    @Test
    void loadModelQuantityByAutomakers() {
        var response = modelRepository.loadModelQuantityByAutomakers();
        assertThat(response)
                .isNotEmpty()
                .extracting(
                        ModelQuantityByAutomakerResponse::getAutomakerName,
                        ModelQuantityByAutomakerResponse::getQuantity)
                .containsExactlyInAnyOrder(
                        tuple(null, 1),
                        tuple("Honda", 2),
                        tuple("Datsun", 2),
                        tuple("Porsche", 1));
    }

    @Test
    void loadModelQuantityByCollection() {
        var response = modelRepository.loadModelQuantityByCollection();
        assertThat(response)
                .isNotEmpty()
                .extracting(
                        ModelQuantityByCollectionResponse::getCollectionName,
                        ModelQuantityByCollectionResponse::getQuantity)
                .containsExactlyInAnyOrder(
                        tuple(null, 2),
                        tuple("Factory Fresh", 3),
                        tuple("Wangan Midnight", 1));
    }
}