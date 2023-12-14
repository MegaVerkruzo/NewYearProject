export enum ApiErrorString {
  NotRegistered = 'notRegistered',
}

export type ApiError = {
  exception: ApiErrorString
}
