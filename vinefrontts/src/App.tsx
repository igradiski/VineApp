import React from "react";
import { BrowserRouter, Redirect, Switch } from "react-router-dom";
import ProtectedRoutes from "./route/protectedRoute";
import Login from './components/forms/LoginForm/Login';
import 'antd/dist/antd.less';
import '../src/App.css';


const App = () => {
  return (
    <div className="App">
      <BrowserRouter basename="DOZ">
        <Switch>
          <ProtectedRoutes
            component={Login}
            path="/pocetna"
            isAuthenticated={true}
          />
          <Redirect from="/" to="/pocetna" />
        </Switch>
      </BrowserRouter>
    </div>
  );
}
export default App;
