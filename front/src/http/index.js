import axios from "axios";

const $host = axios.create({
    baseURL: process.env.REACT_APP_API_URL
})

const $authHost = axios.create({
    baseURL: process.env.REACT_APP_API_URL
})

const authInterceptor = config => {
    try {
        config.headers.authorization = `${localStorage.getItem('token')}`
    } catch (e) {
        config.headers.authorization = ''
    }
    return config
}

function checkConfig(config) {
    console.log('request => config ====================================');
    console.log(localStorage.getItem('token'))
    config.headers.authorization = `${localStorage.getItem('token')}`
    console.log(config);
    console.log('request => config ====================================');
    return config;
}

$authHost.interceptors.request.use(authInterceptor, function (error) {
    return Promise.reject(error);
})

export {
    $host,
    $authHost
}