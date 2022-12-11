import {$host, $authHost} from "./index";
import jwt_decode from "jwt-decode"

export const registration = async (userData) => {
    const {data} = await $host.post('/auth/register', userData)
    localStorage.setItem('token', data)
    return jwt_decode(data)
}

export const login = async (userData) => {
    const {data} = await $host.post('/auth/login', userData)
    localStorage.setItem('token', data)
    return jwt_decode(data)
}

export const check = async () => {
    const {data} = await $authHost.post('/auth/check')
    localStorage.setItem('token', data)
    return jwt_decode(data)
}

export const feedback = async (feedback) => {
    const {data} = await $authHost.post('/feedback', {feedback})
    return data
}
