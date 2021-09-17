import React, { Component,FunctionComponent } from "react";
import { Layout } from "antd";
import SiderCustom from './Sider'
import HeaderCustom from './Header/Header'
import FooterCustom from './Footer/FooterCustom'
import Login from "../forms/LoginForm/Login"
import 'antd/dist/antd.css'
import '../layout/layoutCSS.css'
import { Content } from "antd/lib/layout/layout";


type Props = {
  component: FunctionComponent;
  path: string;
};

const MasterLayout : FunctionComponent<Props> = ({ component, path }: Props) => {

  return (
    <Layout className="main-layout">
        <SiderCustom></SiderCustom>
        <Layout className="site-layout">
            <HeaderCustom></HeaderCustom>
            <Content className="content-layout">
            {component}
            </Content>
            <FooterCustom></FooterCustom>
        </Layout>
       
    </Layout>
  );
};

export default MasterLayout;
