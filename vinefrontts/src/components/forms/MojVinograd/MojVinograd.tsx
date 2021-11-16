import { FunctionComponent, useState, useEffect } from "react";
import { Table, Pagination, Button, Tag,Modal } from 'antd';
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
import IVinogradLozaFilter from "../../../types/IVinogradLozaFilter";
import b64BlobConverter from "../../../feature/base64ToURL";
import UpdateVinogradLozaModal from "./UpdateVinogradLozaModal";


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

    const [lozaIsUpdateVisible,setLozaIsUpdateVisible] = useState(false);
    const [updateId,setUpdateId] = useState("");
    
    const vinogradService = new VinogradService();
    const vinogradLozaService = new VinogradLozaService();
    const datumClass = new DateConverter();
    const converterB64 = new b64BlobConverter();

    const promijeniStranicu = (page: number, pageSize: number | undefined) => {
        page - 1 <= 0 ?  setPageNo(0) :  setPageNo(page - 1)
    }
    const promijeniStranicuLoza = (page: number, pageSize: number | undefined) => {
        page - 1 <= 0 ?  setLozaPageNo(0) :  setLozaPageNo(page - 1)
    }

    const editVinogradClick = (text: any, record: any) => { 
        
    }

    const deleteVinogradClick = (record: any) => { 
        vinogradService.deleteById(record.id)
        .then(()=>{
            Modal.success({
                title: constant.MOJ_VINOGRAD_LOZA_BRISANJE_TITLE,
                content: constant.MOJ_VINOGRAD_LOZA_BRISANJE
            });
            getInitialData();
        }).catch(()=>{
            Modal.error({
                title: constant.MOJ_VINOGRAD_LOZA_BRISANJE_FAIL_TITLE,
                content: constant.MOJ_VINOGRAD_LOZA_BRISANJE_FAIL,
            });
        })
        getVinogradLozaData();
    }

    const editClick = (text: any, record: any) => { 
        setLozaIsUpdateVisible(true);
        setUpdateId(record.id);
    }

    const deleteClick = (record: any) => { 
        vinogradLozaService.deleteById(record.id)
        .then(()=>{
            Modal.success({
                title: constant.MOJ_VINOGRAD_LOZA_BRISANJE_TITLE,
                content: constant.MOJ_VINOGRAD_LOZA_BRISANJE
            });
            getVinogradLozaData();
        }).catch(()=>{
            Modal.error({
                title: constant.MOJ_VINOGRAD_LOZA_BRISANJE_FAIL_TITLE,
                content: constant.MOJ_VINOGRAD_LOZA_BRISANJE_FAIL,
            });
        })
    }
    const closeModal = () => { 
        setModalVisible(false);
        setModalLozaVisible(false);
        setLozaIsUpdateVisible(false)
        getVinogradLozaData();
        getInitialData();
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
        {
            title: "",
            dataIndex: 'buttons',
            render: (text: any, record: any) => TableUpdateDelete(() => editVinogradClick(text, record), () => deleteVinogradClick(record), constant.BOLEST_BRISANJE_PITANJE)
        },
    ]


    const lozaColumns = [
        {
            title: constant.MOJ_VINOGRAD_NAZIV_LOZE_TABLICA,
            dataIndex: 'nazivLoze',
        },
        {
            title: constant.MOJ_VINOGRAD_BROJ_LOZE_TABLICA,
            dataIndex: 'brojCokota',
        },
        {
            dataIndex: 'slikaLoze',
            render: (text:any, record:any) =>{ 
                var blob = converterB64.b64toBlob(text, '');
                var blobUrl = URL.createObjectURL(blob);
                return(
                    <img className="img-normal-size" alt="" src={blobUrl} />
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
            pageNo: lozaPageNo,
            pageSize: pageSize,
            sort: [],
            vinogradId:vinogradId

        }
        if(vinogradId !== ""){
            vinogradLozaService.findByVinogradPaged(data)
        .then(response => {
            setLozaTableData(response.data.content);
            setLozaTotalItems(response.data.totalElements);
            setLozaPageNo(response.data.pageable.pageNumber);
        })
        }
    }

    useEffect(() => {
        getVinogradLozaData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [vinogradId,lozaPageNo])

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
                        <Pagination pageSize={pageSize} total={lozaTotalItems} onChange={promijeniStranicuLoza} />
                    </div>

                    : ""
            }
            <InsertVinogradModal isVisible={modalVisible} closeModal={closeModal}  />
            <InsertVinogradLozaModal isVisible={modalLozaVisible} closeModal={closeModal} 
            vinogradId={vinogradId} vinogradName={vinogradName}/>
            <UpdateVinogradLozaModal closeModal={closeModal} isVisible={lozaIsUpdateVisible} id={updateId} />
        </div>

    );

}
export default MojVinograd;