package com.tcs.ilp.catering_mgmt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.ilp.catering_mgmt.dto.MenuDTO;
import com.tcs.ilp.catering_mgmt.entity.Menu;
import com.tcs.ilp.catering_mgmt.enums.MenuStatus;
import com.tcs.ilp.catering_mgmt.repository.MenuRepository;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public MenuDTO createMenu(MenuDTO menuDTO) {
        if (existsByMenuName(menuDTO.getMenuName())) {
            throw new RuntimeException("Menu with name '" + menuDTO.getMenuName() + "' already exists");
        }

        Menu menu = convertToEntity(menuDTO);
        Menu savedMenu = menuRepository.save(menu);

        // Generate menu ID after saving
        savedMenu.setMenuId("MENU_" + savedMenu.getId());
        savedMenu = menuRepository.save(savedMenu);

        return convertToDTO(savedMenu);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuDTO> getAllMenus() {
        return menuRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MenuDTO> getMenuById(Long id) {
        return menuRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MenuDTO> getMenuByMenuId(String menuId) {
        return menuRepository.findByMenuId(menuId)
                .map(this::convertToDTO);
    }

    @Override
    public MenuDTO updateMenu(Long id, MenuDTO menuDTO) {
        Menu existingMenu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + id));

        if (!existingMenu.getMenuName().equalsIgnoreCase(menuDTO.getMenuName())) {
            if (existsByMenuName(menuDTO.getMenuName())) {
                throw new RuntimeException("Menu with name '" + menuDTO.getMenuName() + "' already exists");
            }
        }

        existingMenu.setMenuName(menuDTO.getMenuName());
        existingMenu.setDescription(menuDTO.getDescription());
        existingMenu.setPrice(menuDTO.getPrice());
        existingMenu.setMenuType(menuDTO.getMenuType());
        existingMenu.setCategory(menuDTO.getCategory());
        if (menuDTO.getStatus() != null) {
            existingMenu.setStatus(menuDTO.getStatus());
        }

        Menu updatedMenu = menuRepository.save(existingMenu);
        return convertToDTO(updatedMenu);
    }

    @Override
    public void deleteMenu(Long id) {
        if (!menuRepository.existsById(id)) {
            throw new RuntimeException("Menu not found with id: " + id);
        }
        menuRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuDTO> getAvailableVegMenus() {
        return menuRepository.findAllAvailableVegMenus().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuDTO> getAvailableNonVegMenus() {
        return menuRepository.findAllAvailableNonVegMenus().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MenuDTO updateMenuStatus(Long id, MenuStatus status) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + id));

        menu.setStatus(status);
        Menu updatedMenu = menuRepository.save(menu);
        return convertToDTO(updatedMenu);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByMenuName(String name) {
        return menuRepository.existsByMenuNameIgnoreCase(name);
    }

    private MenuDTO convertToDTO(Menu menu) {
        MenuDTO dto = new MenuDTO();
        dto.setId(menu.getId());
        dto.setMenuId(menu.getMenuId());
        dto.setMenuName(menu.getMenuName());
        dto.setDescription(menu.getDescription());
        dto.setPrice(menu.getPrice());
        dto.setMenuType(menu.getMenuType());
        dto.setCategory(menu.getCategory());
        dto.setStatus(menu.getStatus());
        dto.setCreatedAt(menu.getCreatedAt());
        dto.setUpdatedAt(menu.getUpdatedAt());
        return dto;
    }

    private Menu convertToEntity(MenuDTO dto) {
        Menu menu = new Menu();
        menu.setMenuName(dto.getMenuName());
        menu.setDescription(dto.getDescription());
        menu.setPrice(dto.getPrice());
        menu.setMenuType(dto.getMenuType());
        menu.setCategory(dto.getCategory());
        menu.setStatus(dto.getStatus() != null ? dto.getStatus() : MenuStatus.AVAILABLE);
        return menu;
    }
}
