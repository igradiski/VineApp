import { FunctionComponent,useState, useEffect } from "react";
import { Table,Pagination,Modal,Popconfirm} from 'antd';
import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./FenofazaCSS.css"
import TableUpdateDelete from "../CustomJSX/TableUpdateDelete";


const FenofazaSifrarnik: FunctionComponent = () => {

    const [pageSize, setPageSize] = useState(2);
    const [pageNo, setPageNo] = useState(0);
    const [totalItems,setTotalItems] = useState(0);
    const [tableData,setTableData] = useState([])
    
    const promijeniStranicu = (page : number ,pageSize: number | undefined)=>{
        setPageNo(page-1);
    }
    //BOLEST_BRISANJE_PITANJE
    const renderButtons = (text:any) =>{
        {TableUpdateDelete(() =>editClick(text),()=>deleteClick(text),constant.BOLEST_BRISANJE_PITANJE)}
    }
    const editClick = (text:any) =>{
        //TODO
        console.log(text);
    }

    const deleteClick = (text:any) =>{
        //TODOa
        console.log(text);
    }

    const columns = [
        {
            title: constant.SREDSTVA_SIFRARNIK_NAZIV,
            dataIndex: 'name',
        },
        {
            title: constant.BOLEST_SIFRARNIK_OPIS,
            dataIndex: 'description',
        },
        {
            title: constant.BOLEST_SIFRARNIK_DATUM,
            dataIndex: 'date',
        },
        {
            title: "",
            dataIndex: 'name',
            render : (text:any) => TableUpdateDelete(() =>editClick(text),()=>deleteClick(text),constant.BOLEST_BRISANJE_PITANJE)
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
            <Pagination pageSize={pageSize} total={totalItems} onChange={promijeniStranicu}/>
        </div>
    );
}
export default FenofazaSifrarnik;