import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  authenticated: false,
  token: null,
  clientId: null,
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    authInit(state, actions) {
      state.authenticated = actions.payload;
    },
    addData(state, actions) {
      state.token = actions.payload.token;
      state.clientId = actions.payload.clientId;
    },
  },
});

export const authActions = authSlice.actions;
export default authSlice;
