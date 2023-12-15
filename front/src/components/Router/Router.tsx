import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { BeforeGame } from '../../pages/BeforeGame/BeforeGame'
import { Register } from '../../pages/Register/Register'
import { App } from '../App/App'
import { InGame } from '../../pages/InGame/InGame'
import { WaitingFeedback } from '../../pages/WaitingFeedback/WaitingFeedback'
import { WaitingNextGame } from '../../pages/WaitingNextGame/WaitingNextGame.1'
import { WaitingLottery } from '../../pages/WaitingLottery/WaitingLottery'
import { AfterLottery } from '../../pages/AfterLottery/AfterLottery'
import TestPage from '../../pages/TestPage'

export const Router = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<App />} path="/">
          <Route element={<BeforeGame />} path="/beforeGame" />
          <Route element={<InGame />} path="/inGame" />
          <Route element={<WaitingFeedback />} path="/waitFeedback" />
          <Route element={<WaitingNextGame />} path="/waitNextGame" />
          <Route element={<WaitingLottery />} path="/waitEndLottery" />
          <Route element={<AfterLottery />} path="/afterLottery" />
          <Route element={<Register />} path="/register" />
          <Route element={<TestPage />} path="/testPage" /> // TODO: remove
        </Route>
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </BrowserRouter>
  )
}
