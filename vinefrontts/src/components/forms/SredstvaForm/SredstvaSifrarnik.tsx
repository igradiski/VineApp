import { FunctionComponent, useState, useEffect } from "react";
import { Table, Pagination, Modal } from 'antd';
import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./SredstvaCSS.css"
import IDefaultPagingData from "../../../types/IDefaultPagingData";
import SredstvaService from "../../../services/SredstvaService";
import ISredstvoData from "../../../types/ISredstvoType";
import DateConverter from "../../../feature/dateConverter";
import SearchByName from "../CustomJSX/SearchBy";
import TableUpdateDelete from "../CustomJSX/TableUpdateDelete";

type Props = {
    onUpdate: (step: number, data: ISredstvoData) => void
}

const SredstvaSifrarnik: FunctionComponent<Props> = ({ onUpdate }) => {

    const pageSize = 2;
    const [pageNo, setPageNo] = useState(0);
    const [totalItems, setTotalItems] = useState(0);
    const [tableData, setTableData] = useState([])
    const sredstvaService = new SredstvaService();
    const datumClass = new DateConverter();


    const promijeniStranicu = (page: number, pageSize: number | undefined) => {
        setPageNo(page - 1);
    }

    const editClick = (text: any, record: any) => {
        console.log(record)
        const data: ISredstvoData = {
            tipSredstvaId:record.tipSredstvaId,
            name: record.name,
            description: record.description,
            composition: record.composition,
            group: record.group,
            formulation: record.formulation,
            typeOfAction: record.typeOfAction,
            usage: record.usage,
            concentration: record.concentration,
            dosageOn100: record.dosageOn100,
            waiting: record.waiting,
            typeOfMedium: record.nameOfTipSredstva,
            id: record.id,
            date: ""
        }
        onUpdate(0, data);
    }

    const deleteClick = (record: any) => {

        sredstvaService.deleteItemById(record.id)
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
        sredstvaService.findByItemName(name)
        .then(response => {
            setTableData(response.data.content);
            setTotalItems(response.data.totalElements);
            setPageNo(response.data.pageable.pageNumber);
        })
    }

    const getInitialData = async () => {
        const data: IDefaultPagingData = {
            pageNo: pageNo,
            pageSize: pageSize,
            sort: []
        }

        sredstvaService.getAllSredstva(data)
            .then(response => {
                setTableData(response.data.content);
                setTotalItems(response.data.totalElements);
                setPageNo(response.data.pageable.pageNumber);
            })
    }
    useEffect(() => {
        getInitialData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [pageNo]);

    const columns = [
        {
            title:  SearchByName(constant.SREDSTVA_SIFRARNIK_NAZIV, findByItemName,getInitialData),
            dataIndex: 'name',
        },
        {
            title: constant.SREDSTVA_SIFRARNIK_OPIS,
            dataIndex: 'description',
        },
        {
            title: constant.SREDSTVA_SIFRARNIK_SASTAV,
            dataIndex: 'composition',
        },
        {
            title: constant.SREDSTVA_SIFRARNIK_GRUPA,
            dataIndex: 'group',
        },
        {
            title: constant.SREDSTVA_SIFRARNIK_FORMULACIJA,
            dataIndex: 'formulation',
        },
        {
            title: constant.SREDSTVA_SIFRARNIK_PRIMJENA,
            dataIndex: 'typeOfAction',
        },
        {
            title: constant.SREDSTVA_SIFRARNIK_KORISTENJE,
            dataIndex: 'usage',
        },
        {
            title: constant.SREDSTVA_SIFRARNIK_KONCENTRACIJA,
            dataIndex: 'concentration',
        },
        {
            title: constant.SREDSTVA_SIFRARNIK_DOZIRANJE,
            dataIndex: 'dosageOn100',
        },
        {
            title: "Karenca",
            dataIndex: 'waiting',
        },
        {
            title: constant.SREDSTVA_SIFRARNIK_TIP,
            dataIndex: 'nameOfTipSredstva',
        },
        {
            title: "Date",
            dataIndex: 'date',
            render:(text:any) => datumClass.convertDateForTable(text)
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
export default SredstvaSifrarnik;