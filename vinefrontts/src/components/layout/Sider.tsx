import { FunctionComponent, useState } from "react";
import { Link } from "react-router-dom";
import 'antd/dist/antd.css'
import { Menu } from 'antd';
import { Layout } from 'antd';
import {  HomeOutlined } from '@ant-design/icons';


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
                    <Link className="left-menu-link" to="/login">Login</Link>
                </Menu.Item>
                <Menu.Item key="2" icon={<HomeOutlined />}>
                    <Link className="left-menu-link" to="/register">Register</Link>
                </Menu.Item>
                <Menu.Item key="3" icon={<HomeOutlined />}>
                    <Link className="left-menu-link" to="/">Pocetna</Link>
                </Menu.Item>

                <Menu.Item key="4" icon={<HomeOutlined />}>
                    <Link className="left-menu-link" to="/pregled">Pregled</Link>
                </Menu.Item>
            </Menu>
        </Sider>
    )
}

export default SiderCustom;