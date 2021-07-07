package de.dlh.lhind.exercise.roommgmt.service;

import de.dlh.lhind.exercise.roommgmt.model.Building;
import de.dlh.lhind.exercise.roommgmt.repository.BuildingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BuildingServiceTest {

    private static final Long BUILDING_ID = 1L;
    public static final String BUILDING_NUMBER = "001";
    public static final String BUILDING_DESCRIPTION_STRING = "Zentrale";
    public static final Boolean PUBLIC_ACCESS = true;

    @Mock
    private BuildingRepository buildingRepository;
    private BuildingService buildingService;

    @BeforeEach
    void setUp() {
        buildingService = new BuildingService(buildingRepository);
    }

    @Test
    @DisplayName("Add new building")
    void shouldAdd_whenAddBuilding_givenValidBuilding() {
        // given
        Building building = new Building();
        building.setId(BUILDING_ID);
        building.setBuildingNumber(BUILDING_NUMBER);
        building.setDescription(BUILDING_DESCRIPTION_STRING);
        building.setPublicAccess(PUBLIC_ACCESS);

        // when
        buildingService.addBuilding(building);

        // then
        ArgumentCaptor<Building> buildingArgumentCaptor = ArgumentCaptor.forClass(Building.class);
        verify(buildingRepository).save(buildingArgumentCaptor.capture());
        Building capturedBuilding = buildingArgumentCaptor.getValue();
        assertThat(capturedBuilding).isEqualTo(building);
    }

    @Test
    @DisplayName("Get all buildings")
    void shouldFindAll_whenGetAllBuildings() {
        // when
        buildingService.getAllBuildings();

        // then
        verify(buildingRepository).findAll();
    }

}