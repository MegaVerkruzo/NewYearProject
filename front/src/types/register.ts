export type Fields = {
  name: string
  surname: string
  middleName: string
  email: string
  phone: string
  place: string
  division: string
}

export type FieldsNames = keyof Fields
