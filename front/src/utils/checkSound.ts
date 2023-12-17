export const checkSound = () => {
  const sound = localStorage.getItem('isSound')
  return sound === 'true' ? true : false
}
