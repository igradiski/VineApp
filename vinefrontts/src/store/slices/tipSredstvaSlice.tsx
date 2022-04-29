import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  addTipSredstva,
  deleteTipSredstvaId,
  getTipSredstvaByNamePaged,
  getTipSredstvaPaged,
  updateTipSredstva,
} from "../../axios/tipSredstva";
import IDefaultPagingData from "../../types/IDefaultPagingData";
import ITipSredstvaData from "../../types/ITipSredstvaData";

const initialState = {
  tableData: [],
  totalItems: 0,
};

export const insertNewTipSredstva = createAsyncThunk(
  "tipSredstva/insertTipSredstva",
  async (data: ITipSredstvaData) => {
    return addTipSredstva(data);
  }
);

export const updateExistingTipSredstva = createAsyncThunk(
  "tipSredstva/insertTipSredstva",
  async (data: ITipSredstvaData) => {
    return updateTipSredstva(data);
  }
);
export const fetchDataForTipSredstvaTablePaged = createAsyncThunk(
  "tipSredstva/fetchAll",
  async (data: IDefaultPagingData) => {
    return getTipSredstvaPaged(data);
  }
);

export const deleteTipSredstvaById = createAsyncThunk(
  "tipSredstva/deleteById",
  async (id: number) => {
    await deleteTipSredstvaId(id);
  }
);

export const tipSredstvaByName = createAsyncThunk(
  "tipSredstva/byName",
  async ({ name, data }: any) => {
    return getTipSredstvaByNamePaged(data, name);
  }
);

const tipSredstvaSlice = createSlice({
  name: "tipSredstva",
  initialState: initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(tipSredstvaByName.fulfilled, (state, action) => {
      return {
        ...state,
        tableData: action.payload.content,
        totalItems: action.payload.totalElements,
      };
    });
    builder.addCase(
      fetchDataForTipSredstvaTablePaged.fulfilled,
      (state, action) => {
        return {
          ...state,
          tableData: action.payload.content,
          totalItems: action.payload.totalElements,
        };
      }
    );
  },
});

const { reducer } = tipSredstvaSlice;
export { reducer as tipSredstvaReducer };
