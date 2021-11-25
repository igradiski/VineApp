import { IloginState } from "../../types/ILoginState";

class TokenService {

    getLocalRefreshToken() {
        const refreshToken = localStorage.getItem('refreshToken');
        console.log("refToken: "+refreshToken)
        return refreshToken;
      }
    
      getLocalAccessToken() {
        const token = localStorage.getItem("token");
        return token;
      }
    
      updateLocalAccessToken(token:String) {
        localStorage.setItem("token", JSON.stringify(token));
      }
    
      getUser() {
        return JSON.parse(localStorage.getItem("user")|| '');
      }
    
      setUserData(payload:IloginState) {
        localStorage.setItem("user",JSON.stringify(payload.user));
        localStorage.setItem("token",JSON.stringify(payload.myAccessToken));
        localStorage.setItem("refreshToken",JSON.stringify(payload.myRefreshToken));
      }
    
      removeUser() {
        localStorage.clear();
      }

}

export default TokenService;