const initialState = {
  success: false,
  error: null,
};

export const registerReducer = (state = initialState, action) => {
  switch (action.type) {
    case "REGISTER":
      return { ...state, success: true, error: null };
    case "REGISTER_FAIL":
      return { ...state, success: false, error: "Tài khoản đã tồn tại!" };
    default:
      return state;
  }
};
