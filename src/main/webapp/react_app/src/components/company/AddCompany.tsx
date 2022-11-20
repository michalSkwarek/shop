import React, {useState, ChangeEvent} from "react";
import {useForm} from "react-hook-form";
import {useTranslation} from "react-i18next";
import {yupResolver} from "@hookform/resolvers/yup";
import * as Yup from "yup";
import ICompany from "../../types/ICompany";
import CompanyService from "../../services/CompanyService";
import {
    Grid,
    Stack,
    Button,
    TextField
} from "@mui/material";

const AddCompany: React.FC = () => {
    const {t} = useTranslation();

    const initialCompanyState = {
        id: null,
        name: "",
        website: "",
        phoneNumber: "",
    };

    const validationSchema = Yup.object().shape({
        name: Yup.string()
            .required("company.name.validation.required"),
        website: Yup.string()
            .required("company.website.validation.required")
            .matches(/^(www)\.[a-z0-9]+\.[a-z]+$/, "company.website.validation.pattern"),
        phoneNumber: Yup.string()
            .required("company.phoneNumber.validation.required")
            .matches(/^\d{3}-\d{3}-\d{3}$/, "company.phoneNumber.validation.pattern"),
    });

    const {
        register,
        handleSubmit,
        reset,
        formState: {errors}
    } = useForm<ICompany>({
        defaultValues: initialCompanyState,
        resolver: yupResolver(validationSchema)
    });

    const [company, setCompany] = useState<ICompany>(initialCompanyState);
    const [submitted, setSubmitted] = useState<boolean>(false);

    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target;
        setCompany({...company, [name]: value});
    };

    const saveCompany = () => {
        let data = {
            name: company.name,
            website: company.website,
            phoneNumber: company.phoneNumber,
        };

        CompanyService.create(data)
            .then((response: any) => {
                setCompany({
                    id: response.data.id,
                    name: response.data.name,
                    website: response.data.website,
                    phoneNumber: response.data.phoneNumber,
                });
                setSubmitted(true);
                console.log(response.data);
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };

    const newCompany = () => {
        setCompany(initialCompanyState);
        setSubmitted(false);
    };

    return (
        <Grid container spacing={2}>
            {submitted ? (
                <Grid item xs={12}>
                    <Stack spacing={2}>
                        <h4>{t("companies.message.youHaveAddedANewCompanySuccessfully")}</h4>
                        <Stack direction="row" spacing={2}>
                            <Button color="primary" variant="outlined"
                                    onClick={() => {
                                        reset(initialCompanyState);
                                        newCompany();
                                    }}>{t("companies.message.addNewCompany")}</Button>
                        </Stack>
                    </Stack>
                </Grid>
            ) : (
                <Grid item xs={12}>
                    <Stack spacing={2}>
                        <h4>{t("companies.message.addNewCompany")}</h4>
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
                                    value={company.name}
                                    label={t("company.details.name.label")}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                    error={!!errors.name}
                                    helperText={t(errors.name?.message as string)}
                                />

                                <TextField
                                    {...register("website", {
                                        onChange: (event) => {
                                            handleInputChange(event);
                                        }
                                    })}
                                    required
                                    type="text"
                                    size="small"
                                    id="website"
                                    name="website"
                                    value={company.website}
                                    placeholder={"www.example.com"}
                                    label={t("company.details.website.label")}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                    error={!!errors.website}
                                    helperText={t(errors.website?.message as string)}
                                />

                                <TextField
                                    {...register("phoneNumber", {
                                        onChange: (event) => {
                                            handleInputChange(event);
                                        }
                                    })}
                                    required
                                    type="text"
                                    size="small"
                                    id="phoneNumber"
                                    name="phoneNumber"
                                    value={company.phoneNumber}
                                    placeholder={"123-123-123"}
                                    label={t("company.details.phoneNumber.label")}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                    error={!!errors.phoneNumber}
                                    helperText={t(errors.phoneNumber?.message as string)}
                                />

                                <Stack direction="row" spacing={2}>
                                    <Button color="success" variant="outlined"
                                            onClick={handleSubmit(saveCompany)}>{t("submit")}</Button>
                                    <Button color="info" variant="outlined"
                                            onClick={() => {
                                                reset(initialCompanyState);
                                                setCompany(initialCompanyState);
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

export default AddCompany;