import { Modal } from "antd";
import { useAppDispatch } from "../store/store";
import IDefaultPagingData from "../types/IDefaultPagingData";
import IFenofazaData from "../types/IFenofazaData";
import axiosInstance from "./axiosInstance";
import constant from "../constantsUI/constantsUI";

function successModal(type: string) {
  if (type === "insert") {
    Modal.success({
      title: constant.FENOFAZA_SUCCESS_TITLE,
      content: constant.FENOFAZA_SUCCESS,
    });
  } else if ((type = "delete")) {
    Modal.success({
      title: constant.BOLEST_BRISANJE_USPJELO_NASLOV,
      content: constant.BOLEST_BRISANJE_USPJELO,
    });
  }
}
function errorModal(type: string) {
  if (type === "insert") {
    Modal.error({
      title: constant.FENOFAZA_ERROR_TITLE,
      content: constant.FENOFAZA_ERROR,
    });
  } else if ((type = "delete")) {
    Modal.error({
      title: constant.BOLEST_BRISANJE_FAIL_NASLOV,
      content: constant.BOLEST_BRISANJE_FAIL,
    });
  }
}

export const addFenofaza = async (data: IFenofazaData) => {
  await axiosInstance
    .post("vineApp/api/fenofaza", data)
    .then((response) => {
      successModal("insert");
    })
    .catch(() => {
      errorModal("insert");
    });
};
export const updateFenofaza = async (data: IFenofazaData) => {
  await axiosInstance
    .put("vineApp/api/fenofaza", data)
    .then((response) => {
      successModal("insert");
    })
    .catch(() => {
      errorModal("insert");
    });
};

export const deleteFenofazaId = async (id: number) => {
  await axiosInstance
    .delete("vineApp/api/fenofaza/" + id)
    .then((response) => {
      successModal("delete");
    })
    .catch(() => {
      errorModal("delete");
    });
};

export const getFenofazePaged = async (data: IDefaultPagingData) => {
  const response = await axiosInstance.get("vineApp/api/fenofaza", {
    params: data,
  });
  return response.data;
};

export const getFenofazeByNamePaged = async (
  data: IDefaultPagingData,
  name: string
) => {
  const response = await axiosInstance.get(
    "vineApp/api/fenofaza/by-name/" + name,
    {
      params: data,
    }
  );
  return response.data;
};
