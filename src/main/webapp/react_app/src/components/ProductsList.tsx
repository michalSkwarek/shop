import React, {useState, useEffect} from "react";
import ProductDataService from "../services/ProductService";
import IProductData from "../types/Product";
import {useTranslation} from "react-i18next";

const ProductsList: React.FC = () => {
    const {t} = useTranslation();

    const [products, setProducts] = useState<Array<IProductData>>([]);

    useEffect(() => {
        retrieveProducts();
    }, []);

    const retrieveProducts = () => {
        ProductDataService.getAll()
            .then((response: any) => {
                setProducts(response.data);
                console.log(response.data);
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };

    return (
        <section>
            <div className="table">
                <div>
                    <a href={"/admin/products/new"}>
                        <button type="button" className="button-edit">
                            {t("products.message.addNewProduct")}
                        </button>
                    </a>
                </div>

                <div>
                    <p className="heading">
                        {t("products.message.allProducts")}
                    </p>
                </div>

                <div>
                    <table className="list-table">
                        <thead>
                        <tr className="head">
                            <th>
                                Name
                                {/*<spring:message code="product.details.model.label"/>*/}
                            </th>
                            <th>
                                Description
                                {/*<spring:message code="product.details.model.label"/>*/}
                            </th>
                            <th>
                                Unit price
                                {/*<spring:message code="product.details.unitPrice.label"/>*/}
                            </th>
                            <th>
                                Category
                                {/*<spring:message code="product.details.category.label"/>*/}
                            </th>
                            <th>
                                Company
                                {/*<spring:message code="manufacturer.details.brand.label"/>*/}
                            </th>
                            <th>
                                Specs
                                {/*<spring:message code="product.details.specifications.label"/>*/}
                            </th>
                            <th>
                                Add specs
                                {/*<spring:message code="products.message.addSpecifications"/>*/}
                            </th>
                            <th>
                                Edit
                                {/*<spring:message code="edit"/>*/}
                            </th>
                            <th>
                                Delete
                                {/*<spring:message code="delete"/>*/}
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        {products.map((product) => (
                            <tr key={product.id}>
                                <td>{product.name}</td>
                                <td>{product.description}</td>
                                <td className="text-right">{product.unitPrice} PLN</td>
                                <td>{product.category.name}</td>
                                <td>{product.company.name}</td>
                                <td className="option">
                                    <a href={"/products/" + product.id}>
                                        Specs
                                        {/*<spring:message code="product.details.specifications.label"/>*/}
                                    </a>
                                </td>
                                <td className="option">
                                    {/*<c:if test="${product.productSpecifications == null}">*/}
                                    {/*    <a href="<c:url value="/admin/products/spec/${product.id}"/>">*/}
                                    {/*        <spring:message code="products.message.add"/>*/}
                                    {/*    </a>*/}
                                    {/*</c:if>*/}
                                </td>
                                <td className="option">
                                    <a href={"/admin/products/edit/" + product.id}>
                                        Edit
                                        {/*<spring:message code="edit"/>*/}
                                    </a>
                                </td>
                                <td className="option">
                                    <a href={"/admin/products/delete/" + product.id}>
                                        Delete
                                        {/*<spring:message code="delete"/>*/}
                                    </a>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
    );
};

export default ProductsList;