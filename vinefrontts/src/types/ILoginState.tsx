export interface IloginState {
    isAuthentificated : boolean;
    myAccessToken: string;
    error : boolean;
    user : String;
    myUserRole : number[];
    myUserServerRole : [];
    myRefreshToken:string;
  }

  