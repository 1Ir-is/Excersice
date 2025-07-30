import axios from "axios";

const API_URL = "http://localhost:3001/categories";

export async function getAllCategories() {
  const response = await axios.get(API_URL);
  return response.data;
}
