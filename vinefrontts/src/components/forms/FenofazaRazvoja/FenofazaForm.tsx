import { FunctionComponent, useState, useEffect } from "react";
import { Form, Input, Button, Modal, Spin } from "antd";
import constant from "../../../constantsUI/constantsUI";
import "antd/dist/antd.css";
import "./FenofazaCSS.css";
import IFenofazaData from "../../../types/IFenofazaData";
import { useDispatch, useSelector } from "react-redux";
import {
  insertNewFenofaza,
  updateExistingFenofaza,
} from "../../../store/slices/fenofazaSlice";
import { RootState } from "../../../store/reducer";
import { useAppDispatch, useAppSelector } from "../../../store/store";

type Props = {
  isUpdate: boolean;
  updateData: IFenofazaData;
};

const FenofazaForm: FunctionComponent<Props> = ({ isUpdate, updateData }) => {
  const [name, setName] = useState("");
  const [timeOfUsage, setTimeOfUsage] = useState("");
  const [form] = Form.useForm();
  const { TextArea } = Input;
  const dispatch = useAppDispatch();

  const unesiFenofazu = () => {
    const data: IFenofazaData = {
      id: "",
      name: name,
      timeOfUsage: timeOfUsage,
      date: "",
    };
    if (isUpdate) {
      data.id = updateData.id;
      console.log(data);
      dispatch(updateExistingFenofaza(data));
    } else dispatch(insertNewFenofaza(data));
  };

  useEffect(() => {
    setName(updateData.name);
    setTimeOfUsage(updateData.timeOfUsage);
    form.setFieldsValue({
      name: updateData.name,
      timeOfUsage: updateData.timeOfUsage,
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <Form
      form={form}
      name="basic"
      className="forma-sredstva"
      onFinish={() => form.resetFields()}
    >
      <h1 className="form-title">
        {isUpdate
          ? constant.FENOFAZA_AZURIRANJE_NASLOV
          : constant.FENOFAZA_NASLOV}
      </h1>
      <Form.Item
        label={constant.FENOFAZA_FORM_NAZIV}
        name="name"
        rules={[
          {
            required: true,
            message: constant.FENOFAZA_FORM_NAZIV_REQUIRED,
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
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
      </Form.Item>
      <Form.Item
        label={constant.FENOFAZA_FORM_VRIJEME}
        name="timeOfUsage"
        rules={[
          {
            required: true,
            message: constant.FENOFAZA_FORM_VRIJEME_REQUIRED,
          },
        ]}
      >
        <TextArea
          style={{
            background: "#f4ffb8",
            border: "solid 1px #254000",
            borderRadius: "10px",
            textAlign: "center",
            fontWeight: "bold",
          }}
          className="text-area-4row"
          rows={5}
          value={timeOfUsage}
          onChange={(e) => setTimeOfUsage(e.target.value)}
        />
      </Form.Item>

      <Form.Item>
        <Button
          className="main-button"
          type="primary"
          htmlType="submit"
          onClick={unesiFenofazu}
        >
          {isUpdate
            ? constant.FENOFAZA_BUTTON_AZURIRAJ
            : constant.FENOFAZA_BUTTON_UNOS}
        </Button>
      </Form.Item>
    </Form>
  );
};
export default FenofazaForm;
