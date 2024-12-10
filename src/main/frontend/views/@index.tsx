import {ViewConfig} from '@vaadin/hilla-file-router/types.js';
import {useSignal} from '@vaadin/hilla-react-signals';
import {Button, TextField} from "@vaadin/react-components";
import Markdown from "react-markdown";
import {AiService} from "Frontend/generated/endpoints";

export const config: ViewConfig = {
  title: 'React Streaming',
  menu: {
    order: 0
  }
};

export default function ReactStreaming() {
  const prompt = useSignal<string>('')
  const markdown = useSignal<string>('')

  function getResponse() {
    AiService.getResponse(prompt.value).onNext(token => {
        markdown.value += token
    });
  }

  return (
    <div className="p-m flex flex-col gap-m">
      <div className="flex gap-m">
        <TextField className="flex-grow" value={prompt.value} onChange={e => prompt.value = e.target.value} />
        <Button theme="primary" onClick={getResponse}>Submit</Button>
      </div>
      <Markdown>{markdown.value}</Markdown>
    </div>
  );
}
