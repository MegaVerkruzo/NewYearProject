import {makeAutoObservable} from "mobx";

class Store {
    user_data = {
        name: '',
        surname: '',
        middlename: '',
        phone: '',
        email: '',
        place: '',
        password: '',
        isAgreePolicy: false
    }

    constructor() {
        makeAutoObservable(this)
    }

    changeUserData(field, data) {
        this.user_data[field] = data
    }
}

export default new Store()