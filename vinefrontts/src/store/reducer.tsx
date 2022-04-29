import { combineReducers } from "redux";
import { bolestReducer } from "./slices/bolestSlice";
import { fenofazaReducer } from "./slices/fenofazaSlice";
import { tipSredstvaReducer } from "./slices/tipSredstvaSlice";

export const rootReducer = combineReducers({
  fenofaze: fenofazaReducer,
  bolest: bolestReducer,
  tipSredstva: tipSredstvaReducer,
});

export type RootState = ReturnType<typeof rootReducer>;
