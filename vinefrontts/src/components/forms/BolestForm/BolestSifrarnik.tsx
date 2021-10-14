import { FunctionComponent,useState, useEffect } from "react";
import { Table,Pagination,Modal,Popconfirm} from 'antd';
import { EditOutlined,DeleteOutlined} from '@ant-design/icons';
import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./BolestCSS.css"
import DefaultPagingData from "../../../types/IDefaultPagingData";
import BolestService from "../../../services/BolestService";
import IBolestdata from "../../../types/IBolestData";
import PopConfirmCustom from "../CustomJSX/PopConfirmCustom";
import TableUpdateDelete from "../CustomJSX/TableUpdateDelete";


const BolestSifrarnik: FunctionComponent = () => {

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
        console.log(text);
    }

    const deleteClick = (text:any) =>{
        let bolestService = new BolestService();
        const data:IBolestdata ={
            name:text,
            description:"",
            date:""
        }
        bolestService.deleteBolestByName(data)
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
    const getInitialData = async () =>{
        const data : DefaultPagingData ={
            pageNo:pageNo,
            pageSize:pageSize,
            sort:[]
        }
        let bolestService = new BolestService();
        bolestService.getAllBolesti(data)
        .then(response =>{
            setTableData(response.data.bolesti);
            setTotalItems(response.data.totalItems);
            setPageNo(response.data.currentPage);
        })
    }

    useEffect(()=>{
        getInitialData();
    },[pageNo]);

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
export default BolestSifrarnik;