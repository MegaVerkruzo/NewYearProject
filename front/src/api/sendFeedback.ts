import { useMutation, useQueryClient } from '@tanstack/react-query'
import { baseApiRequest } from './baseApiRequest'

type FeedbackData = {
  feedback: string
}

type SendFeedbackParams = {
  setIsFeedbackJustSent: (value: boolean) => void
}

export const useSendFeedback = ({
  setIsFeedbackJustSent,
}: SendFeedbackParams) => {
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
    retry: false,
    onSuccess: () => {
      setIsFeedbackJustSent(true)
      setTimeout(() => {
        client.invalidateQueries({ queryKey: ['getState'] })
      }, 5000)
    },
    onError: () => {},
  })
}
