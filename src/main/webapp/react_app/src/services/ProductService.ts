import http from "../http-common";
import IProduct from "../types/IProduct";

const getAll = () => {
    return http.get<Array<IProduct>>("/products");
};

const get = (id: any) => {
    return http.get<IProduct>(`/products/${id}`);
};

const create = (data: IProduct) => {
    return http.post<IProduct>("/products/create", data);
};

const update = (id: any, data: IProduct) => {
    return http.put<any>(`/products/${id}`, data);
};

const remove = (id: any) => {
    return http.delete<any>(`/products/${id}`);
};

const ProductService = {
    getAll,
    get,
    create,
    update,
    remove,
};

export default ProductService;