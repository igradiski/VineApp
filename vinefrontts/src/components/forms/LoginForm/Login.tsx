import { FunctionComponent, useState } from "react";
import "antd/dist/antd.css";
import "./LoginCSS.css";
import { Form, Input, Button, Modal } from "antd";
import constant from "../../../constantsUI/constantsUI";

import { logUser } from "../../../feature/userLogin/userLogin";
import IUserRegisterData from "../../../types/userTypes";
import UserService from "../../../services/userService";
import { IloginState } from "../../../types/ILoginState";
import { useHistory } from "react-router-dom";

const Login: FunctionComponent = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const userService = new UserService();

  const history = useHistory();

  function successModal() {
    Modal.success({
      title: constant.LOGIN_MODAL_USPJEH_TITLE,
      content: constant.LOGIN_MODAL_USPJEH,
    });
  }
  function errorModal() {
    Modal.error({
      title: constant.LOGIN_MODAL_FAIL_TITLE,
      content: constant.LOGIN_MODAL_FAIL,
    });
  }

  const userLogin = () => {
    var data: IUserRegisterData = {
      email: "",
      name: "",
      password: "",
      surname: "",
      username: "",
    };
    userService
      .loginUser(data)
      .then((response) => {
        if (response.status === 200) {
          console.log(response.data);
          var data: IloginState = {
            error: false,
            isAuthentificated: true,
            myAccessToken: response.data.token,
            myRefreshToken: response.data.refreshToken,
            myUserRole: [],
            myUserServerRole: response.data.role,
            user: response.data.username,
          };
          //dispatch(logUser(data));
          successModal();
          history.push("/MojVinograd");
        } else {
          errorModal();
        }
      })
      .catch((reason: any) => {
        console.log(reason);
        errorModal();
      });
  };

  //const dispatch = useAppDispatch();
  return (
    <Form name="basic">
      <h1 className="form-title">Prijava</h1>
      <Form.Item
        label={constant.LABEL_USERNAME}
        name={constant.PLACEHOLDER_USERNAME}
        rules={[
          {
            required: true,
            message: constant.MESSAGE_USERNAME,
          },
        ]}
      >
        <Input
          style={{
            background: "#f4ffb8",
            border: "solid 1px #254000",
            borderRadius: "10px",
            textAlign: "center",
            fontWeight: "bold",
          }}
          className="input-login"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
      </Form.Item>

      <Form.Item
        label={constant.LABEL_PASSWORD}
        name="password"
        rules={[
          {
            required: true,
            message: constant.MESSAGE_PASSWORD,
          },
        ]}
      >
        <Input.Password
          style={{
            background: "#f4ffb8",
            border: "solid 1px #254000",
            borderRadius: "10px",
            textAlign: "center",
            fontWeight: "bold",
          }}
          className="input-login"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </Form.Item>

      <Form.Item>
        <Button
          type="primary"
          htmlType="submit"
          onClick={() => userLogin()}
          className="main-button"
        >
          {constant.BUTTON_LOGIN}
        </Button>
      </Form.Item>
    </Form>
  );
};
export default Login;
