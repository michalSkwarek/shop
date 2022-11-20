import React, {useState, useEffect} from "react";
import {useTranslation} from "react-i18next";
import IAccount from "../../types/IAccount";
import AccountService from "../../services/AccountService";
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

const AccountsList: React.FC = () => {
    const {t} = useTranslation();

    const [accounts, setAccounts] = useState<Array<IAccount>>([]);

    useEffect(() => {
        retrieveAccounts();
    }, []);

    const retrieveAccounts = () => {
        AccountService.getAll()
            .then((response: any) => {
                setAccounts(response.data);
                console.log(response.data);
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };

    const refreshList = () => {
        retrieveAccounts();
    };

    const deleteAccount = (id: number) => {
        AccountService.remove(id)
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
            {accounts.length === 0 ? (
                <Grid item xs={12}>
                    <h4>{t("accounts.message.thereAreNoAccounts")}</h4>
                </Grid>
            ) : (
                <Grid item xs={12}>
                    <Stack spacing={2}>
                        <h4>{t("accounts.message.allAccounts")}</h4>
                        <TableContainer>
                            <Table>
                                <TableHead>
                                    <TableRow selected>
                                        <TableCell>
                                            #
                                        </TableCell>
                                        <TableCell>
                                            {t("account.details.email.label")}
                                        </TableCell>
                                        <TableCell>
                                            {t("account.details.newsletter.label")}
                                        </TableCell>
                                        <TableCell>
                                            {t("actions")}
                                        </TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {accounts.map((account, index) => (
                                        <TableRow key={account.id} hover>
                                            <TableCell scope="row">{++index}</TableCell>
                                            <TableCell>{account.email}</TableCell>
                                            <TableCell>{account.newsletter ? t("yes") : t("no")}</TableCell>
                                            <TableCell>
                                                <Stack direction="row" spacing={2}>
                                                    <Button color="error" variant="outlined"
                                                            onClick={() => deleteAccount(account.id)}>{t("delete")}</Button>
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
        </Grid>
    );
};

export default AccountsList;