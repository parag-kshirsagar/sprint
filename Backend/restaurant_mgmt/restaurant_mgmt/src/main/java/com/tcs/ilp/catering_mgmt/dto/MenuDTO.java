package com.tcs.ilp.catering_mgmt.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.tcs.ilp.catering_mgmt.enums.MenuCategory;
import com.tcs.ilp.catering_mgmt.enums.MenuStatus;
import com.tcs.ilp.catering_mgmt.enums.MenuType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MenuDTO {

    private Long id;
    private String menuId;

    @NotBlank(message = "Menu name is required")
    @Size(min = 2, max = 100, message = "Menu name must be between 2 and 100 characters")
    private String menuName;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Menu type is required")
    private MenuType menuType;

    @NotNull(message = "Menu category is required")
    private MenuCategory category;

    private MenuStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public MenuDTO() {
    }

    public MenuDTO(String menuName, String description, BigDecimal price, MenuType menuType, MenuCategory category) {
        this.menuName = menuName;
        this.description = description;
        this.price = price;
        this.menuType = menuType;
        this.category = category;
        this.status = MenuStatus.AVAILABLE;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuType menuType) {
        this.menuType = menuType;
    }

    public MenuCategory getCategory() {
        return category;
    }

    public void setCategory(MenuCategory category) {
        this.category = category;
    }

    public MenuStatus getStatus() {
        return status;
    }

    public void setStatus(MenuStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "MenuDTO{"
                + "id=" + id
                + ", menuId='" + menuId + '\''
                + ", menuName='" + menuName + '\''
                + ", description='" + description + '\''
                + ", price=" + price
                + ", menuType=" + menuType
                + ", category=" + category
                + ", status=" + status
                + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt
                + '}';
    }
}
