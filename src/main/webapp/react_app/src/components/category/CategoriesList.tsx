import React, {useState, useEffect} from "react";
import {Link} from "react-router-dom";
import {useTranslation} from "react-i18next";
import ICategory from "../../types/ICategory";
import CategoryService from "../../services/CategoryService";
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

const CategoriesList: React.FC = () => {
    const {t} = useTranslation();

    const [categories, setCategories] = useState<Array<ICategory>>([]);

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

    const refreshList = () => {
        retrieveCategories();
    };

    const deleteCategory = (id: number) => {
        CategoryService.remove(id)
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
            {categories.length === 0 ? (
                <Grid item xs={12}>
                    <h4>{t("categories.message.thereAreNoCategories")}</h4>
                </Grid>
            ) : (
                <Grid item xs={12}>
                    <Stack spacing={2}>
                        <h4>{t("categories.message.allCategories")}</h4>
                        <TableContainer>
                            <Table>
                                <TableHead>
                                    <TableRow selected>
                                        <TableCell>
                                            #
                                        </TableCell>
                                        <TableCell>
                                            {t("category.details.name.label")}
                                        </TableCell>
                                        <TableCell>
                                            {t("category.details.description.label")}
                                        </TableCell>
                                        <TableCell>
                                            {t("actions")}
                                        </TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {categories.map((category, index) => (
                                        <TableRow key={category.id} hover>
                                            <TableCell scope="row">{++index}</TableCell>
                                            <TableCell>{category.name}</TableCell>
                                            <TableCell>{category.description}</TableCell>
                                            <TableCell>
                                                <Stack direction="row" spacing={2}>
                                                    <Button color="warning" variant="outlined" component={Link}
                                                            to={"/categories/" + category.id}>{t("edit")}</Button>
                                                    <Button color="error" variant="outlined"
                                                            onClick={() => deleteCategory(category.id)}>{t("delete")}</Button>
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
                            to={"/categories/create"}>{t("categories.message.addNewCategory")}</Button>
                </Stack>
            </Grid>
        </Grid>
    );
};

export default CategoriesList;