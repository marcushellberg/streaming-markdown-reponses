import React from 'react';
import {ReactAdapterElement, RenderHooks} from 'Frontend/generated/flow/ReactAdapter';
import Markdown from "react-markdown";

class MarkdownElement extends ReactAdapterElement {

    protected override render(hooks: RenderHooks) {
        const [markdown] = hooks.useState('markdown', '')

        return <Markdown>{markdown}</Markdown>;
    }
}

customElements.define('markdown-component', MarkdownElement);