import { FunctionComponent, useState, useEffect } from "react";
import { Table,Pagination } from 'antd';

import constant from "../../../constantsUI/constantsUI";
import TipSredstvaService from "../../../services/TipSredstvaService";
import IDefaultPagingData from "../../../types/IDefaultPagingData";
import 'antd/dist/antd.css';
import "./TipSredstvaCSS.css"


const TipSredstvaSifrarnik: FunctionComponent = () => {

    const [pageSize, setPageSize] = useState(2);
    const [pageNo, setPageNo] = useState(0);
    const [totalItems,setTotalItems] = useState(0);
    const [tableData,setTableData] = useState([])

    const promijeniStranicu = (page : number ,pageSize: number | undefined)=>{
       setPageNo(page-1);
    }

    useEffect(() => {

        const getInitialData = async () =>{
            const data : IDefaultPagingData ={
                pageNo:pageNo,
                pageSize:pageSize,
                sort:[]
            }
            let tipSredstvaSrc = new TipSredstvaService();
            tipSredstvaSrc.getAllTipSredstva(data)
            .then(response =>{
                setTableData(response.data.tipovi);
                setTotalItems(response.data.totalItems);
                setPageNo(response.data.currentPage);
            })
        }
        getInitialData();
      },[pageNo]);

    const columns = [
        {
            title: constant.TIP_SREDSTVA_TBL_NAZIV,
            dataIndex: 'name',
            key: 'name',
        },
        {
            title: constant.TIP_SREDSTVA_TBL_DATUM,
            dataIndex: 'date',
            key: 'date',
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
            />
            <Pagination pageSize={pageSize} total={totalItems} onChange={promijeniStranicu}/>
        </div>
    );
}
export default TipSredstvaSifrarnik;