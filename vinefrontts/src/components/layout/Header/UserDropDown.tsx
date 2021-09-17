import { FunctionComponent } from "react";

import 'antd/dist/antd.less';
import './HeaderCSS.css';
import { Menu, Dropdown, Button, message, Space, Tooltip } from 'antd';
import { ArrowDownOutlined,ArrowUpOutlined, UserOutlined ,LineHeightOutlined} from '@ant-design/icons';


const menuData = (
    <Menu >
        <Menu.Item key="1" icon={<LineHeightOutlined />}>
        
        </Menu.Item>
        <Menu.Item key="2" icon={<UserOutlined />}>
            2nd menu item
        </Menu.Item>
        <Menu.Item key="3" icon={<UserOutlined />}>
            3rd menu item
        </Menu.Item>
    </Menu>

);

const UserDropDown: FunctionComponent = () => {
    return (
        <Dropdown.Button overlay={menuData} placement="bottomCenter" icon={<UserOutlined />}>
            Ivor Gradiski-Zrinski
        </Dropdown.Button>
    );

}

export default UserDropDown;