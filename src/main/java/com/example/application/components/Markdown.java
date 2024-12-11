package com.example.application.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.react.ReactAdapterComponent;

@NpmPackage(value = "react-markdown", version = "9.0.1")
@JsModule("./flow/markdown-component.tsx")
@Tag("markdown-component")
public class Markdown extends ReactAdapterComponent {

    private String markdown = "";

    public Markdown() {
    }

    public Markdown(String markdown) {
        setMarkdown(markdown);
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
        getElement().executeJs("this.markdown = $0", markdown);
    }

    public void appendMarkdown(String additionalMarkdown) {
        this.markdown += additionalMarkdown;
        getElement().executeJs("this.markdown += $0", additionalMarkdown);
    }

    public void clear() {
        setMarkdown("");
    }

    public String getMarkdown() {
        return markdown;
    }
}