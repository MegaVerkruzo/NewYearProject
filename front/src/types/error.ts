export enum ApiErrorString {
  NotRegistered = 'notRegistered',
  BadRequest = 'badRequest',
  NotValid = 'notValid',
  NoWordInDictionary = 'noWordInDictionary',
}

export type ApiError = {
  exception: ApiErrorString
}
