import http from "../http-common";
import IAddress from "../types/IAddress";

const getAll = () => {
    return http.get<Array<IAddress>>("/addresses");
};

const get = (id: any) => {
    return http.get<IAddress>(`/addresses/${id}`);
};

const create = (data: IAddress) => {
    return http.post<IAddress>("/addresses/create", data);
};

const update = (id: any, data: IAddress) => {
    return http.put<any>(`/addresses/${id}`, data);
};

const remove = (id: any) => {
    return http.delete<any>(`/addresses/${id}`);
};

const AddressService = {
    getAll,
    get,
    create,
    update,
    remove,
};

export default AddressService;