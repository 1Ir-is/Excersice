import axios from "axios";

const API_URL = "http://localhost:3001/users";

export const checkLogin = async ({ username, password }) => {
  const response = await axios.get(
    `${API_URL}?username=${username}&password=${password}`
  );
  return response.data.length > 0 ? response.data[0] : null;
};

export const registerAccount = async ({ username, password }) => {
  const response = await axios.get(`${API_URL}?username=${username}`);
  if (response.data.length > 0) {
    return null;
  }
  const newUser = await axios.post(API_URL, { username, password });
  return newUser.data;
};
