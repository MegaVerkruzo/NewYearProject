import store from '../../store/store'
import {newAttempt} from "../../http/wordsAPI";

export const onWrite = (letter) => {
    store.setGameError('')
    store.setNewLetter(letter)
}

export const onDelete = () => {
    store.setGameError('')
    store.deleteLetter()
}

export const onEnter = async () => {
    store.setGameError('')
    try {
        if (store.isCanSendAttempt) {
            let s = ''
            for (let i = 0; i < store.wordLength; i++) {
                s += store.attempts[store.currentAttempt.curRow * store.wordLength + i].letter
            }
            console.log(s)
            const data = await newAttempt(s)
            console.log(data)
            if (data.exception) {
                if (data.exception === 'noUser') {
                    store.setGameError('Пользователь не зарегистрирован')
                } else if (data.exception === 'noWordInDictionary') {
                    store.setGameError('Некорректное слово')
                }
            } else {
                store.setIsKeyBoardOpen(false)
                store.setNewAttempt(data)
            }
        }
    } catch (e) {
        store.setGameError('Произошла ошибка сервера')
    }
}