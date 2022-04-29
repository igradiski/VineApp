import { FunctionComponent, useState, useEffect } from "react";
import { Table, Pagination, Modal } from "antd";
import constant from "../../../constantsUI/constantsUI";
import "antd/dist/antd.css";
import "./FenofazaCSS.css";
import DefaultPagingData from "../../../types/IDefaultPagingData";
import TableUpdateDelete from "../CustomJSX/TableUpdateDelete";
import SearchByName from "../CustomJSX/SearchBy";
import IFenofazaData from "../../../types/IFenofazaData";
import DateConverter from "../../../feature/dateConverter";
import { useSelector } from "react-redux";
import { RootState } from "../../../store/reducer";
import {
  deleteFenofazaById,
  fetchDataForFenofazaTablePaged,
  fetchFenofazeByName,
} from "../../../store/slices/fenofazaSlice";
import { useAppDispatch } from "../../../store/store";

type Props = {
  onUpdate: (step: number, data: IFenofazaData) => void;
};

const FenofazaSifrarnik: FunctionComponent<Props> = ({ onUpdate }) => {
  const size = 5;
  const [pageNo, setPageNo] = useState(0);
  const totalItems = useSelector(
    (state: RootState) => state.fenofaze.totalItems
  );
  const tableData = useSelector((state: RootState) => state.fenofaze.tableData);
  const datumClass = new DateConverter();
  //var highestRole = roleFetch.getHighestOrderRole(useAppSelector(state => state.login.myUserRole));

  var highestRole = 3;
  const promijeniStranicu = (page: number, size: number | undefined) => {
    setPageNo(page - 1);
  };

  const dispatch = useAppDispatch();

  function successModal() {
    Modal.success({
      title: constant.BOLEST_BRISANJE_USPJELO_NASLOV,
      content: constant.BOLEST_BRISANJE_USPJELO,
    });
  }
  function errorModal() {
    Modal.error({
      title: constant.BOLEST_BRISANJE_FAIL_NASLOV,
      content: constant.BOLEST_BRISANJE_FAIL,
    });
  }

  const editClick = (text: any, record: any) => {
    const data: IFenofazaData = {
      id: record.id,
      name: record.name,
      timeOfUsage: record.timeOfUsage,
      date: "",
    };
    onUpdate(0, data);
  };

  const deleteClick = (record: any) => {
    var id: number = record.id;
    dispatch(deleteFenofazaById(id))
      .unwrap()
      .then(() => {
        successModal();
        const data: DefaultPagingData = {
          page: 0,
          size: size,
          sort: [],
        };
        dispatch(fetchDataForFenofazaTablePaged(data));
      })
      .catch(() => {
        errorModal();
      });
    getInitialData();
  };

  const findByItemName = (name: string) => {
    console.log(name);
    var data: DefaultPagingData = {
      page: 0,
      size: size,
      sort: [],
    };
    dispatch(fetchFenofazeByName({ name, data }));
  };

  const getInitialData = async () => {
    const data: DefaultPagingData = {
      page: pageNo,
      size: size,
      sort: [],
    };
    dispatch(fetchDataForFenofazaTablePaged(data));
  };

  useEffect(() => {
    getInitialData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [pageNo]);

  const columns = [
    {
      title: SearchByName(
        constant.FENOFAZA_SIFRARNIK_NAZIV,
        findByItemName,
        getInitialData
      ),
      dataIndex: "name",
    },
    {
      title: constant.FENOFAZA_SIFRARNIK_VRIJEME,
      dataIndex: "timeOfUsage",
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
      <h1 className="form-title">{constant.FENOFAZA_NASLOV_SIFRARNIK}</h1>
      <Table
        className="tablica-tip-sredstva"
        columns={columns}
        dataSource={tableData}
        pagination={false}
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
export default FenofazaSifrarnik;
