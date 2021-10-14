
import { BrowserRouter, Switch } from "react-router-dom";
import PrivateRoute from "./route/protectedRoute";
import Login from './components/forms/LoginForm/Login';
import RegisterForm from "./components/forms/RegisterForm/RegisterForm";
import 'antd/dist/antd.less';
import '../src/App.css';
import PublicRoutes from "./route/publicRoute";
import MainForm from "./components/forms/MainForm/MainForm";
import GlavniPregled from "./components/forms/Kalendar/Kalendar";
import TipSredstva from "./components/forms/TipSredstvaForm/TipSredstvaForm";
import SredstvaSteps from "./components/forms/SredstvaForm/SredstvaSteps";
import BolestSteps from "./components/forms/BolestForm/BolestSteps";


const App = () => {
  return (
    <div className="App">
      <BrowserRouter basename="VineApp">
        <Switch>
        <PrivateRoute
            component={GlavniPregled}
            path="/pregled"
          />

          <PublicRoutes
            component={Login}
            path="/login"
          /> 
          <PublicRoutes
            component={RegisterForm}
            path="/register"
          /> 
          <PublicRoutes
            component={BolestSteps}
            path="/bolest"
          />
          <PublicRoutes
            component={TipSredstva}
            path="/tipSredstva"
          />
          <PublicRoutes
            component={SredstvaSteps}
            path="/sredstva"
          />
          <PublicRoutes
            component={MainForm}
            path="/home"
          />
          <PublicRoutes
            component={MainForm}
            path="/"
          />
        </Switch>
      </BrowserRouter>
    </div>
  );
}
export default App;
