import { FunctionComponent } from "react";
import { Layout, Row, Col, PageHeader} from 'antd';
import 'antd/dist/antd.less';
import './FooterCSS.css';

const { Footer } = Layout;
const FooterCustom: FunctionComponent = () => {

    return (
        <Footer className="footer-main">IGZ ©2018 Ivor Gradiski-Zrinski</Footer>
    );

};

export default FooterCustom;