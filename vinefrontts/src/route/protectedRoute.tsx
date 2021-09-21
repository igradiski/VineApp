import { FunctionComponent } from "react";
import { Route, Redirect } from "react-router-dom";
import MasterLayout from "../components/layout/MainLayout";
import { useAppSelector } from "../hooks";

type Props = {
  component: FunctionComponent;
  path: string;
};


const PrivateRoute: FunctionComponent<Props> = (prop: Props) => {
  var userLogged = useAppSelector(state => state.login.isAuthentificated)
  return (
    <MasterLayout>
      {userLogged ? 
        <Route
        {...prop}
        />
      : <Redirect to="/"/>}
      
    </MasterLayout>
  );
};
export default PrivateRoute;
