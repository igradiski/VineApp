import React, { FunctionComponent, useState } from "react";
import { Link } from "react-router-dom";
import 'antd/dist/antd.css'
import { Menu } from 'antd';
import { Layout } from 'antd';
import { FileTextOutlined, ReadOutlined, ProfileOutlined, TeamOutlined, HomeOutlined } from '@ant-design/icons';

const SiderCustom: FunctionComponent = () => {

    const { Sider } = Layout;
    const [collapsed, setCollapsed] = useState(false);
    const toggleCollapsed = () => {
        collapsed ? setCollapsed(false) : setCollapsed(true);
    };
    return (
        <Sider collapsible collapsed={collapsed} onCollapse={toggleCollapsed}>
            <Menu theme="dark" mode="inline">
                <Menu.Item key="1" icon={<HomeOutlined />}>
                    <Link className="left-menu-link" to="/pocetna">Test</Link>
                </Menu.Item>
                <Menu.Item key="2" icon={<HomeOutlined />}>
                    <Link className="left-menu-link" to="/pocetna">Test</Link>
                </Menu.Item>
                <Menu.Item key="3" icon={<HomeOutlined />}>
                    <Link className="left-menu-link" to="/pocetna">Test</Link>
                </Menu.Item>
            </Menu>
        </Sider>
    )
}

export default SiderCustom;