export const getTimeDiff = (timeDiff: string | null) => {
  if (!timeDiff) return null

  const finishTime = Date.parse(timeDiff)
  const nowTime = Date.now()
  const diffInMs = finishTime - nowTime

  if (diffInMs < 0) return null

  const days = Math.floor(diffInMs / (1000 * 60 * 60 * 24))
  const hours = Math.floor(diffInMs / (1000 * 60 * 60)) % 24
  const minutes = Math.floor(diffInMs / (1000 * 60)) % 60
  return { days, hours, minutes }
}
