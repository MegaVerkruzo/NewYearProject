import { FC } from 'react'
import MessageAngle from '../../assets/svgs/MessageAngleIcon'
import { TOKEN } from '../../config/config'

type MessageProps = {
  text: string
}

export const Message: FC<MessageProps> = ({ text }) => {
  console.log('RAW string ', TOKEN)
  const token = new URLSearchParams(TOKEN)
  console.log('Decoded params from string')

  for (const p of token) {
    console.log(p)
  }

  return (
    <div className="message">
      <span dangerouslySetInnerHTML={{ __html: text }}></span>
      <MessageAngle />
    </div>
  )
}
