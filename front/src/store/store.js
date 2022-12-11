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
        this.wordLength = word_length
        this.attemptsLeft = attempts_left
        this.postLink = post_link
        this.description = description
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
}

export default new Store()