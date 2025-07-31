import axios from "axios";

const API_URL = "http://localhost:3001/invoices";

export async function getAll() {
  const response = await axios.get(API_URL);
  return response.data;
}

export async function getById(id) {
  const response = await axios.get(`${API_URL}/${id}`);
  return response.data;
}

export async function addNew(invoice) {
  const response = await axios.post(API_URL, invoice);
  return response.data;
}

export async function updateById(id, newInvoice) {
  const response = await axios.put(`${API_URL}/${id}`, newInvoice);
  return response.data;
}

export async function deleteById(id) {
  await axios.delete(`${API_URL}/${id}`);
}
