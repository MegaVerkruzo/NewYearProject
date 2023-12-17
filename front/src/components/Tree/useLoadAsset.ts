import { Assets, Cache } from 'pixi.js'
import { useEffect, useState } from 'react'

export const useLoadAsset = <T>(key: string): T | undefined => {
  const [asset, setAsset] = useState<T>()

  useEffect(() => {
    const loadAsset = async () => {
      if (Cache.has(key)) {
        return setAsset(Cache.get(key))
      }

      const loadedAsset = await Assets.load<T>(key)

      setAsset(loadedAsset)
    }

    loadAsset()
  }, [key])

  return asset
}
