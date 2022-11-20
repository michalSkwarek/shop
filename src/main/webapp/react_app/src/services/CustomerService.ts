import http from "../http-common";
import ICustomer from "../types/ICustomer";

const getAll = () => {
    return http.get<Array<ICustomer>>("/customers");
};

const get = (id: any) => {
    return http.get<ICustomer>(`/customers/${id}`);
};

const create = (data: ICustomer) => {
    return http.post<ICustomer>("/customers/create", data);
};

const update = (id: any, data: ICustomer) => {
    return http.put<any>(`/customers/${id}`, data);
};

const remove = (id: any) => {
    return http.delete<any>(`/customers/${id}`);
};

const CustomerService = {
    getAll,
    get,
    create,
    update,
    remove,
};

export default CustomerService;