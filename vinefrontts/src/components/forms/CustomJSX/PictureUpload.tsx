import { FunctionComponent, useState } from "react";
import { Button } from "antd";
import "antd/dist/antd.css";
import "./UploadCSS.css";
type Props = {
  setFileData: (name: string, base64: string) => void;
};

const PictureUpload: FunctionComponent<Props> = ({ setFileData }) => {
  const [imagePreview, setImagePreview] = useState<any>("");
  const [confirmed, setConfirmed] = useState(false);
  var base642: string = "";
  var pictureName: string = "";

  const setPicture = (file: any) => {
    if (file) {
      const reader = new FileReader();
      reader.onload = _handleReaderLoaded;
      reader.readAsBinaryString(file);
    }
  };

  const _handleReaderLoaded = (readerEvt: any) => {
    let binaryString = readerEvt.target.result;
    base642 = btoa(binaryString);
    onFileSubmit();
  };

  const onFileSubmit = () => {
    setFileData(pictureName, base642);
    setConfirmed(true);
  };

  const photoUpload = (e: any) => {
    const reader = new FileReader();
    const file = e.target.files[0];
    if (reader !== undefined && file !== undefined) {
      reader.onloadend = () => {
        setPicture(file);
        pictureName = file.name;
        setImagePreview(reader.result);
      };
      reader.readAsDataURL(file);
    }
  };
  const remove = () => {
    base642 = "";
    setImagePreview("");
    setFileData("", "");
    setConfirmed(false);
  };

  function success(e: any) {
    photoUpload(e);
  }
  return (
    <div className="div-upload">
      <input
        className="upload-button"
        type="file"
        ref={(e) => imagePreview}
        placeholder={"aa"}
        name="avatar"
        id="file"
        accept=".jpef, .png, .jpg"
        src={imagePreview}
        onChange={(e: any) => {
          success(e);
        }}
      />
      {imagePreview !== "" && (
        <div className="add-remove-btn">
          <Button className="main-button" type="primary" onClick={remove}>
            Izbrisi
          </Button>
        </div>
      )}
      {confirmed ? (
        <p className="info-p">Slika je spremljena u memoriju!</p>
      ) : (
        <p className="info-p">Slika je prazna!</p>
      )}
    </div>
  );
};
export default PictureUpload;
