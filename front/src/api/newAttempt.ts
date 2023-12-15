import { useMutation, useQueryClient } from '@tanstack/react-query'
import { baseApiRequest } from './baseApiRequest'

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
  })
}
