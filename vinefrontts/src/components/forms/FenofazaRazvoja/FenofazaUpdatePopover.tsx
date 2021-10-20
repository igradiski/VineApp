import { Button, Space, Popover, Input,Row,Col } from 'antd';
import { SearchOutlined } from "@ant-design/icons";

const FenofazaUpdatePopover = () =>{
    const updateContent  = () =>{
        <div style={{ padding: 8 }}>
            <h1>Heder</h1>
                <Input
                    style={{ marginBottom: 8, display: 'block' }}
                />
                <Space>
                </Space>
            </div>
    }
    return(
        <div>
            <h1>Heder</h1>
            <Popover placement="rightBottom" title={"naslov"} content={updateContent} trigger="click">
                <SearchOutlined />
            </Popover>
        </div>
    )
}

export default FenofazaUpdatePopover;