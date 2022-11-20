import React from "react";
import {Routes, Route, Link} from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import "./i18n";
import i18next from "i18next";
import {useTranslation} from "react-i18next";
import Homepage from "./components/Homepage";
import CategoriesList from "./components/category/CategoriesList";
import AddCategory from "./components/category/AddCategory";
import CompaniesList from "./components/company/CompaniesList";
import CompanyDetails from "./components/company/CompanyDetails";
import AddCompany from "./components/company/AddCompany";
import ProductsList from "./components/product/ProductsList";
import ProductDetails from "./components/product/ProductDetails";
import AddProduct from "./components/product/AddProduct";
// import Product from "./components/product/Product";
import {Container, Grid, Stack, Button} from "@mui/material";
import AccountsList from "./components/account/AccountsList";
import AddAccount from "./components/account/AddAccount";
import AddCustomer from "./components/customer/AddCustomer";
import AddAddress from "./components/address/AddAddress";

const App: React.FC = () => {
    const {t} = useTranslation();

    return (
        <Container>
            <Grid container spacing={2}>
                <Grid item xs={12} sx={{mt: 2}}>
                    <Stack direction="row" spacing={2} justifyContent="flex-end">
                        <Button color="primary" variant="outlined"
                                onClick={() => i18next.changeLanguage("en")}>en</Button>
                        <Button color="primary" variant="outlined"
                                onClick={() => i18next.changeLanguage("pl")}>pl</Button>
                        <Button color="success" variant="outlined" component={Link}
                                to={"/auth/login"}>Login</Button>
                        <Button color="success" variant="outlined" component={Link}
                                to={"/auth/register"}>Sign up</Button>
                        <Button color="error" variant="outlined" component={Link}
                                to={"/auth/ logout"}>Logout</Button>
                    </Stack>
                </Grid>

                <Grid item xs={12}>
                    <Stack direction="row" spacing={2}>
                        <Button color="primary" variant="outlined" component={Link}
                                to={"/"}>{t("page.homepage")}</Button>
                        <Button color="primary" variant="outlined" component={Link}
                                to={"/products"}>{t("page.products")}</Button>
                        <Button color="primary" variant="outlined" component={Link}
                                to={"/companies"}>{t("page.companies")}</Button>
                        <Button color="primary" variant="outlined" component={Link}
                                to={"/categories"}>{t("page.categories")}</Button>
                    </Stack>
                </Grid>

                <Grid item xs={12}>
                    <Stack direction="row" spacing={2}>
                        <Button color="primary" variant="outlined" component={Link}
                                to={"/accounts"}>{t("page.accounts")}</Button>
                        <Button color="primary" variant="outlined" component={Link}
                                to={"/comments"}>Comments</Button>
                        <Button color="primary" variant="outlined" component={Link}
                                to={"/customers/create"}>{t("customers.message.addNewCustomer")}</Button>
                        <Button color="primary" variant="outlined" component={Link}
                                to={"/addresses/create"}>{t("addresses.message.addNewAddress")}</Button>
                    </Stack>
                </Grid>

                <Grid item xs={12}>
                    <Routes>
                        <Route path="/" element={<Homepage/>}/>
                        <Route path="/categories" element={<CategoriesList/>}/>
                        <Route path="/categories/create" element={<AddCategory/>}/>
                        {/*<Route path="/categories/:id" element={<Category/>}/>*/}

                        <Route path="/companies" element={<CompaniesList/>}/>
                        <Route path="/companies/create" element={<AddCompany/>}/>
                        {/*<Route path="/companies/:id" element={<Company/>}/>*/}
                        <Route path="/companies/:id/details" element={<CompanyDetails/>}/>

                        <Route path="/products" element={<ProductsList/>}/>
                        <Route path="/products/create" element={<AddProduct/>}/>
                        {/*<Route path="/products/:id" element={<Product/>}/>*/}
                        <Route path="/products/:id/details" element={<ProductDetails/>}/>

                        <Route path="/accounts" element={<AccountsList/>}/>

                        <Route path="/auth/register" element={<AddAccount/>}/>
                        {/*<Route path="/accounts/:id" element={<Account/>}/>*/}

                        <Route path="/customers/create" element={<AddCustomer/>}/>
                        {/*<Route path="/customers/:id" element={<Customer/>}/>*/}

                        <Route path="/addresses/create" element={<AddAddress/>}/>
                        {/*<Route path="/addresses/:id" element={<Address/>}/>*/}
                    </Routes>
                </Grid>
            </Grid>
        </Container>
    );
}

export default App;
