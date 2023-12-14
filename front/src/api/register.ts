import { useMutation } from '@tanstack/react-query'
import { RegisterFields } from '../types/register'
import { baseApiRequest } from './baseApiRequest'
import { NavigateFunction } from 'react-router-dom'

type UseRegisterParams = {
  navigate: NavigateFunction
  setError: React.Dispatch<React.SetStateAction<string | null>>
}

export const useRegister = ({ navigate, setError }: UseRegisterParams) => {
  return useMutation({
    mutationFn: (mutationData: RegisterFields) => {
      return baseApiRequest({
        url: '/auth/register/v2',
        method: 'POST',
        data: mutationData,
      })
    },
    onSuccess: () => {
      navigate('/')
    },
    onError: (data) => {
      console.log(data)

      // setError()
    },
  })
}
