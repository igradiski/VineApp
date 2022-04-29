import IDefaultPagingData from "../types/IDefaultPagingData";
import ITipSredstvaData from "../types/ITipSredstvaData";
import axiosInstance from "./axiosInstance";

export const addTipSredstva = async (data: ITipSredstvaData) => {
  var response = await axiosInstance.post("vineApp/api/tip_sredstva", data);
  return response.data;
};

export const updateTipSredstva = async (data: ITipSredstvaData) => {
  var response = await axiosInstance.put("vineApp/api/tip_sredstva", data);
  return response.data;
};

export const getTipSredstvaPaged = async (data: IDefaultPagingData) => {
  const response = await axiosInstance.get("vineApp/api/tip_sredstva", {
    params: data,
  });
  return response.data;
};

export const deleteTipSredstvaId = async (id: number) => {
  var response = await axiosInstance.delete("vineApp/api/tip_sredstva/" + id);
  return response.data;
};

export const getTipSredstvaByNamePaged = async (
  data: IDefaultPagingData,
  name: string
) => {
  const response = await axiosInstance.get(
    "vineApp/api/tip_sredstva/by-name/" + name,
    {
      params: data,
    }
  );
  return response.data;
};
