import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import UserRoles from '../../enums/UserRoles';
import { IloginState } from '../../types/ILoginState';
import TokenService from './TokenService';


  const tokenService = new TokenService();

  const initialStat : IloginState = {
    isAuthentificated: false,
    myAccessToken: "",
    error: false,
    user: "",
    myUserRole:[],
    myUserServerRole:[],
    myRefreshToken: "",
};

const getRoles = (role:[]) =>{
  
  var roles:number[] = [];

  role.forEach( role =>{
    if(role["authority"] === UserRoles[UserRoles.ROLE_ADMIN].toString()){
      roles.push(UserRoles.ROLE_ADMIN)
    }
    if(role["authority"] === UserRoles[UserRoles.ROLE_MODERATOR]){
      roles.push(UserRoles.ROLE_MODERATOR)
    }
    if(role["authority"] === UserRoles[UserRoles.ROLE_USER]){
      roles.push(UserRoles.ROLE_USER)
    }
  })
  return roles;

} 

export const userSlice = createSlice({
  name : 'login',
  initialState : initialStat,
  reducers:{
    logUser : (state,{ payload }: PayloadAction<IloginState>) =>{
      payload.myUserRole = getRoles(payload.myUserServerRole)
      tokenService.setUserData(payload)
      state = {...state,...payload}
      return state;
    },
    logOffUser : (state) => {
      state.isAuthentificated = false;
      tokenService.removeUser();
    },
    isUserLogged : (state) =>{

    },
  }
})
export const {logUser,isUserLogged,logOffUser} = userSlice.actions;

export default userSlice.reducer;
