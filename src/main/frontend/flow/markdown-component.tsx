import React, {useEffect, useState} from 'react';
import ReactMarkdown from 'react-markdown';
import {ReactAdapterElement} from 'Frontend/generated/flow/ReactAdapter';
import {effect, signal, useSignal} from "@vaadin/hilla-react-signals";

class MarkdownElement extends ReactAdapterElement {

    markdown = signal('');

    async connectedCallback() {
        await super.connectedCallback();
        effect(() => console.log(this.markdown.value));
    }

    protected override render() {
        // In a React component, we could use the signal directly,
        // but it's not possible in a Web Component
        const [content, setContent] = useState('');
        useEffect(() => effect(() => {
                setContent(this.markdown.value);
        }), []);
        return <ReactMarkdown>{content}</ReactMarkdown>;
    }
}

customElements.define('markdown-component', MarkdownElement);