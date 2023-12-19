import { Letter } from './game'

export type GameState =
  | 'beforeGame'
  | 'inGame'
  | 'waitFeedback'
  | 'waitNextGame'
  | 'waitEndLottery'
  | 'afterLottery'

export type Gifts = {
  activePrizes: string
  nonActivePrizes: string
  activeGifts: 3
}

export type BeforeGameState = {
  gameState: 'beforeGame'
  text: string
}

export type InGameState = {
  gameState: 'inGame'
  text: string
  letters: Letter[]
  wordLength: number
  currentLine: number
} & Gifts

export type WaitFeedbackState = {
  gameState: 'waitFeedback'
  text: string
  letters: Letter[]
  wordLength: number
  feedbackQuestion: string
  afterFeedbackResponse: string
} & Gifts

export type WaitNextGameState = {
  gameState: 'waitNextGame'
  text: string
} & Gifts

export type WaitEndLotteryState = {
  gameState: 'waitEndLottery'
  text: string
  lotteryTime: string | null
  ticketNumber: number
  textWithLink: string
} & Gifts

export type AfterLotteryState = {
  gameState: 'afterLottery'
  text: string
  activeGifts: number
}

export type GetState =
  | BeforeGameState
  | InGameState
  | WaitFeedbackState
  | WaitNextGameState
  | WaitEndLotteryState
  | AfterLotteryState
