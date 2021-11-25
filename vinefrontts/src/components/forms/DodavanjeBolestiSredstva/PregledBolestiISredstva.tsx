import { FunctionComponent, useState, useEffect } from "react";
import constants from "../../../constantsUI/constantsUI";
import { Switch, Table, Pagination,Cascader,Button } from 'antd';
import "./BolestiSredstvaCSS.css";
import DefaultPagingData from "../../../types/IDefaultPagingData";
import BolestSredstvoFazaService from "../../../services/BolestSredstvoFazaService";
import DateConverter from "../../../feature/dateConverter";
import BolestService from "../../../services/BolestService";
import FenofazaService from "../../../services/FenofazaService";
import SredstvaService from "../../../services/SredstvaService";
import IBFSFilterData from "../../../types/IBFSFilterData";
import TableFieldForModal from "../CustomJSX/TableFieldForModal";
import BolestModal from "../../Modals/BolestModal";
import FazaModal from "../../Modals/FazaModal";
import SredstvoModal from "../../Modals/Sredstvomodal";




const PregledBolestiISredstva: FunctionComponent = () => {

    const [sresdstvoSwitch, setSredstvoSwitch] = useState(true);
    const [fazaSwitch, setfazaSwitch] = useState(false);
    const pageSize = 3;
    const [pageNo, setPageNo] = useState(0);
    const [totalItems, setTotalItems] = useState(0);
    const [tableDataSredstva, setTableDataSredstva] = useState([])
    const [tableDataFaze, setTableDataFaze] = useState([])
    const [bolesti, setBolesti] = useState([]);
    const [sredstva, setSredstva] = useState([]);
    const [faze, setFaze] = useState([]);
    const [bolestSelected, setBolestSelected] = useState("");
    const [sredstvoSelected, setSredstvoSelected] = useState("");
    const [fazaSelected, setFazaSelected] = useState("");
    const [bolestiModalVisible,setBolestiModalVisible] = useState(false);
    const [bolestiModalName,setBolestiModalName] = useState("");
    const [fazaModalVisible,setFazaModalVisible] = useState(false);
    const [fazaModalName,setFazaModalName] = useState("");
    const [sredstvoModalName,setSredstvoModalName] = useState("");
    const [sredstvoModalVisible,setSredstvoModalVisible] = useState(false);
    const bfsService = new BolestSredstvoFazaService();
    const bolestService = new BolestService();
    const fazaService = new FenofazaService();
    const sredstvoService = new SredstvaService();
    const datumClass = new DateConverter();

    
    const handleTableChange = () => {
        sresdstvoSwitch ? setSredstvoSwitch(false) : setSredstvoSwitch(true);
        fazaSwitch ? setfazaSwitch(false) : setfazaSwitch(true);
    }

    const promijeniStranicu = (page: number, pageSize: number | undefined) => {
        setPageNo(page - 1);
    }

    const openBolestiModal = (record:any) =>{
        setBolestiModalName(record.bolestName)
        setBolestiModalVisible(true);
    }
    const closeBolestiModal = () =>{
        setBolestiModalVisible(false);
    }
    const openFazaModal = (record:any) =>{
        setFazaModalName(record.fazaName)
        setFazaModalVisible(true);
    }
    const closeFazaModal = () =>{
        setFazaModalVisible(false);
    }
    const openSredstvoModal = (record:any) =>{
        setSredstvoModalName(record.sredstvoName)
        setSredstvoModalVisible(true);
    }
    const closeSredstvoModal = () =>{
        setSredstvoModalVisible(false);
    }


    const columns = [
        {
            title: "Bolest",
            dataIndex: 'bolestName',
            render: (text: any,record:any) => TableFieldForModal(text, () => openBolestiModal(record)) 
        },
        {
            title: sresdstvoSwitch ? "Sredstvo" : "Faza",
            dataIndex: sresdstvoSwitch ? "sredstvoName" : "fazaName",
            render: (text: any,record:any) => TableFieldForModal(text,
                 sresdstvoSwitch ? () => openSredstvoModal(record) :
                  () =>openFazaModal(record))
        },
        //TODO update brisanje
        {
            title: "Zadnje azuriranje",
            dataIndex: 'lastUpdate',
            render: (text: any) => datumClass.convertDateForTable(text)
        },
    ]



    const dohvatiPodatkeZaTablicu = () => {
        const data: DefaultPagingData = {
            pageNo: pageNo,
            pageSize: pageSize,
            sort: []
        }
        if (sresdstvoSwitch) {
            bfsService.getBolestSredstvo(data)
                .then(response => {
                    setTableDataSredstva(response.data.content);
                    setPageNo(response.data.pageable.pageNumber);
                    setTotalItems(response.data.totalPages);

                })
        } else {
            bfsService.getBolestFaza(data)
                .then(response => {
                    setTableDataFaze(response.data.content);
                    setPageNo(response.data.pageable.pageNumber);
                    setTotalItems(response.data.totalPages);
                })
        }
    }
    
    const getSveBolesti = () => {
        bolestService.getAllBolestiForCascader()
            .then(response => {
                setBolesti(response.data)
            })
    }

    const getSvaSredstva = () => {
        sredstvoService.getAllSredstvaForCascader()
            .then(response => {
                setSredstva(response.data)
            })
    }
    const getSveFaze = () => {
        fazaService.getAllFazeForCascader()
            .then(response => {
                setFaze(response.data)
            })
    }

    const useFilter = () =>{
        if(bolestSelected === "" && fazaSelected === "" && sredstvoSelected ===""){
            dohvatiPodatkeZaTablicu();
        }else{
            const data: IBFSFilterData = {
                pageNo: 0,
                pageSize: pageSize,
                sort: [],
                bolestId:bolestSelected,
                sredstvoFaza:sredstvoSelected
            }
            if(sresdstvoSwitch){
                bfsService.getBolestSredstvoFiltered(data)
                .then(response => {
                    setPageNo(response.data.pageable.pageNumber);
                    setTotalItems(response.data.totalPages);
                    setTableDataSredstva(response.data.content);
                })
            }else{
                data.sredstvoFaza = fazaSelected;
                bfsService.getBolestFazaFiltered(data)
                .then(response => {
                    setPageNo(response.data.pageable.pageNumber);
                    setTotalItems(response.data.totalPages);
                    setTableDataFaze(response.data.content);
                })
            }
        }
    }

    useEffect(() => {
        dohvatiPodatkeZaTablicu();
        getSveBolesti();
        getSvaSredstva();
        getSveFaze();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [sresdstvoSwitch,pageNo]);
    return (

        <div>

            <div className="splitter-div">
                <div className="left-div">
                    <p className="form-label">{constants.BOLESTI_SREDSTVA_SWITCH_SREDSTVA}</p>
                    <Switch className="switch-class" checked={sresdstvoSwitch} onChange={handleTableChange} />
                    <p className="form-label">{constants.BOLESTI_SREDSTVA_BOLEST_CASCADE}</p>
                    <Cascader
                    style={{height:"20px"}}
                        placeholder="Odaberite"
                        options={bolesti}
                        onChange={e => {
                            if(e[0] !== undefined){
                                setBolestSelected(e[0].toString())
                            }else{
                                setBolestSelected("");
                            }
                        }}
                    />
                </div>
                <div className="right-div">
                    <p className="form-label">{constants.BOLESTI_SREDSTVA_SWITCH_FAZE}</p>
                    <Switch className="switch-class" checked={fazaSwitch} onChange={handleTableChange} />
                    <p className="form-label">{sresdstvoSwitch ?  
                    constants.BOLESTI_SREDSTVA_SREDSTV_CASCADE : 
                    constants.BOLESTI_SREDSTVA_FENOFAZA_CASCADE
                    }</p>
                    <Cascader
                        placeholder="Odaberite"
                        options={sresdstvoSwitch ? sredstva : faze}
                        onChange={e => {
                            if(e[0] !== undefined){
                                sresdstvoSwitch ? 
                            setSredstvoSelected(e[0].toString()) :
                            setFazaSelected(e[0].toString())
                            }else{
                                setFazaSelected("");
                                setSredstvoSelected("");
                            }
                        }}
                    />
                </div>
            </div>
            <Button htmlType="submit" onClick={useFilter}>
                            {constants.PREGLED_BOLESTI_FILTER}
                        </Button>
            <h1 className="form-title">{constants.BOLESTI_SREDSTVA_TABLICA_SREDSTVA_NASLOV}</h1>
            <Table
                className="tablica-tip-sredstva"
                columns={columns}
                dataSource={sresdstvoSwitch ? tableDataSredstva : tableDataFaze}
                pagination={false}
                rowKey={sresdstvoSwitch ? "sredstvo" : "faza"}
            />
            <Pagination pageSize={pageSize} total={totalItems} onChange={promijeniStranicu} />
            <BolestModal visibleModal={bolestiModalVisible} closeModal={closeBolestiModal} bolest={bolestiModalName} />
            <FazaModal visibleModal={fazaModalVisible} closeModal={closeFazaModal} faza={fazaModalName}/>
            <SredstvoModal visibleModal={sredstvoModalVisible} closeModal={closeSredstvoModal} sredstvo={sredstvoModalName}/>
        
        </div>
    )
}

export default PregledBolestiISredstva;