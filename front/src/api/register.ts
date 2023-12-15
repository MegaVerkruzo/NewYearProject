import { useMutation, useQueryClient } from '@tanstack/react-query'
import { RegisterFields } from '../types/register'
import { baseApiRequest } from './baseApiRequest'

export const useRegister = () => {
  const client = useQueryClient()
  return useMutation({
    mutationFn: (mutationData: RegisterFields) => {
      return baseApiRequest({
        url: '/auth/register/v2',
        method: 'POST',
        data: mutationData,
      })
    },
    retry: false,
    onSuccess: () => {
      client.invalidateQueries({ queryKey: ['getState'] })
    },
  })
}
