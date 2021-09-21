import {  FunctionComponent } from "react";
import { Route } from "react-router-dom";
import MasterLayout from "../components/layout/MainLayout";


type Props = {
  component: FunctionComponent;
  path: string;
};

const PublicRoutes: FunctionComponent<Props> = (prop: Props) => {
  return (
    <MasterLayout>
      <Route
        {...prop}
      />
    </MasterLayout>
  );
};
export default PublicRoutes;
