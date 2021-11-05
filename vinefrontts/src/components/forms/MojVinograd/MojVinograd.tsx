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


const MojVinograd: FunctionComponent = () => {

    const pageSize = 2;
    const [pageNo, setPageNo] = useState(0);
    const [totalItems, setTotalItems] = useState(0);
    const [tableData, setTableData] = useState([])
    const [modalVisible, setModalVisible] = useState(false);
    const [detaljiVisible, setDetaljiVisibe] = useState(false);
    const vinogradService = new VinogradService();
    const datumClass = new DateConverter();

    const promijeniStranicu = (page: number, pageSize: number | undefined) => {
        setPageNo(page - 1);
    }
    const editClick = (text: any, record: any) => { }
    const deleteClick = (record: any) => { }
    const closeModal = () => { setModalVisible(false) }
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
            dataIndex: 'cokoti',
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
                    <Tag icon={<EyeOutlined />} color="success" onClick={() => setDetaljiVisibe(true)}>
                        Prikazi detalje
                    </Tag>
                )
            }
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

    return (
        <div className="tip-sredstva-main-div">
            <h1 className="form-title">{constant.MOJ_VINOGRAD_NASLOV}</h1>
            <Table
                className="tablica-tip-sredstva"
                columns={columns}
                dataSource={tableData}
                pagination={false}
                rowKey="name"
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
                    <div style={{paddingTop:"3%"}}>
                        <h1 className="form-title">"Druga"</h1>
                        <Table
                            className="tablica-tip-sredstva"
                            columns={columns}
                            dataSource={tableData}
                            pagination={false}
                            rowKey="name"
                        />
                    </div>

                    : ""
            }



            <InsertVinogradModal isVisible={modalVisible} closeModal={closeModal} />
        </div>

    );

}
export default MojVinograd;