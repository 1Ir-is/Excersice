import { combineReducers } from "redux";
import { loginReducer } from "./loginReducer";
import { registerReducer } from "./registerReducer";

export const reducers = combineReducers({
  login: loginReducer,
  register: registerReducer,
});
