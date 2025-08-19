package com.tcs.ilp.catering_mgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tcs.ilp.catering_mgmt.entity.Menu;
import com.tcs.ilp.catering_mgmt.enums.MenuCategory;
import com.tcs.ilp.catering_mgmt.enums.MenuStatus;
import com.tcs.ilp.catering_mgmt.enums.MenuType;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Optional<Menu> findByMenuId(String menuId);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Menu m WHERE LOWER(m.menuName) = LOWER(:name)")
    boolean existsByMenuNameIgnoreCase(@Param("name") String name);

    @Query("SELECT m FROM Menu m WHERE m.menuType = 'VEG' AND m.status = 'AVAILABLE' ORDER BY m.menuName")
    List<Menu> findAllAvailableVegMenus();

    @Query("SELECT m FROM Menu m WHERE m.menuType = 'NON_VEG' AND m.status = 'AVAILABLE' ORDER BY m.menuName")
    List<Menu> findAllAvailableNonVegMenus();
    
    

    @Query("SELECT m FROM Menu m WHERE "
            + "(:type IS NULL OR m.menuType = :type) AND "
            + "(:category IS NULL OR m.category = :category) AND "
            + "(:status IS NULL OR m.status = :status) "
            + "ORDER BY m.menuName")
    List<Menu> findByFilters(@Param("type") MenuType type,
            @Param("category") MenuCategory category,
            @Param("status") MenuStatus status);

}


