package com.github.vb010894.sv.task.view.login;

import com.github.vb010894.sv.task.view.ProductionView;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.SneakyThrows;

@Route("login")
@AnonymousAllowed
public class LoginPage extends VerticalLayout {

    LoginForm loginForm = new LoginForm();

    public LoginPage() {
        this.setSizeFull();
        this.setJustifyContentMode(JustifyContentMode.CENTER);
        this.setAlignItems(Alignment.CENTER);
        this.add(loginForm);
        this.loginForm.addLoginListener(this::onUserLogin);
    }

    @SneakyThrows
    private void onUserLogin(AbstractLogin.LoginEvent event) {
        VaadinServletRequest.getCurrent().login(event.getUsername(), event.getPassword());
        VaadinServletRequest.getCurrent().changeSessionId();
        this.getUI().ifPresent(ui -> ui.navigate(ProductionView.class));
    }
}
