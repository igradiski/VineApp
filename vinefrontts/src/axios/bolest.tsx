import IBolestdata from "../types/IBolestData";
import axiosInstance from "./axiosInstance";
import IDefaultPagingData from "../types/IDefaultPagingData";

export const addBolest = async (data: IBolestdata) => {
  var response = await axiosInstance.post("vineApp/api/bolest", data);
  return response.data;
};

export const getBolestiPaged = async (data: IDefaultPagingData) => {
  var response = await axiosInstance.get("vineApp/api/bolest");
  return response.data;
};

export const getBolestByNamePaged = async (
  data: IDefaultPagingData,
  name: string
) => {
  const response = await axiosInstance.get(
    "vineApp/api/bolest/by-name/" + name,
    {
      params: data,
    }
  );
  return response.data;
};

export const getBolestCard = async (id: string) => {
  var response = await axiosInstance.get(
    "vineApp/api/bolest/bolest-card/" + id
  );
  return response.data;
};

export const updateBolest = async (data: IBolestdata) => {
  var response = await axiosInstance.put("vineApp/api/bolest", data);
  return response.data;
};

export const deleteBolestId = async (id: string) => {
  var response = await axiosInstance.delete("vineApp/api/bolest/" + id);
  return response.data;
};
