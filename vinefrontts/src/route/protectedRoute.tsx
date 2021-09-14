import React, { Component, FunctionComponent } from "react";
import { Route, Redirect } from "react-router-dom";
import MasterLayout from "../components/layout/MainLayout";


type Props = {
  component: FunctionComponent;
  path: string;
  isAuthenticated: boolean;
};


const ProtectedRoutes: FunctionComponent<Props> = ({ component, path, isAuthenticated }: Props) => {
  return (
    <MasterLayout>
      {isAuthenticated ? 
      <Route exact path={path} component={component}/>:
      <Redirect to="/error" />}
    </MasterLayout>
  );
};<Redirect to="/error" />
export default ProtectedRoutes;
