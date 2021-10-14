import { FunctionComponent,useState, useEffect } from "react";
import { Table,Pagination } from 'antd';
import { EditOutlined,DeleteOutlined} from '@ant-design/icons';
import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./SredstvaCSS.css"
import IDefaultPagingData from "../../../types/IDefaultPagingData";
import SredstvaService from "../../../services/SredstvaService";


const SredstvaSifrarnik: FunctionComponent = () => {

    const [pageSize, setPageSize] = useState(2);
    const [pageNo, setPageNo] = useState(0);
    const [totalItems,setTotalItems] = useState(0);
    const [tableData,setTableData] = useState([])
    
    const promijeniStranicu = (page : number ,pageSize: number | undefined)=>{
        setPageNo(page-1);
    }
    const renderButtons = () =>{
        return (<div>
             <EditOutlined />
            <br></br>
            <DeleteOutlined />
            </div>)
        
    }

    useEffect(()=>{
        const getInitialData = async () =>{
            const data : IDefaultPagingData ={
                pageNo:pageNo,
                pageSize:pageSize,
                sort:[]
            }
            let sredstvaService = new SredstvaService();
            sredstvaService.getAllSredstva(data)
            .then(response =>{
                setTableData(response.data.sredstva);
                setTotalItems(response.data.totalItems);
                setPageNo(response.data.currentPage);
            })
        }
        getInitialData();
    },[pageNo]);

    const columns = [
        {
            title: constant.SREDSTVA_SIFRARNIK_NAZIV,
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
            title: constant.SREDSTVA_SIFRARNIK_UNESEN,
            dataIndex: 'waiting',
        },
        {
            title: constant.SREDSTVA_SIFRARNIK_TIP,
            dataIndex: 'nameOfTipSredstva',
        },
        {
            title: "Date",
            dataIndex: 'date',
        },
        {
            title: "",
            dataIndex: 'buttons',
            render : renderButtons
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
export default SredstvaSifrarnik;