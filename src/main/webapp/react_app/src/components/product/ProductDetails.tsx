import React, {useState, useEffect} from "react";
import {useParams, Link} from "react-router-dom";
import {useTranslation} from "react-i18next";
import IProduct from "../../types/IProduct";
import ProductService from "../../services/ProductService";
import {Grid, Stack, Button, Card, CardMedia, CardContent, TextField} from "@mui/material";

const ProductDetails: React.FC = () => {
    const {id} = useParams();
    const {t} = useTranslation();

    const initialProductState = {
        id: null,
        name: "",
        description: "",
        unitPrice: null,
        category: null,
        company: null,
    };

    const [currentProduct, setCurrentProduct] = useState<IProduct>(initialProductState);

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

    return (
        <Grid container spacing={2} justifyContent="center">
            <Grid item xs={4}>
                <Stack spacing={2}>
                    <h4>{t("products.message.productDetails")}</h4>
                    <Card>
                        <CardMedia
                            sx={{
                                width: "80%",
                                marginLeft: "10%",
                            }}
                            component="img"
                            alt={currentProduct.name}
                            src={require("./_iphone-7-plus.jpg")}
                        />
                        <CardContent>
                            <Stack spacing={2}>
                                <TextField
                                    type="text"
                                    size="small"
                                    disabled
                                    id="name"
                                    name="name"
                                    value={currentProduct.name}
                                    label={t("product.details.name.label")}
                                    sx={{
                                        "& .MuiInputBase-input.Mui-disabled": {
                                            WebkitTextFillColor: "black",
                                        },
                                    }}
                                    InputProps={{
                                        readOnly: true,
                                    }}
                                    InputLabelProps={{
                                        shrink: true,
                                        style: {color: "black"},
                                    }}
                                />

                                <TextField
                                    type="text"
                                    size="small"
                                    disabled
                                    id="unitPrice"
                                    name="unitPrice"
                                    value={currentProduct.unitPrice ? currentProduct.unitPrice.toFixed(2).concat(" PLN") : ""}
                                    label={t("product.details.unitPrice.label")}
                                    sx={{
                                        "& .MuiInputBase-input.Mui-disabled": {
                                            WebkitTextFillColor: "black",
                                        },
                                    }}
                                    InputProps={{
                                        readOnly: true,
                                    }}
                                    InputLabelProps={{
                                        shrink: true,
                                        style: {color: "black"},
                                    }}
                                />

                                <TextField
                                    type="text"
                                    size="small"
                                    disabled
                                    id="category"
                                    name="category"
                                    value={currentProduct.category ? currentProduct.category.name : ""}
                                    label={t("product.details.category.label")}
                                    sx={{
                                        "& .MuiInputBase-input.Mui-disabled": {
                                            WebkitTextFillColor: "black",
                                        },
                                    }}
                                    InputProps={{
                                        readOnly: true,
                                    }}
                                    InputLabelProps={{
                                        shrink: true,
                                        style: {color: "black"},
                                    }}
                                />

                                <TextField
                                    type="text"
                                    size="small"
                                    disabled
                                    id="company"
                                    name="company"
                                    value={currentProduct.company ? currentProduct.company.name : ""}
                                    label={t("product.details.company.label")}
                                    sx={{
                                        "& .MuiInputBase-input.Mui-disabled": {
                                            WebkitTextFillColor: "black",
                                        },
                                    }}
                                    InputProps={{
                                        readOnly: true,
                                    }}
                                    InputLabelProps={{
                                        shrink: true,
                                        style: {color: "black"},
                                    }}
                                />

                                <TextField
                                    type="text"
                                    size="small"
                                    multiline
                                    disabled
                                    id="description"
                                    name="description"
                                    value={currentProduct.description}
                                    label={t("product.details.description.label")}
                                    sx={{
                                        "& .MuiInputBase-input.Mui-disabled": {
                                            WebkitTextFillColor: "black",
                                        },
                                    }}
                                    InputProps={{
                                        readOnly: true,
                                    }}
                                    InputLabelProps={{
                                        shrink: true,
                                        style: {color: "black"},
                                    }}
                                />

                                <Button color="warning" variant="outlined" component={Link}
                                        to={"/products/" + currentProduct.id}>{t("edit")}</Button>
                            </Stack>
                        </CardContent>
                    </Card>
                </Stack>
            </Grid>
        </Grid>
    );
};

export default ProductDetails;