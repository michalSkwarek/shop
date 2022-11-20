import http from "../http-common";
import ICompany from "../types/ICompany";

const getAll = () => {
    return http.get<Array<ICompany>>("/companies");
};

const get = (id: any) => {
    return http.get<ICompany>(`/companies/${id}`);
};

const create = (data: ICompany) => {
    return http.post<ICompany>("/companies/create", data);
};

const update = (id: any, data: ICompany) => {
    return http.put<any>(`/companies/${id}`, data);
};

const remove = (id: any) => {
    return http.delete<any>(`/companies/${id}`);
};

const CompanyService = {
    getAll,
    get,
    create,
    update,
    remove,
};

export default CompanyService;