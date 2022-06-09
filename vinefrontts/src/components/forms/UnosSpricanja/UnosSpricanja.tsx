import { FunctionComponent, useState, useEffect } from "react";
import { Table, Pagination, Button, Tag, Modal } from "antd";
import { EyeOutlined } from "@ant-design/icons";
import constant from "../../../constantsUI/constantsUI";
import "./UnosSpricanjaCSS.css";
import DateConverter from "../../../feature/dateConverter";
import TableUpdateDelete from "../CustomJSX/TableUpdateDelete";
import UnosSpricanjaModal from "./UnosSpricanjaModal";
import IDefaultPagingData from "../../../types/IDefaultPagingData";
import SpricanjeService from "../../../services/SpricanjeService";
import ISpricanjeData from "../../../types/ISpricanjeData";
import InsertSredstvoForSpricanjeModal from "./InsertSredstvoForSpricanjeModal";
import SpricanjeSredstvoService from "../../../services/SpricanjeSredstvoService";
import ISredstvaFilter from "../../../services/ISredstvaFilter";
import b64BlobConverter from "../../../feature/base64ToURL";
import SredstvoOmjer from "../CustomJSX/SredstvoOmjer";

const UnosSpricanja: FunctionComponent = () => {
  const size = 2;
  const [pageNo, setPageNo] = useState(0);
  const [totalItems, setTotalItems] = useState(0);
  const [tableData, setTableData] = useState([]);
  const [modalSpricanjeVisible, setModalSpricanjeVisible] = useState(false);
  const [isSpricanjeUpdate, setIsSpricanjeUpdate] = useState(false);
  const [updateData, setUpdatedata] = useState<ISpricanjeData>({
    date: "",
    description: "",
    id: "",
    userId: "",
    water: "",
  });

  const sredstvasize = 3;
  const [sredstvaVisible, setSredstvaVisible] = useState(false);
  const [sredstvaPageNo, setSredstvaPageNo] = useState(0);
  const [sredstvaTotalItems, setSredstvaTotalItems] = useState(0);
  const [sredstvaTableData, setSredstvatableData] = useState([]);
  const [litraza, setLitraza] = useState("");
  const [sredstvoModalVisible, setSredstvoModalVisible] = useState(false);
  const [spricanjeId, setSpricanjeId] = useState("");
  const [isSredstvoUpdate, setIsSredstvoUpdate] = useState(false);
  const [sredstvoUpdateUtrosak, setSredstvoUpdateUtrosak] = useState("");
  const [sredstvoUpdateNapomena, setSredstvoUpdateNapomena] = useState("");
  const [zapisId, setZapisId] = useState("");
  const spricanjeService = new SpricanjeService();
  const spricanjeSredstvoService = new SpricanjeSredstvoService();
  const converterB64 = new b64BlobConverter();

  const promijeniStranicu = (page: number, size: number | undefined) => {
    page - 1 <= 0 ? setPageNo(0) : setPageNo(page - 1);
  };
  const sredstvaPromijeniStranicu = (
    page: number,
    size: number | undefined
  ) => {
    page - 1 <= 0 ? setSredstvaPageNo(0) : setSredstvaPageNo(page - 1);
  };

  const editSpricanje = (text: any, record: any) => {
    setIsSpricanjeUpdate(true);
    setModalSpricanjeVisible(true);
    setUpdatedata({ date: "", description: "", id: "", userId: "", water: "" });
  };

  const deleteSpricanje = (record: any) => {
    spricanjeService
      .deleteById(record.id)
      .then(() => {
        Modal.success({
          title: constant.SPRICANJE_MODAL_BRISANJE_SUCCESS_TITLE,
          content: constant.SPRICANJE_MODAL_BRISANJE_SUCCESS,
        });
        getInitialData();
      })
      .catch(() => {
        Modal.error({
          title: constant.SPRICANJE_MODAL_BRISANJE_FAIL_TITLE,
          content: constant.SPRICANJE_MODAL_BRISANJE_FAIL,
        });
      });
  };

  const deleteSredstvo = (record: any) => {
    spricanjeSredstvoService
      .deleteById(record.id)
      .then(() => {
        Modal.success({
          title: constant.SPRICANJE_SREDSTVO_MODAL_BRISANJE_SUCCESS_TITLE,
          content: constant.SPRICANJE_SREDSTVO_MODAL_BRISANJE_SUCCESS,
        });
      })
      .catch(() => {
        Modal.error({
          title: constant.SPRICANJE_SREDSTVO_MODAL_BRISANJE_FAIL_TITLE,
          content: constant.SPRICANJE_SREDSTVO_MODAL_BRISANJE_FAIL,
        });
      });
    getInitialSredstvoData();
  };

  const editSredstvo = (text: any, record: any) => {
    setIsSredstvoUpdate(true);
    setSredstvoUpdateUtrosak(record.utrosak);
    setSredstvoUpdateNapomena(record.napomena);
    setZapisId(record.id);
    setSredstvoModalVisible(true);
  };

  const closeSpricanjeModal = () => {
    setModalSpricanjeVisible(false);
    setIsSpricanjeUpdate(false);
    getInitialData();
    getInitialSredstvoData();
  };

  const closeSredstvaModal = () => {
    setSredstvoModalVisible(false);
    getInitialSredstvoData();
  };

  const columns = [
    {
      title: constant.SPRICANJE_OPIS,
      dataIndex: "description",
    },
    {
      title: constant.SPRICANJE_DATUM,
      dataIndex: "date",
      render: (text: any) => DateConverter(text),
    },
    {
      title: constant.SPRICANJE_KOLICINA_VODE,
      dataIndex: "water",
      render: (text: any) => {
        return <p>{text} litara</p>;
      },
    },
    {
      title: "",
      dataIndex: "detalji",
      render: (text: any, record: any) => {
        return (
          <Tag
            icon={<EyeOutlined />}
            color="success"
            onClick={() => {
              setSredstvaVisible(true);
              setSpricanjeId(record.id);
              setLitraza(record.water);
            }}
          >
            Prikazi sredstva
          </Tag>
        );
      },
    },
    {
      title: "",
      dataIndex: "buttons",
      render: (text: any, record: any) =>
        TableUpdateDelete(
          () => editSpricanje(text, record),
          () => deleteSpricanje(record),
          constant.BOLEST_BRISANJE_PITANJE
        ),
    },
  ];

  const getInitialData = async () => {
    const data: IDefaultPagingData = {
      page: pageNo,
      size: size,
      sort: [],
    };
    spricanjeService.getSpricanja(data).then((response) => {
      setTableData(response.data.content);
      setTotalItems(response.data.totalElements);
      setPageNo(response.data.pageable.pageNumber);
    });
  };

  useEffect(() => {
    getInitialData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [pageNo]);

  const getInitialSredstvoData = () => {
    const data: ISredstvaFilter = {
      pageNo: sredstvaPageNo,
      size: sredstvasize,
      sort: [],
      id: spricanjeId,
    };
    if (spricanjeId !== "") {
      spricanjeSredstvoService.findBySpricanje(data).then((response) => {
        setSredstvatableData(response.data.content);
        setSredstvaTotalItems(response.data.totalElements);
        setSredstvaPageNo(response.data.pageable.pageNumber);
      });
    }
  };

  useEffect(() => {
    getInitialSredstvoData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [spricanjeId, sredstvaPageNo]);

  const sredstvaColumns = [
    {
      title: constant.SPRICANJE_SREDSTVA_NAZIV,
      dataIndex: "naziv",
    },
    {
      title: constant.SPRICANJE_SREDSTVA_NAPOMENA,
      dataIndex: "napomena",
    },
    {
      title: constant.SPRICANJE_SREDSTVA_TIP,
      dataIndex: "tip",
    },
    {
      dataIndex: "base64",
      render: (text: any, record: any) => {
        var blob = converterB64.b64toBlob(text, "");
        var blobUrl = URL.createObjectURL(blob);
        return <img className="img-normal-size" alt="" src={blobUrl} />;
      },
    },
    {
      title: constant.SPRICANJE_SREDSTVA_DOZIRANJE,
      dataIndex: "preporuceno",
      render: (text: any, record: any) => SredstvoOmjer(text, record),
    },
    {
      title: constant.SPRICANJE_SREDSTVA_DOZIRANJE_USER,
      dataIndex: "utrosak",
    },
    {
      title: constant.SPRICANJE_SREDSTVA_KARENCA,
      dataIndex: "karenca",
    },

    {
      title: "",
      dataIndex: "buttons",
      render: (text: any, record: any) =>
        TableUpdateDelete(
          () => editSredstvo(text, record),
          () => deleteSredstvo(record),
          constant.BOLEST_BRISANJE_PITANJE
        ),
    },
  ];

  return (
    <div className="tip-sredstva-main-div">
      <h1 className="form-title">{constant.SPRICANJE_NASLOV}</h1>
      <Table
        className="tablica-tip-sredstva"
        columns={columns}
        dataSource={tableData}
        pagination={false}
        rowKey={(record) => record.id}
      />
      <div className="right-float">
        <Button
          type="primary"
          htmlType="submit"
          onClick={() => setModalSpricanjeVisible(true)}
        >
          {constant.SPRICANJE_UNOS_SPRICANJA}
        </Button>
      </div>
      <Pagination
        pageSize={size}
        total={totalItems}
        onChange={promijeniStranicu}
      />
      {sredstvaVisible ? (
        <div style={{ paddingTop: "3%" }}>
          <h1 className="form-title">{"Kolicina: " + litraza + " L"}</h1>
          <Table
            className="tablica-tip-sredstva"
            columns={sredstvaColumns}
            dataSource={sredstvaTableData}
            pagination={false}
            rowKey="naziv"
          />
          <div className="right-float">
            <Button
              type="primary"
              htmlType="submit"
              onClick={() => setSredstvoModalVisible(true)}
            >
              {constant.SPRICANJE_DODAJ_NOVO_SREDSTVO}
            </Button>
          </div>
          <Pagination
            pageSize={sredstvasize}
            total={sredstvaTotalItems}
            onChange={sredstvaPromijeniStranicu}
          />
        </div>
      ) : (
        ""
      )}

      <UnosSpricanjaModal
        isVisible={modalSpricanjeVisible}
        isUpdate={isSpricanjeUpdate}
        updateData={updateData}
        closeModal={closeSpricanjeModal}
      />
      <InsertSredstvoForSpricanjeModal
        isVisible={sredstvoModalVisible}
        spricanjeId={spricanjeId}
        isUpdate={isSredstvoUpdate}
        closeModal={closeSredstvaModal}
        water={litraza}
        utrosak={sredstvoUpdateUtrosak}
        napomena={sredstvoUpdateNapomena}
        zapisId={zapisId}
      />
    </div>
  );
};

export default UnosSpricanja;
