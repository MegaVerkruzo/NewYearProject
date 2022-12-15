import {$host, $authHost} from "./index";

export const registration = async ({name, surname, middlename, email, password, phone, place}) => {
    const {data} = await $host.post('/auth/register', {
        name,
        surname,
        middleName: middlename,
        email,
        password,
        phone,
        place
    })
    return data
}

export const login = async (userData) => {
    const {data} = await $host.post('/auth/login', userData)
    return data
}

export const check = async () => {
    try {
        const {data} = await $authHost.get('/check')
        return data
    } catch (e) {
        return
    }

}

export const feedback = async (feedback) => {
    const {data} = await $authHost.post('/feedback', {feedback})
    return data
}
