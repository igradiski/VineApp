import { FunctionComponent,useState, useEffect } from "react";
import { Table,Pagination,Modal} from 'antd';
import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./BolestCSS.css"
import DefaultPagingData from "../../../types/IDefaultPagingData";
import BolestService from "../../../services/BolestService";
import IBolestdata from "../../../types/IBolestData";
import TableUpdateDelete from "../CustomJSX/TableUpdateDelete";
import DateConverter from "../../../feature/dateConverter";
import SearchByName from "../CustomJSX/SearchBy";
import TableFieldForDescription from "../CustomJSX/TableFieldForDescription";

type Props = {
    onUpdate : (step: number,data:IBolestdata) =>void
}

const BolestSifrarnik: FunctionComponent<Props> = ({onUpdate}) => {

    const pageSize = 2;
    const [pageNo, setPageNo] = useState(0);
    const [totalItems,setTotalItems] = useState(0);
    const [tableData,setTableData] = useState([])
    const bolestService = new BolestService();
    const datumClass = new DateConverter();
    
    const promijeniStranicu = (page : number ,pageSize: number | undefined)=>{
        setPageNo(page-1);
    }
    const editClick = (text:any,record:any) =>{
        const data:IBolestdata ={
            id:record.id,
            name:record.name,
            description:record.description,
            date:"",
            base64:"",
            picture_name:"",
        }
        onUpdate(0,data);
    }

    const deleteClick = (record:any) =>{
        bolestService.deleteBolestById(record.id)
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
        bolestService.findByItemName(name)
        .then(response => {
            setTableData(response.data.content);
            setTotalItems(response.data.totalElements);
            setPageNo(response.data.pageable.pageNumber);
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
            console.log(response)
            setTableData(response.data.content);
            setTotalItems(response.data.totalElements);
            setPageNo(response.data.pageable.pageNumber);
        })
    }

    useEffect(()=>{
        getInitialData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    },[pageNo]);

    const columns = [
        {
            
            title: SearchByName(constant.SREDSTVA_SIFRARNIK_NAZIV, findByItemName,getInitialData),
            dataIndex: 'name',
        },
        {
            title: constant.BOLEST_SIFRARNIK_OPIS,
            dataIndex: 'description',
            render:(text:any,record:any) => TableFieldForDescription(text,record)
        },
        {
            title: constant.BOLEST_SIFRARNIK_DATUM,
            dataIndex: 'date',
            render:(text:any) => datumClass.convertDateForTable(text)
        },
        {
            title: "",
            dataIndex: 'name',
            render : (text:any,record:any) => TableUpdateDelete(() =>editClick(text,record),()=>deleteClick(record),constant.BOLEST_BRISANJE_PITANJE)
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