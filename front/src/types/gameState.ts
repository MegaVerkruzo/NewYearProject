export type GameState =
  | 'beforeGame'
  | 'inGame'
  | 'waitFeedback'
  | 'waitNextGame'
  | 'waitEndLottery'
  | 'afterLottery'

export type BeforeGameState = {
  getState: 'beforeGame'
  text: string
}
