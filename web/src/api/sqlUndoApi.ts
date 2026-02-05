import apiClient from "../services/apiClient";
import type Query from "../models/query";
import type Script from "../models/script";

export const revertScript = async (script: Script): Promise<Query[]> => {
    const response = await apiClient.post("/sql-undo", script);
    return response.data;
};
