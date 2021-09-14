import React, { Component,FunctionComponent } from "react";
import { Layout } from "antd";
import SiderCustom from './Sider'
import HeaderCustom from './Header/Header'
import 'antd/dist/antd.css'
import '../layout/layoutCSS.css'

const MasterLayout : FunctionComponent = () => {

  return (
    <Layout className="main-layout">
        <SiderCustom></SiderCustom>
        <Layout className="site-layout">
            <HeaderCustom></HeaderCustom>
        </Layout>
    </Layout>
  );
};

export default MasterLayout;
