import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  activePage: null,
};

const pageSlice = createSlice({
  name: "page",
  initialState,
  reducers: {
    setActivePage(state, actions) {
      state.activePage = actions.payload;
    },
    clearPage(state) {
      state.activePage = null;
    }
  },
});

export const pageActions = pageSlice.actions;
export default pageSlice;
