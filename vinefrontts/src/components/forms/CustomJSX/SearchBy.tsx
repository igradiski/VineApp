import { useState } from "react";
import { SearchOutlined } from "@ant-design/icons";
import constant from "../../../constantsUI/constantsUI";
import { Button, Space, Popover, Input,Row,Col } from 'antd';

const SearchByName = (nameOfColumn: string, action: (name: string) => void,actionRefresh: () => void) => {

    const SearchPopUp = () => {

        const [name, setName] = useState("");
        return (
            <div style={{ padding: 8 }}>
                <Input
                    placeholder={constant.CUSTOM_SEARCH_BY_PLACEHOLDER}
                    value={name}
                    onChange={e => setName(e.target.value)}
                    style={{ marginBottom: 8, display: 'block' }}
                />
                <Space>
                    <Button
                        type="primary"
                        icon={<SearchOutlined />}
                        size="small"
                        style={{ width: 90 }}
                        onClick={() => action(name)}
                    >
                        {constant.CUSTOM_SEARCH_BY_PRETRAGA_BUTTON}
                    </Button>
                    <Button size="small"
                        style={{ width: 90 }}
                        onClick={() => {
                            setName("")
                            actionRefresh();
                        }}
                    >
                        {constant.CUSTOM_SEARCH_BY_RESET_BUTTON}
                    </Button>
                </Space>
            </div>

        )
    }


    return (
        <div>
            <Row>
                <Col span={12}>{nameOfColumn}</Col>
                <Col span={12}>
                    <Popover placement="rightBottom" title={constant.CUSTOM_SEARCH_BY_NASLOV} content={SearchPopUp} trigger="click">
                        <SearchOutlined />
                    </Popover>
                </Col>
            </Row>
            
            
        </div>
    )
}

export default SearchByName;