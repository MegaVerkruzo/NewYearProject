export const fieldsData = [
  {
    id: 1,
    field: 'surname',
    label: 'Ваша фамилия',
    placeholder: 'Введите фамилию',
    type: 'text',
  },
  {
    id: 2,
    field: 'name',
    label: 'Ваше имя',
    placeholder: 'Введите имя',
    type: 'text',
  },
  {
    id: 3,
    field: 'middleName',
    label: 'Ваше отчество',
    placeholder: 'Введите отчество',
    type: 'text',
  },
  {
    id: 4,
    field: 'phone',
    label: 'Ваш телефон',
    placeholder: '+7 (999) 999-99-99',
    type: 'tel',
  },
  {
    id: 5,
    field: 'email',
    label: 'Ваш личный Email',
    placeholder: 'example@gmail.com',
    type: 'email',
  },
  {
    id: 6,
    field: 'place',
    label: 'Населенный пункт',
    placeholder: 'Населенный пункт',
    type: 'text',
  },
  {
    id: 7,
    field: 'division',
    label: 'Ваш блок или дивизион',
    placeholder: 'Укажите ваш блок или дивизион',
    type: 'text',
  },
] as const

// React.useEffect(() => {
//   store.setRegError(null)
// }, [])
// const navigate = useNavigate()
// const onChangeInput = (field, data) => {
//   store.changeUserData(field, data)
// }
// const onSignUp = async () => {
//   try {
//     store.setRegError('')
//     if (store.userData.password !== store.userData.password_repeat) {
//       store.setRegError('Пароли не совпадают')
//       return
//     }
//     if (
//       !store.userData.name ||
//       !store.userData.surname ||
//       !store.userData.middlename ||
//       !store.userData.email ||
//       !store.userData.phone ||
//       !store.userData.password ||
//       !store.userData.place
//     ) {
//       store.setRegError('Не все поля заполнены')
//       return
//     }
//     if (store.userData.phone.length < 10) {
//       store.setRegError('Телефон неверно введен')
//       return
//     }
//     if (!store.userData.isAgreePolicy) {
//       store.setRegError('Подтвердите согласие с политикой конфиденциальности')
//       return
//     }
//     store.setIsFormLoading(true)
//     const data = await registration(userData)
//     store.setIsFormLoading(false)
//     if (data.token) {
//       localStorage.setItem('token', data.token)
//       navigate('/game')
//     } else {
//       if (data.exception === 'userExists') {
//         store.setRegError('Пользователь уже существует')
//       } else if (data.exception === 'hugeSizeField') {
//         store.setRegError(
//           'Размер данных в поле ввода не должен превышать 255 символов',
//         )
//       }
//     }
//   } catch (e) {
//     console.log(e.message)
//     store.setIsFormLoading(false)
//     store.setRegError('Произошла ошибка сервера')
//   }
// }
