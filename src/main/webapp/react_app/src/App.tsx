import React from 'react';
import {Routes, Route, Link} from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import './App.css';
import ProductsList from "./components/ProductsList";
import Home from "./components/Home";

const App: React.FC = () => {
    return (
        <div>
            <div>
                <div>
                    <Link to={"/"}>
                        home
                    </Link>
                </div>
                <div>
                    <Link to={"/products"}>
                        products
                    </Link>
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
