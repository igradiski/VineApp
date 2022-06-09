import { FunctionComponent, useState, useEffect } from "react";
import { Modal } from "antd";
import constant from "../../constantsUI/constantsUI";
import "../Modals/ModalCSS.css";
import FenofazaService from "../../services/FenofazaService";
import DateConverter from "../../feature/dateConverter";
type Props = {
  visibleModal: boolean;
  closeModal: () => void;
  faza: string;
};

const FazaModal: FunctionComponent<Props> = ({
  visibleModal,
  closeModal,
  faza,
}) => {
  const fazaService = new FenofazaService();

  const [opis, setOpis] = useState("");
  const [datum, setDatum] = useState("");
  const getFazaData = () => {
    if (faza !== "") {
      fazaService.findByName(faza).then((response) => {
        if (response.data !== null) {
          console.log(response.data);
          setOpis(response.data.timeOfUsage);
          setDatum(response.data.date);
        }
      });
    }
  };

  useEffect(() => {
    getFazaData();
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
          <p className="p-content">{faza}</p>
        </div>
        <div className="modal-row">
          <p className="p-bold">{constant.FAZA_MODAL_VRIJEME}</p>
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

export default FazaModal;
