import { createSlice } from '@reduxjs/toolkit';

export interface loginState {
    isAuthentificated : boolean;
    myAccessToken: string;
    error : boolean;
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
    },
    logOffUser : (state) => {
      state.isAuthentificated = false;
    },
    isUserLogged : (state) =>{
    }
  }
})
export const {logUser,isUserLogged,logOffUser} = userSlice.actions;

export default userSlice.reducer;
