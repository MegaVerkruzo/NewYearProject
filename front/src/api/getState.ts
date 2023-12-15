import { useQuery } from '@tanstack/react-query'
import { baseApiRequest } from './baseApiRequest'
import { GetState } from '../types/gameState'

export const useGetState = () => {
  return useQuery({
    queryKey: ['getState'],
    retry: false,
    queryFn: () => {
      return baseApiRequest<GetState>({
        url: '/getState/v2',
        method: 'GET',
      })
    },
  })
}
