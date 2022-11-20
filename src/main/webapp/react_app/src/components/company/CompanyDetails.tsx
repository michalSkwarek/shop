import React, {useState, useEffect} from "react";
import {useParams, Link} from "react-router-dom";
import {useTranslation} from "react-i18next";
import ICompany from "../../types/ICompany";
import CompanyService from "../../services/CompanyService";
import {Grid, Stack, Button, Card, CardMedia, CardContent, TextField} from "@mui/material";

const CompanyDetails: React.FC = () => {
    const {id} = useParams();
    const {t} = useTranslation();

    const initialCompanyState = {
        id: null,
        name: "",
        website: "",
        phoneNumber: "",
    };

    const [currentCompany, setCurrentCompany] = useState<ICompany>(initialCompanyState);

    const getCompany = (id: string) => {
        CompanyService.get(id)
            .then((response: any) => {
                setCurrentCompany(response.data);
                console.log(response.data);
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };

    useEffect(() => {
        if (id)
            getCompany(id);
    }, [id]);

    return (
        <Grid container spacing={2} justifyContent="center">
            <Grid item xs={4}>
                <Stack spacing={2}>
                    <h4>{t("companies.message.companyDetails")}</h4>
                    <Card>
                        <CardMedia
                            sx={{
                                width: "80%",
                                marginLeft: "10%",
                            }}
                            component="img"
                            alt={currentCompany.name}
                            src={require("./_apple.jpg")}
                        />
                        <CardContent>
                            <Stack spacing={2}>
                                <TextField
                                    type="text"
                                    size="small"
                                    disabled
                                    id="name"
                                    name="name"
                                    value={currentCompany.name}
                                    label={t("company.details.name.label")}
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
                                    id="website"
                                    name="website"
                                    value={currentCompany.website}
                                    label={t("company.details.website.label")}
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
                                    id="phoneNumber"
                                    name="phoneNumber"
                                    value={currentCompany.phoneNumber}
                                    label={t("company.details.phoneNumber.label")}
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
                                        to={"/companies/" + currentCompany.id}>{t("edit")}</Button>
                            </Stack>
                        </CardContent>
                    </Card>
                </Stack>
            </Grid>
        </Grid>
    );
};

export default CompanyDetails;