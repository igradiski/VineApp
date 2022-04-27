import { FunctionComponent } from "react";
import { Route, Redirect } from "react-router-dom";
import MasterLayout from "../components/layout/MainLayout";

type Props = {
  component: FunctionComponent;
  path: string;
};

const PrivateRoute: FunctionComponent<Props> = (prop: Props) => {
  //var userLogged = useAppSelector(state => state.login.isAuthentificated)
  var userLogged = true;
  return (
    <MasterLayout>
      {userLogged ? <Route {...prop} /> : <Redirect to="/" />}
    </MasterLayout>
  );
};
export default PrivateRoute;
