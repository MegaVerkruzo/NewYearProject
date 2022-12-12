import {$authHost} from "./index";

export const getAllInfo = async () => {
    const {data} = await $authHost.get('/game/get_all_info')
    return data
}

export const newAttempt = async () => {
    const {data} = await $authHost.post('/game/new_attempt')
    return data
}
