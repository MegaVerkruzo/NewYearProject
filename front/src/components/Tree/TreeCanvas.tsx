import { FC, useEffect, useRef, useState } from 'react'
import { TreeEntity } from './TreeEntity'

type TreeProps = {
  activeGifts: number
  activePrizes?: string
  nonActivePrizes?: string
}

export const TreeCanvas: FC<TreeProps> = ({
  activeGifts,
  activePrizes,
  nonActivePrizes,
}) => {
  const containerRef = useRef<HTMLDivElement>(null)
  const [app, setApp] = useState<TreeEntity | null>(null)

  useEffect(() => {
    const container = containerRef.current
    if (!container) return
    const canvas = document.createElement('canvas')
    container.appendChild(canvas)

    // const app = new TreeEntity({

    // })

    // setApp()
  }, [])

  return <div className="canvas-wrapper" ref={containerRef}></div>
}
