import { useMutation, useQueryClient } from '@tanstack/react-query'
import { baseApiRequest } from './baseApiRequest'

type NewAttemptData = {
  word: string
}

export const useNewAttempt = () => {
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
    onSuccess: () => {
      client.invalidateQueries({ queryKey: ['getState'] })
    },
    onError: () => {},
  })
}
