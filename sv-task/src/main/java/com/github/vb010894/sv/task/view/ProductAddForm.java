package com.github.vb010894.sv.task.view;

import com.github.vb010894.sv.task.domain.Plant;
import com.github.vb010894.sv.task.domain.ProductionEntity;
import com.github.vb010894.sv.task.services.ProductionService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextField;
import jakarta.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ProductAddForm extends Dialog {

    @Setter(onMethod_ = @Autowired)
    ProductionService service;

    Plant plant;

    TextField name = new TextField("Name");

    Button save = new Button("Save");
    Button cancel = new Button("Cancel");

    @Autowired(required = false)
    public ProductAddForm(Plant plant) {
        this.plant = plant;
        this.add(name);
        this.getFooter().add(save, cancel);
    }

    @PostConstruct
    public void init() {
        this.save.addClickListener(e -> {
            ProductionEntity entity = new ProductionEntity();
            entity.setPlant(plant);
            entity.setName(name.getValue());
            this.service.save(entity);
            this.close();
        });
        this.cancel.addClickListener(e -> {this.close();});
    }
}
