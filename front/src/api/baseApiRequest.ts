import axios from 'axios'
import { API_URL } from '../config/config'
import { getTgParams } from '../utils/gettgParams'

interface BaseApiRequestOptions {
  url: string
  method?: 'GET' | 'POST' | 'PUT' | 'DELETE' | 'PATCH'
  data?: object
}

export interface ApiError {
  exception: string
}

export const baseApiRequest = async <T>({
  url,
  method,
  data,
}: BaseApiRequestOptions): Promise<T | ApiError> => {
  const signature = getTgParams()

  const headers: Record<string, string> = {
    Authorization: signature,
  }

  const apiUrl = `${API_URL}${url}`
  try {
    const response = await axios({
      method,
      url: apiUrl,
      data,
      headers,
    })

    return response.data
  } catch (error) {
    throw new Error(`Request failed: ${error}`)
  }
}