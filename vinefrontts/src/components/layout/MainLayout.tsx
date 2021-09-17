import React, { Component,FunctionComponent } from "react";
import { Layout } from "antd";
import SiderCustom from './Sider'
import HeaderCustom from './Header/Header'
import FooterCustom from './Footer/FooterCustom'
import Register from "../forms/RegisterForm/RegisterForm"
import 'antd/dist/antd.css'
import '../layout/layoutCSS.css'
import { Content } from "antd/lib/layout/layout";

const MasterLayout : FunctionComponent = () => {

  return (
    <Layout className="main-layout">
        <SiderCustom></SiderCustom>
        <Layout className="site-layout">
            <HeaderCustom></HeaderCustom>
            <Content className="content-layout">
              <Register></Register>
            </Content>
            <FooterCustom></FooterCustom>
        </Layout>
       
    </Layout>
  );
};

export default MasterLayout;
