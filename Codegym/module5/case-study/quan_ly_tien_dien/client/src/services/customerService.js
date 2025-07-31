import axios from "axios";

const API_URL = "http://localhost:3001/customers";

export async function getAllCustomers() {
  const response = await axios.get(API_URL);
  return response.data;
}

export async function addCustomer(customer) {
  const response = await axios.post(API_URL, customer);
  return response.data;
}
