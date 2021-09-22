package de.dlh.lhind.exercise.roommgmt.repository;

import static org.assertj.core.api.Assertions.assertThat;

import de.dlh.lhind.exercise.roommgmt.model.Building;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BuildingRepositoryTest {

    public static final String BUILDING_NUMBER = "001";
    public static final String BUILDING_DESCRIPTION_STRING = "Zentrale";
    public static final Boolean PUBLIC_ACCESS = true;
    private static final Long BUILDING_ID = 1L;
    @Autowired
    private BuildingRepository buildingRepository;

    @AfterEach
    void tearDown() {
        buildingRepository.deleteAll();
    }

    @Test
    public void shouldCheck_whenSelectedBuildingExists_givenValidBuildingNumber() {
        // given
        Building building = new Building();
        building.setId(BUILDING_ID);
        building.setBuildingNumber(BUILDING_NUMBER);
        building.setDescription(BUILDING_DESCRIPTION_STRING);
        building.setPublicAccess(PUBLIC_ACCESS);

        buildingRepository.save(building);

        // when
        boolean expected = buildingRepository.selectExistsBuilding(BUILDING_NUMBER);

        // then
        assertThat(expected).isTrue();
    }

    @Test
    public void shouldCheck_whenSelectedBuildingDoesNotExists_givenInvalidBuildingNumber() {
        // given
        String INVALID_BUILDING_NUMBER = "002";

        // when
        boolean expected = buildingRepository.selectExistsBuilding(INVALID_BUILDING_NUMBER);

        // then
        assertThat(expected).isFalse();
    }

}
