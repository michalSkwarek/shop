import React, {useState, ChangeEvent} from "react";
import {useForm} from "react-hook-form";
import {useTranslation} from "react-i18next";
import {yupResolver} from "@hookform/resolvers/yup";
import * as Yup from "yup";
import ICategory from "../../types/ICategory";
import CategoryService from "../../services/CategoryService";
import {
    Grid,
    Stack,
    Button,
    TextField
} from "@mui/material";

const AddCategory: React.FC = () => {
    const {t} = useTranslation();

    const initialCategoryState = {
        id: null,
        name: "",
        description: "",
    };

    const validationSchema = Yup.object().shape({
        name: Yup.string()
            .required("category.name.validation.required"),
        description: Yup.string()
            .required("category.description.validation.required"),
    });

    const {
        register,
        handleSubmit,
        reset,
        formState: {errors}
    } = useForm<ICategory>({
        defaultValues: initialCategoryState,
        resolver: yupResolver(validationSchema)
    });

    const [category, setCategory] = useState<ICategory>(initialCategoryState);
    const [submitted, setSubmitted] = useState<boolean>(false);

    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target;
        setCategory({...category, [name]: value});
    };

    const saveCategory = () => {
        let data = {
            name: category.name,
            description: category.description,
        };

        CategoryService.create(data)
            .then((response: any) => {
                setCategory({
                    id: response.data.id,
                    name: response.data.name,
                    description: response.data.description,
                });
                setSubmitted(true);
                console.log(response.data);
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };

    const newCategory = () => {
        setCategory(initialCategoryState);
        setSubmitted(false);
    };

    return (
        <Grid container spacing={2}>
            {submitted ? (
                <Grid item xs={12}>
                    <Stack spacing={2}>
                        <h4>{t("categories.message.youHaveAddedANewCategorySuccessfully")}</h4>
                        <Stack direction="row" spacing={2}>
                            <Button color="primary" variant="outlined"
                                    onClick={() => {
                                        reset(initialCategoryState);
                                        newCategory();
                                    }}>{t("categories.message.addNewCategory")}</Button>
                        </Stack>
                    </Stack>
                </Grid>
            ) : (
                <Grid item xs={12}>
                    <Stack spacing={2}>
                        <h4>{t("categories.message.addNewCategory")}</h4>
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
                                    value={category.name}
                                    label={t("category.details.name.label")}
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
                                    value={category.description}
                                    label={t("category.details.description.label")}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                    error={!!errors.description}
                                    helperText={t(errors.description?.message as string)}
                                />

                                <Stack direction="row" spacing={2}>
                                    <Button color="success" variant="outlined"
                                            onClick={handleSubmit(saveCategory)}>{t("submit")}</Button>
                                    <Button color="info" variant="outlined"
                                            onClick={() => {
                                                reset(initialCategoryState);
                                                setCategory(initialCategoryState);
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

export default AddCategory;