import { TKeyboardLetter, Letter, LetterState } from '../../types/game'

export const setKeyboardState = (
  keyboardData: TKeyboardLetter[][],
  letters: Letter[],
): TKeyboardLetter[][] => {
  for (let i = 0; i < keyboardData.length; i++) {
    for (let j = 0; j < keyboardData[i].length; j++) {
      if (keyboardData[i][j].id < 50) {
        let state: LetterState = ''
        if (
          letters.find(
            (item) =>
              item.letter === keyboardData[i][j].letter &&
              item.state === 'grey',
          )
        ) {
          state = 'grey'
        }
        if (
          letters.find(
            (item) =>
              item.letter === keyboardData[i][j].letter &&
              item.state === 'yellow',
          )
        ) {
          state = 'yellow'
        }
        if (
          letters.find(
            (item) =>
              item.letter === keyboardData[i][j].letter &&
              item.state === 'green',
          )
        ) {
          state = 'green'
        }
        if (state) {
          keyboardData[i][j].state = state
        }
      }
    }
  }
  return keyboardData
}
