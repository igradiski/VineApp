import { FunctionComponent, useState, useEffect } from "react";
import { Table, Pagination,Modal } from 'antd';
import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./FenofazaCSS.css"
import DefaultPagingData from "../../../types/IDefaultPagingData";
import TableUpdateDelete from "../CustomJSX/TableUpdateDelete";
import SearchByName from "../CustomJSX/SearchBy";
import FenofazaService from "../../../services/FenofazaService";
import IFenofazaData from "../../../types/IFenofazaData";
import DateConverter from "../../../feature/dateConverter";

type Props = {
    onUpdate : (step: number,data:IFenofazaData) =>void
}


const FenofazaSifrarnik: FunctionComponent<Props> = ({onUpdate}) => {

    const pageSize = 5;
    const [pageNo, setPageNo] = useState(0);
    const [totalItems, setTotalItems] = useState(0);
    const [tableData, setTableData] = useState();
    const fenofazaService = new FenofazaService();
    const datumClass = new DateConverter();

    const promijeniStranicu = (page: number, pageSize: number | undefined) => {
        setPageNo(page - 1);
    }

    const editClick = (text: any, record: any) => {
        const data: IFenofazaData = {
            id:record.id,
            name: record.name,
            timeOfUsage: record.timeOfUsage,
            date: ""
        }
        onUpdate(0,data);
    }

    const deleteClick = (record: any) => {
       fenofazaService.deleteItemById(record.id)
       .then(()=>{
        Modal.success({
            title: constant.BOLEST_BRISANJE_USPJELO_NASLOV,
            content: constant.BOLEST_BRISANJE_USPJELO
        });
        getInitialData();
    }).catch(()=>{
        Modal.error({
            title: constant.BOLEST_BRISANJE_FAIL_NASLOV,
            content: constant.BOLEST_BRISANJE_FAIL,
        });
    })
    }

    const findByItemName = (name: string) => {
        fenofazaService.findByItemName(name)
        .then(response => {
            setTableData(response.data.content);
            setTotalItems(response.data.totalElements);
            setPageNo(response.data.pageable.pageNumber);
        })
    }

    const getInitialData = async () => {
        const data: DefaultPagingData = {
            pageNo: pageNo,
            pageSize: pageSize,
            sort: []
        }
        fenofazaService.getAllFenofaze(data)
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
            title: SearchByName(constant.FENOFAZA_SIFRARNIK_NAZIV, findByItemName,getInitialData),
            dataIndex: 'name',

        },
        {
            title: constant.FENOFAZA_SIFRARNIK_VRIJEME,
            dataIndex: 'timeOfUsage',
        },
        {
            title: constant.BOLEST_SIFRARNIK_DATUM,
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
            <h1 className="form-title">{constant.FENOFAZA_NASLOV_SIFRARNIK}</h1>
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
export default FenofazaSifrarnik;