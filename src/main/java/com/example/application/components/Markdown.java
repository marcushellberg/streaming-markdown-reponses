package com.example.application.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.react.ReactAdapterComponent;

@NpmPackage(value = "react-markdown", version = "9.0.1")
@JsModule("./flow/markdown-component.tsx")
@Tag("markdown-component")
public class Markdown extends ReactAdapterComponent {

    public Markdown() {
        setState("content", "");
        setState("appendSegment", "");
    }

    public Markdown(String markdown) {
        this();
        setMarkdown(markdown);
    }

    /**
     * Set the full markdown content, replacing any existing content.
     */
    public void setMarkdown(String markdown) {
        setState("content", markdown);
        // Clear appendSegment to avoid unintended merges
        setState("appendSegment", "");
    }

    /**
     * Append markdown content to the existing content without
     * sending the entire content again.
     */
    public void appendMarkdown(String additionalMarkdown) {
        setState("appendSegment", additionalMarkdown);
    }

    public void clear() {
        setMarkdown("");
    }
}