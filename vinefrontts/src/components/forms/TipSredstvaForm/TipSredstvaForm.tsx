import { FunctionComponent, useState, useEffect } from "react";
import { Form, Input, Button, Modal } from "antd";
import constant from "../../../constantsUI/constantsUI";
import "antd/dist/antd.css";
import "./TipSredstvaCSS.css";
import ITipSredstvaData from "../../../types/ITipSredstvaData";
import { useAppDispatch } from "../../../store/store";
import {
  insertNewTipSredstva,
  updateExistingTipSredstva,
} from "../../../store/slices/tipSredstvaSlice";

type Props = {
  isUpdate: boolean;
  updateData: ITipSredstvaData;
};

const TipSredstva: FunctionComponent<Props> = ({ isUpdate, updateData }) => {
  const [name, setName] = useState("");
  const [form] = Form.useForm();

  const dispatch = useAppDispatch();

  function successModal() {
    Modal.success({
      title: constant.UNOS_TIP_SREDSTVA_SUCCESS_TITLE,
      content: constant.UNOS_TIP_SREDSTVA_SUCCESS,
    });
  }
  function errorModal() {
    Modal.error({
      title: constant.UNOS_TIP_SREDSTVA_ERROR_TITLE,
      content: constant.UNOS_TIP_SREDSTVA_ERROR,
    });
  }
  const ocistiFormu = () => {
    setName("");
    form.resetFields();
  };

  const addTipSredstva = () => {
    const data: ITipSredstvaData = {
      name: name,
      date: "",
      id: "",
    };
    if (isUpdate) {
      data.id = updateData.id;
      dispatch(updateExistingTipSredstva(data))
        .unwrap()
        .then(() => {
          isUpdate = false;
          ocistiFormu();
          successModal();
        })
        .catch(() => {
          errorModal();
        });
    } else {
      dispatch(insertNewTipSredstva(data))
        .unwrap()
        .then(() => {
          successModal();
        })
        .catch(() => {
          errorModal();
        });
    }
  };

  useEffect(() => {
    setName(updateData.name);
    form.setFieldsValue({
      name: updateData.name,
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div className="tip-sredstva-main-div">
      <Form
        name="basic"
        className="tip-sredstva-forma"
        form={form}
        onFinish={() => form.resetFields()}
      >
        <h1 className="form-title">{constant.TIP_SREDSTVA_NASLOV}</h1>
        <Form.Item
          label={constant.TIP_SREDSTVA_NAZIV}
          name="name"
          rules={[
            {
              required: true,
              message: constant.TIP_SREDSTVA_NAZIV_REQ,
            },
          ]}
        >
          <Input
            className="input-login"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit" onClick={addTipSredstva}>
            {isUpdate
              ? constant.UNOS_SREDSTVA_BUTTON_UPDATE
              : constant.TIP_SREDSTVA_BUTTON_DODAJ}
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
};
export default TipSredstva;
