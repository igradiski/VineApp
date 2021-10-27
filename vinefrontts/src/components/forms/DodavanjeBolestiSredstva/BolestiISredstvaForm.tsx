import { FunctionComponent, useState, useEffect } from "react";
import { Cascader, Form, Button,Modal } from 'antd';
import "./BolestiSredstvaCSS.css"
import constants from "../../../constantsUI/constantsUI";
import BolestService from "../../../services/BolestService";
import SredstvaService from "../../../services/SredstvaService";
import FenofazaService from "../../../services/FenofazaService";
import BolestSredstvoFazaService from "../../../services/BolestSredstvoFazaService";


const PregledBolestiISredstvaForm: FunctionComponent = () => {

    const [form1] = Form.useForm();
    const [form2] = Form.useForm();
    const [bolesti, setBolesti] = useState([]);
    const [sredstva, setSredstva] = useState([]);
    const [faze, setFaze] = useState([]);
    const [bolestSredstvoSelected, setBolestSredstvoSelected] = useState("");
    const [bolestFazaSelected, setBolestFazaSelected] = useState("");
    const [sredstvoSelected, setSredstvoSelected] = useState("");
    const [fazaSelected, setFazaSelected] = useState("");
    const bolestService = new BolestService();
    const sredstvaService = new SredstvaService();
    const fazeService = new FenofazaService();
    const bfsService = new BolestSredstvoFazaService();

    function successModal() {
        Modal.success({
            title: constants.BOLESTI_SREDSTVA_MODAL_SUCCESS_TITLE,
            content: constants.BOLESTI_SREDSTVA_MODAL_SUCCESS
        });
    }
    function errorModal() {
        Modal.error({
            title: constants.BOLESTI_SREDSTVA_MODAL_ERROR_TITLE,
            content: constants.BOLESTI_SREDSTVA_MODAL_ERROR,

        });
    }

    const getSveBolesti = () => {
        bolestService.getAllBolestiForCascader()
            .then(response => {
                setBolesti(response.data)
            })
            .catch(error => {

            })
    }
    const getSvaSredstva = () => {
        sredstvaService.getAllSredstvaForCascader()
            .then(response => {
                setSredstva(response.data)
            })
            .catch(error => {

            })
    }
    const getSveFaze = () => {
        fazeService.getAllFazeForCascader()
            .then(response => {
                setFaze(response.data)
            })
            .catch(error => {

            })
    }

    const unesiBolestSredstvo = () => {
        console.log(bolestSredstvoSelected+" "+sredstvoSelected);
        bfsService.addBolestSredstvo(bolestSredstvoSelected,sredstvoSelected)
        .then(response => {
            successModal();
        }).catch((error) => {
            errorModal();
        })
    }
    const unesiBolestFaza = () => {
        console.log(bolestFazaSelected+" "+fazaSelected);
        bfsService.addBolestFaza(bolestFazaSelected,fazaSelected)
        .then(response => {
            successModal();
        }).catch((error) => {
            errorModal();
        })
    }




    useEffect(() => {
        getSveBolesti();
        getSvaSredstva();
        getSveFaze();
    }, []);

    return (

        <div className="splitter-div">
            <div className="left-div">
                <Form
                    form={form1}
                    name="basic"
                    className="forma-sredstva-bolest"
                >
                    <h1 className="form-title">{constants.BOLESTI_SREDSTVA_BOLEST_SREDSTVO_TITLE}</h1>
                    <p className="form-label">{constants.BOLESTI_SREDSTVA_BOLEST_CASCADE}</p>
                    <Cascader
                        placeholder="Odaberite"
                        options={bolesti}
                        onChange={e => {
                            setBolestSredstvoSelected(e[0].toString())
                        }}
                    />
                    <p className="form-label">{constants.BOLESTI_SREDSTVA_SREDSTV_CASCADE}</p>
                    <Cascader
                        placeholder="Odaberite"
                        options={sredstva}
                        onChange={e => {
                            setSredstvoSelected(e[0].toString())
                        }}
                    />
                    <Form.Item style={{ padding: "2%" }}>
                        <Button htmlType="submit"
                            onClick={() => unesiBolestSredstvo()}
                        >
                            {constants.BOLESTI_SREDSTVA_BUTTON_UNOS}
                        </Button>
                    </Form.Item>
                </Form>
            </div>
            <div className="right-div">

                <Form
                    form={form2}
                    name="basic"
                    className="forma-sredstva-bolest"
                >
                    <h1 className="form-title">{constants.BOLESTI_SREDSTVA_BOLEST_FENOFAZA_TITLE}</h1>
                    <p className="form-label">{constants.BOLESTI_SREDSTVA_BOLEST_CASCADE}</p>
                    <Cascader
                        placeholder="Odaberite"
                        options={bolesti}
                        onChange={e => {
                            setBolestFazaSelected(e[0].toString())
                        }}
                    />
                    <p className="form-label">{constants.BOLESTI_SREDSTVA_FENOFAZA_CASCADE}</p>
                    <Cascader
                        placeholder="Odaberite"
                        options={faze}
                        onChange={e => {
                            setFazaSelected(e[0].toString())
                        }}
                    />
                    <Form.Item style={{ padding: "2%" }}>
                        <Button htmlType="submit"
                            onClick={() => unesiBolestFaza()}
                        >
                            {constants.BOLESTI_SREDSTVA_BUTTON_UNOS}
                        </Button>
                    </Form.Item>
                </Form>
            </div>
        </div>
    )
}

export default PregledBolestiISredstvaForm;