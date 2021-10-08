import { FunctionComponent, useState } from "react";
import { Table,Pagination } from 'antd';

import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./TipSredstvaCSS.css"


const TipSredstvaSifrarnik: FunctionComponent = () => {


    const promijeniStranicu = (page : number ,pageSize: number | undefined)=>{
        console.log("promjena"+page+" "+pageSize)
    }

    const columns = [
        {
            title: constant.TIP_SREDSTVA_TBL_NAZIV,
            dataIndex: 'name',
            key: 'name',
        },
        {
            title: constant.TIP_SREDSTVA_TBL_DATUM,
            dataIndex: 'name',
            key: 'name',
        },
    ]

    const data = [
        {
            key: '1',
            name: 'John Brown',
          },
          {
            key: '2',
            name: 'Jim Green',
          },
    ]

    return (
        <div>
            <h1 className="form-title">{constant.TIP_SREDSTVA_NASLOV_SIFRARNIK}</h1>
            <Table 
            className="tablica-tip-sredstva" 
            columns={columns}
            dataSource={data} 
            pagination={false}
            />
            <Pagination pageSize={1} total={5} onChange={promijeniStranicu}/>
        </div>
    );
}
export default TipSredstvaSifrarnik;