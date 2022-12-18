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
        if (store.isCanSendAttempt && !store.isLoading) {
            let s = ''
            for (let i = 0; i < store.wordLength; i++) {
                s += store.attempts[store.currentAttempt.curRow * store.wordLength + i].letter
            }
            store.setIsLoading(true)
            const data = await newAttempt(s)
            store.setIsLoading(false)
            if (data.exception) {
                if (data.exception === 'noUser') {
                    store.setGameError('Пользователь не зарегистрирован')
                } else if (data.exception === 'noWordInDictionary') {
                    store.setGameError('Некорректное слово')
                } else if (data.exception === 'alreadyExistCorrectAttempt') {
                    store.setGameError('Вы уже завершили игру на сегодня')
                } else if (data.exception === 'alreadyExist5Attempts') {
                    store.setGameError('Вы уже сделали 5 попыток')
                } else if (data.exception === 'wrongSize') {
                    store.setGameError('Неверная длина слова')
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