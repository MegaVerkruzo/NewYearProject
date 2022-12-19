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
        password_repeat: '',
        isAgreePolicy: false
    }
    isAuth = false

    currentAttempt = {curRow: 0, curCol: 0}
    wordLength = 5
    description = null
    isEnd = false
    isPuttedFeedback = false
    attempts = Array(this.wordLength * 5).fill({letter: '', state: ''})

    isSound = true
    sound = null
    regError = ''
    gameError = ''
    feedbackError = ''
    isMenuOpen = false
    isKeyboardOpen = false
    isCanSendAttempt = false
    countCorrectAnswersBefore = 0
    isLoading = false
    isFormLoading = false
    isFeedbackJustSent = false

    untilNewYear = {days: 0, hours: 0, minutes: 0}

    feedback = ''

    keyboardData = [[{id: 1, letter: 'й', state: ''}, {id: 2, letter: 'ц', state: ''}, {
        id: 3,
        letter: 'у',
        state: ''
    }, {id: 4, letter: 'к', state: ''}, {id: 5, letter: 'е', state: ''}, {id: 6, letter: 'н', state: ''}, {
        id: 7,
        letter: 'г',
        state: ''
    }, {id: 8, letter: 'ш', state: ''}, {id: 9, letter: 'щ', state: ''}, {id: 10, letter: 'з', state: ''}, {
        id: 11,
        letter: 'х',
        state: ''
    }, {id: 12, letter: 'ъ', state: ''}], [{id: 13, letter: 'ф', state: ''}, {id: 14, letter: 'ы', state: ''}, {
        id: 15,
        letter: 'в',
        state: ''
    }, {id: 16, letter: 'а', state: ''}, {id: 17, letter: 'п', state: ''}, {id: 18, letter: 'р', state: ''}, {
        id: 19,
        letter: 'о',
        state: ''
    }, {id: 20, letter: 'л', state: ''}, {id: 21, letter: 'д', state: ''}, {id: 22, letter: 'ж', state: ''}, {
        id: 23,
        letter: 'э',
        state: ''
    }], [{id: 52, letter: '✓'}, {id: 24, letter: 'я', state: ''}, {id: 25, letter: 'ч', state: ''}, {
        id: 26,
        letter: 'с',
        state: ''
    }, {id: 27, letter: 'м', state: ''}, {id: 28, letter: 'и', state: ''}, {id: 29, letter: 'т', state: ''}, {
        id: 30,
        letter: 'ь',
        state: ''
    }, {id: 31, letter: 'б', state: ''}, {id: 32, letter: 'ю', state: ''}, {id: 51, letter: '🠔'}]]

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

    setKeyboardState(letters) {
        for (let i = 0; i < this.keyboardData.length; i++) {
            for (let j = 0; j < this.keyboardData[i].length; j++) {
                if (this.keyboardData[i][j].id < 50) {
                    let state = ''
                    if (letters.find(item => item.letter === this.keyboardData[i][j].letter && item.state === 'grey')) {
                        state = 'grey'
                    }
                    if (letters.find(item => item.letter === this.keyboardData[i][j].letter && item.state === 'yellow')) {
                        state = 'yellow'
                    }
                    if (letters.find(item => item.letter === this.keyboardData[i][j].letter && item.state === 'green')) {
                        state = 'green'
                    }
                    if (state) {
                        this.keyboardData[i][j].state = state
                    }
                }
            }
        }
    }

    setMainInfo({
                    letters,
                    currentLine,
                    description,
                    isEnd,
                    wordLength,
                    isPuttedFeedback,
                    countCorrectAnswersBefore
                }) {
        this.attempts = letters
        for (let i = 0; i < wordLength * (5 - currentLine); i++) {
            this.attempts.push({letter: '', state: ''})
        }
        this.setKeyboardState(letters)
        this.currentAttempt = {curRow: currentLine, curCol: 0}
        this.description = description
        this.isEnd = isEnd
        this.wordLength = wordLength
        this.isPuttedFeedback = isPuttedFeedback
        this.countCorrectAnswersBefore = countCorrectAnswersBefore
    }

    setFeedbackText(data) {
        this.feedback = data
    }

    setToggleIsSound() {
        this.isSound = !this.isSound
    }

    setSoundEffect(sound) {
        this.sound = new Audio(sound)
    }

    setUntilNewYear(data) {
        this.untilNewYear = data
    }

    setRegError(message) {
        this.regError = message
    }

    setMenuToggle() {
        this.isMenuOpen = !this.isMenuOpen
    }

    setIsKeyBoardOpen(value) {
        this.isKeyboardOpen = value
    }

    setIsCanSendAttempt() {
        this.isCanSendAttempt = !this.isCanSendAttempt
    }

    setGameError(message) {
        this.gameError = message
    }

    setFeedbackError(message) {
        this.feedbackError = message
    }

    setNewLetter(letter) {
        if (!this.isEnd && this.currentAttempt.curRow < 5 && this.currentAttempt.curCol < this.wordLength) {
            this.attempts[this.currentAttempt.curRow * this.wordLength + this.currentAttempt.curCol].letter = letter
            this.currentAttempt.curCol += 1
        }
        if (this.currentAttempt.curCol === this.wordLength) {
            this.isCanSendAttempt = true
        }
    }

    deleteLetter() {
        if (!this.isEnd && this.currentAttempt.curRow < 5 && this.currentAttempt.curCol > 0) {
            this.attempts[this.currentAttempt.curRow * this.wordLength + this.currentAttempt.curCol - 1].letter = ''
            this.currentAttempt.curCol -= 1
            this.isCanSendAttempt = false
        }
    }

    setNewAttempt({letters, isCorrect, description}) {
        for (let i = 0; i < this.wordLength; i++) {
            this.attempts[this.currentAttempt.curRow * this.wordLength + i] = letters[i]
        }
        this.currentAttempt.curRow += 1
        this.currentAttempt.curCol = 0
        this.isEnd = isCorrect
        this.description = description
        this.isCanSendAttempt = false
        if (this.isEnd) {
            this.countCorrectAnswersBefore += 1
        }
        this.setKeyboardState(letters)
        if (this.isSound && this.sound && this.isEnd) {
            this.sound?.play()
        }
        if (this.currentAttempt.curRow >= 5) {
            this.isEnd = true
        }
    }

    setIsLoading(value) {
        this.isLoading = value
    }

    setIsFormLoading(value) {
        this.isFormLoading = value
    }

    setIsFeedbackJustSent() {
        this.isFeedbackJustSent = true
    }
}

export default new Store()