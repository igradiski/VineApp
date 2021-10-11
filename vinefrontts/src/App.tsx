
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
import SredstvaForm from "./components/forms/SredstvaForm/SredstvaForm";


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
            component={TipSredstva}
            path="/tipSredstva"
          />
          <PublicRoutes
            component={SredstvaForm}
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
