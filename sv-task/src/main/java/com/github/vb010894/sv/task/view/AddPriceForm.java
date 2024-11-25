package com.github.vb010894.sv.task.view;

import com.github.vb010894.sv.task.domain.ProductPrice;
import com.github.vb010894.sv.task.domain.ProductionEntity;
import com.github.vb010894.sv.task.services.PriceService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.NumberField;
import jakarta.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class AddPriceForm extends Dialog {

    @Setter(onMethod_ = @Autowired)
    PriceService service;

    ProductionEntity production;

    NumberField price = new NumberField("Price");
    DatePicker from = new DatePicker("C");
    DatePicker to = new DatePicker("По");
    Button save = new Button("Save");
    Button cancel = new Button("Cancel");

    @Autowired(required = false)
    public AddPriceForm(ProductionEntity production) {
        this.production = production;
        this.add(price, from, to);
        this.getFooter().add(save, cancel);
    }

    @PostConstruct
    public void init() {
        this.cancel.addClickListener(e -> this.close());
        this.save.addClickListener(e -> {
            ProductPrice entity = new ProductPrice();
            entity.setProductionEntity(production);
            entity.setPrice(price.getValue().floatValue());
            entity.setValidFrom(from.getValue());
            entity.setValidTo(to.getValue());
            this.service.savePrice(entity);
            this.close();
        });
    }


}
