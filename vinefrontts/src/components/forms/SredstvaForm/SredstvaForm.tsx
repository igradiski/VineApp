import { FunctionComponent, useState, useEffect } from "react";
import { Form, Input, Button, Cascader, Modal } from "antd";

import constant from "../../../constantsUI/constantsUI";
import "antd/dist/antd.css";
import "./SredstvaCSS.css";
import TipSredstvaService from "../../../services/TipSredstvaService";
import ISredstvoData from "../../../types/ISredstvoType";
import SredstvaService from "../../../services/SredstvaService";
import PictureUpload from "../CustomJSX/PictureUpload";

type Props = {
  isUpdate: boolean;
  updateData: ISredstvoData;
};

const SredstvaForm: FunctionComponent<Props> = ({ isUpdate, updateData }) => {
  const [tipSredstvaData, setTipSredstvaData] = useState([]);
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [composition, setComposition] = useState("");
  const [group, setGroup] = useState("");
  const [formulation, setFormulation] = useState("");
  const [typeOfAction, setTypeOfAction] = useState("");
  const [usage, setUsage] = useState("");
  const [concentration, setConcentration] = useState("");
  const [dosageOn100, setDosageOn100] = useState("");
  const [waiting, setWaiting] = useState("2");
  const [typeOfMedium, setTypeOfMedium] = useState("");
  const [fileBase64, setFileBase64] = useState("");
  const [fileName, setFileName] = useState("");
  const { TextArea } = Input;
  const [form] = Form.useForm();
  const sredstvaSrc = new SredstvaService();

  function successModal() {
    Modal.success({
      title: constant.UNOS_SREDSTVA_SUCCESS_TITLE,
      content: constant.UNOS_SREDSTVA_SUCCESS,
    });
  }
  function errorModal() {
    Modal.error({
      title: constant.UNOS_SREDSTVA_ERROR_TITLE,
      content: constant.UNOS_SREDSTVA_ERROR,
    });
  }
  const unesiSredstvo = () => {
    const data: ISredstvoData = {
      name: name,
      description: description,
      composition: composition,
      group: group,
      formulation: formulation,
      typeOfAction: typeOfAction,
      usage: usage,
      concentration: concentration,
      dosageOn100: dosageOn100,
      waiting: waiting,
      typeOfMedium: typeOfMedium,
      id: "",
      date: "",
      tipSredstvaId: "",
      base64: fileBase64,
      picture_name: fileName,
    };
    if (isUpdate) {
      sredstvaSrc
        .updateSredstvo(data, updateData.id)
        .then((response) => {
          successModal();
        })
        .catch((error) => {
          errorModal();
        });
    } else {
      sredstvaSrc
        .addSredstvo(data)
        .then((response) => {
          successModal();
        })
        .catch((error) => {
          errorModal();
        });
    }
  };

  const getInitialData = async () => {
    let tipSredstvaSrc = new TipSredstvaService();
    tipSredstvaSrc.getAll().then((response) => {
      setTipSredstvaData(response.data);
    });
  };
  const setDataForUpdate = () => {
    setName(updateData.name);
    setDescription(updateData.description);
    setComposition(updateData.composition);
    setGroup(updateData.group);
    setFormulation(updateData.formulation);
    setTypeOfAction(updateData.typeOfAction);
    setUsage(updateData.usage);
    setConcentration(updateData.concentration);
    setDosageOn100(updateData.dosageOn100);
    setWaiting(updateData.waiting);
    setTypeOfMedium(updateData.tipSredstvaId);
    form.setFieldsValue({
      name: updateData.name,
      description: updateData.description,
      composition: updateData.composition,
      group: updateData.group,
      formulation: updateData.formulation,
      typeOfAction: updateData.typeOfAction,
      usage: updateData.usage,
      concentration: updateData.concentration,
      dosageOn100: updateData.dosageOn100,
      waiting: updateData.waiting,
    });
  };

  const setFileData = (name: string, base64: string) => {
    setFileBase64(base64);
    setFileName(name);
  };

  useEffect(() => {
    getInitialData();
    setDataForUpdate();
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
        {isUpdate ? constant.SREDSTVA_UPDATE_NASLOV : constant.SREDSTVA_NASLOV}
      </h1>
      <Form.Item
        label={constant.SREDSTVA_NAME}
        name="name"
        rules={[
          {
            required: false,
            message: constant.SREDSTVA_NAME_REQUIRED,
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
      <Form.Item label={constant.SREDSTVA_OPIS} name="description">
        <TextArea
          style={{
            background: "#f4ffb8",
            border: "solid 1px #254000",
            borderRadius: "10px",
            textAlign: "center",
            fontWeight: "bold",
          }}
          className="text-area-4row"
          rows={4}
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
      </Form.Item>
      <Form.Item label={constant.SREDSTVA_SASTAV} name="composition">
        <Input
          style={{
            background: "#f4ffb8",
            border: "solid 1px #254000",
            borderRadius: "10px",
            textAlign: "center",
            fontWeight: "bold",
          }}
          className="input-login"
          value={composition}
          onChange={(e) => setComposition(e.target.value)}
        />
      </Form.Item>
      <Form.Item label={constant.SREDSTVA_GRUPA} name="group">
        <Input
          style={{
            background: "#f4ffb8",
            border: "solid 1px #254000",
            borderRadius: "10px",
            textAlign: "center",
            fontWeight: "bold",
          }}
          className="input-login"
          value={group}
          onChange={(e) => setGroup(e.target.value)}
        />
      </Form.Item>
      <Form.Item label={constant.SREDSTVA_FORMULACIJA} name="formulation">
        <Input
          style={{
            background: "#f4ffb8",
            border: "solid 1px #254000",
            borderRadius: "10px",
            textAlign: "center",
            fontWeight: "bold",
          }}
          className="input-login"
          value={formulation}
          onChange={(e) => setFormulation(e.target.value)}
        />
      </Form.Item>
      <Form.Item label={constant.SREDSTVA_NACIN_DJELOVANJA} name="typeOfAction">
        <Input
          style={{
            background: "#f4ffb8",
            border: "solid 1px #254000",
            borderRadius: "10px",
            textAlign: "center",
            fontWeight: "bold",
          }}
          className="input-login"
          value={typeOfAction}
          onChange={(e) => setTypeOfAction(e.target.value)}
        />
      </Form.Item>
      <Form.Item label={constant.SREDSTVA_UPORABA} name="usage">
        <Input
          style={{
            background: "#f4ffb8",
            border: "solid 1px #254000",
            borderRadius: "10px",
            textAlign: "center",
            fontWeight: "bold",
          }}
          className="input-login"
          value={usage}
          onChange={(e) => setUsage(e.target.value)}
        />
      </Form.Item>
      <Form.Item label={constant.SREDSTVA_KONCENTRACIJA} name="concentration">
        <Input
          style={{
            background: "#f4ffb8",
            border: "solid 1px #254000",
            borderRadius: "10px",
            textAlign: "center",
            fontWeight: "bold",
          }}
          className="input-login"
          value={concentration}
          onChange={(e) => setConcentration(e.target.value)}
        />
      </Form.Item>
      <Form.Item label={constant.SREDSTVA_DOZIRANJE} name="dosageOn100">
        <Input
          style={{
            background: "#f4ffb8",
            border: "solid 1px #254000",
            borderRadius: "10px",
            textAlign: "center",
            fontWeight: "bold",
          }}
          className="input-login"
          value={dosageOn100}
          onChange={(e) => setDosageOn100(e.target.value)}
        />
      </Form.Item>
      <Form.Item label={constant.SREDSTVA_KARENCA} name="waiting" key="waiting">
        <Input
          style={{
            background: "#f4ffb8",
            border: "solid 1px #254000",
            borderRadius: "10px",
            textAlign: "center",
            fontWeight: "bold",
          }}
          className="input-login"
          value={waiting}
          onChange={(e) => setWaiting(e.target.value)}
        />
      </Form.Item>
      <Form.Item label={constant.SREDSTVA_TIP_SREDSTVA} name="tipSredstva">
        <Cascader
          style={{
            background: "#f4ffb8",
            border: "solid 1px #254000",
            borderRadius: "10px",
            textAlign: "center",
            fontWeight: "bold",
          }}
          placeholder="Odaberite"
          options={tipSredstvaData}
          onChange={(e) => {
            setTypeOfMedium(e[0].toString());
          }}
        />
      </Form.Item>
      <Form.Item
        label={constant.UNOS_SREDSTVA_SLIKA}
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
        <Button type="primary" htmlType="submit" onClick={unesiSredstvo}>
          {isUpdate
            ? constant.UNOS_SREDSTVA_FORM_UDPATE
            : constant.SREDSTVA_UNOS_BUTTON}
        </Button>
      </Form.Item>
    </Form>
  );
};
export default SredstvaForm;
