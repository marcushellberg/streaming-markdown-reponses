package com.example.application.views;

import com.example.application.components.Markdown;
import com.example.application.services.AiService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.Duration;

@PageTitle("Java Streaming")
@Menu(title = "Java Streaming")
@Route("java")
public class JavaStreaming extends VerticalLayout {

    public JavaStreaming(AiService ai) {
        var ui = UI.getCurrent();
        var promptInput = new TextField();
        var submitButton = new Button("Submit");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        var outputMarkdown = new Markdown();

        submitButton.addClickListener(e -> {
            outputMarkdown.clear();

            ai.getCompletion(promptInput.getValue())
                // Buffer tokens for 200ms or until we have 10 tokens
                // The synchronization breaks down if we push stuff too fast
                .bufferTimeout(10, Duration.ofMillis(200))
                .subscribe(ui.accessLater(bufferedTokens -> {
                    // Combine buffered tokens into a single string update
                    String combined = String.join("", bufferedTokens);
                    outputMarkdown.appendMarkdown(combined);
                }, null));
        });

        var form = new HorizontalLayout(promptInput, submitButton);
        form.setWidthFull();
        form.expand(promptInput);

        add(form, outputMarkdown);
    }

}
