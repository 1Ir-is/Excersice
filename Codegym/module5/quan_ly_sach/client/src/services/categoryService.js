import axios from "axios";

const API_URL = "http://localhost:8080/api/v1/categories";

export async function getAllCategories() {
  const response = await axios.get(API_URL);
  return response.data;
}

export async function getCategoryById(id) {
  const response = await axios.get(`${API_URL}/${id}`);
  return response.data;
}

export async function createCategory(category) {
  const response = await axios.post(API_URL, category);
  return response.data;
}

export async function updateCategory(id, category) {
  const response = await axios.put(`${API_URL}/${id}`, category);
  return response.data;
}

export async function deleteCategory(id) {
  const response = await axios.delete(`${API_URL}/${id}`);
  return response.data;
}
