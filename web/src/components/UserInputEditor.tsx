import {useEffect, useRef} from "preact/hooks";
import {EditorState} from "@codemirror/state";
import {EditorView, keymap, lineNumbers} from "@codemirror/view";
import {defaultKeymap} from "@codemirror/commands";
import {sql} from "@codemirror/lang-sql";
import {autocompletion} from "@codemirror/autocomplete";

interface UserInputEditorProps {
    value: string;
    onChange: (newValue: string) => void;
}

export default function UserInputEditor({value, onChange}: UserInputEditorProps) {
    const editorRef = useRef<HTMLDivElement>(null);
    const viewRef = useRef<EditorView | null>(null);

    useEffect(() => {
        if (!editorRef.current) return;

        const state = EditorState.create({
            doc: value,
            extensions: [
                sql(),
                autocompletion(),
                lineNumbers(),
                keymap.of([...defaultKeymap]),
                EditorView.lineWrapping,
                EditorView.updateListener.of((update) => {
                    if (update.docChanged) {
                        onChange(update.state.doc.toString());
                    }
                }),
                EditorView.theme({
                    "&": {backgroundColor: "#1e1e1e", color: "#d4d4d4"},
                    ".cm-content": {caretColor: "#ffffff"},
                    "&.cm-focused .cm-cursor": {borderLeftColor: "#ffffff"},
                    "&.cm-focused .cm-selectionBackground, ::selection": {
                        backgroundColor: "#264f78"
                    },
                    ".cm-scroller": {
                        overflow: "auto",
                        maxHeight: "384px",
                        scrollbarWidth: "thin"
                    },
                    ".cm-scroller::-webkit-scrollbar": {width: "8px"},
                    ".cm-scroller::-webkit-scrollbar-thumb": {
                        backgroundColor: "#444",
                        borderRadius: "4px"
                    },
                    ".cm-gutters": {
                        backgroundColor: "#1e1e1e",
                        color: "#999999",
                        border: "none"
                    },
                    ".cm-gutterElement": {padding: "0 8px"}
                })
            ]
        });

        viewRef.current = new EditorView({
            state,
            parent: editorRef.current
        });

        return () => {
            viewRef.current?.destroy();
            viewRef.current = null;
        };
    }, []);

    useEffect(() => {
        const editor = viewRef.current;
        if (!editor) return;

        const currentDoc = editor.state.doc.toString();
        if (value !== currentDoc) {
            const transaction = editor.state.update({
                changes: {from: 0, to: currentDoc.length, insert: value}
            });
            editor.dispatch(transaction);
        }
    }, [value]);

    return (
        <div ref={ editorRef } className="h-96 bg-gray-800 border border-gray-700 rounded-md" />
    );
}
