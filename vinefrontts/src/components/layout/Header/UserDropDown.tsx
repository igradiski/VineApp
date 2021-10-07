import { FunctionComponent } from "react";
import { Button} from 'antd';
import 'antd/dist/antd.less';
import './HeaderCSS.css';
import { Menu, Dropdown} from 'antd';
import { UserOutlined ,UserDeleteOutlined} from '@ant-design/icons';
import { useAppDispatch } from '../../../hooks';
import { logOffUser } from "../../../feature/userLogin/userLogin";
import { useAppSelector } from "../../../hooks";
import { ArrowDownOutlined, ArrowUpOutlined ,EyeInvisibleOutlined, BoldOutlined} from '@ant-design/icons';



const UserDropDown: FunctionComponent = () => {
    var dispatch = useAppDispatch();
    var userLogged = useAppSelector(state => state.login.isAuthentificated)
    const menuData = (
        <Menu>
             {userLogged ? 
             <Menu.Item key="1">
                <Button type="primary" onClick={() => dispatch(logOffUser())}>Log Off {<UserDeleteOutlined />}</Button>
            </Menu.Item> :  null}
            <Menu.Item key="2" className="visible-FullWidth">
            <Button type="primary" >Font<ArrowUpOutlined></ArrowUpOutlined><ArrowDownOutlined></ArrowDownOutlined></Button>
            </Menu.Item>
            <Menu.Item key="3" className="visible-FullWidth">
            <Button type="primary" ><EyeInvisibleOutlined /></Button>
            </Menu.Item>
            <Menu.Item key="4" className="visible-FullWidth">
            <Button type="primary" ><BoldOutlined /></Button>
            </Menu.Item>
        </Menu>
    
    );
    return (
        <Dropdown.Button overlay={menuData} placement="bottomCenter" icon={<UserOutlined />}>
            {userLogged ? "Prijavljeni korinsik":"-"}
        </Dropdown.Button>
    );

}

export default UserDropDown;