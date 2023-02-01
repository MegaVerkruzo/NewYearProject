import React from 'react';
import {ReactComponent as Spinner} from "../../img/spinner.svg";

const isLoading = () => {
    return (
        <div className="loading">
            <Spinner />
        </div>
    );
};

export default isLoading;
