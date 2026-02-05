interface DatabaseSelectProps {
    label: string;
    value?: string;
    onChange?: (value: string) => void;
}

const DATABASES = [
    { label: "Firebird", value: "firebird" }
];

export default function DatabaseSelect({ label, value, onChange }: DatabaseSelectProps) {
    const handleChange = (e: Event) => {
        const v = (e.target as HTMLSelectElement).value;
        onChange?.(v);
    };

    return (
        <div className="flex flex-col gap-2">
            <label className="text-sm font-medium">{label}</label>

            <select
                className="bg-gray-800 border border-gray-700 rounded-md px-3 py-2"
                onChange={handleChange}
                {...(value !== undefined ? { value } : {})}
            >
                {DATABASES.map((db) => (
                    <option key={db.value} value={db.value}>
                        {db.label}
                    </option>
                ))}
            </select>
        </div>
    );
}
