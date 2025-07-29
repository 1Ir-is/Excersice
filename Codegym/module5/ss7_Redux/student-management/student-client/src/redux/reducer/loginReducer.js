const initialState = {
  user: null,
  error: null,
};

export const loginReducer = (state = initialState, action) => {
  switch (action.type) {
    case "LOGIN":
      return { ...state, user: action.payload, error: null };
    case "FAIL":
      return { ...state, user: null, error: "Sai tài khoản hoặc mật khẩu!" };
    case "LOGOUT":
      return { ...state, user: null, error: null };
    default:
      return state;
  }
};
