import React, {useState, useEffect} from "react";
import {Link} from "react-router-dom";
import {useTranslation} from "react-i18next";
import IProduct from "../../types/IProduct";
import ProductService from "../../services/ProductService";
import {
    Grid,
    Stack,
    Button,
    TableContainer,
    Table,
    TableHead,
    TableBody,
    TableRow,
    TableCell
} from "@mui/material";

const ProductsList: React.FC = () => {
    const {t} = useTranslation();

    const [products, setProducts] = useState<Array<IProduct>>([]);

    useEffect(() => {
        retrieveProducts();
    }, []);

    const retrieveProducts = () => {
        ProductService.getAll()
            .then((response: any) => {
                setProducts(response.data);
                console.log(response.data);
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };

    const refreshList = () => {
        retrieveProducts();
    };

    const deleteProduct = (id: number) => {
        ProductService.remove(id)
            .then((response: any) => {
                console.log(response.data);
                refreshList();
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };

    return (
        <Grid container spacing={2}>
            {products.length === 0 ? (
                <Grid item xs={12}>
                    <h4>{t("products.message.thereAreNoProducts")}</h4>
                </Grid>
            ) : (
                <Grid item xs={12}>
                    <Stack spacing={2}>
                        <h4>{t("products.message.allProducts")}</h4>
                        <TableContainer>
                            <Table>
                                <TableHead>
                                    <TableRow selected>
                                        <TableCell>
                                            #
                                        </TableCell>
                                        <TableCell>
                                            {t("product.details.name.label")}
                                        </TableCell>
                                        <TableCell>
                                            {t("product.details.description.label")}
                                        </TableCell>
                                        <TableCell>
                                            {t("product.details.unitPrice.label")}
                                        </TableCell>
                                        <TableCell>
                                            {t("product.details.category.label")}
                                        </TableCell>
                                        <TableCell>
                                            {t("product.details.company.label")}
                                        </TableCell>
                                        <TableCell>
                                            {t("actions")}
                                        </TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {products.map((product, index) => (
                                        <TableRow key={product.id} hover>
                                            <TableCell scope="row">{++index}</TableCell>
                                            <TableCell>{product.name}</TableCell>
                                            <TableCell>{product.description}</TableCell>
                                            <TableCell
                                                align="right">{product.unitPrice ? product.unitPrice.toFixed(2).concat(" PLN") : ""}</TableCell>
                                            <TableCell>{product.category ? product.category.name : ""}</TableCell>
                                            <TableCell>{product.company ? product.company.name : ""}</TableCell>
                                            <TableCell>
                                                <Stack direction="row" spacing={2}>
                                                    <Button color="info" variant="outlined" component={Link}
                                                            to={"/products/" + product.id + "/details"}>{t("details")}</Button>
                                                    <Button color="warning" variant="outlined" component={Link}
                                                            to={"/products/" + product.id}>{t("edit")}</Button>
                                                    <Button color="error" variant="outlined"
                                                            onClick={() => deleteProduct(product.id)}>{t("delete")}</Button>
                                                </Stack>
                                            </TableCell>
                                        </TableRow>
                                    ))}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </Stack>
                </Grid>
            )}
            <Grid item xs={12}>
                <Stack direction="row" spacing={2}>
                    <Button color="primary" variant="outlined" component={Link}
                            to={"/products/create"}>{t("products.message.addNewProduct")}</Button>
                </Stack>
            </Grid>
        </Grid>
    );
};

export default ProductsList;