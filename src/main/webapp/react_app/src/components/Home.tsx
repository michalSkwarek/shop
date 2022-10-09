import React from "react";
import {useTranslation} from "react-i18next";

const Home: React.FC = () => {
    const {t} = useTranslation();

    return (
        <div>
            <h4>{t("welcome")}</h4>
        </div>
    );
};

export default Home;