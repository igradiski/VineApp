import { createAsyncThunk, createSlice, PayloadAction } from "@reduxjs/toolkit";
import { authUser, registerUser } from "../../api/UserService";

export const userSlice = createSlice({
    name : 'user',
    initialState :{
      accessToken:null,
      reissueToken:null,
    },
    reducers:{
      updateJwt: (state,{payload}:PayloadAction<ILoginState>) =>{
        return {...state,...payload}
      },
      clearJwt: () =>{
        return {accessToken:null,reissueToken: null}
      }
    }
  })

export const signUp = createAsyncThunk('user/signUp',async(args :IUserData,{dispatch}) =>{
    try{
        await registerUser(args);
        
    }catch (error:any){
      if(error.response.status === 409){
        alert("Korisnik vec postoji");
      }
      throw error;
    }
})
export const authenticate = createAsyncThunk('user/authenticate',async (args:IUserData, {dispatch})=>{
    try{
      const data = await authUser(args);
      dispatch(updateJwt(data));
      console.log(data);
    }catch(error:any){
      console.log(error)
      if(error.response.status === 403){
        alert("Pogrešno korisnicko ime ili lozinka");
      }
    }
})

export const {updateJwt,clearJwt} = userSlice.actions;

export default userSlice.reducer;