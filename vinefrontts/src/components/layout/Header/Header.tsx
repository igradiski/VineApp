import { FunctionComponent } from "react";
import { Layout, Row, Col, PageHeader,Button } from 'antd';
import 'antd/dist/antd.less';
import './HeaderCSS.css';
import UserDropDown from './UserDropDown'
import { ArrowDownOutlined, ArrowUpOutlined ,EyeInvisibleOutlined, BoldOutlined} from '@ant-design/icons';

const { Header } = Layout;
const HeaderCustom: FunctionComponent = () => {

    return (
        <Header className="header-main">
            <Row >
                <Col span={12}>
                    <PageHeader
                        className="site-page-header"
                        title="VineApp"
                    />
                </Col>
                <Col className="user-drop" span={12}>
                    <UserDropDown></UserDropDown>
                    <Button type="primary" >Font<ArrowUpOutlined></ArrowUpOutlined><ArrowDownOutlined></ArrowDownOutlined></Button>
                    <Button type="primary" ><EyeInvisibleOutlined /></Button>
                    <Button type="primary" ><BoldOutlined /></Button>
                </Col>
            </Row>

        </Header>
    );

};

export default HeaderCustom;