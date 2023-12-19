import React, { FC, useCallback, useEffect, useState } from 'react'
import cn from 'classnames'
import { Letter } from '../../types/game'
import { useNewAttempt } from '../../api/newAttempt'
import { GameLetter } from './GameLetter'
import { Keyboard } from '../Keyboard/Keyboard'
import { createPortal } from 'react-dom'
import { AxiosError } from 'axios'
import { ApiError, ApiErrorString } from '../../types/error'
import Spinner from '../../assets/svgs/Spinner'
import { russianLetters } from '../../config/gameData'

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
  const freeCellsCount = !isEnd
    ? (5 - currentLine - 1) * wordLength
    : 5 * wordLength - letters.length

  const freeCells: Letter[] = new Array(freeCellsCount).fill({
    letter: '',
    state: '',
  })

  const [error, setError] = useState<string | null>(null)
  const [inputWord, setInputWord] = useState<string[]>(
    new Array(wordLength).fill(''),
  )

  const [isKeyboardOpen, setIsKeyboardOpen] = useState(false)
  const [canAttempt, setCanAttempt] = useState(false)
  const clearField = () => setInputWord(new Array(wordLength).fill(''))

  const {
    mutate: sendNewAttempt,
    error: mutateError,
    isError,
    isPending,
  } = useNewAttempt({ clearField })

  const onNewAttempt = useCallback(() => {
    if (inputWord.some((item) => item === '')) return

    setError(null)
    const transformedWord = inputWord.join('').toLocaleLowerCase()
    sendNewAttempt({ word: transformedWord })
  }, [inputWord, sendNewAttempt])

  const keyboardHandler = useCallback(
    (e: KeyboardEvent) => {
      if (!isEnd) {
        if (e.key === 'Backspace') {
          e.preventDefault()
          onChangeInput()
        } else if (e.key === 'Enter') {
          e.preventDefault()
          onNewAttempt()
        } else if (e.key === ' ') {
          e.preventDefault()
        } else if (russianLetters.includes(e.key)) {
          onChangeInput(e.key)
        }
      }
    },
    [isEnd, onNewAttempt],
  )

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

  useEffect(() => {
    if (isError && mutateError instanceof AxiosError) {
      const err = mutateError as AxiosError<ApiError>
      if (err.response?.data.exception === ApiErrorString.NoWordInDictionary) {
        return setError('Некорректное слово')
      }

      if (err.response?.status === 500) {
        return setError('Ошибка сервера')
      }
      setError('Некорректный запрос')
    }
  }, [isError, mutateError])

  const onKeyboardOpen = (e: React.MouseEvent<HTMLDivElement>) => {
    if (isEnd) return
    setIsKeyboardOpen(true)
    e.stopPropagation()
  }

  useEffect(() => {
    document.addEventListener('keydown', keyboardHandler)
    return () => {
      document.removeEventListener('keydown', keyboardHandler)
    }
  }, [keyboardHandler])

  return (
    <div className={cn('game', { open: isKeyboardOpen })}>
      <div className="game-field" id="game-field">
        <div
          className={cn('game-field__wrapper', {
            'game-field__wrapper--long': wordLength >= 8,
          })}
          style={{ gridTemplateColumns: `repeat(${wordLength}, 1fr)` }}
          onClick={onKeyboardOpen}
        >
          {letters.map((item, index) => (
            <GameLetter key={index} letter={item.letter} state={item.state} />
          ))}
          {!isEnd &&
            inputWord.map((item, index) => (
              <GameLetter key={index} letter={item} />
            ))}
          {freeCells.map((item, index) => (
            <GameLetter key={index} letter={item.letter} state={item.state} />
          ))}
        </div>
        {error && <div className="error">{error}</div>}
        {isPending && (
          <div className="form_loading">
            <Spinner />
          </div>
        )}
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
