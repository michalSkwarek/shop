import http from "../http-common";
import IAccount from "../types/IAccount";

const getAll = () => {
    return http.get<Array<IAccount>>("/accounts");
};

const get = (id: any) => {
    return http.get<IAccount>(`/accounts/${id}`);
};

const create = (data: IAccount) => {
    return http.post<IAccount>("/auth/register", data);
};

const update = (id: any, data: IAccount) => {
    return http.put<any>(`/accounts/${id}`, data);
};

const remove = (id: any) => {
    return http.delete<any>(`/accounts/${id}`);
};

const AccountService = {
    getAll,
    get,
    create,
    update,
    remove,
};

export default AccountService;