import { useState } from "react";
import { FullscreenOutlined } from '@ant-design/icons';
import { Card, Modal, Spin } from 'antd';
import BolestService from "../../../services/BolestService";
import b64BlobConverter from "../../../feature/base64ToURL";



const TableFieldForDescription = (text: any, record: any) => {

    const [popVisible, setPopVisible] = useState(false);
    const [loading, setLoading] = useState(false);
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");
    const [imgUrl, setImgUrl] = useState("");
    const { Meta } = Card;
    const bolestService = new BolestService();
    const converterB64 = new b64BlobConverter();

    const openCard = () => {
        setLoading(true);
        console.log(record)
        bolestService.getBolestForCard(record.id)
            .then(response => {
                console.log(response);
                setTitle(response.data.name);
                setImage(response.data.base64);
                setContent(response.data.description)
            })

        setLoading(false);
        setPopVisible(true);
    }
    const setImage = (base64: string) => {
        const blob = converterB64.b64toBlob(base64, '');
        const blobUrl = URL.createObjectURL(blob);
        setImgUrl(blobUrl);
    }

    

    return (
        <div>
            {text.substring(0, 25) + "..."}
            <FullscreenOutlined style={{ float: "right" }} onClick={openCard} />
            <Modal visible={popVisible}
                title={title}
                onCancel={() => setPopVisible(false)}
                footer={null}
                style={{ textAlign: "center" }}
            >
                <Spin size="large" spinning={loading}>
                    <Card
                        hoverable
                        style={{ width: "100%" }}
                        cover={<img style={{ width: "100%",height:"10%" }} alt="example" src={imgUrl} />}
                    >
                        <p>
                        {content}
                        </p>
                    </Card>
                </Spin>

            </Modal>
        </div>
    )
}
export default TableFieldForDescription;