import { KeyboardLetter } from './KeyboardLetter'
import cn from 'classnames'
import { keyboardData } from './keyboardData'
import { FC, useEffect } from 'react'
import { ClickEvent } from '../../types/event'
import { Letter } from '../../types/game'
import { setKeyboardState } from './keyboardStateHandler'

type KeyboardProps = {
  isOpen: boolean
  setIsOpen: React.Dispatch<React.SetStateAction<boolean>>
  onChangeInput: (letter?: string) => void
  onNewAttempt: () => void
  letters: Letter[]
  canAttempt: boolean
  inputWord?: string[]
}

export const Keyboard: FC<KeyboardProps> = ({
  isOpen,
  setIsOpen,
  onChangeInput,
  onNewAttempt,
  letters,
  canAttempt,
}) => {
  const keyboardDataWithState = setKeyboardState(keyboardData, letters)
  const onClickLetter = (e: ClickEvent, key: string) => {
    e.stopPropagation()
    onChangeInput(key)
  }

  const onClickEnter = async (e: ClickEvent) => {
    e.stopPropagation()
    onNewAttempt()
  }

  const onClickDelete = (e: ClickEvent) => {
    e.stopPropagation()
    onChangeInput()
  }

  const closeHandler = () => {
    setIsOpen(false)
  }

  useEffect(() => {
    document.addEventListener('click', closeHandler)

    return () => {
      document.removeEventListener('click', closeHandler)
    }
  }, [])

  return (
    <div className={cn('keyboard', { open: isOpen })}>
      {keyboardDataWithState.map((row) => (
        <div className="keyboard__row" key={row[0].id}>
          {row.map((item) =>
            item.id === 52 ? (
              <KeyboardLetter
                key={item.id}
                id={item.id}
                letter={item.letter}
                onClickLetter={(e) => onClickEnter(e)}
                canAttempt={canAttempt}
              />
            ) : item.id === 51 ? (
              <KeyboardLetter
                key={item.id}
                id={item.id}
                letter={item.letter}
                onClickLetter={(e) => onClickDelete(e)}
              />
            ) : (
              <KeyboardLetter
                key={item.id}
                id={item.id}
                state={item.state}
                letter={item.letter}
                onClickLetter={(e) => onClickLetter(e, item.letter)}
              />
            ),
          )}
        </div>
      ))}
    </div>
  )
}
