import React, {useState, useEffect, ChangeEvent} from "react";
import ProductService from "../../services/ProductService";
import IProduct from "../../types/IProduct";
import {useTranslation} from "react-i18next";
import {useParams, useNavigate} from "react-router-dom";
import ICategory from "../../types/ICategory";

const Product: React.FC = () => {
    const {t} = useTranslation();
    const {id} = useParams();
    let navigate = useNavigate();

    const initialProductState = {
        id: 0,
        name: "",
        description: "",
        unitPrice: null,
        category: null,
        company: null,
    };

    const [currentProduct, setCurrentProduct] = useState<IProduct>(initialProductState);
    // const [currentCategory, setCurrentCategory] = useState<ICategoryData>({null});
    // const [categories, setCategories] = useState<ICategoryData>({});

    const getProduct = (id: string) => {
        ProductService.get(id)
            .then((response: any) => {
                setCurrentProduct(response.data);
                console.log(response.data);
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };

    useEffect(() => {
        if (id)
            getProduct(id);
    }, [id]);

    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target;
        setCurrentProduct({...currentProduct, [name]: value});
    };

    const updateProduct = () => {
        ProductService.update(currentProduct.id, currentProduct)
            .then((response: any) => {
                console.log(response.data);
                navigate("/products");
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };

    return (
        <div className="edit-form">
            <h4>Product</h4>
            <form>
                <div className="form-group">
                    <label htmlFor="name">{t("product.details.name.label")}</label>
                    <input
                        type="text"
                        className="form-control"
                        id="name"
                        name="name"
                        value={currentProduct.name}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="description">{t("product.details.description.label")}</label>
                    <input
                        type="text"
                        className="form-control"
                        id="description"
                        name="description"
                        value={currentProduct.description}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="unitPrice">{t("product.details.unitPrice.label")}</label>
                    <input
                        type="text"
                        className="form-control"
                        id="unitPrice"
                        name="unitPrice"
                        // value={currentProduct.unitPrice}
                        onChange={handleInputChange}
                    />
                </div>
                {/*<div>*/}
                {/*    <select>*/}
                {/*        {categories.map((category) => (*/}
                {/*            <option value={category.value}>{category.label}</option>*/}
                {/*        ))}*/}
                {/*    </select>*/}
                {/*</div>*/}
            </form>

            <button type="submit" onClick={updateProduct}>
                Update
            </button>
        </div>
    );
};

export default Product;