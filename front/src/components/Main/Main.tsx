import { Outlet, useNavigate } from 'react-router-dom'
import { useGetState } from '../../api/getState'
import { useEffect } from 'react'
import { IsLoading } from '../IsLoading/isLoading'
import { AxiosError } from 'axios'
import { ApiError, ApiErrorString } from '../../types/error'

export const Main = () => {
  const navigate = useNavigate()
  const { data, isLoading, isError, error } = useGetState()
  console.log(error)

  useEffect(() => {
    if (isError && error instanceof AxiosError) {
      const err = error as AxiosError<ApiError>
      if (err.response?.data.exception === ApiErrorString.NotRegistered) {
        navigate('/register')
      }
    }
  }, [data, isError, isLoading])

  return (
    <main className="main">
      {isLoading ? (
        <IsLoading />
      ) : (
        <div className="main__content">
          <div className="main-page">
            <div className="container">
              <Outlet />
            </div>
          </div>
        </div>
      )}
    </main>
  )
}
