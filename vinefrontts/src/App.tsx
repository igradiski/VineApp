import React from "react";
import { BrowserRouter, Redirect, Switch } from "react-router-dom";
import ProtectedRoutes from "./route/protectedRoute";
import HomePage from "./components/forms/homePage";
import 'antd/dist/antd.less';


const App = () => {
  return (
    <div className="App">
      <BrowserRouter basename="DOZ">
        <Switch>
          <ProtectedRoutes
            component={HomePage}
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
