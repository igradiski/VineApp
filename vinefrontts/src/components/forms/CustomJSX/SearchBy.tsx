import { useState } from "react";
import { SearchOutlined } from "@ant-design/icons";
import constant from "../../../constantsUI/constantsUI";
import { Button, Space, Popover, Input, Row, Col } from "antd";

const SearchByName = (
  nameOfColumn: string,
  action: (name: string) => void,
  actionRefresh: () => void
) => {
  const SearchPopUp = () => {
    const [name, setName] = useState("");
    return (
      <div style={{ padding: 8, width: "20vw" }}>
        <Input
          placeholder={constant.CUSTOM_SEARCH_BY_PLACEHOLDER}
          value={name}
          onChange={(e) => setName(e.target.value)}
          style={{ marginBottom: 8, display: "block" }}
        />
        <div
          style={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "space-evenly",
          }}
        >
          <Button
            className="main-button"
            icon={<SearchOutlined />}
            style={{ width: "8vw" }}
            onClick={() => action(name)}
          >
            {constant.CUSTOM_SEARCH_BY_PRETRAGA_BUTTON}
          </Button>
          <Button
            className="main-button"
            style={{ width: "8vw" }}
            onClick={() => {
              setName("");
              actionRefresh();
            }}
          >
            {constant.CUSTOM_SEARCH_BY_RESET_BUTTON}
          </Button>
        </div>
      </div>
    );
  };

  return (
    <div>
      <Row>
        <Col span={12}>{nameOfColumn}</Col>
        <Col span={12}>
          <Popover
            placement="rightBottom"
            title={constant.CUSTOM_SEARCH_BY_NASLOV}
            content={SearchPopUp}
            trigger="click"
          >
            <SearchOutlined />
          </Popover>
        </Col>
      </Row>
    </div>
  );
};

export default SearchByName;
