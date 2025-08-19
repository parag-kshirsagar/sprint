package com.tcs.ilp.catering_mgmt.config;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tcs.ilp.catering_mgmt.entity.Menu;
import com.tcs.ilp.catering_mgmt.enums.MenuCategory;
import com.tcs.ilp.catering_mgmt.enums.MenuStatus;
import com.tcs.ilp.catering_mgmt.enums.MenuType;
import com.tcs.ilp.catering_mgmt.repository.MenuRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public void run(String... args) throws Exception {
        if (menuRepository.count() == 0) {
            initializeMenuData();
        }
    }

    private void initializeMenuData() {
        List<Menu> menuItems = Arrays.asList(
                new Menu("Buffet101", "Starter - Fresh fruit platter, mini idlis with chutney; Main Course - Masala dosa, upma, poha; Dessert - Kesari halwa", new BigDecimal("180.00"), MenuType.VEG, MenuCategory.BREAKFAST),
                new Menu("Buffet102", "Starter - Tomato soup, hara bhara kebab; Main Course - Paneer butter masala, dal tadka, jeera rice, chapati", new BigDecimal("250.00"), MenuType.VEG, MenuCategory.LUNCH),
                new Menu("Buffet103", "Starter - Sweet corn soup, veg spring rolls; Main Course - Palak paneer, veg biryani, naan; Dessert - Rasmalai", new BigDecimal("280.00"), MenuType.VEG, MenuCategory.DINNER),
                new Menu("Buffet201", "Starter - Chicken sausages, boiled eggs; Main Course - Chicken dosa, egg bhurji, buttered toast; Dessert - Fruit custard", new BigDecimal("220.00"), MenuType.NON_VEG, MenuCategory.BREAKFAST),
                new Menu("Buffet202", "Starter - Chicken soup, fish fingers; Main Course - Butter chicken, mutton curry, steamed rice, naan; Dessert - Phirni", new BigDecimal("350.00"), MenuType.NON_VEG, MenuCategory.LUNCH),
                new Menu("Buffet203", "Starter - Chicken tikka, hot & sour soup; Main Course - Chicken biryani, egg curry, butter naan; Dessert - Gajar ka halwa", new BigDecimal("380.00"), MenuType.NON_VEG, MenuCategory.DINNER)       
        );

        for (Menu menu : menuItems) {
            Menu savedMenu = menuRepository.save(menu);
            savedMenu.setMenuId("MENU_" + savedMenu.getId());
            menuRepository.save(savedMenu);
        }

        List<Menu> allMenus = menuRepository.findAll();
        
        if (allMenus.size() > 5) {
            for (int i = 5; i < allMenus.size(); i += 6) {
                Menu menu = allMenus.get(i);
                menu.setStatus(MenuStatus.UNAVAILABLE);
                menuRepository.save(menu);
            }
        }

        System.out.println("Database initialized with " + menuRepository.count() + " menu items");

    }
}
