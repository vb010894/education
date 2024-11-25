package com.github.vb010894.sv.task.view;

import com.github.vb010894.sv.task.domain.Plant;
import com.github.vb010894.sv.task.services.PlantService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PlantAddForm extends Dialog {

    PlantService service;

    TextField name = new TextField("Plant name");

    Button save = new Button("Save");
    Button cancel = new Button("Cancel");

    @Autowired
    public PlantAddForm(PlantService service) {
        this.service = service;
        this.add(name);
        this.getFooter().add(save, cancel);

        cancel.addClickListener(e -> close());
        save.addClickListener(e -> {
            Plant plant = new Plant();
            plant.setName(name.getValue());
            this.service.save(plant);
            this.close();
        });

    }
}
