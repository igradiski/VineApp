import { FunctionComponent } from "react";
import { Button} from 'antd';
import 'antd/dist/antd.less';
import './HeaderCSS.css';
import { Menu, Dropdown} from 'antd';
import { UserOutlined ,UserDeleteOutlined} from '@ant-design/icons';
import { useAppDispatch } from '../../../hooks';
import { logOffUser } from "../../../feature/userLogin/userLogin";
import { useAppSelector } from "../../../hooks";



const UserDropDown: FunctionComponent = () => {
    var dispatch = useAppDispatch();
    var userLogged = useAppSelector(state => state.login.isAuthentificated)
    const menuData = (
        <Menu>
             {userLogged ? 
             <Menu.Item key="1">
                <Button onClick={() => dispatch(logOffUser())}>Log Off {<UserDeleteOutlined />}</Button>
            </Menu.Item> : null}
        </Menu>
    
    );
    return (
        <Dropdown.Button overlay={menuData} placement="bottomCenter" icon={<UserOutlined />}>
            {userLogged ? "Prijavljeni korinsik":"-"}
        </Dropdown.Button>
    );

}

export default UserDropDown;