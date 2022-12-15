import {makeAutoObservable} from "mobx";

class Store {
    userData = {
        name: '',
        surname: '',
        middlename: '',
        phone: '',
        email: '',
        place: '',
        password: '',
        isAgreePolicy: false
    }
    isAuth = false
    attempts = []
    newAttempt = ''
    attemptsLeft = 0
    wordLength = 0
    postLink = ''
    description = null
    isCorrect = false
    isSound = true

    feedback = ''

    constructor() {
        makeAutoObservable(this)
    }

    changeUserData(field, data) {
        this.userData[field] = data
    }

    setUserData(data) {
        this.userData = data
        this.isAuth = true
    }

    setMainInfo({attempts, word_length, attempts_left, post_link, description}) {
        this.attempts = attempts
        if (attempts.length < 5) {
            let cur_id = attempts[attempts.length - 1].id + 1
            for (let i = 0; i < attempts_left; i++) {
                this.attempts.push({id: cur_id + i, empty: true})
            }
        }
        this.wordLength = word_length
        this.attemptsLeft = attempts_left
        this.postLink = post_link
        this.description = description
        console.log(this.attempts)
    }

    setNewAttempt({attempt, is_correct}) {
        this.isCorrect = is_correct
        this.attempts.push(attempt)
    }

    setFeedbackText(data) {
        this.feedback = data
    }

    setNewAttemptText(data) {
        this.newAttempt = data
    }

    setToggleIsSound() {
        this.isSound = !this.isSound
    }
}

export default new Store()