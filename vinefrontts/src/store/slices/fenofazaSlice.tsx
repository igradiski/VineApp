import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  addFenofaza,
  getFenofazePaged,
  deleteFenofazaId,
  getFenofazeByNamePaged,
  updateFenofaza,
} from "../../axios/fenofaza";
import IDefaultPagingData from "../../types/IDefaultPagingData";
import IFenofazaData from "../../types/IFenofazaData";

export const insertNewFenofaza = createAsyncThunk(
  "fenofaze/insertFenofaza",
  async (data: IFenofazaData) => {
    return addFenofaza(data);
  }
);
export const updateExistingFenofaza = createAsyncThunk(
  "fenofaze/insertFenofaza",
  async (data: IFenofazaData) => {
    return updateFenofaza(data);
  }
);

export const fetchDataForFenofazaTablePaged = createAsyncThunk(
  "fenofaze/fetchAll",
  async (data: IDefaultPagingData) => {
    return getFenofazePaged(data);
  }
);

export const fetchFenofazeByName = createAsyncThunk(
  "fenofaze/byName",
  async ({ name, data }: any) => {
    return getFenofazeByNamePaged(data, name);
  }
);

export const deleteFenofazaById = createAsyncThunk(
  "fenofaze/deleteById",
  async (id: number) => {
    await deleteFenofazaId(id);
  }
);

const initialState = {
  insertedFenofaza: [],
  tableData: [],
  totalItems: 0,
  status: false,
};

const fenofazaSlice = createSlice({
  name: "fenofaze",
  initialState: initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(fetchFenofazeByName.fulfilled, (state, action) => {
      return {
        ...state,
        tableData: action.payload.content,
        totalItems: action.payload.totalElements,
      };
    });
    builder.addCase(
      fetchDataForFenofazaTablePaged.fulfilled,
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
const { reducer } = fenofazaSlice;
//export const {} = actions;
export { reducer as fenofazaReducer };
