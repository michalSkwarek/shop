import React, {useState, ChangeEvent, useEffect} from "react";
import {useForm, Controller} from "react-hook-form";
import {Link} from "react-router-dom";
import {useTranslation} from "react-i18next";
import {yupResolver} from "@hookform/resolvers/yup";
import * as Yup from "yup";
import IProduct from "../../types/IProduct";
import ICategory from "../../types/ICategory";
import ICompany from "../../types/ICompany";
import ProductService from "../../services/ProductService";
import CategoryService from "../../services/CategoryService";
import CompanyService from "../../services/CompanyService";
import {
    Grid,
    Stack,
    Button,
    TextField,
    FormControl,
    InputLabel,
    Select,
    MenuItem,
    FormHelperText
} from "@mui/material";

const AddProduct: React.FC = () => {
    const {t} = useTranslation();

    const initialProductState = {
        id: null,
        name: "",
        description: "",
        unitPrice: null,
        category: null,
        company: null,
    };

    const validationSchema = Yup.object().shape({
        name: Yup.string()
            .required("product.name.validation.required"),
        description: Yup.string()
            .required("product.description.validation.required"),
        unitPrice: Yup.number()
            .typeError("product.unitPrice.validation.typeError")
            .required("product.unitPrice.validation.required")
            .min(0, "product.unitPrice.validation.min")
            .max(1_000_000, "product.unitPrice.validation.max")
            .test("isValidPattern", "product.unitPrice.validation.pattern", (value) => /^\d*.?\d{0,2}$/.test(String(value))),
        category: Yup.string()
            .required("product.category.validation.required")
            .nullable(),
        company: Yup.string()
            .required("product.company.validation.required")
            .nullable(),
    });

    const {
        register,
        handleSubmit,
        reset,
        control,
        formState: {errors}
    } = useForm<IProduct>({
        defaultValues: initialProductState,
        resolver: yupResolver(validationSchema)
    });

    const [product, setProduct] = useState<IProduct>(initialProductState);
    const [categories, setCategories] = useState<Array<ICategory>>([]);
    const [companies, setCompanies] = useState<Array<ICompany>>([]);
    const [submitted, setSubmitted] = useState<boolean>(false);

    useEffect(() => {
        retrieveCategories();
    }, []);

    const retrieveCategories = () => {
        CategoryService.getAll()
            .then((response: any) => {
                setCategories(response.data);
                console.log(response.data);
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };

    useEffect(() => {
        retrieveCompanies();
    }, []);

    const retrieveCompanies = () => {
        CompanyService.getAll()
            .then((response: any) => {
                setCompanies(response.data);
                console.log(response.data);
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };

    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target;
        setProduct({...product, [name]: value});
    };

    const handleInputChangeCategory = (event: ChangeEvent<HTMLInputElement>) => {
        const categoryId = event.target.value;
        const category = categories.find(c => c.id === categoryId);
        const {name} = event.target;
        setProduct({...product, [name]: category});
    };

    const handleInputChangeCompany = (event: ChangeEvent<HTMLInputElement>) => {
        const companyId = event.target.value;
        const company = companies.find(c => c.id === companyId);
        const {name} = event.target;
        setProduct({...product, [name]: company});
    };

    const saveProduct = () => {
        let data = {
            name: product.name,
            description: product.description,
            unitPrice: product.unitPrice,
            category: product.category,
            company: product.company,
        };

        ProductService.create(data)
            .then((response: any) => {
                setProduct({
                    id: response.data.id,
                    name: response.data.name,
                    description: response.data.description,
                    unitPrice: response.data.unitPrice,
                    category: response.data.category,
                    company: response.data.company,
                });
                setSubmitted(true);
                console.log(response.data);
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };

    const newProduct = () => {
        setProduct(initialProductState);
        setSubmitted(false);
    };

    return (
        <Grid container spacing={2}>
            {submitted ? (
                <Grid item xs={12}>
                    <Stack spacing={2}>
                        <h4>{t("products.message.youHaveAddedANewProductSuccessfully")}</h4>
                        <Stack direction="row" spacing={2}>
                            <Button color="primary" variant="outlined"
                                    onClick={() => {
                                        reset(initialProductState);
                                        newProduct();
                                    }}>{t("products.message.addNewProduct")}</Button>
                        </Stack>
                    </Stack>
                </Grid>
            ) : (
                <Grid item xs={12}>
                    <Stack spacing={2}>
                        <h4>{t("products.message.addNewProduct")}</h4>
                        <form>
                            <Stack spacing={2}>
                                <TextField
                                    {...register("name", {
                                        onChange: (event) => {
                                            handleInputChange(event);
                                        }
                                    })}
                                    required
                                    type="text"
                                    size="small"
                                    id="name"
                                    name="name"
                                    value={product.name}
                                    label={t("product.details.name.label")}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                    error={!!errors.name}
                                    helperText={t(errors.name?.message as string)}
                                />

                                <TextField
                                    {...register("description", {
                                        onChange: (event) => {
                                            handleInputChange(event);
                                        }
                                    })}
                                    required
                                    type="text"
                                    size="small"
                                    multiline
                                    id="description"
                                    name="description"
                                    value={product.description}
                                    label={t("product.details.description.label")}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                    error={!!errors.description}
                                    helperText={t(errors.description?.message as string)}
                                />

                                <TextField
                                    {...register("unitPrice", {
                                        onChange: (event) => {
                                            handleInputChange(event);
                                        }
                                    })}
                                    required
                                    type="number"
                                    size="small"
                                    id="unitPrice"
                                    name="unitPrice"
                                    value={product.unitPrice || ""}
                                    placeholder={"1234.56"}
                                    label={t("product.details.unitPrice.label")}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                    error={!!errors.unitPrice}
                                    helperText={t(errors.unitPrice?.message as string)}
                                />

                                <FormControl
                                    required
                                    size="small"
                                    error={!!errors.category}
                                >
                                    <InputLabel
                                        id="product-details-category-label"
                                        shrink
                                    >
                                        {t("product.details.category.label")}
                                    </InputLabel>
                                    <Controller
                                        name="category"
                                        control={control}
                                        render={({field}) => (
                                            <Select
                                                {...field}
                                                {...register("category", {
                                                    onChange: (event) => {
                                                        handleInputChangeCategory(event);
                                                    }
                                                })}
                                                id="category"
                                                name="category"
                                                labelId="product-details-category-label"
                                                label={t("product.details.category.label")}
                                                notched
                                                value={field.value || ""}
                                                displayEmpty
                                            >
                                                <MenuItem value="" disabled>
                                                    <span style={{color: "darkgrey"}}>{t("selectOne")}</span>
                                                </MenuItem>
                                                {categories.map((category) => (
                                                    <MenuItem key={category.id} value={category.id}>
                                                        {category.name}
                                                    </MenuItem>
                                                ))}
                                            </Select>
                                        )}
                                    />
                                    <FormHelperText>{t(errors.category?.message as string)}</FormHelperText>
                                </FormControl>

                                <Stack direction="row" spacing={2} justifyContent="flex-end">
                                    <Button color="primary" variant="outlined" component={Link}
                                            to={"/categories/create"}>{t("categories.message.addNewCategory")}</Button>
                                </Stack>

                                <FormControl
                                    required
                                    size="small"
                                    error={!!errors.company}
                                >
                                    <InputLabel
                                        id="product-details-company-label"
                                        shrink
                                    >
                                        {t("product.details.company.label")}
                                    </InputLabel>
                                    <Controller
                                        name="company"
                                        control={control}
                                        render={({field}) => (
                                            <Select
                                                {...field}
                                                {...register("company", {
                                                    onChange: (event) => {
                                                        handleInputChangeCompany(event);
                                                    }
                                                })}
                                                id="company"
                                                name="company"
                                                labelId="product-details-company-label"
                                                label={t("product.details.company.label")}
                                                notched
                                                value={field.value || ""}
                                                displayEmpty
                                            >
                                                <MenuItem value="" disabled>
                                                    <span style={{color: "darkgrey"}}>{t("selectOne")}</span>
                                                </MenuItem>
                                                {companies.map((company) => (
                                                    <MenuItem key={company.id} value={company.id}>
                                                        {company.name}
                                                    </MenuItem>
                                                ))}
                                            </Select>
                                        )}
                                    />
                                    <FormHelperText>{t(errors.company?.message as string)}</FormHelperText>
                                </FormControl>

                                <Stack direction="row" spacing={2} justifyContent="flex-end">
                                    <Button color="primary" variant="outlined" component={Link}
                                            to={"/companies/create"}>{t("companies.message.addNewCompany")}</Button>
                                </Stack>

                                <Stack direction="row" spacing={2}>
                                    <Button color="success" variant="outlined"
                                            onClick={handleSubmit(saveProduct)}>{t("submit")}</Button>
                                    <Button color="info" variant="outlined"
                                            onClick={() => {
                                                reset(initialProductState);
                                                setProduct(initialProductState);
                                            }}>{t("reset")}</Button>
                                </Stack>
                            </Stack>
                        </form>
                    </Stack>
                </Grid>
            )}
        </Grid>
    );
};

export default AddProduct;