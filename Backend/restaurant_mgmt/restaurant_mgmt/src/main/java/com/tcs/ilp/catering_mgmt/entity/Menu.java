package com.tcs.ilp.catering_mgmt.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.tcs.ilp.catering_mgmt.enums.MenuCategory;
import com.tcs.ilp.catering_mgmt.enums.MenuStatus;
import com.tcs.ilp.catering_mgmt.enums.MenuType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Menu_2689422")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menuseq_1234")
    private Long id;

    @Column(name = "menu_id", unique = true, nullable = false)
    private String menuId;

    @NotBlank(message = "Menu name is required")
    @Size(min = 2, max = 100, message = "Menu name must be between 2 and 100 characters")
    @Column(name = "menu_name", nullable = false)
    private String menuName;

    @Column(name = "description", length = 500)
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @NotNull(message = "Menu type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "menu_type", nullable = false)
    private MenuType menuType;

    @NotNull(message = "Menu category is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "menu_category", nullable = false)
    private MenuCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MenuStatus status = MenuStatus.AVAILABLE;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public Menu() {
    }

    public Menu(String menuName, String description, BigDecimal price, MenuType menuType, MenuCategory category) {
        this.menuName = menuName;
        this.description = description;
        this.price = price;
        this.menuType = menuType;
        this.category = category;
        this.status = MenuStatus.AVAILABLE;
    }

    // Lifecycle callbacks
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.id != null) {
            this.menuId = "MENU_" + this.id;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @PostLoad
    @PostPersist
    protected void postLoad() {
        if (this.menuId == null && this.id != null) {
            this.menuId = "MENU_" + this.id;
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        if (id != null) {
            this.menuId = "MENU_" + id;
        }
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
        return "Menu{"
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
