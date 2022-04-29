import { FunctionComponent, useState, useEffect } from "react";
import { Form, Input, Button, Modal } from "antd";
import PictureUpload from "../CustomJSX/PictureUpload";
import constant from "../../../constantsUI/constantsUI";
import "antd/dist/antd.css";
import "./BolestCSS.css";
import IBolestdata from "../../../types/IBolestData";
import { useAppDispatch } from "../../../store/store";
import {
  insertNewBolest,
  updateExistingBolest,
} from "../../../store/slices/bolestSlice";

type Props = {
  isUpdate: boolean;
  updateData: IBolestdata;
};

const BolestForm: FunctionComponent<Props> = ({ isUpdate, updateData }) => {
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [form] = Form.useForm();
  const [fileBase64, setFileBase64] = useState("");
  const [fileName, setFileName] = useState("");
  const { TextArea } = Input;
  const dispatch = useAppDispatch();

  const setFileData = (name: string, base64: string) => {
    setFileBase64(base64);
    setFileName(name);
  };

  function successModal() {
    Modal.success({
      title: constant.UNOS_BOLEST_SUCCESS_TITLE,
      content: constant.UNOS_BOLEST_SUCCESS,
    });
  }
  function errorModal() {
    Modal.error({
      title: constant.UNOS_BOLEST_ERROR_TITLE,
      content: constant.UNOS_BOLEST_ERROR,
    });
  }

  const unesiBolest = () => {
    let data: IBolestdata = {
      id: "",
      name: name,
      description: description,
      date: "",
      picture_name: fileName,
      base64: fileBase64,
    };
    if (isUpdate) {
      data.id = updateData.id;
      dispatch(updateExistingBolest(data))
        .unwrap()
        .then(() => {
          successModal();
        })
        .catch(() => {
          errorModal();
        });
    } else {
      dispatch(insertNewBolest(data))
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
    setDescription(updateData.description);
    form.setFieldsValue({
      name: updateData.name,
      description: updateData.description,
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
      <h1 className="form-title">{constant.BOLEST_NASLOV}</h1>
      <Form.Item
        label={constant.BOLEST_NAZIV}
        name="name"
        rules={[
          {
            required: true,
            message: constant.BOLEST_NAZIV_MESSAGE_REQUIRED,
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
        label={constant.BOLEST_OPIS}
        name="description"
        rules={[
          {
            required: true,
            message: constant.BOLEST_OPIS_MESSAGE_REQUIRED,
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
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
      </Form.Item>
      <Form.Item
        label="Slika bolesti:"
        name="description"
        rules={[
          {
            required: true,
            message: constant.BOLEST_OPIS_MESSAGE_REQUIRED,
          },
        ]}
      >
        <PictureUpload setFileData={setFileData} />
      </Form.Item>
      <Form.Item>
        <Button
          className="main-button"
          type="primary"
          htmlType="submit"
          onClick={unesiBolest}
        >
          {isUpdate
            ? constant.BOLEST_BUTTON_AZURIRAJ
            : constant.BOLEST_BUTTON_UNESI}
        </Button>
      </Form.Item>
    </Form>
  );
};
export default BolestForm;
