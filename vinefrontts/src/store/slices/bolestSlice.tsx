import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  addBolest,
  deleteBolestId,
  getBolestByNamePaged,
  getBolestCard,
  getBolestiPaged,
  updateBolest,
} from "../../axios/bolest";
import IBolestdata from "../../types/IBolestData";
import IDefaultPagingData from "../../types/IDefaultPagingData";

export const insertNewBolest = createAsyncThunk(
  "bolest/insertBolest",
  async (data: IBolestdata) => {
    return addBolest(data);
  }
);

export const fetchDataForBolestiPaged = createAsyncThunk(
  "bolest/fetchAll",
  async (data: IDefaultPagingData) => {
    return getBolestiPaged(data);
  }
);

export const fetchBolestCardData = createAsyncThunk(
  "bolest/fetchCard",
  async (id: string) => {
    return getBolestCard(id);
  }
);

export const fetchBolestByName = createAsyncThunk(
  "bolest/byName",
  async ({ name, data }: any) => {
    return getBolestByNamePaged(data, name);
  }
);

export const updateExistingBolest = createAsyncThunk(
  "bolest/updateBolest",
  async (data: IBolestdata) => {
    return updateBolest(data);
  }
);

export const deleteBolestById = createAsyncThunk(
  "bolest/deleteById",
  async (id: string) => {
    await deleteBolestId(id);
  }
);

const initialState = {
  tableData: [],
  totalItems: 0,
  title: "",
  image: "",
  content: "",
};

const bolestSlice = createSlice({
  name: "bolest",
  initialState: initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(fetchDataForBolestiPaged.fulfilled, (state, action) => {
      return {
        ...state,
        tableData: action.payload.content,
        totalItems: action.payload.totalElements,
      };
    });
    builder.addCase(fetchBolestCardData.fulfilled, (state, action) => {
      return {
        ...state,
        loading: false,
        title: action.payload.name,
        image: action.payload.base64,
        content: action.payload.description,
      };
    });
    builder.addCase(fetchBolestByName.fulfilled, (state, action) => {
      return {
        ...state,
        tableData: action.payload.content,
        totalItems: action.payload.totalElements,
      };
    });
  },
});

const { reducer } = bolestSlice;
//export const {} = actions;
export { reducer as bolestReducer };
