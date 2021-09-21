import { FunctionComponent } from "react";

import 'antd/dist/antd.less';
import './HeaderCSS.css';
import { Menu, Dropdown} from 'antd';
import { UserOutlined ,LineHeightOutlined} from '@ant-design/icons';


const menuData = (
    <Menu >
        <Menu.Item key="1" icon={<LineHeightOutlined />}>
        
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