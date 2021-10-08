import { FunctionComponent, useState } from "react";
import { Link } from "react-router-dom";
import 'antd/dist/antd.css'
import { Menu } from 'antd';
import { Layout } from 'antd';
import { HomeOutlined,UserOutlined ,UsergroupAddOutlined,CalendarOutlined} from '@ant-design/icons';
import { useAppSelector } from "../../hooks";
import constantsUI from "../../constantsUI/constantsUI";

const LoggedUserMenu = () => {
    return (
        <Menu theme="dark" mode="inline">
            <Menu.Item key="3" icon={<HomeOutlined />}>
                <Link className="left-menu-link" to="/">{constantsUI.LEFT_MENU_HOME}</Link>
            </Menu.Item>
            <Menu.Item key="4" icon={<CalendarOutlined />}>
                <Link className="left-menu-link" to="/pregled">{constantsUI.LEFT_MENU_CALENDAR}</Link>
            </Menu.Item>
        </Menu>
    )
}
const GuestMenu = () => {
    return (
        <Menu theme="dark" mode="inline">
            <Menu.Item key="1" icon={<HomeOutlined />}>
                <Link className="left-menu-link" to="/">{constantsUI.LEFT_MENU_HOME}</Link>
            </Menu.Item>
            <Menu.Item key="2" icon={<UserOutlined />}>
                <Link className="left-menu-link" to="/login">{constantsUI.LEFT_MENU_LOGIN}</Link>
            </Menu.Item>
            <Menu.Item key="3" icon={<UsergroupAddOutlined />}>
                <Link className="left-menu-link" to="/register">{constantsUI.LEFT_MENU_REGISTER}</Link>
            </Menu.Item>
            <Menu.Item key="4" icon={<UsergroupAddOutlined />}>
                <Link className="left-menu-link" to="/sredstva">{constantsUI.LEFT_MENU_SREDSTVA}</Link>
            </Menu.Item>
            <Menu.Item key="4" icon={<UsergroupAddOutlined />}>
                <Link className="left-menu-link" to="/tipSredstva">{constantsUI.LEFT_MENU_TIP_SREDSTVA}</Link>
            </Menu.Item>
            
        </Menu>
    )
}


const SiderCustom: FunctionComponent = () => {

    const { Sider } = Layout;
    const [collapsed, setCollapsed] = useState(true);
    const toggleCollapsed = () => {
        collapsed ? setCollapsed(false) : setCollapsed(true);
    };
    var userLogged = useAppSelector(state => state.login.isAuthentificated);
    return (
        <Sider className="left-sider" collapsible collapsed={collapsed} onCollapse={toggleCollapsed}>
            {userLogged ? <LoggedUserMenu/> : <GuestMenu/> }
        </Sider>
    )
}

export default SiderCustom;