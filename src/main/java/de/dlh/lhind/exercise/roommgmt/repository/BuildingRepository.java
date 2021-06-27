package de.dlh.lhind.exercise.roommgmt.repository;

import de.dlh.lhind.exercise.roommgmt.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

    @Query("FROM Building b WHERE b.buildingNumber = (:buildingNumber)")
    Optional<Building> getBuildingById(@Param("buildingNumber") String buildingNumber);

    @Query("FROM Building b WHERE b.publicAccess = TRUE")
    List<Building> getPublicBuildings();

    void deleteBuildingById(Long id);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Building b " +
            "WHERE b.buildingNumber = ?1")
    Boolean selectExistsBuilding(String buildingNumber);

}
