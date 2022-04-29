import IDefaultPagingData from "../types/IDefaultPagingData";
import IFenofazaData from "../types/IFenofazaData";
import axiosInstance from "./axiosInstance";

export const addFenofaza = async (data: IFenofazaData) => {
  var response = await axiosInstance.post("vineApp/api/fenofaza", data);
  return response.data;
};
export const updateFenofaza = async (data: IFenofazaData) => {
  var response = await axiosInstance.put("vineApp/api/fenofaza", data);
  return response.data;
};

export const deleteFenofazaId = async (id: number) => {
  var response = await axiosInstance.delete("vineApp/api/fenofaza/" + id);
  return response.data;
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
