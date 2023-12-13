import { useMutation } from '@tanstack/react-query'
import { RegisterFields } from '../types/register'
import { baseApiRequest } from './baseApiRequest'

export const useRegister = () => {
  return useMutation({
    mutationFn: (mutationData: RegisterFields) => {
      return baseApiRequest({
        url: '/auth/register/v2',
        method: 'POST',
        data: mutationData,
      })
    },
  })
}
