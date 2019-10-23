import axios from "axios";

const api = axios.create({
  baseURL: "https://economundi-api.herokuapp.com/economundi"
  //baseURL: "http://localhost:5000/economundi/"
});

export default api;
