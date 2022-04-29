import { FunctionComponent, useState, useEffect } from "react";
import { Table, Pagination, Modal } from "antd";
import constant from "../../../constantsUI/constantsUI";
import "antd/dist/antd.css";
import "./BolestCSS.css";
import DefaultPagingData from "../../../types/IDefaultPagingData";
import IBolestdata from "../../../types/IBolestData";
import TableUpdateDelete from "../CustomJSX/TableUpdateDelete";
import DateConverter from "../../../feature/dateConverter";
import SearchByName from "../CustomJSX/SearchBy";
import TableFieldForDescription from "../CustomJSX/TableFieldForDescription";
import { useSelector } from "react-redux";
import { RootState } from "../../../store/reducer";
import {
  deleteBolestById,
  fetchBolestByName,
  fetchDataForBolestiPaged,
} from "../../../store/slices/bolestSlice";
import { useAppDispatch } from "../../../store/store";

type Props = {
  onUpdate: (step: number, data: IBolestdata) => void;
};

const BolestSifrarnik: FunctionComponent<Props> = ({ onUpdate }) => {
  const size = 3;
  const [pageNo, setPageNo] = useState(0);
  const totalItems = useSelector((state: RootState) => state.bolest.totalItems);
  const tableData = useSelector((state: RootState) => state.bolest.tableData);
  const datumClass = new DateConverter();
  //var highestRole = roleFetch.getHighestOrderRole(useAppSelector(state => state.login.myUserRole));
  var highestRole = 3;
  const dispatch = useAppDispatch();

  const promijeniStranicu = (page: number, size: number | undefined) => {
    setPageNo(page - 1);
  };
  const editClick = (text: any, record: any) => {
    const data: IBolestdata = {
      id: record.id,
      name: record.name,
      description: record.description,
      date: "",
      base64: "",
      picture_name: "",
    };
    onUpdate(0, data);
  };

  const deleteClick = (record: any) => {
    var id: string = record.id;
    dispatch(deleteBolestById(id))
      .unwrap()
      .then(() => {
        Modal.success({
          title: constant.BOLEST_BRISANJE_USPJELO_NASLOV,
          content: constant.BOLEST_BRISANJE_USPJELO,
        });
        const data: DefaultPagingData = {
          page: 0,
          size: size,
          sort: [],
        };
        dispatch(fetchDataForBolestiPaged(data));
      })
      .catch(() => {
        Modal.error({
          title: constant.BOLEST_BRISANJE_FAIL_NASLOV,
          content: constant.BOLEST_BRISANJE_FAIL,
        });
      });
  };

  const findByItemName = (name: string) => {
    const data: DefaultPagingData = {
      page: 0,
      size: size,
      sort: [],
    };
    dispatch(fetchBolestByName({ name, data }));
  };

  const getInitialData = async () => {
    const data: DefaultPagingData = {
      page: pageNo,
      size: size,
      sort: [],
    };
    dispatch(fetchDataForBolestiPaged(data));
  };

  useEffect(() => {
    getInitialData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [pageNo]);

  const columns = [
    {
      title: SearchByName(
        constant.SREDSTVA_SIFRARNIK_NAZIV,
        findByItemName,
        getInitialData
      ),
      dataIndex: "name",
    },
    {
      title: constant.BOLEST_SIFRARNIK_OPIS,
      dataIndex: "description",
      render: (text: any, record: any) =>
        TableFieldForDescription(text, record, "bolest"),
    },
    {
      title: constant.BOLEST_SIFRARNIK_DATUM,
      dataIndex: "date",
      render: (text: any) => datumClass.convertDateForTable(text),
    },
    highestRole > 1
      ? {
          title: "",
          dataIndex: "name",
          render: (text: any, record: any) =>
            TableUpdateDelete(
              () => editClick(text, record),
              () => deleteClick(record),
              constant.BOLEST_BRISANJE_PITANJE
            ),
        }
      : {},
  ];

  return (
    <div>
      <h1 className="form-title">{constant.BOLEST_SIFRARNIK}</h1>
      <Table
        className="tablica-tip-sredstva"
        columns={columns}
        dataSource={tableData}
        pagination={false}
        scroll={{ x: 150 }}
        rowKey="name"
      />
      <Pagination
        pageSize={size}
        total={totalItems}
        onChange={promijeniStranicu}
      />
    </div>
  );
};
export default BolestSifrarnik;
