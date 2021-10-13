import { FunctionComponent } from "react";
import { Table,Pagination } from 'antd';
import constant from "../../../constantsUI/constantsUI";
import 'antd/dist/antd.css';
import "./SredstvaCSS.css"


const SredstvaSifrarnik: FunctionComponent = () => {

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
            dataIndex: 'typeOfMedium',
        },
    ]

    return (
        <div>
            <h1 className="form-title">{constant.TIP_SREDSTVA_NASLOV_SIFRARNIK}</h1>
            <Table 
            className="tablica-tip-sredstva" 
            columns={columns}
            pagination={false}
            />
        </div>
    );
}
export default SredstvaSifrarnik;