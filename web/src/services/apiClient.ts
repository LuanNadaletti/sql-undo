import axios, {type AxiosInstance} from "axios";

const baseURL = "http://localhost:8080";

const apiClient: AxiosInstance = axios.create({
    baseURL
});

export default apiClient;
