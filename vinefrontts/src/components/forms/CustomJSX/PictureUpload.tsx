import React, { FunctionComponent, useState } from 'react';
import { setTimeout } from 'timers';
import { Button,Spin } from 'antd';
import 'antd/dist/antd.css';
import "./UploadCSS.css"

type Props = {
    setFileData: (file: any, name: any, base64: any) => void;
}


const PictureUpload: FunctionComponent<Props> = ({ setFileData }) => {
    
    const [file, setFile] = useState<string>();
    const [imagePreview, setImagePreview] = useState<any>("");
    const [base64, setBase64] = useState<string>();
    const [name, setName] = useState<string>();
    
    const setPicture = (file: any) => {
        if (file) {
            const reader = new FileReader();
            reader.onload = _handleReaderLoaded
            reader.readAsBinaryString(file)
        }
    }

    const _handleReaderLoaded = (readerEvt: any) => {
        let binaryString = readerEvt.target.result;
        setBase64(btoa(binaryString))
    }

    const onFileSubmit = (e: any) => {
        setFileData(file, name, base64)
    }

    const photoUpload = (e: any) => {
        e.preventDefault();
        const reader = new FileReader();
        const file = e.target.files[0];
        if (reader !== undefined && file !== undefined) {
            reader.onloadend = () => {
                setFile(file)
                setPicture(file);
                setName(file.name)
                setImagePreview(reader.result)
            }
            reader.readAsDataURL(file);
        }
    }

    const remove = () => {
        setFile("")
        setImagePreview("")
        setBase64("")
        setName("")
    }

    return (
        <div className="div-upload">
            <input className="upload-button" type="file" name="avatar" id="file" accept=".jpef, .png, .jpg" onChange={photoUpload} src={imagePreview} />
            {imagePreview !== "" &&
                <div className="add-remove-btn">
                    <Button className="btn-style" type="primary" onClick={onFileSubmit} >
                        Spremi
                    </Button>
                    <Button className="btn-style" type="primary" onClick={remove} >Izbrisi</Button>
                </div>
            }
        </div>

    )
}
export default PictureUpload;