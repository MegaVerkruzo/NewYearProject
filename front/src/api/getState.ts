import { useQuery } from '@tanstack/react-query'
import { baseApiRequest } from './baseApiRequest'

export const useGetState = () => {
  return useQuery({
    queryKey: ['getState'],
    retry: false,
    queryFn: () => {
      return baseApiRequest({
        url: '/getState/v2',
        method: 'GET',
      })
    },
  })
}
