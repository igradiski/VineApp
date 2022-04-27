import create from "@ant-design/icons/lib/components/IconFont";
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
  async ({ id, data }: any, { dispatch }) => {
    deleteFenofazaId(id);
    await dispatch(fetchDataForFenofazaTablePaged(data));
  }
);

const initialState = {
  insertedFenofaza: [],
  tableData: [],
  size: 0,
  pageNo: 0,
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
        size: action.payload.pageable.pageNumber,
        pageNo: action.payload.pageable.pageNo,
        totalItems: action.payload.totalElements,
      };
    });
    builder.addCase(
      fetchDataForFenofazaTablePaged.fulfilled,
      (state, action) => {
        return {
          ...state,
          tableData: action.payload.content,
          size: action.payload.pageable.pageNumber,
          pageNo: action.payload.pageable.pageNo,
          totalItems: action.payload.totalElements,
        };
      }
    );
  },
});
const { actions, reducer } = fenofazaSlice;
export const {} = actions;
export { reducer as fenofazaReducer };
