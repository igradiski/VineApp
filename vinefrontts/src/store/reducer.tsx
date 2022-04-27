import { combineReducers } from "redux";
import { fenofazaReducer } from "./slices/fenofazaSlice";

export const rootReducer = combineReducers({
  fenofaze: fenofazaReducer,
});

export type RootState = ReturnType<typeof rootReducer>;
