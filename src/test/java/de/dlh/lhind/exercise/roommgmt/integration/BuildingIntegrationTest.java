package de.dlh.lhind.exercise.roommgmt.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.dlh.lhind.exercise.roommgmt.model.Building;
import de.dlh.lhind.exercise.roommgmt.repository.BuildingRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class BuildingIntegrationTest {

    public static final String BUILDING_NUMBER = "002";
    public static final String BUILDING_DESCRIPTION_STRING = "Test";
    public static final Boolean PUBLIC_ACCESS = true;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BuildingRepository buildingRepository;


    @Test
    void canCreateNewBuilding() throws Exception {
        // given
        Building building = new Building(BUILDING_NUMBER, BUILDING_DESCRIPTION_STRING,
            PUBLIC_ACCESS);

        // when
        ResultActions resultActions = mockMvc.perform(post("/buildings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(building)));

        // then
        resultActions.andExpect(status().isCreated());
        List<Building> buildings = buildingRepository.findAll();
        assertThat(buildings)
            .usingElementComparatorIgnoringFields("id")
            .contains(building);
    }

}
