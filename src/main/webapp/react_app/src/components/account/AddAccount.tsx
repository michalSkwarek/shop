import React, {useState, ChangeEvent, useEffect} from "react";
import {useForm, Controller} from "react-hook-form";
import {Link} from "react-router-dom";
import {useTranslation} from "react-i18next";
import {yupResolver} from "@hookform/resolvers/yup";
import * as Yup from "yup";
import IAccount from "../../types/IAccount";
import AccountService from "../../services/AccountService";
import {
    Grid,
    Stack,
    Button,
    TextField,
    FormControl,
    FormControlLabel,
    InputLabel,
    Select,
    MenuItem,
    FormHelperText, Checkbox
} from "@mui/material";

const AddAccount: React.FC = () => {
    const {t} = useTranslation();

    const initialAccountState = {
        id: null,
        email: "",
        password: "",
        confirmPassword: "",
        newsletter: false,
        acceptTerms: false,
    };

    const validationSchema = Yup.object().shape({
        email: Yup.string()
            .required("account.email.validation.required")
            .email("account.email.validation.pattern"),
        password: Yup.string()
            .required("account.password.validation.required")
            .min(6, "account.password.validation.min")
            .max(20, "account.password.validation.max"),
        confirmPassword: Yup.string()
            .required("account.confirmPassword.validation.required")
            .oneOf([Yup.ref("password"), null], "account.confirmPassword.validation.oneOf"),
        acceptTerms: Yup.bool().oneOf([true], "account.acceptTerms.validation.required")
    });

    const {
        register,
        handleSubmit,
        reset,
        control,
        formState: {errors}
    } = useForm<IAccount>({
        defaultValues: initialAccountState,
        resolver: yupResolver(validationSchema)
    });

    const [account, setAccount] = useState<IAccount>(initialAccountState);
    const [submitted, setSubmitted] = useState<boolean>(false);

    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target;
        setAccount({...account, [name]: value});
    };

    const saveAccount = () => {
        let data = {
            email: account.email,
            password: account.password,
            confirmPassword: account.confirmPassword,
            newsletter: account.newsletter,
            acceptTerms: account.acceptTerms,
        };

        AccountService.create(data)
            .then((response: any) => {
                setAccount({
                    id: response.data.id,
                    email: response.data.email,
                    password: response.data.password,
                    confirmPassword: response.data.confirmPassword,
                    newsletter: response.data.newsletter,
                    acceptTerms: response.data.acceptTerms,
                });
                setSubmitted(true);
                console.log(response.data);
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };

    const newProduct = () => {
        setAccount(initialAccountState);
        setSubmitted(false);
    };

    return (
        <Grid container spacing={2}>
            {submitted ? (
                <Grid item xs={12}>
                    <Stack spacing={2}>
                        <h4>{t("accounts.message.youHaveCreatedANewAccountSuccessfully")}</h4>
                        <Stack direction="row" spacing={2}>
                            <Button color="primary" variant="outlined" component={Link}
                                    to="/">{t("page.goToTheHomepage")}</Button>
                        </Stack>
                    </Stack>
                </Grid>
            ) : (
                <Grid item xs={12}>
                    <Stack spacing={2}>
                        <h4>{t("accounts.message.createNewAccount")}</h4>
                        <form>
                            <Stack spacing={2}>
                                <TextField
                                    {...register("email", {
                                        onChange: (event) => {
                                            handleInputChange(event);
                                        }
                                    })}
                                    required
                                    type="email"
                                    size="small"
                                    id="email"
                                    name="email"
                                    value={account.email}
                                    placeholder={"example@email.com"}
                                    label={t("account.details.email.label")}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                    error={!!errors.email}
                                    helperText={t(errors.email?.message as string)}
                                />

                                <TextField
                                    {...register("password", {
                                        onChange: (event) => {
                                            handleInputChange(event);
                                        }
                                    })}
                                    required
                                    type="password"
                                    size="small"
                                    id="password"
                                    name="password"
                                    value={account.password}
                                    label={t("account.details.password.label")}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                    error={!!errors.password}
                                    helperText={t(errors.password?.message as string)}
                                />

                                <TextField
                                    {...register("confirmPassword", {
                                        onChange: (event) => {
                                            handleInputChange(event);
                                        }
                                    })}
                                    required
                                    type="password"
                                    size="small"
                                    id="confirmPassword"
                                    name="confirmPassword"
                                    value={account.confirmPassword}
                                    label={t("account.details.confirmPassword.label")}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                    error={!!errors.confirmPassword}
                                    helperText={t(errors.confirmPassword?.message as string)}
                                />

                                {/*<FormControl*/}
                                {/*    size="small"*/}
                                {/*>*/}
                                {/*    <Controller*/}
                                {/*        name="newsletter"*/}
                                {/*        control={control}*/}
                                {/*        render={({field}) => (*/}
                                {/*            <FormControlLabel*/}
                                {/*                control={*/}
                                {/*                    <Checkbox*/}
                                {/*                        {...register("newsletter", {*/}
                                {/*                            onChange: (event) => {*/}
                                {/*                                handleInputChange(event);*/}
                                {/*                            }*/}
                                {/*                        })}*/}
                                {/*                    />*/}
                                {/*                }*/}
                                {/*                value={account.newsletter}*/}
                                {/*                label={t("account.details.newsletter.label")}*/}
                                {/*            />*/}
                                {/*        )}*/}
                                {/*    />*/}
                                {/*</FormControl>*/}

                                <FormControl
                                    size="small"
                                    error={!!errors.acceptTerms}
                                >
                                    <FormControlLabel
                                        control={
                                            <Controller
                                                name="acceptTerms"
                                                control={control}
                                                render={({field}) => (
                                                    <Checkbox
                                                        {...field}
                                                        {...register("acceptTerms", {
                                                            onChange: (event) => {
                                                                handleInputChange(event);
                                                            }
                                                        })}
                                                        checked={account.acceptTerms}
                                                    />
                                                )}
                                            />
                                        }
                                        label={t("accounts.message.iAcceptTermsOfUse")}
                                    />
                                    <FormHelperText>{t(errors.acceptTerms?.message as string)}</FormHelperText>
                                </FormControl>




                                    {/*// control={<Checkbox />}*/}
                                    {/*// onChange={handleInputChange}*/}
                                    {/*// value={account.newsletter}*/}
                                    {/*// label={t("account.details.newsletter.label")}*/}
                                    {/*// labelPlacement="end"*/}

                                {/*<TextField*/}
                                {/*    {...register("newsletter", {*/}
                                {/*        onChange: (event) => {*/}
                                {/*            handleInputChange(event);*/}
                                {/*        }*/}
                                {/*    })}*/}
                                {/*    type="checkbox"*/}
                                {/*    size="small"*/}
                                {/*    id="newsletter"*/}
                                {/*    name="newsletter"*/}
                                {/*    value={account.newsletter}*/}
                                {/*    label={t("account.details.newsletter.label")}*/}
                                {/*    InputLabelProps={{*/}
                                {/*        shrink: true,*/}
                                {/*    }}*/}
                                {/*    // error={!!errors.newsletter}*/}
                                {/*    // helperText={t(errors.newsletter?.message as string)}*/}
                                {/*/>*/}

                                <Stack direction="row" spacing={2}>
                                    <Button color="success" variant="outlined"
                                            onClick={handleSubmit(saveAccount)}>{t("submit")}</Button>
                                    <Button color="info" variant="outlined"
                                            onClick={() => {
                                                reset(initialAccountState);
                                                setAccount(initialAccountState);
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

export default AddAccount;