package com.tcs.ilp.catering_mgmt.service;

import java.util.List;
import java.util.Optional;

import com.tcs.ilp.catering_mgmt.dto.MenuDTO;
import com.tcs.ilp.catering_mgmt.enums.MenuStatus;

public interface MenuService {

    MenuDTO createMenu(MenuDTO menuDTO);

    List<MenuDTO> getAllMenus();

    Optional<MenuDTO> getMenuById(Long id);

    Optional<MenuDTO> getMenuByMenuId(String menuId);

    MenuDTO updateMenu(Long id, MenuDTO menuDTO);

    void deleteMenu(Long id);

    List<MenuDTO> getAvailableVegMenus();

    List<MenuDTO> getAvailableNonVegMenus();

    MenuDTO updateMenuStatus(Long id, MenuStatus status);

    boolean existsByMenuName(String name);

    class MenuStatistics {

        private long totalMenus;
        private long availableMenus;
        private long vegMenus;
        private long nonVegMenus;
        private long breakfastMenus;
        private long lunchMenus;
        private long dinnerMenus;
        private long snacksMenus;
        private long beveragesMenus;

        public MenuStatistics() {
        }

        public MenuStatistics(long totalMenus, long availableMenus, long vegMenus, long nonVegMenus,
                long breakfastMenus, long lunchMenus, long dinnerMenus) {
            this.totalMenus = totalMenus;
            this.availableMenus = availableMenus;
            this.vegMenus = vegMenus;
            this.nonVegMenus = nonVegMenus;
            this.breakfastMenus = breakfastMenus;
            this.lunchMenus = lunchMenus;
            this.dinnerMenus = dinnerMenus;
        }

        public long getTotalMenus() {
            return totalMenus;
        }

        public void setTotalMenus(long totalMenus) {
            this.totalMenus = totalMenus;
        }

        public long getAvailableMenus() {
            return availableMenus;
        }

        public void setAvailableMenus(long availableMenus) {
            this.availableMenus = availableMenus;
        }

        public long getVegMenus() {
            return vegMenus;
        }

        public void setVegMenus(long vegMenus) {
            this.vegMenus = vegMenus;
        }

        public long getNonVegMenus() {
            return nonVegMenus;
        }

        public void setNonVegMenus(long nonVegMenus) {
            this.nonVegMenus = nonVegMenus;
        }

        public long getBreakfastMenus() {
            return breakfastMenus;
        }

        public void setBreakfastMenus(long breakfastMenus) {
            this.breakfastMenus = breakfastMenus;
        }

        public long getLunchMenus() {
            return lunchMenus;
        }

        public void setLunchMenus(long lunchMenus) {
            this.lunchMenus = lunchMenus;
        }

        public long getDinnerMenus() {
            return dinnerMenus;
        }

        public void setDinnerMenus(long dinnerMenus) {
            this.dinnerMenus = dinnerMenus;
        }

        public long getSnacksMenus() {
            return snacksMenus;
        }

        public void setSnacksMenus(long snacksMenus) {
            this.snacksMenus = snacksMenus;
        }

        public long getBeveragesMenus() {
            return beveragesMenus;
        }

        public void setBeveragesMenus(long beveragesMenus) {
            this.beveragesMenus = beveragesMenus;
        }
    }
}

