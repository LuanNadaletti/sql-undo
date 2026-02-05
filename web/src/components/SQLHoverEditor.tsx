import { useEffect, useRef, useState } from "preact/hooks";
import { EditorState } from "@codemirror/state";
import { EditorView, lineNumbers, Decoration } from "@codemirror/view";
import { sql } from "@codemirror/lang-sql";
import type Query from "../models/query";

interface SQLHoverEditorProps {
    queries: Query[];
}

type TooltipState = { text: string; x: number; y: number } | null;

export default function SQLHoverEditor({ queries }: SQLHoverEditorProps) {
    const editorHostRef = useRef<HTMLDivElement>(null);
    const viewRef = useRef<EditorView | null>(null);

    const [tooltip, setTooltip] = useState<TooltipState>(null);

    useEffect(() => {
        const host = editorHostRef.current;

        if (!host || queries.length === 0) {
            viewRef.current?.destroy();
            viewRef.current = null;
            setTooltip(null);
            return;
        }

        const revertedScript = queries.map((q) => q.reverted).join("\n");

        const ranges: { from: number; to: number; original: string }[] = [];
        let searchFrom = 0;

        for (const q of queries) {
            const idx = revertedScript.indexOf(q.reverted, searchFrom);
            if (idx >= 0) {
                ranges.push({ from: idx, to: idx + q.reverted.length, original: q.original });
                searchFrom = idx + q.reverted.length;
            }
        }

        const decorations = Decoration.set(
            ranges.map((r, index) =>
                Decoration.mark({
                    attributes: {
                        class: `query-${index} cm-decoration`,
                        "data-original": r.original
                    }
                }).range(r.from, r.to)
            )
        );

        viewRef.current?.destroy();

        viewRef.current = new EditorView({
            state: EditorState.create({
                doc: revertedScript,
                extensions: [
                    sql(),
                    lineNumbers(),
                    EditorView.lineWrapping,
                    EditorView.theme({
                        "&": {
                            color: "white",
                            backgroundColor: "#202124"
                        },
                        ".cm-content": { caretColor: "#0e9" },
                        "&.cm-focused .cm-cursor": { borderLeftColor: "#0e9" },
                        "&.cm-focused .cm-selectionBackground, ::selection": {
                            backgroundColor: "#555"
                        },
                        "& .cm-decoration:hover": {
                            backgroundColor: "rgba(14, 156, 255, 0.2)"
                        },
                        ".cm-scroller": {
                            overflow: "auto",
                            maxHeight: "384px",
                            scrollbarWidth: "thin"
                        },
                        ".cm-scroller::-webkit-scrollbar": { width: "8px" },
                        ".cm-scroller::-webkit-scrollbar-thumb": {
                            backgroundColor: "#444",
                            borderRadius: "4px"
                        },
                        ".cm-gutters": {
                            backgroundColor: "#1e1e1e",
                            color: "#999999",
                            border: "none"
                        },
                        ".cm-gutterElement": { padding: "0 8px" }
                    }),
                    EditorView.decorations.of(decorations),
                    EditorView.domEventHandlers({
                        mouseover: (event) => {
                            const target = event.target as HTMLElement;
                            const originalQuery = target.getAttribute("data-original");
                            if (!originalQuery || !host) return;

                            const rect = target.getBoundingClientRect();
                            const hostRect = host.getBoundingClientRect();

                            setTooltip({
                                text: originalQuery,
                                x: rect.left - hostRect.left,
                                y: rect.top - hostRect.top - 20
                            });
                        },
                        mouseout: () => setTooltip(null)
                    })
                ]
            }),
            parent: host
        });

        return () => {
            viewRef.current?.destroy();
            viewRef.current = null;
        };
    }, [queries]);

    return (
        <div className="relative w-full h-full bg-gray-900 text-gray-100 pl-4">
            <div ref={editorHostRef} className="h-96 border border-gray-700 rounded-md" />

            {tooltip && (
                <div
                    style={{
                        position: "absolute",
                        top: tooltip.y,
                        left: tooltip.x,
                        backgroundColor: "rgba(0, 0, 0, 0.5)",
                        color: "white",
                        padding: "8px",
                        borderRadius: "4px",
                        zIndex: 1000,
                        fontSize: "12px",
                        maxWidth: "100%",
                        wordWrap: "break-word"
                    }}
                >
                    {tooltip.text}
                </div>
            )}
        </div>
    );
}
