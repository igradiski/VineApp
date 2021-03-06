import { useEffect, useState } from "react";
import { FullscreenOutlined } from "@ant-design/icons";
import { Card, Modal, Spin, Divider } from "antd";
import BolestService from "../../../services/BolestService";
import b64BlobConverter from "../../../feature/base64ToURL";
import SredstvaService from "../../../services/SredstvaService";
import VinovaLozaService from "../../../services/VinovaLozaService";
import { useAppDispatch } from "../../../store/store";
import { fetchBolestCardData } from "../../../store/slices/bolestSlice";
import "./modalCSS.css";
import { useSelector } from "react-redux";
import { RootState } from "../../../store/reducer";

const TableFieldForDescription = (text: any, record: any, forma: string) => {
  const [popVisible, setPopVisible] = useState(false);
  const [loading, setLoading] = useState(false);
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [imgUrl, setImgUrl] = useState("");
  const [sastav, setSastav] = useState("");
  const [formulacija, setFormulacija] = useState("");
  const [primjena, setPrimjena] = useState("");
  //const[koncentracija,setKoncentracija] = useState("");
  const [litara100, set100L] = useState("");
  const [tipSredstva, setTipSredstva] = useState("");
  const [karenca, setKarenca] = useState("");
  const sredstvoService = new SredstvaService();
  const lozaService = new VinovaLozaService();
  const converterB64 = new b64BlobConverter();

  const dispatch = useAppDispatch();

  const openCard = () => {
    setLoading(true);
    if (forma === "bolest") {
      dispatch(fetchBolestCardData(record.id))
        .unwrap()
        .then((result) => {
          setTitle(result.name);
          setImage(result.base64);
          setContent(result.description);
        });
    } else if (forma === "loza") {
      lozaService.getAllLozaForCard(record.id).then((response) => {
        setTitle(response.data.name);
        setImage(response.data.base64);
        setContent(response.data.description);
      });
    } else {
      sredstvoService.getSredstvoForCard(record.id).then((response) => {
        setTitle(response.data.name);
        setImage(response.data.base64);
        setSastav(response.data.composition);
        setFormulacija(response.data.formulation);
        setPrimjena(response.data.typeOfAction);
        //setKoncentracija(response.data.concentration);
        set100L(response.data.dosageOn100);
        setTipSredstva(response.data.usage);
        setKarenca(response.data.waiting);
      });
    }
    setLoading(false);
    setPopVisible(true);
  };
  const setImage = (base64: string) => {
    const blob = converterB64.b64toBlob(base64, "");
    const blobUrl = URL.createObjectURL(blob);
    setImgUrl(blobUrl);
  };

  const bolestdata = () => {
    return (
      <div className="bolestContentContainer">
        <p>{content}</p>
      </div>
    );
  };
  const lozaData = () => {
    return <p style={{ backgroundColor: "white" }}>{content}</p>;
  };

  const sredstvoData = () => {
    return (
      <div>
        Sastav:{" "}
        <p style={{ textDecoration: "underline", fontWeight: "normal" }}>
          {sastav}
        </p>
        <Divider type="horizontal" />
        <p>Formulacija: </p>
        <p style={{ textDecoration: "underline", fontWeight: "normal" }}>
          {formulacija}
        </p>
        <Divider type="horizontal" />
        <p>Primjena: </p>
        <p style={{ textDecoration: "underline", fontWeight: "normal" }}>
          {primjena}
        </p>
        <Divider type="horizontal" />
        <p>Na 100 litara: </p>
        <p style={{ textDecoration: "underline", fontWeight: "normal" }}>
          {litara100}
        </p>
        <Divider type="horizontal" />
        <p>Tip sredstva: </p>
        <p style={{ textDecoration: "underline", fontWeight: "normal" }}>
          {tipSredstva}
        </p>
        <Divider type="horizontal" />
        <p>Karenca: </p>
        <p style={{ textDecoration: "underline", fontWeight: "normal" }}>
          {karenca}
        </p>
      </div>
    );
  };

  return (
    <div>
      {text.substring(0, 25) + "..."}
      <FullscreenOutlined
        style={{ float: "right" }}
        onClick={() => openCard()}
      />
      <Modal
        visible={popVisible}
        title={null}
        onCancel={() => setPopVisible(false)}
        footer={null}
        style={{ textAlign: "center" }}
      >
        <div className="modalTitleCustom">{title}</div>

        <div className="imgContainer">
          <img
            style={{
              width: "40vw",
              height: "auto",
              borderRadius: "10%",
              backgroundColor: "#7cb305",
              textAlign: "center",
            }}
            alt=""
            src={imgUrl}
          />
        </div>

        {forma === "bolest" ? bolestdata() : ""}
        {forma === "loza" ? lozaData() : ""}
        {forma === "sredstvo" ? sredstvoData() : ""}
      </Modal>
    </div>
  );
};
export default TableFieldForDescription;
