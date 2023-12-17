import { Outlet, useNavigate } from 'react-router-dom'
import { useGetState } from '../../api/getState'
import { useEffect } from 'react'
import { AxiosError } from 'axios'
import { ApiError, ApiErrorString } from '../../types/error'
import { IsLoading } from '../IsLoading/IsLoading'

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
      return
    }

    if (data) {
      console.log('Main components data ', data)
      // navigate('/testPage')
      // return
      switch (data.gameState) {
        case 'beforeGame':
          navigate('/beforeGame', { state: data })
          return
        case 'inGame':
          navigate('/inGame', { state: data })
          return
        case 'waitFeedback':
          navigate('/waitFeedback', { state: data })
          return
        case 'waitNextGame':
          navigate('/waitNextGame', { state: data })
          return
        case 'waitEndLottery':
          navigate('/waitEndLottery', { state: data })
          return
        case 'afterLottery':
          navigate('/afterLottery', { state: data })
          return
        default:
          console.log('default')
          return
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
