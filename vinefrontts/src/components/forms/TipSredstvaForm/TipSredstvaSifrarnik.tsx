import { FunctionComponent, useState, useEffect } from "react";
import { Table, Pagination, Modal } from "antd";
import constant from "../../../constantsUI/constantsUI";
import TipSredstvaService from "../../../services/TipSredstvaService";
import IDefaultPagingData from "../../../types/IDefaultPagingData";
import "antd/dist/antd.css";
import "./TipSredstvaCSS.css";
import ITipSredstvaData from "../../../types/ITipSredstvaData";
import DateConverter from "../../../feature/dateConverter";
import TableUpdateDelete from "../CustomJSX/TableUpdateDelete";
import SearchByName from "../CustomJSX/SearchBy";
import roleFetcher from "../../../feature/roleFetcher";
import { useAppDispatch } from "../../../store/store";
import {
  deleteTipSredstvaById,
  fetchDataForTipSredstvaTablePaged,
  tipSredstvaByName,
} from "../../../store/slices/tipSredstvaSlice";
import { useSelector } from "react-redux";
import { RootState } from "../../../store/reducer";

type Props = {
  onUpdate: (step: number, data: ITipSredstvaData) => void;
};

const TipSredstvaSifrarnik: FunctionComponent<Props> = ({ onUpdate }) => {
  const size = 4;
  const [pageNo, setPageNo] = useState(0);
  const totalItems = useSelector(
    (state: RootState) => state.tipSredstva.totalItems
  );
  const tableData = useSelector(
    (state: RootState) => state.tipSredstva.tableData
  );
  const tipSredstvaSrc = new TipSredstvaService();
  const datumClass = new DateConverter();
  const roleFetch = new roleFetcher();
  //var highestRole = roleFetch.getHighestOrderRole(useAppSelector(state => state.login.myUserRole));
  var highestRole = 3;

  const dispatch = useAppDispatch();

  const promijeniStranicu = (page: number, size: number | undefined) => {
    setPageNo(page - 1);
  };

  const editClick = (text: any, record: any) => {
    const data: ITipSredstvaData = {
      id: record.id,
      name: record.name,
      date: "",
    };
    onUpdate(0, data);
  };

  const deleteClick = (record: any) => {
    dispatch(deleteTipSredstvaById(record.id))
      .unwrap()
      .then(() => {
        Modal.success({
          title: constant.BOLEST_BRISANJE_USPJELO_NASLOV,
          content: constant.BOLEST_BRISANJE_USPJELO,
        });
        const data: IDefaultPagingData = {
          page: 0,
          size: size,
          sort: [],
        };
        dispatch(fetchDataForTipSredstvaTablePaged(data));
      })
      .catch(() => {
        Modal.error({
          title: constant.BOLEST_BRISANJE_FAIL_NASLOV,
          content: constant.BOLEST_BRISANJE_FAIL,
        });
      });
  };

  const findByItemName = (name: string) => {
    const data: IDefaultPagingData = {
      page: 0,
      size: size,
      sort: [],
    };
    dispatch(tipSredstvaByName({ name, data }));
  };

  const getInitialData = async () => {
    const data: IDefaultPagingData = {
      page: pageNo,
      size: size,
      sort: [],
    };
    dispatch(fetchDataForTipSredstvaTablePaged(data));
  };

  useEffect(() => {
    getInitialData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [pageNo]);

  const columns = [
    {
      title: SearchByName(
        constant.TIP_SREDSTVA_TBL_NAZIV,
        findByItemName,
        getInitialData
      ),
      dataIndex: "name",
      key: "name",
    },
    {
      title: constant.TIP_SREDSTVA_TBL_DATUM,
      dataIndex: "date",
      key: "date",
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
      <h1 className="form-title">{constant.TIP_SREDSTVA_NASLOV_SIFRARNIK}</h1>
      <Table
        className="tablica-tip-sredstva"
        columns={columns}
        dataSource={tableData}
        pagination={false}
        rowKey="name"
        scroll={{ x: 150 }}
      />
      <Pagination
        pageSize={size}
        total={totalItems}
        onChange={promijeniStranicu}
      />
    </div>
  );
};
export default TipSredstvaSifrarnik;
