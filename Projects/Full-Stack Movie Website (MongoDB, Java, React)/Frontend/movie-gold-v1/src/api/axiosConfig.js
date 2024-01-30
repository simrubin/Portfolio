import axios from "axios";

export default axios.create({
    baseURL: "https://69c7-106-71-74-237.ngrok-free.app",
    headers: {"ngrok-skip-browser-warning": "true"}
});