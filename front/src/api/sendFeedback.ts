import { useMutation, useQueryClient } from '@tanstack/react-query'
import { baseApiRequest } from './baseApiRequest'

type FeedbackData = {
  feedback: string
}

export const useSendFeedback = () => {
  const client = useQueryClient()
  return useMutation({
    mutationKey: ['feedback'],
    mutationFn: (mutationData: FeedbackData) => {
      return baseApiRequest({
        method: 'POST',
        url: '/feedback/v2',
        data: mutationData,
      })
    },
    onSuccess: () => {
      client.invalidateQueries({ queryKey: ['getState'] })
    },
    onError: () => {},
  })
}
