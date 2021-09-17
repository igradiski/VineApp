import React from "react";
import {useSelector} from 'react-redux'
import { BrowserRouter, Redirect, Switch } from "react-router-dom";
import ProtectedRoutes from "./route/protectedRoute";
import Login from './components/forms/LoginForm/Login';

import 'antd/dist/antd.less';
import '../src/App.css';
import { useAppSelector } from "./hooks";
import PublicRoutes from "./route/publicRoute";
import Homepage from "./views/Homepage";
import MainForm from "./components/forms/MainForm/MainForm";


const App = () => {
  const userLogged = useAppSelector(state => state.login.isAuthentificated)
  return (
    <div className="App">
      <BrowserRouter basename="DOZ">
        <Switch>
          <PublicRoutes
            component={Homepage}
            path="/homepage"
            isAuthenticated={userLogged}
          />
          <PublicRoutes
            component={Login}
            path="/login"
            isAuthenticated={userLogged}
          />
          <PublicRoutes
            component={Login}
            path="/register"
            isAuthenticated={userLogged}
          />
          <ProtectedRoutes
            component={MainForm}
            path="/main"
            isAuthenticated={userLogged}
          />
          <Redirect from="/" to="/homepage" />
        </Switch>
      </BrowserRouter>
    </div>
  );
}
export default App;
