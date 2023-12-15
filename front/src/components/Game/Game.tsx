import { FC, useState } from 'react'
import cn from 'classnames'
import { Letter } from '../../types/game'
import { useNewAttempt } from '../../api/newAttempt'
import { GameLetter } from './GameLetter'
import { Keyboard } from '../Keyboard/Keyboard'
import { createPortal } from 'react-dom'

type GameProps = {
  letters: Letter[]
  wordLength: number
  currentLine?: number
  isEnd: boolean
}

export const Game: FC<GameProps> = ({
  letters,
  wordLength,
  currentLine = 0,
  isEnd,
}) => {
  const freeCellsCount = (5 - currentLine - 1) * wordLength
  const freeCells: Letter[] = new Array(freeCellsCount).fill({
    letter: '',
    state: '',
  })

  // TODO: дописать обработку ошибок из игры с сервера
  const [inputWord, setInputWord] = useState<string[]>(
    new Array(wordLength).fill(''),
  )

  const [isKeyboardOpen, setIsKeyboardOpen] = useState(false)
  const [canAttempt, setCanAttempt] = useState(false)

  const { mutate: sendNewAttempt } = useNewAttempt()

  const onNewAttempt = () => {
    if (!canAttempt) return

    const transformedWord = inputWord.join('').toLocaleLowerCase()
    sendNewAttempt({ word: transformedWord })
  }

  const onChangeInput = (letter?: string) => {
    if (letter) {
      setInputWord((prev) => {
        for (let i = 0; i < prev.length; i++) {
          if (prev[i] === '') {
            prev[i] = letter
            break
          }
        }
        setCanAttempt(prev.every((item) => item !== ''))
        return [...prev]
      })
    } else {
      setInputWord((prev) => {
        for (let i = prev.length - 1; i >= 0; i--) {
          if (prev[i] !== '') {
            prev[i] = ''
            break
          }
        }
        setCanAttempt(prev.every((item) => item !== ''))
        return [...prev]
      })
    }
  }

  return (
    <div className={cn('game', { open: isKeyboardOpen })}>
      <div
        className="game-field"
        id="game-field"
        onClick={() => setIsKeyboardOpen(true)}
      >
        <div
          className={'game-field__wrapper'}
          style={{ gridTemplateColumns: `repeat(${wordLength}, 1fr)` }}
        >
          {letters.map((item, index) => (
            <GameLetter key={index} letter={item.letter} state={item.state} />
          ))}
          {inputWord.map((item, index) => (
            <GameLetter key={index} letter={item} />
          ))}
          {freeCells.map((item, index) => (
            <GameLetter key={index} letter={item.letter} state={item.state} />
          ))}
        </div>
        {/* {store.gameError && <div className="error">{store.gameError}</div>}
        {store.isFormLoading && (
          <div className="form_loading">
            <Spinner />
          </div>
        )} */}
        {!isEnd && (
          <div className="game-field__interface">
            <div className="game-field__attempts">
              Попыток осталось: {5 - currentLine}
            </div>
            <button className="main-page__btn" onClick={() => onNewAttempt()}>
              Проверить
            </button>
          </div>
        )}
      </div>
      {!isEnd &&
        createPortal(
          <Keyboard
            isOpen={isKeyboardOpen}
            setIsOpen={setIsKeyboardOpen}
            onChangeInput={onChangeInput}
            onNewAttempt={onNewAttempt}
            canAttempt={canAttempt}
            letters={letters}
          />,
          document.body,
        )}
    </div>
  )
}
