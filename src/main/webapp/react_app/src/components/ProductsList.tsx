import React, {useState, useEffect} from "react";
import ProductDataService from "../services/ProductService";
import IProductData from "../types/Product";

const ProductsList: React.FC = () => {
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
                {/*<div>*/}
                {/*    <a href="<spring:url value="/admin/products/new" />">*/}
                {/*        <button type="button" className="button-edit">*/}
                {/*            <spring:message code="products.message.addNewProduct"/>*/}
                {/*        </button>*/}
                {/*    </a>*/}
                {/*</div>*/}

                {/*<div>*/}
                {/*    <p className="heading">*/}
                {/*        <spring:message code="products.message.allProducts"/>*/}
                {/*    </p>*/}
                {/*</div>*/}

                <div>
                    <table className="list-table">
                        <thead>
                        <tr className="head">
                            <th>
                                name
                                {/*<spring:message code="product.details.model.label"/>*/}
                            </th>
                            <th>
                                description
                                {/*<spring:message code="product.details.model.label"/>*/}
                            </th>
                            {/*<th>*/}
                            {/*    <spring:message code="product.details.unitPrice.label"/>*/}
                            {/*</th>*/}
                            {/*<th>*/}
                            {/*    <spring:message code="product.details.category.label"/>*/}
                            {/*</th>*/}
                            {/*<th>*/}
                            {/*    <spring:message code="manufacturer.details.brand.label"/>*/}
                            {/*</th>*/}
                            {/*<th>*/}
                            {/*    <spring:message code="product.details.specifications.label"/>*/}
                            {/*</th>*/}
                            {/*<th>*/}
                            {/*    <spring:message code="products.message.addSpecifications"/>*/}
                            {/*</th>*/}
                            {/*<th>*/}
                            {/*    <spring:message code="edit"/>*/}
                            {/*</th>*/}
                            {/*<th>*/}
                            {/*    <spring:message code="delete"/>*/}
                            {/*</th>*/}
                        </tr>
                        </thead>
                        <tbody>
                        {products.map((product) => (
                        // <c:forEach items="${products}" var="product">
                            <tr key={product.id}>
                                <td>{product.name}</td>
                                <td>{product.description}</td>
                                {/*<td className="text-right">${product.unitPrice} PLN</td>*/}
                                {/*<td>${product.category.name}</td>*/}
                                {/*<td>${product.manufacturer.brand}</td>*/}
                                {/*<td className="option"><a href="/products/${product.id}">*/}
                                {/*    <spring:message code="product.details.specifications.label"/>*/}
                                {/*</a></td>*/}
                                {/*<td className="option">*/}
                                {/*    <c:if test="${product.productSpecifications == null}">*/}
                                {/*        <a href="<c:url value="/admin/products/spec/${product.id}"/>">*/}
                                {/*            <spring:message code="products.message.add"/>*/}
                                {/*        </a>*/}
                                {/*    </c:if>*/}
                                {/*</td>*/}
                                {/*<td className="option"><a href="/admin/products/edit/${product.id}">*/}
                                {/*    <spring:message code="edit"/>*/}
                                {/*</a></td>*/}
                                {/*<td className="option"><a href="/admin/products/delete/${product.id}">*/}
                                {/*    <spring:message code="delete"/>*/}
                                {/*</a></td>*/}
                            </tr>
                        ))}
                        </tbody>
                         {/*</c:forEach>*/}
                    </table>
                </div>
            </div>
        </section>

        // <div>
        //     <h4>Products list</h4>
        //     <table>
        //         <thead>
        //         <tr>
        //             <th>id</th>
        //             <th>name</th>
        //             <th>description</th>
        //         </tr>
        //         </thead>
        //         <tbody>
        //         {products.map((product) => (
        //             <tr key={product.id}>
        //                 <td>{product.id}</td>
        //                 <td>{product.name}</td>
        //                 <td>{product.description}</td>
        //             </tr>
        //         ))}
        //         </tbody>
        //     </table>
        // </div>
    );
};

export default ProductsList;