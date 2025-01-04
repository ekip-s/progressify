import { configureStore } from "@reduxjs/toolkit";
import pageSlice from "./page-slice.js";
import authSlice from "./auth-slice.js";

const store = configureStore({
  reducer: {
    page: pageSlice.reducer,
    auth: authSlice.reducer,
  },
});

export default store;
