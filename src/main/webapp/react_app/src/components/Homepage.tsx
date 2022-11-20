import React from "react";
import {useTranslation} from "react-i18next";
import {Grid} from "@mui/material";

const Homepage: React.FC = () => {
    const {t} = useTranslation();

    return (
        <Grid container spacing={2}>
            <Grid item xs={12}>
                <h4>{t("welcome")}</h4>
            </Grid>
        </Grid>
    );
};

export default Homepage;