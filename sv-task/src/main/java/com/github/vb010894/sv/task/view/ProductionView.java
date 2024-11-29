package com.github.vb010894.sv.task.view;

import com.github.vb010894.sv.task.domain.Plant;
import com.github.vb010894.sv.task.domain.ProductPrice;
import com.github.vb010894.sv.task.domain.ProductionEntity;
import com.github.vb010894.sv.task.services.PlantService;
import com.github.vb010894.sv.task.services.PriceService;
import com.github.vb010894.sv.task.services.ProductionService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServletRequest;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.ServletException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.security.cert.X509Certificate;
import java.util.List;

/// # Страница продуктов\
/// **url = http://localhost:8082/product-service/all**
@Route("all")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@PermitAll
public class ProductionView extends VerticalLayout {

    ApplicationContext context;

    PlantService plantService;

    PriceService priceService;

    ProductionService productionService;

    ComboBox<Plant> plants = new ComboBox<>();
    Button addPlant = new Button(VaadinIcon.PLUS_CIRCLE_O.create());
    Grid<ProductionEntity> productions = new Grid<>(ProductionEntity.class, false);
    Button addProduction = new Button(VaadinIcon.PLUS_CIRCLE_O.create());
    Button addPrice = new Button(VaadinIcon.MONEY.create());

    @PostConstruct
    public void init() {
        this.setSizeFull();
        HorizontalLayout plantWrapper = new HorizontalLayout();
        plantWrapper.setWidthFull();
        HorizontalLayout space = new HorizontalLayout();
        space.setWidthFull();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Text username;
        if(authentication.getPrincipal() instanceof User u) {
            username = new Text(u.getUsername());
        } else {
            username = new Text(authentication.getPrincipal().toString());
        }
        Button logoutButton = new Button(VaadinIcon.SIGN_OUT.create());
        logoutButton.addClickListener(e -> {
            try {
                VaadinServletRequest.getCurrent().logout();
            } catch (ServletException ex) {
                throw new RuntimeException(ex);
            }
        });
        plantWrapper.add(plants, addPlant, space, new HorizontalLayout(username, logoutButton));
        productions.setSizeFull();
        productions.addColumn(ProductionEntity::getName).setHeader("Наименование");
        productions.addColumn(this.getPriceRender()).setHeader("Цены");
        HorizontalLayout gridTools = new HorizontalLayout(addProduction, addPrice);
        gridTools.setWidthFull();
        this.add(plantWrapper, gridTools, productions);
        addPlant.addClickListener(event -> this.context.getBean(PlantAddForm.class).open());
        addProduction.addClickListener(event -> this.context.getBean(ProductAddForm.class, this.plants.getValue()).open());
        addPrice.addClickListener(event -> this.context.getBean(AddPriceForm.class, this.productions.getSelectedItems().toArray()[0]).open());
        plants.setItems(plantService.getAllPlants());
        this.plants.addValueChangeListener(event -> {
            this.productions.setItems(productionService.getAll(event.getValue()));
        });
    }

    private ComponentRenderer<Div, ProductionEntity> getPriceRender() {
        return new ComponentRenderer<>(Div::new, (t, e) -> {
            List<ProductPrice> pricesByProduct = this.priceService.getPricesByProduct(e);
            t.setText(pricesByProduct.toString());
        });
    }
}
