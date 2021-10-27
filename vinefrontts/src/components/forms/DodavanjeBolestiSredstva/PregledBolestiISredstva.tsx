import { FunctionComponent, useState } from "react";
import constants from "../../../constantsUI/constantsUI";
import { Switch, Table, Pagination } from 'antd';
import "./BolestiSredstvaCSS.css";



const PregledBolestiISredstva: FunctionComponent = () => {

    const [sresdstvoSwitch, setSredstvoSwitch] = useState(true);
    const [fazaSwitch, setfazaSwitch] = useState(false);
    const [pageSize, setPageSize] = useState(2);
    const [pageNo, setPageNo] = useState(0);
    const [totalItems, setTotalItems] = useState(0);
    const [tableDataSredstva, setTableDataSredstva] = useState([])
    const [tableDataFaze, setTableDataFaze] = useState([])

    const handleTableChange = () => {
        sresdstvoSwitch ? setSredstvoSwitch(false) : setSredstvoSwitch(true);
        fazaSwitch ? setfazaSwitch(false) : setfazaSwitch(true);
    }

    const promijeniStranicu = (page: number, pageSize: number | undefined) => {
        setPageNo(page - 1);
    }

    const columnsSredstva = [
        {
            title: "Sredstva",
            dataIndex: 'name',
        },
    ]

    const columnsFaze = [
        {
            title: "Faze",
            dataIndex: 'name',
        },
    ]

    return (

        <div>
            
            <div className="splitter-div">
                <div className="left-div">
                    <p className="form-label">{constants.BOLESTI_SREDSTVA_SWITCH_SREDSTVA}</p>
                    <Switch checked={sresdstvoSwitch} onChange={handleTableChange} />
                </div>
                <div className="right-div">
                    <p className="form-label">{constants.BOLESTI_SREDSTVA_SWITCH_FAZE}</p>
                    <Switch checked={fazaSwitch} onChange={handleTableChange} />
                </div>
            </div>
            <h1 className="form-title">{constants.BOLESTI_SREDSTVA_TABLICA_SREDSTVA_NASLOV}</h1>
            <Table
                className="tablica-tip-sredstva"
                columns={sresdstvoSwitch ? columnsSredstva : columnsFaze}
                dataSource={sresdstvoSwitch ? tableDataSredstva : tableDataSredstva}
                pagination={false}
                rowKey="name"
            />
            <Pagination pageSize={pageSize} total={totalItems} onChange={promijeniStranicu} />
        </div>
    )
}

export default PregledBolestiISredstva;