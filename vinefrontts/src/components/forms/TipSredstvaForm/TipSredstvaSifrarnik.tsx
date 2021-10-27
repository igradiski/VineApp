import { FunctionComponent, useState, useEffect } from "react";
import { Table, Pagination, Modal } from 'antd';

import constant from "../../../constantsUI/constantsUI";
import TipSredstvaService from "../../../services/TipSredstvaService";
import IDefaultPagingData from "../../../types/IDefaultPagingData";
import 'antd/dist/antd.css';
import "./TipSredstvaCSS.css"
import ITipSredstvaData from "../../../types/ITipSredstvaData";
import DateConverter from "../../../feature/dateConverter";
import TableUpdateDelete from "../CustomJSX/TableUpdateDelete";
import SearchByName from "../CustomJSX/SearchBy";


type Props = {
    onUpdate: (step: number, data: ITipSredstvaData) => void
}


const TipSredstvaSifrarnik: FunctionComponent<Props> = ({ onUpdate }) => {

    const [pageSize, setPageSize] = useState(2);
    const [pageNo, setPageNo] = useState(0);
    const [totalItems, setTotalItems] = useState(0);
    const [tableData, setTableData] = useState([])
    const tipSredstvaSrc = new TipSredstvaService();
    const datumClass = new DateConverter();


    const promijeniStranicu = (page: number, pageSize: number | undefined) => {
        setPageNo(page - 1);
    }

    const editClick = (text: any, record: any) => {
        const data: ITipSredstvaData = {
            id: record.id,
            name: record.name,
            date: ""
        }
        onUpdate(0, data);
    }

    const deleteClick = (record: any) => {

        tipSredstvaSrc.deleteItemById(record.id)
            .then(() => {
                Modal.success({
                    title: constant.BOLEST_BRISANJE_USPJELO_NASLOV,
                    content: constant.BOLEST_BRISANJE_USPJELO
                });
                getInitialData();
            }).catch(() => {
                Modal.error({
                    title: constant.BOLEST_BRISANJE_FAIL_NASLOV,
                    content: constant.BOLEST_BRISANJE_FAIL,
                });
            })
    }

    const findByItemName = (name: string) => {
        tipSredstvaSrc.findByItemName(name)
        .then(response => {
            setTableData(response.data.tipoviSredstava);
            setTotalItems(response.data.totalItems);
            setPageNo(response.data.currentPage);
        })
    }

    const getInitialData = async () => {
        const data: IDefaultPagingData = {
            pageNo: pageNo,
            pageSize: pageSize,
            sort: []
        }
        tipSredstvaSrc.getAllTipSredstva(data)
            .then(response => {
                setTableData(response.data.tipoviSredstava);
                setTotalItems(response.data.totalItems);
                setPageNo(response.data.currentPage);
            })
    }


    useEffect(() => {
        getInitialData();
    }, [pageNo]);

    const columns = [
        {
            title: SearchByName(constant.TIP_SREDSTVA_TBL_NAZIV, findByItemName,getInitialData),
            dataIndex: 'name',
            key: 'name',
        },
        {
            title: constant.TIP_SREDSTVA_TBL_DATUM,
            dataIndex: 'date',
            key: 'date',
        },
        {
            title: "",
            dataIndex: 'name',
            render: (text: any, record: any) => TableUpdateDelete(() => editClick(text, record), () => deleteClick(record), constant.BOLEST_BRISANJE_PITANJE)
        },
    ]
    return (
        <div>
            <h1 className="form-title">{constant.TIP_SREDSTVA_NASLOV_SIFRARNIK}</h1>
            <Table
                className="tablica-tip-sredstva"
                columns={columns}
                dataSource={tableData}
                pagination={false}
                rowKey="name"
            />
            <Pagination pageSize={pageSize} total={totalItems} onChange={promijeniStranicu} />
        </div>
    );
}
export default TipSredstvaSifrarnik;