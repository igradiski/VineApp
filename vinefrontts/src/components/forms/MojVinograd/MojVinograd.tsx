import { FunctionComponent, useState, useEffect } from "react";
import { Table, Pagination, Button, Tag, } from 'antd';
import constant from "../../../constantsUI/constantsUI";
import "./MojVinogradCSS.css"
import TableUpdateDelete from "../CustomJSX/TableUpdateDelete";
import InsertVinogradModal from "./InsertVinogradModal";
import VinogradService from "../../../services/VinogradService";
import IDefaultPagingData from "../../../types/IDefaultPagingData";
import DateConverter from "../../../feature/dateConverter";
import { EyeOutlined } from "@ant-design/icons";
import InsertVinogradLozaModal from "./InsertVinogradLozaModal";
import VinogradLozaService from "../../../services/VinogradLozaService";
import IVinovaLozaData from "../../../types/IVinovaLozaData";
import IVinogradLozaFilter from "../../../types/IVinogradLozaFilter";
import b64BlobConverter from "../../../feature/base64ToURL";


const MojVinograd: FunctionComponent = () => {

    const pageSize = 2;
    const [pageNo, setPageNo] = useState(0);
    const [totalItems, setTotalItems] = useState(0);
    const [tableData, setTableData] = useState([]);
    const [vinogradId,setVinogradId] =useState("");
    const [vinogradName,setVinogradName] =useState("");
    const[lozaTableData,setLozaTableData] =useState([])
    const [lozaPageNo, setLozaPageNo] = useState(0);
    const [lozaTotalItems, setLozaTotalItems] = useState(0);
    const [modalVisible, setModalVisible] = useState(false);
    const [modalLozaVisible, setModalLozaVisible] = useState(false);
    const [detaljiVisible, setDetaljiVisibe] = useState(false);
    
    const vinogradService = new VinogradService();
    const vinogradLozaService = new VinogradLozaService();
    const datumClass = new DateConverter();
    const converterB64 = new b64BlobConverter();

    const promijeniStranicu = (page: number, pageSize: number | undefined) => {
        setPageNo(page - 1);
    }
    const editClick = (text: any, record: any) => { }
    const deleteClick = (record: any) => { }
    const closeModal = () => { setModalVisible(false);setModalLozaVisible(false)}

    const pokaziDetalje = () => {

    }

    const columns = [
        {
            title: constant.MOJ_VINOGRAD_TABLE_NAZIV,
            dataIndex: 'name',
        },
        {
            title: constant.MOJ_VINOGRAD_TABLE_ADRESA,
            dataIndex: 'adress',
        },
        {
            title: constant.MOJ_VINOGRAD_TABLE_OPIS,
            dataIndex: 'description',
        },
        {
            title: constant.MOJ_VINOGRAD_TABLE_TRSJE,
            dataIndex: 'brojCokota',
        },
        {
            title: constant.MOJ_VINOGRAD_TABLE_DATUM,
            dataIndex: 'date',
            render: (text: any) => datumClass.convertDateForTable(text)
        },
        {
            title: "",
            dataIndex: 'buttons',
            render: (text: any, record: any) => TableUpdateDelete(() => editClick(text, record), () => deleteClick(record), constant.BOLEST_BRISANJE_PITANJE)
        },
        {
            title: "",
            dataIndex: 'detalji',
            render: (text: any, record: any) => {
                return (
                    <Tag icon={<EyeOutlined />} color="success" onClick={() => {
                        setDetaljiVisibe(true);
                        setVinogradId(record.id);
                        setVinogradName(record.name)
                    }}>
                        Prikazi detalje
                    </Tag>
                )
            }
        },
    ]


    const lozaColumns = [
        {
            title: constant.MOJ_VINOGRAD_TABLE_NAZIV,
            dataIndex: 'nazivLoze',
        },
        {
            title: constant.MOJ_VINOGRAD_TABLE_ADRESA,
            dataIndex: 'brojCokota',
        },
        {
            title: constant.MOJ_VINOGRAD_TABLE_DATUM,
            dataIndex: 'slikaLoze',
            render: (text:any, record:any) =>{ 
                var blob = converterB64.b64toBlob(text, '');
                var blobUrl = URL.createObjectURL(blob);
                return(
                    <img style={{ width: "50px",height:"50px",borderRadius: "10%",backgroundColor:"#7cb305"}} alt="" src={blobUrl} />
                )
        }
        },
        {
            title: constant.MOJ_VINOGRAD_TABLE_DATUM,
            dataIndex: 'date',
            render: (text: any) => datumClass.convertDateForTable(text)
        },
        {
            title: "",
            dataIndex: 'buttons',
            render: (text: any, record: any) => TableUpdateDelete(() => editClick(text, record), () => deleteClick(record), constant.BOLEST_BRISANJE_PITANJE)
        },
    ]

    const getInitialData = async () => {
        const data: IDefaultPagingData = {
            pageNo: pageNo,
            pageSize: pageSize,
            sort: []
        }
        vinogradService.getVinogradi(data)
            .then(response => {
                setTableData(response.data.content);
                setTotalItems(response.data.totalElements);
                setPageNo(response.data.pageable.pageNumber);
            })
    }

    useEffect(() => {
        getInitialData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [pageNo])


    const getVinogradLozaData = async () => {
        const data: IVinogradLozaFilter = {
            pageNo: pageNo,
            pageSize: pageSize,
            sort: [],
            vinogradId:vinogradId

        }
        if(vinogradId !== ""){
            vinogradLozaService.findByVinogradPaged(data)
        .then(response => {
            console.log(response.data)
            setLozaTableData(response.data.content);
            setLozaTotalItems(response.data.totalElements);
            setLozaPageNo(response.data.pageable.pageNumber);
        })
        }
    }

    useEffect(() => {
        getVinogradLozaData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [vinogradId])

    return (
        <div className="tip-sredstva-main-div">
            <h1 className="form-title">{constant.MOJ_VINOGRAD_NASLOV}</h1>
            <Table
                className="tablica-tip-sredstva"
                columns={columns}
                dataSource={tableData}
                pagination={false}
                rowKey="naziv"
            />
            <div className="right-float">
                <Button type="primary"
                    htmlType="submit"
                    onClick={() => setModalVisible(true)}
                >
                    {constant.MOJ_VINOGRAD_TABLE_UNOS}
                </Button>
            </div>

            <Pagination pageSize={pageSize} total={totalItems} onChange={promijeniStranicu} />
            {
                detaljiVisible ?
                    <div style={{ paddingTop: "3%" }}>
                        <h1 className="form-title">{vinogradName}</h1>
                        <Table
                            className="tablica-tip-sredstva"
                            columns={lozaColumns}
                            dataSource={lozaTableData}
                            pagination={false}
                            rowKey="name"
                        />
                        <div className="right-float">
                            <Button type="primary"
                                htmlType="submit"
                                onClick={() => setModalLozaVisible(true)}
                            >
                                {constant.MOJ_VINOGRAD_BUTTON_DODAJ_LOZU}
                            </Button>
                        </div>
                    </div>

                    : ""
            }
            <InsertVinogradModal isVisible={modalVisible} closeModal={closeModal} />
            <InsertVinogradLozaModal isVisible={modalLozaVisible} closeModal={closeModal} vinogradId={vinogradId} vinogradName={vinogradName} />
        </div>

    );

}
export default MojVinograd;