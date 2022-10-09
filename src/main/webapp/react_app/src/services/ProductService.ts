import http from "../http-common";
import IProductData from "../types/Product";

const getAll = () => {
    return http.get<Array<IProductData>>("/products");
};

const ProductService = {
    getAll,
};

export default ProductService;