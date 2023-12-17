import { useMutation, useQueryClient } from '@tanstack/react-query'
import { baseApiRequest } from './baseApiRequest'
import { AxiosError } from 'axios'
import { ApiError, ApiErrorString } from '../types/error'

type NewAttemptData = {
  word: string
}

type NewAttemptParams = {
  clearField: () => void
}

export const useNewAttempt = ({ clearField }: NewAttemptParams) => {
  const client = useQueryClient()
  return useMutation({
    mutationKey: ['newAttepmt'],
    mutationFn: (mutationData: NewAttemptData) => {
      return baseApiRequest({
        url: '/game/new_attempt/v2',
        method: 'POST',
        data: mutationData,
      })
    },
    retry: false,
    onSuccess: () => {
      client.invalidateQueries({ queryKey: ['getState'] })
      clearField()
    },
    onError: (error: AxiosError) => {
      const err = error as AxiosError<ApiError>
      if (err.response?.data.exception === ApiErrorString.OldState) {
        client.invalidateQueries({ queryKey: ['getState'] })
      }
    },
  })
}
