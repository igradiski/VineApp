
import { BrowserRouter, Switch } from "react-router-dom";
import PrivateRoute from "./route/protectedRoute";
import Login from './components/forms/LoginForm/Login';
import RegisterForm from "./components/forms/RegisterForm/RegisterForm";
import 'antd/dist/antd.less';
import '../src/App.css';
import PublicRoutes from "./route/publicRoute";
import MainForm from "./components/forms/MainForm/MainForm";
import GlavniPregled from "./components/forms/Kalendar/Kalendar";
import SredstvaSteps from "./components/forms/SredstvaForm/SredstvaSteps";
import BolestSteps from "./components/forms/BolestForm/BolestSteps";
import FenofazaSteps from "./components/forms/FenofazaRazvoja/FenofazaSteps";
import TipSredstvaSteps from "./components/forms/TipSredstvaForm/TipSredstvaSteps";
import MojVinograd from "./components/forms/MojVinograd/MojVinograd";
import DodavanjeBolestiSredstvaSteps from "./components/forms/DodavanjeBolestiSredstva/DodavanjeBolestiSredstvaSteps";
import VinovaLozaSteps from "./components/forms/VinovaLozaForm/VinovaLozaSteps";
import UnosSpricanja from "./components/forms/UnosSpricanja/UnosSpricanja";


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
            component={MojVinograd}
            path="/mojVinograd" 
          />
          <PublicRoutes
            component={DodavanjeBolestiSredstvaSteps}
            path="/dodavanjeBolesti"
          />
          <PublicRoutes
            component={Login}
            path="/login"
          />
          <PublicRoutes
            component={UnosSpricanja}
            path="/spricanja"
          />
          <PublicRoutes
          component={VinovaLozaSteps}
          path="/vinovaLoza"
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
            component={FenofazaSteps}
            path="/fenofaza"
          />
          <PublicRoutes
            component={TipSredstvaSteps}
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
