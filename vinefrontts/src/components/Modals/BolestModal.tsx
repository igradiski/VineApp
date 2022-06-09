import { FunctionComponent, useState, useEffect } from "react";
import { Modal } from "antd";
import constant from "../../constantsUI/constantsUI";
import "../Modals/ModalCSS.css";
import BolestService from "../../services/BolestService";
import DateConverter from "../../feature/dateConverter";
type Props = {
  visibleModal: boolean;
  closeModal: () => void;
  bolest: string;
};

const BolestModal: FunctionComponent<Props> = ({
  visibleModal,
  closeModal,
  bolest,
}) => {
  const bolestService = new BolestService();
  const [opis, setOpis] = useState("");
  const [datum, setDatum] = useState("");
  const getBolestData = () => {
    if (bolest !== "") {
      bolestService.findByName(bolest).then((response) => {
        if (response.data !== null) {
          setOpis(response.data.description);
          setDatum(response.data.date);
        }
      });
    }
  };

  useEffect(() => {
    getBolestData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [visibleModal]);

  return (
    <Modal
      title={constant.BOLEST_MODAL_TITLE}
      visible={visibleModal}
      onCancel={closeModal}
      onOk={closeModal}
    >
      <div className="modal-div">
        <div className="modal-row">
          <p className="p-bold">{constant.BOLEST_MODAL_NAZIV}</p>
          <p className="p-content">{bolest}</p>
        </div>
        <div className="modal-row">
          <p className="p-bold">{constant.BOLEST_MODAL_OPIS}</p>
          <p className="p-content">{opis}</p>
        </div>
        <div className="modal-row">
          <p className="p-bold">{constant.BOLEST_MODAL_DATUM}</p>
          <p className="p-content">{DateConverter(datum)}</p>
        </div>
      </div>
    </Modal>
  );
};

export default BolestModal;
