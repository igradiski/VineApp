import React, { Component, FunctionComponent } from "react";
import { Route, Redirect } from "react-router-dom";
import MasterLayout from "../components/layout/MainLayout";


type Props = {
  component: FunctionComponent;
  path: string;
  isAuthenticated: boolean;
};


const PublicRoutes: FunctionComponent<Props> = ({ component, path, isAuthenticated }: Props) => {
  return (
    <MasterLayout>
      <Route exact path={path} component={component}/>
    </MasterLayout>
  );
};
export default PublicRoutes;
