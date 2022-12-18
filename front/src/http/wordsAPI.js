import {$authHost} from "./index";

export const getAllInfo = async () => {
    const {data} = await $authHost.get('/game/game')
    return data
}

export const newAttempt = async (word) => {
    const {data} = await $authHost.post('/game/new_attempt', {word})
    return data
}