import React, {useState, useEffect} from "react";
import {Link} from "react-router-dom";
import {useTranslation} from "react-i18next";
import ICompany from "../../types/ICompany";
import CompanyService from "../../services/CompanyService";
import {
    TableContainer,
    Table,
    TableHead,
    TableBody,
    TableRow,
    TableCell,
    Button,
    Stack,
    Grid
} from "@mui/material";

const CompaniesList: React.FC = () => {
    const {t} = useTranslation();

    const [companies, setCompanies] = useState<Array<ICompany>>([]);

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

    const refreshList = () => {
        retrieveCompanies();
    };

    const deleteCompany = (id: number) => {
        CompanyService.remove(id)
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
            {companies.length === 0 ? (
                <Grid item xs={12}>
                    <h4>{t("companies.message.thereAreNoCompanies")}</h4>
                </Grid>
            ) : (
                <Grid item xs={12}>
                    <Stack spacing={2}>
                        <h4>{t("companies.message.allCompanies")}</h4>
                        <TableContainer>
                            <Table>
                                <TableHead>
                                    <TableRow selected>
                                        <TableCell>
                                            #
                                        </TableCell>
                                        <TableCell>
                                            {t("company.details.name.label")}
                                        </TableCell>
                                        <TableCell>
                                            {t("company.details.website.label")}
                                        </TableCell>
                                        <TableCell>
                                            {t("company.details.phoneNumber.label")}
                                        </TableCell>
                                        <TableCell>
                                            {t("actions")}
                                        </TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {companies.map((company, index) => (
                                        <TableRow key={company.id} hover>
                                            <TableCell scope="row">{++index}</TableCell>
                                            <TableCell>{company.name}</TableCell>
                                            <TableCell>{company.website}</TableCell>
                                            <TableCell>{company.phoneNumber}</TableCell>
                                            <TableCell>
                                                <Stack direction="row" spacing={2}>
                                                    <Button color="info" variant="outlined" component={Link}
                                                            to={"/companies/" + company.id + "/details"}>{t("details")}</Button>
                                                    <Button color="warning" variant="outlined" component={Link}
                                                            to={"/companies/" + company.id}>{t("edit")}</Button>
                                                    <Button color="error" variant="outlined"
                                                            onClick={() => deleteCompany(company.id)}>{t("delete")}</Button>
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
                            to={"/companies/create"}>{t("companies.message.addNewCompany")}</Button>
                </Stack>
            </Grid>
        </Grid>
    );
};

export default CompaniesList;