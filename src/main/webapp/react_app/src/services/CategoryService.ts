import http from "../http-common";
import ICategory from "../types/ICategory";

const getAll = () => {
    return http.get<Array<ICategory>>("/categories");
};

const get = (id: any) => {
    return http.get<ICategory>(`/categories/${id}`);
};

const create = (data: ICategory) => {
    return http.post<ICategory>("/categories/create", data);
};

const update = (id: any, data: ICategory) => {
    return http.put<any>(`/categories/${id}`, data);
};

const remove = (id: any) => {
    return http.delete<any>(`/categories/${id}`);
};

const CategoryService = {
    getAll,
    get,
    create,
    update,
    remove,
};

export default CategoryService;