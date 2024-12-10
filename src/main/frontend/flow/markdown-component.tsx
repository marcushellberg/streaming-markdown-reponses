import React, {ReactElement, useEffect, useState} from 'react';
import ReactMarkdown from 'react-markdown';
import { ReactAdapterElement, RenderHooks } from 'Frontend/generated/flow/ReactAdapter';

class MarkdownElement extends ReactAdapterElement {
    protected override render(hooks: RenderHooks) {
        const [internalContent, setInternalContent] = useState<string>('');
        const [content, setContent] = hooks.useState<string>('content');
        const [appendSegment, setAppendSegment] = hooks.useState<string>('appendSegment');

        // Whenever content changes, update the internal content
        useEffect(() => {
            setInternalContent(content);
        }, [content, setInternalContent]);

        // Whenever internalContent changes, update the content
        useEffect(() => {
            setContent(internalContent);
        }, [internalContent, setContent]);

        // Whenever appendSegment changes, merge it into the main content and clear it
        useEffect(() => {
            if (appendSegment && appendSegment.length > 0) {
                setInternalContent(prev  => prev + appendSegment);
                setAppendSegment("");
            }
        }, [appendSegment]);

        return <ReactMarkdown>{content}</ReactMarkdown>;
    }
}

customElements.define('markdown-component', MarkdownElement);