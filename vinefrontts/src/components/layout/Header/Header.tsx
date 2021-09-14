import { FunctionComponent } from "react";
import { Layout, Row, Col,PageHeader } from 'antd';
import 'antd/dist/antd.less';
import './HeaderCSS.css';
import UserDropDown from './UserDropDown'

const { Header } = Layout;
const HeaderCustom: FunctionComponent = () => {

    return (
        <Header className="header-main">
            <Row>
                <Col xs={2} sm={4} md={6} lg={10} xl={8}>
                    <PageHeader
                        className="site-page-header"
                        onBack={() => null}
                        title="Title"
                    />
                </Col>
                <Col xs={2} sm={4} md={6} lg={10} xl={16}>
                    <UserDropDown></UserDropDown>
                </Col>
            </Row>

        </Header>
    );

};

export default HeaderCustom;