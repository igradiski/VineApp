import { createAsyncThunk, createSlice, PayloadAction } from '@reduxjs/toolkit';
import { RootState, AppThunk } from '../../store';

export interface loginState {
    isAuthentificated : Boolean;
    myAccessToken: string;
    error : Boolean;
    user : String;
    myUserRole : [];
    myRefreshToken:string;
  }
  
  const initialStat : loginState = {
    isAuthentificated: false,
    myAccessToken: "",
    error: false,
    user: "",
    myUserRole: [],
    myRefreshToken: "",
};

export const userSlice = createSlice({
  name : 'login',
  initialState : initialStat,
  reducers:{
    logUser : (state) =>{
      state.isAuthentificated = true;
      console.log("User logged: "+state.isAuthentificated);
    },
    logOffUser : (state) => {
      state.isAuthentificated = false;
    },
    isUserLogged : (state) =>{
      console.log("Korisnik logiran:"+state.isAuthentificated)
    }
  }
})

export const {logUser,isUserLogged} = userSlice.actions;

export default userSlice.reducer;
