import { FunctionComponent, useState, useEffect } from "react";
import { Table, Pagination, Modal } from 'antd';
import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./VinovaLozaCSS.css"
import DateConverter from "../../../feature/dateConverter";
import IDefaultPagingData from "../../../types/IDefaultPagingData";
import VinovaLozaService from "../../../services/VinovaLozaService";
import SearchByName from "../CustomJSX/SearchBy";
import TableUpdateDelete from "../CustomJSX/TableUpdateDelete";
import TableFieldForDescription from "../CustomJSX/TableFieldForDescription";
import IVinovaLozaData from "../../../types/IVinovaLozaData";

type Props = {
    onUpdate : (step: number,data:IVinovaLozaData) =>void
}

const VinovaLozaSifrarnik:FunctionComponent<Props> = ({onUpdate}) =>{

    const pageSize = 2;
    const [pageNo, setPageNo] = useState(0);
    const [totalItems, setTotalItems] = useState(0);
    const [tableData, setTableData] = useState([])
    
    const datumClass = new DateConverter();
    const lozaService = new VinovaLozaService();


    const promijeniStranicu = (page: number, pageSize: number | undefined) => {
        setPageNo(page - 1);
    }


    const findByItemName = (name:string) =>{

    }

    const editClick = (text: any, record: any) =>{

        const data : IVinovaLozaData ={
            base64:record.base64,
            date:"",
            description:record.description,
            name:record.name,
            id:record.id,
            picture_name:record.fileName
        }
        onUpdate(0,data);
    }
    const deleteClick = (record:any) =>{
        lozaService.deleteById(record.id)
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

    const getInitialData = async () => {
        const data: IDefaultPagingData = {
            pageNo: pageNo,
            pageSize: pageSize,
            sort: []
        }
        lozaService.getAllLoza(data)
        .then(response => {
            console.log(response.data)
            setTableData(response.data.content);
            setTotalItems(response.data.totalElements);
            setPageNo(response.data.pageable.pageNumber);
        })
        
    }


    const columns = [
        {
            title:  SearchByName(constant.VINOVA_LOZA_TABLICA_NAZIV, findByItemName,getInitialData),
            dataIndex: 'name',
        },
        {
            title: constant.VINOVA_LOZA_TABLICA_OPIS,
            dataIndex: 'description',
            render:(text:any,record:any) => TableFieldForDescription(text,record,"loza")
        },
        {
            title: constant.VINOVA_LOZA_TABLICA_DATUM,
            dataIndex: 'date',
            render:(text:any) => datumClass.convertDateForTable(text)
        },
        {
            title: "",
            dataIndex: 'name',
            render: (text: any, record: any) => TableUpdateDelete(() => editClick(text, record), () => deleteClick(record), constant.BOLEST_BRISANJE_PITANJE)
        },
    ]


    useEffect(() => {
        getInitialData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [pageNo]);

    return (
        <div>
            <h1 className="form-title">{constant.VINOVA_LOZA_SIFRARNIK_NASLOV}</h1>
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
export default VinovaLozaSifrarnik;