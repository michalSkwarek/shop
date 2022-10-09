import React from "react";
import {Routes, Route} from "react-router-dom";
// import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import "./i18n";
import {useTranslation} from "react-i18next";
import ProductsList from "./components/ProductsList";
import Home from "./components/Home";
import i18next from "i18next";

const App: React.FC = () => {
    const {t} = useTranslation();

    return (
        <div>
            <div>
                <button onClick={() => i18next.changeLanguage("en")}>English</button>
                <button onClick={() => i18next.changeLanguage("pl")}>Polski</button>
            </div>

            <div>
                <div>
                    <a href={"/"}>
                        {t("page.home")}
                    </a>
                </div>
                <div>
                    <a href={"/products"}>
                        {t("page.products")}
                    </a>
                </div>
                {/*<div>*/}
                {/*  <Link to={"/add"}>*/}
                {/*    Add*/}
                {/*  </Link>*/}
                {/*</div>*/}
            </div>

            <div>
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/products" element={<ProductsList/>}/>
                    {/*<Route path="/add" element={<AddTutorial/>}/>*/}
                    {/*<Route path="/tutorials/:id" element={<Tutorial/>}/>*/}
                </Routes>
            </div>
        </div>
    );
}

export default App;
