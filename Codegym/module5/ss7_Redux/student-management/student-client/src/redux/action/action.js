import { checkLogin, registerAccount } from "../../services/authService";

export const loginAction = (account) => ({
  type: "LOGIN",
  payload: account,
});

export const loginActionAsync = (accountLogin) => {
  return async (dispatch) => {
    let account = await checkLogin(accountLogin);
    if (account) {
      dispatch({
        type: "LOGIN",
        payload: account,
      });
      return true;
    } else {
      dispatch({
        type: "FAIL",
        payload: null,
      });
      return false;
    }
  };
};

export const registerActionAsync = (accountRegister) => {
  return async (dispatch) => {
    let account = await registerAccount(accountRegister);
    if (account) {
      dispatch({
        type: "REGISTER",
        payload: account,
      });
      return true;
    } else {
      dispatch({
        type: "REGISTER_FAIL",
        payload: null,
      });
      return false;
    }
  };
};

export const logoutActionAsync = () => {
  return async (dispatch) => {
    dispatch({
      type: "LOGOUT",
      payload: null,
    });
  };
};
