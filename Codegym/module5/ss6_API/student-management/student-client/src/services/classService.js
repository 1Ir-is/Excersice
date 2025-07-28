import axios from "axios";

const CLASS_API = "http://localhost:3001/classes";

export async function getAllClasses() {
  const response = await axios.get(CLASS_API);
  return response.data;
}
