import {useState} from "preact/hooks";
import {revertScript} from "../../api/sqlUndoApi";
import DatabaseSelect from "../../components/DatabaseSelect";
import SQLHoverEditor from "../../components/SQLHoverEditor";
import UserInputEditor from "../../components/UserInputEditor";
import type Query from "../../models/query";
import type Script from "../../models/script";

export default function Main() {
    const [queries, setQueries] = useState<Query[]>([]);
    const [scriptText, setScriptText] = useState<string>("");
    const [revertError, setRevertError] = useState<string>("");

    const handleRevertScript = async () => {
        try {
            const script: Script = {script: scriptText};
            const result = await revertScript(script);
            setQueries(result);
            setRevertError("");
        } catch (error: any) {
            setRevertError(error?.message || "An unexpected error occurred.");
        }
    };

    return (
        <div className="flex flex-col space-y-6 p-8 bg-gray-900 text-white min-h-screen">
            <h1 className="text-2xl font-bold text-center">SQLUndo</h1>

            <DatabaseSelect label="Database" />

            <div className="flex flex-col lg:flex-row items-center lg:space-x-6 space-y-4 lg:space-y-0">
                <section className="flex-1">
                    <h2 className="text-lg font-semibold mb-2">Enter SQL Script</h2>
                    <UserInputEditor value={ scriptText } onChange={ setScriptText } />
                </section>

                <section className="flex-1">
                    <h2 className="text-lg font-semibold mb-2 ml-4">SQL Output Editor</h2>
                    <SQLHoverEditor queries={ queries } />
                </section>
            </div>

            <div className="flex flex-col lg:flex-row items-center lg:space-x-6 space-y-4 lg:space-y-0 justify-between">
                <p className="font-bold text-red-700">{ revertError }</p>

                <button
                    onClick={ handleRevertScript }
                    className="bg-blue-600 hover:bg-blue-500 text-white font-medium py-2 px-6 rounded transition"
                >
                    Revert Script
                </button>
            </div>
        </div>
    );
}
