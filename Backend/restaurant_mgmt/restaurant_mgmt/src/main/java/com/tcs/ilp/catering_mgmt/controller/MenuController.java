package com.tcs.ilp.catering_mgmt.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.ilp.catering_mgmt.dto.MenuDTO;
import com.tcs.ilp.catering_mgmt.enums.MenuStatus;
import com.tcs.ilp.catering_mgmt.service.MenuService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/menus")
@CrossOrigin(origins = "*")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<?> createMenu(@Valid @RequestBody MenuDTO menuDTO) {
        try {
            MenuDTO createdMenu = menuService.createMenu(menuDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "success", true,
                    "message", "The menu has been added successfully and the Menu Id is " + createdMenu.getMenuId(),
                    "data", createdMenu
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "success", false,
                    "message", "Failed to create menu: " + e.getMessage()
            ));
        }
    }

    @GetMapping
    public ResponseEntity<List<MenuDTO>> getAllMenus() {
        List<MenuDTO> menus = menuService.getAllMenus();
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMenuById(@PathVariable Long id) {
        Optional<MenuDTO> menu = menuService.getMenuById(id);
        if (menu.isPresent()) {
            return ResponseEntity.ok(menu.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "success", false,
                    "message", "Menu not found with id: " + id
            ));
        }
    }

    @GetMapping("/menu-id/{menuId}")
    public ResponseEntity<?> getMenuByMenuId(@PathVariable String menuId) {
        Optional<MenuDTO> menu = menuService.getMenuByMenuId(menuId);
        if (menu.isPresent()) {
            return ResponseEntity.ok(menu.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "success", false,
                    "message", "Menu not found with menu id: " + menuId
            ));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMenu(@PathVariable Long id, @Valid @RequestBody MenuDTO menuDTO) {
        try {
            MenuDTO updatedMenu = menuService.updateMenu(id, menuDTO);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Menu updated successfully",
                    "data", updatedMenu
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "success", false,
                    "message", "Failed to update menu: " + e.getMessage()
            ));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable Long id) {
        try {
            menuService.deleteMenu(id);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Menu deleted successfully"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "success", false,
                    "message", "Failed to delete menu: " + e.getMessage()
            ));
        }
    }

    @GetMapping("/veg")
    public ResponseEntity<List<MenuDTO>> getAvailableVegMenus() {
        List<MenuDTO> vegMenus = menuService.getAvailableVegMenus();
        return ResponseEntity.ok(vegMenus);
    }

    @GetMapping("/non-veg")
    public ResponseEntity<List<MenuDTO>> getAvailableNonVegMenus() {
        List<MenuDTO> nonVegMenus = menuService.getAvailableNonVegMenus();
        return ResponseEntity.ok(nonVegMenus);
    }


    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateMenuStatus(@PathVariable Long id, @RequestParam MenuStatus status) {
        try {
            MenuDTO updatedMenu = menuService.updateMenuStatus(id, status);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Menu status updated successfully",
                    "data", updatedMenu
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "success", false,
                    "message", "Failed to update menu status: " + e.getMessage()
            ));
        }
    }



}
