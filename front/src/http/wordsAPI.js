import {$authHost} from "./index";

export const get_all_info = async () => {
    const {data} = await $authHost.get('/game/get_all_info')
    return data
}

export const new_attempt = async () => {
    const {data} = await $authHost.post('/game/new_attempt')
    return data
}
